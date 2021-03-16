package com.rude.kboot.repository.coustom

import com.rude.kboot.entity.seq.SeqDetail

interface SeqDetailRepositoryCustom {
    fun findBySeqInfo(seqKey: String, seqId: String): SeqDetail?
}
