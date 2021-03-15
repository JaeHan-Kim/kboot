package com.rude.kboot.service

import com.rude.kboot.dto.EmployeeDto
import com.rude.kboot.entity.Employee
import com.rude.kboot.repository.EmpRepository
import org.springframework.stereotype.Service

@Service
class EmpService(val empRepository: EmpRepository) {

    fun selectAllEmployees(): List<EmployeeDto> {

        return empRepository.selectAllEmployee()
    }

    fun saveEmployee(employeeDto: EmployeeDto): EmployeeDto {

        val save = this.empRepository.save(Employee(employeeDto.name))

        return EmployeeDto(save.id, employeeDto.name)
    }
}
