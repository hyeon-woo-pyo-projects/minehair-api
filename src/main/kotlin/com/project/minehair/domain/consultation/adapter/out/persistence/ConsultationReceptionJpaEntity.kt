package com.project.minehair.domain.consultation.adapter.out.persistence

import com.project.minehair.domain.consultation.domain.ReceptionState
import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "consultation_reception")
class ConsultationReceptionJpaEntity(
    @Column(name = "category_id")
    val categoryId: Long,

    @Column(name = "name")
    val name: String,

    @Column(name = "phone")
    val phone: String,

    @Column(name = "phone_hash")
    val phoneHash: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "reception_state")
    val receptionState: ReceptionState,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long? = 0L,
    override val updatedAt: LocalDateTime? = null

) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt) {
}