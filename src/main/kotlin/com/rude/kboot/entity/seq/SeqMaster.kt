package com.rude.kboot.entity.seq

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "SEQ_MASTER")
class SeqMaster(
    @Id
    @Column(name = "SEQ_ID", nullable = false)
    var id: String,
    @Column(name = "SEQ_LEN", nullable = false)
    var seqLen: Int,
    @Column(name = "SEQ_NAME", nullable = false)
    var seqName: String
) : Serializable {
    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHAshCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHAshCodeProperties)

    companion object {
        private val equalsAndHAshCodeProperties = arrayOf(SeqMaster::id)
        private val toStringProperties = arrayOf(
            SeqMaster::id,
            SeqMaster::seqLen,
            SeqMaster::seqName
        )
    }
}
