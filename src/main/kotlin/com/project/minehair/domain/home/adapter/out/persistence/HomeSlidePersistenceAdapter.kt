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
    override fun findAll(): List<HomeSlide> {
        return homeSlideJpaRepository.findAllByStatus(Status.active)
            .let { homeSlideMapper.toDomainList(it) }
    }
}