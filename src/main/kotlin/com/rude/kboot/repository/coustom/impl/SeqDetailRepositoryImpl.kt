package com.rude.kboot.repository.coustom.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.rude.kboot.entity.seq.QSeqDetail
import com.rude.kboot.entity.seq.QSeqMaster
import com.rude.kboot.entity.seq.SeqDetail
import com.rude.kboot.repository.coustom.SeqDetailRepositoryCustom

class SeqDetailRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : SeqDetailRepositoryCustom {
    override fun findBySeqInfo(seqId: String, seqKey: String): SeqDetail? {
        return queryFactory.selectFrom(QSeqDetail.seqDetail)
            .innerJoin(QSeqDetail.seqDetail.seqMaster, QSeqMaster.seqMaster)
            .where(QSeqDetail.seqDetail.seqKey.eq(seqKey), QSeqMaster.seqMaster.id.eq(seqId))
            .fetchJoin()
            .fetchOne()
    }
}
