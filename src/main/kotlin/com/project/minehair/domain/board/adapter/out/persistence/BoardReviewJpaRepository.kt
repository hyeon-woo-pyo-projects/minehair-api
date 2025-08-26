package com.project.minehair.domain.board.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardReviewJpaRepository : JpaRepository<BoardReviewJpaEntity, Long> {

    fun findAllByStatusOrderByCreatedAtDesc(status: Status, pageable: Pageable): Page<BoardReviewJpaEntity>

    fun findByIdAndStatus(id: Long, status: Status): BoardReviewJpaEntity
}