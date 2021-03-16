package com.rude.kboot.repository

import com.rude.kboot.entity.seq.SeqDetail
import com.rude.kboot.entity.seq.SeqDetailId
import com.rude.kboot.repository.coustom.SeqDetailRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface SeqDetailRepository : JpaRepository<SeqDetail, SeqDetailId>, SeqDetailRepositoryCustom
