package com.example.demo.model


import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "dept")
data class Department(
    @Id
    @Column(name = "deptno", nullable = false )
    var deptno : Int,

    @Column(name = "dname")
    var dname : String?,

    @Column(name = "loc")
    var loc : String?,




)
