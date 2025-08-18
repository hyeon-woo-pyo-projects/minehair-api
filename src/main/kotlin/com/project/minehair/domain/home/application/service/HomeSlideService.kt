package com.project.minehair.domain.home.application.service

import com.project.minehair.domain.home.adapter.`in`.web.dto.HomeSlideResponse
import com.project.minehair.domain.home.application.port.`in`.HomeSlideUseCase
import com.project.minehair.domain.home.application.port.out.HomeSlidePersistencePort
import com.project.minehair.domain.home.domain.HomeSlideMapper
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
        homeSlidePersistencePort.findAll().let { homeSlideList ->
            return homeSlideMapper.toResponseList(homeSlideList)
        }
    }
}