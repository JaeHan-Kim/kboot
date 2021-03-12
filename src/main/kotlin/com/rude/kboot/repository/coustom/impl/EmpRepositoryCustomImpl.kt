package com.rude.kboot.repository.coustom.impl

import com.rude.kboot.dto.EmployeeDto
import com.rude.kboot.entity.Employee
import com.rude.kboot.entity.QEmployee
import com.rude.kboot.repository.coustom.EmpRepositoryCustom
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import java.util.stream.Collectors

class EmpRepositoryCustomImpl(): QuerydslRepositorySupport(Employee::class.java), EmpRepositoryCustom {
    override fun selectAllEmployee(): List<EmployeeDto> {

        val empList : List<Employee> = from(QEmployee.employee).fetch();
        return empList.stream().map { emp -> EmployeeDto(emp.id, emp.name) }.collect(Collectors.toList())
    }
}