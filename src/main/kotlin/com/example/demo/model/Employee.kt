package com.example.demo.model

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "emp")
data class Employee(
    @Id
    @Column(name = "empno", nullable = false )
    var empNo : String,
    @Column(name = "ename")
    var ename : String?,
    @Column(name = "job")
    var job : String?,
    @Column(name = "mgr")
    var mgr : String?,
    @Column(name = "hiredate")
    var hiredate : Date?,
    @Column(name = "sal")
    var sal : Double?,
    @Column(name = "commission_pct")
    var commission_pct : Double?,
    @Column(name = "deptno")
    var deptno : Int?,
    )