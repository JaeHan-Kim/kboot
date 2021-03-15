package com.rude.kboot.repository

import com.rude.kboot.entity.Employee
import com.rude.kboot.repository.coustom.EmpRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface EmpRepository : JpaRepository<Employee, Long>, EmpRepositoryCustom
