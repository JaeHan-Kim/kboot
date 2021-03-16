package com.rude.kboot.service

import com.rude.kboot.entity.seq.SeqDetail
import com.rude.kboot.repository.SeqDetailRepository
import com.rude.kboot.repository.SeqMasterRepository
import org.springframework.stereotype.Service

@Service
class SequenceService(
    val seqMasterRepository: SeqMasterRepository,
    val seqDetailRepository: SeqDetailRepository
) {

    fun getSequence(sequenceKey: String, sequenceId: String): SeqDetail? {
        return seqDetailRepository.findBySeqInfo(sequenceKey, sequenceId)
    }
}
