package com.rude.kboot.repository.coustom

import com.rude.kboot.dto.EmployeeDto
import com.rude.kboot.entity.Employee

interface EmpRepositoryCustom {
    fun selectAllEmployee(): List<EmployeeDto>
}