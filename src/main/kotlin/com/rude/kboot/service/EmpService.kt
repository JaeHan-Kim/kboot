package com.rude.kboot.service

import com.rude.kboot.dto.EmployeeDto
import com.rude.kboot.entity.Employee
import com.rude.kboot.repository.EmpRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
@Transactional(readOnly = true)
class EmpService(val empRepository: EmpRepository) {

    fun selectAllEmployees(): List<EmployeeDto> {
        return empRepository.selectAllEmployee()
    }

    @Transactional
    fun saveEmployee(employeeDto: EmployeeDto): EmployeeDto {

        val save = this.empRepository.save(Employee(employeeDto.name))

        if (employeeDto.name == "kjh") throw RuntimeException()
        return EmployeeDto(save.id, employeeDto.name)
    }

    @Transactional
    fun updateEmployee(employeeDto: EmployeeDto) {

        var empInfo = this.empRepository.findByIdOrNull(employeeDto.id ?: throw RuntimeException()) ?: return
        empInfo.name = employeeDto.name
    }
}
