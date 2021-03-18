package com.rude.kboot.web.controller

import com.rude.kboot.dto.EmployeeDto
import com.rude.kboot.service.EmpService
import com.rude.kboot.service.SequenceService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
class EmpController(
    val empService: EmpService,
    val sequenceService: SequenceService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/emps")
    fun getEmps(): List<EmployeeDto> {
        return empService.selectAllEmployees()
    }

    @PostMapping("/emps")
    fun saveEmp(@RequestBody employeeDto: EmployeeDto): ResponseEntity<Any> {

        return ResponseEntity
            .ok()
            .body(empService.saveEmployee(employeeDto).id)
    }

    @PatchMapping("/emps/{id}")
    fun updateEmp(@PathVariable id: Long?, @RequestBody employeeDto: EmployeeDto) {
        log.info("$employeeDto / $id")
        employeeDto.id = id

        empService.updateEmployee(employeeDto)
    }
}
