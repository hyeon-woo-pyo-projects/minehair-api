package com.project.minehair.domain.home.application.service

import com.project.minehair.domain.home.adapter.`in`.web.dto.CreateHomeSlideRequest
import com.project.minehair.domain.home.adapter.`in`.web.dto.HomeSlideResponse
import com.project.minehair.domain.home.application.port.`in`.HomeSlideUseCase
import com.project.minehair.domain.home.application.port.out.HomeSlidePersistencePort
import com.project.minehair.domain.home.domain.HomeSlideMapper
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class HomeSlideService(
    private val homeSlidePersistencePort: HomeSlidePersistencePort,
    private val homeSlideMapper: HomeSlideMapper
) : HomeSlideUseCase {

    /**
     * 홈 슬라이드 리스트 조회
     */
    override fun getHomeSlideList(): List<HomeSlideResponse> {
        homeSlidePersistencePort.findAllActiveState().let { homeSlideList ->
            return homeSlideMapper.toResponseList(homeSlideList)
        }
    }

    /**
     * 홈 슬라이드 생성
     */
    @Transactional
    override fun createHomeSlide(request: CreateHomeSlideRequest): HomeSlideResponse {
        val maxOrderNo = homeSlidePersistencePort.findAllActiveState()
            .maxOfOrNull { it.orderNo } ?: 0

        val orderNoSetRequest = request.setOrderNo(maxOrderNo + 1)

        val homeSlideForCreate = homeSlideMapper.toDomain(orderNoSetRequest)

        val homeSlide = homeSlidePersistencePort.save(homeSlideForCreate)
        return homeSlideMapper.toResponse(homeSlide)
    }

    /**
     * 홈 슬라이드 삭제
     */
    @Transactional
    override fun deleteHomeSlide(id: Long): HomeSlideResponse {
        val homeSlide = homeSlidePersistencePort.findById(id)
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "홈 슬라이드가 존재하지 않습니다. id: $id")

        val homeSlideForDelete = homeSlide.delete()

        val deletedHomeSlide = homeSlidePersistencePort.save(homeSlideForDelete)
        return homeSlideMapper.toResponse(deletedHomeSlide)
    }
}