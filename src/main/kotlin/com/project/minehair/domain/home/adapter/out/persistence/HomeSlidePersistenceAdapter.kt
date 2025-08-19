package com.project.minehair.domain.home.adapter.out.persistence

import com.project.minehair.domain.home.application.port.out.HomeSlidePersistencePort
import com.project.minehair.domain.home.domain.HomeSlide
import com.project.minehair.domain.home.domain.HomeSlideMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class HomeSlidePersistenceAdapter(
    private val homeSlideJpaRepository: HomeSlideJpaRepository,
    private val homeSlideMapper: HomeSlideMapper
) : HomeSlidePersistencePort {

    /**
     * 홈 슬라이드 목록 조회
     */
    override fun findAllActiveState(): List<HomeSlide> {
        return homeSlideJpaRepository.findAllByStatus(Status.active)
            .let { homeSlideMapper.toDomainList(it) }
    }

    /**
     * 홈 슬라이드 저장
     */
    override fun save(homeSlide: HomeSlide): HomeSlide {
        return homeSlideJpaRepository.save(homeSlideMapper.toEntity(homeSlide))
            .let { homeSlideMapper.toDomain(it) }
    }

    /**
     * 홈 슬라이드 Id로 조회
     */
    override fun findById(id: Long): HomeSlide? {
        return homeSlideJpaRepository.findById(id)
            .map { homeSlideMapper.toDomain(it) }
            .orElse(null)
    }




}