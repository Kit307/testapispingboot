package com.example.demo.repository

import com.example.demo.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface EmployeeRepository : JpaRepository<Employee, String> {
    fun findByEmpNoEquals(@Param("empNo") empNo: String): Employee

    fun findAllByMgr(@Param("mgr") mgr: String): MutableList<Employee>

    @Query("FROM Employee WHERE empNo = :empNo and mgr = :mgr")
    fun findByEmpNo(@Param("empNo") empNo: String, @Param("mgr") mgr: String): Employee

    @Transactional
    fun deleteByEmpNo(empNo: String)

    // Use the `save` method instead of `saveAll`
    override fun <S : Employee?> save(entity: S): S
}


