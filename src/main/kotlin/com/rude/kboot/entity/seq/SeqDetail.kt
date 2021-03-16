package com.rude.kboot.entity.seq

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.Table
import javax.persistence.ManyToOne

@Entity
@Table(name = "SEQ_DETAIL")
@IdClass(SeqDetailId::class)
class SeqDetail(
    @Id @Column(name = "SEQ_ID")
    var seqId: String,
    @Id @Column(name = "SEQ_KEY")
    var seqKey: String,
    @Column
    var num: Int,
    @ManyToOne(targetEntity = SeqMaster::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SEQ_ID", insertable = false, updatable = false)
    var seqMaster: SeqMaster?
) : Serializable {

    fun increaseNum() {
        this.num++
    }

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHAshCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHAshCodeProperties)

    companion object {
        private val equalsAndHAshCodeProperties = arrayOf(SeqDetail::seqId, SeqDetail::seqKey)
        private val toStringProperties = arrayOf(
            SeqDetail::seqId,
            SeqDetail::seqKey,
            SeqDetail::num
        )
    }
}
