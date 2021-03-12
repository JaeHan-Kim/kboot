package com.rude.kboot.entity

import javax.persistence.*

@Entity
@Table(name = "EMPLOYEE")
class Employee (name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_NO")
    var id: Long? = null

    @Column(name = "EMP_NAME", nullable = false)
    var name: String = name
}