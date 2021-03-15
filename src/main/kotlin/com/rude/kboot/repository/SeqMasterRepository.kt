package com.rude.kboot.repository

import com.rude.kboot.entity.seq.SeqMaster
import org.springframework.data.jpa.repository.JpaRepository

interface SeqMasterRepository : JpaRepository<SeqMaster, String>
