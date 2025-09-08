package com.project.minehair.domain.coupon.application.service

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponIssueResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponIssueRequest
import com.project.minehair.domain.coupon.application.port.`in`.CouponIssueUseCase
import com.project.minehair.domain.coupon.application.port.out.CouponIssuePersistencePort
import com.project.minehair.domain.coupon.application.port.out.CouponPersistencePort
import com.project.minehair.domain.coupon.domain.CouponIssue
import com.project.minehair.domain.coupon.domain.CouponIssueMapper
import com.project.minehair.domain.coupon.domain.CouponMapper
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import com.project.minehair.global.filter.context.JwtTokenContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Transactional(readOnly = true)
@Service
class CouponIssueService(
    private val couponPersistencePort: CouponPersistencePort,
    private val couponMapper: CouponMapper,
    private val couponIssuePersistencePort: CouponIssuePersistencePort,
    private val couponIssueMapper: CouponIssueMapper,
) : CouponIssueUseCase {

    @Transactional
    override fun issueCoupon(request: CreateCouponIssueRequest): CouponIssueResponse {

        val userId = JwtTokenContext.getId()
        // 발급 밭기 전 이미 발급 받았는지 체크
        this.checkCouponAlreadyIssued(request.couponId, userId)

        // 쿠폰 유효성 체크
        this.checkCouponValid(request.couponId)

        val domainForCreate = CouponIssue.of(
            request.couponId,
            userId,
            LocalDate.now(),
            false
        )
        return couponIssueMapper.toResponse(couponIssuePersistencePort.save(domainForCreate))
    }

    override fun getUserIssuedCoupons(): List<CouponIssueResponse> {
        val userId = JwtTokenContext.getId()
        val userIssuedCouponList = couponIssuePersistencePort.findAllByUserIdActiveStatus(userId)
        val couponListMap = couponPersistencePort.findAllByIdIn(
            userIssuedCouponList.map { it.couponId }.toSet()
        ).filterNot { it.isExpired() }
        val couponResponseListMap = couponMapper.toResponseList(couponListMap).associateBy { it.id }

        // 쿠폰 정보 매핑, 매핑이 없으면 넘어가기
        val mappedIssueCouponList =  userIssuedCouponList.map { couponIssue ->
            if (couponResponseListMap[couponIssue.couponId] != null) {
                couponIssue.mapCoupon(couponResponseListMap[couponIssue.couponId]!!)
            } else {
                null
            }
        }.filterNotNull()

        return couponIssueMapper.toResponseList(mappedIssueCouponList)
    }

    @Transactional
    override fun useCoupon(id: Long): CouponIssueResponse {
        val couponIssue = couponIssuePersistencePort.findById(id)
            ?: throw BusinessException(ErrorCode.NOT_FOUND)
        val domainForUpdate = couponIssue.use()
        return couponIssueMapper.toResponse(couponIssuePersistencePort.save(domainForUpdate))
    }

    // [private method] ==========
    fun checkCouponAlreadyIssued(couponId: Long, userId: Long) {
        couponIssuePersistencePort.findByCouponIdAndUserId(couponId, userId)?.let {
            throw BusinessException(ErrorCode.FOUND, "이미 발급받은 쿠폰입니다.")
        }
    }

    fun checkCouponValid(couponId: Long) {
        val coupon = couponPersistencePort.findById(couponId)
        if (coupon.isExpired()) throw BusinessException(ErrorCode.CONFLICT, "만료된 쿠폰입니다.")
    }


}