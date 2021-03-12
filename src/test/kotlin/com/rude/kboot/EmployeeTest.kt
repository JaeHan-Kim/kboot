package com.rude.kboot

import com.rude.kboot.entity.Employee
import com.rude.kboot.repository.EmpRepository
import com.rude.kboot.service.EmpService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class EmployeeTest {

    @Autowired
    private lateinit var empService: EmpService

    @Autowired
    private lateinit var empRepository: EmpRepository

    @BeforeEach
    fun setup(){
        val jordan = Employee("Jordan")
        empRepository.save(jordan)

        val police1 = Employee("경찰관#1")
        empRepository.save(police1)

        val firefighter1 = Employee("소방관#1")
        empRepository.save(firefighter1)
    }
    @Test
    @DisplayName("QueryDsl 설정 제대로 잡혔나 확인하는 테스트 코드 #1")
    fun testQuerydslConfigurationRepository(){
        val employees = empService.selectAllEmployees()
        assertThat(employees.size).isEqualTo(3)
    }
}