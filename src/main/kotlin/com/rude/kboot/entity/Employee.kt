package com.rude.kboot.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "EMPLOYEE")
class Employee(@Column(name = "EMP_NAME", nullable = false) var name: String) : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_NO")
    var id: Long? = null

    fun changEmpName(empName: String) {
        this.name = empName
    }

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHAshCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHAshCodeProperties)

    companion object {
        private val equalsAndHAshCodeProperties = arrayOf(Employee::id)
        private val toStringProperties = arrayOf(
            Employee::id,
            Employee::name
        )
    }
}
