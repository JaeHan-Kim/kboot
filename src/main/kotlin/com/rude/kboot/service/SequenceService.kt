package com.rude.kboot.service

import com.rude.kboot.dto.Sequence
import com.rude.kboot.entity.seq.SeqDetail
import com.rude.kboot.repository.SeqDetailRepository
import com.rude.kboot.repository.SeqMasterRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class SequenceService(
    val seqMasterRepository: SeqMasterRepository,
    val seqDetailRepository: SeqDetailRepository
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun getSequence(sequenceId: String, sequenceKey: String): Sequence {
        var seqDetail = seqDetailRepository.findBySeqInfo(sequenceId, sequenceKey)
        val seqMaster = seqDetail?.seqMaster ?: seqMasterRepository.findById(sequenceId)
            .orElseGet { throw RuntimeException() }
        // .orElseGet(throw RuntimeException())
        seqDetail = seqDetail ?: seqDetailRepository.save(SeqDetail(sequenceId, sequenceKey, 0))
        seqDetail.increaseNum()

        return Sequence(seqMaster.id, seqMaster.seqLen, seqDetail.num)
    }
}
