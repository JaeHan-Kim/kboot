package com.rude.kboot.web.controller

import com.rude.kboot.dto.EmployeeDto
import com.rude.kboot.service.EmpService
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
    val empService: EmpService
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/emps")
    fun empList(): List<EmployeeDto> {
        return empService.selectAllEmployees()
    }

    @PostMapping("/emps")
    fun emp(@RequestBody employeeDto: EmployeeDto): ResponseEntity<Any> {

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
