package com.project.minehair.domain.board.adapter.out.persistence

import com.project.minehair.domain.board.application.port.out.BoardQnaPersistencePort
import com.project.minehair.domain.board.domain.BoardQna
import com.project.minehair.domain.board.domain.BoardQnaMapper
import com.project.minehair.global.enums.Status
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class BoardQnaPersistenceAdapter(
    private val boardQnaJpaRepository: BoardQnaJpaRepository,
    private val boardQnaMapper: BoardQnaMapper
) : BoardQnaPersistencePort {

    override fun findAllPageActiveState(page: Int, size: Int): Page<BoardQna> {
        val pageable = PageRequest.of(page - 1, size) // page는 1부터 시작하므로 -1
        return boardQnaJpaRepository.findAllByStatusOrderByCreatedAtDesc(Status.active, pageable)
            .let { boardQnaMapper.toDomainPage(it) }
    }

    override fun findByIdActiveState(id: Long): BoardQna {
        return boardQnaJpaRepository.findByIdAndStatus(id, Status.active)
            .let { boardQnaMapper.toDomain(it) }
    }

    override fun save(boardQna: BoardQna): BoardQna {
        val entityForCreate = boardQnaMapper.toEntity(boardQna)
        val createdEntity = boardQnaJpaRepository.save(entityForCreate)
        return boardQnaMapper.toDomain(createdEntity)
    }

}