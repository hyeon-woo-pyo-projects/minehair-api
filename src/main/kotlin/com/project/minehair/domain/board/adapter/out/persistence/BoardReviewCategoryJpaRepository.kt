package com.project.minehair.domain.board.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardReviewCategoryJpaRepository : JpaRepository<BoardReviewCategoryJpaEntity, Long> {
    fun findByStatus(status: Status): List<BoardReviewCategoryJpaEntity>
    fun findByIdAndStatus(id: Long, status: Status): BoardReviewCategoryJpaEntity
    fun findTopByOrderByOrderNoDesc(): BoardReviewCategoryJpaEntity?
}