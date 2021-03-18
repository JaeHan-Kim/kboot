package com.rude.kboot.service

import com.rude.kboot.entity.seq.SeqDetailId
import com.rude.kboot.repository.SeqDetailRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Transactional
@SpringBootTest
internal class SequenceServiceTest {

    @Autowired
    lateinit var sequenceService: SequenceService

    @Autowired
    lateinit var seqDetailRepository: SeqDetailRepository

    @Test
    fun `test`() {
        // val data = sequenceService.getSequence("orderNum1", "orderNum1")

        // println("$data")
        // assertThat(data).isNotNull
        // data?.seqMaster?.let { assertThat(it.seqLen).isEqualTo(7) }

        println("------------------")
        val data2 = seqDetailRepository.findById(SeqDetailId("prodCd", "prodCd"))
        println(data2.get().seqMaster)

        /*
        val result = seqDetailRepository.findById(SeqDetailId("prodCd", "prodCd"))
        assertThat(result.get().num).isEqualTo(2)
         */
    }

    @Test
    @DisplayName("쿼리 테스트")
    fun `query test`() {
        val times = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"))
        val data = sequenceService.getSequence("orderNum", times)
        assertThat(data).isNotNull
        println(data.nextVal())
        println(data)
        assertThat("$times${data.nextVal()}").isEqualTo("${times}00001")
        val data2 = sequenceService.getSequence("orderNum", times)
        println(data2)
        assertThat("$times${data2.nextVal()}").isEqualTo("${times}00002")
    }
}
