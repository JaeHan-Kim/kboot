package com.rude.kboot.service

import com.rude.kboot.entity.seq.SeqDetailId
import com.rude.kboot.repository.SeqDetailRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
internal class SequenceServiceTest {

    @Autowired
    lateinit var sequenceService: SequenceService

    @Autowired
    lateinit var seqDetailRepository: SeqDetailRepository

    @Test
    @DisplayName("test")
    fun `test`() {
        val data = sequenceService.getSequence("prodCd", "prodCd")

        println("$data")
        assertThat(data).isNotNull
        data?.seqMaster?.let { assertThat(it.seqLen).isEqualTo(7) }

        val result = seqDetailRepository.findById(SeqDetailId("prodCd", "prodCd"))
        assertThat(result.get().num).isEqualTo(2)
    }
}
