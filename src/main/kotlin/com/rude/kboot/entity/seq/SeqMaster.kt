package com.rude.kboot.entity.seq

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
)
