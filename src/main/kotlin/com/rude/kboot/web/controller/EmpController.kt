package com.rude.kboot.web.controller

import com.rude.kboot.dto.EmployeeDto
import com.rude.kboot.service.EmpService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EmpController(
    val empService: EmpService
) {

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
}
