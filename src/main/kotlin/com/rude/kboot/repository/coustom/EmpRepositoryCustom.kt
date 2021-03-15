package com.rude.kboot.repository.coustom

import com.rude.kboot.dto.EmployeeDto

interface EmpRepositoryCustom {
    fun selectAllEmployee(): List<EmployeeDto>
}
