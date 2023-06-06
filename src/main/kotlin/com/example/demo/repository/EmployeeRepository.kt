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

    @Query("FROM Employee WHERE empNo = :empNo or mgr = :mgr")
    fun findByEmpNo(@Param("empNo") empNo: String, @Param("mgr") mgr: String): Employee

    @Transactional
    fun deleteByEmpNo(empNo: String)



     // columc data epm is empNo, ename, mgr, job, sal, commission_pct, deptno, hiredate , select 1 column is method
    @Query("FROM Employee WHERE empNo = :empNo")
    fun findByAllColumByEmpNO(@Param("empNo") empNo: String): MutableList<Employee>

    @Query("FROM Employee WHERE ename = :ename")
    fun findByAllColumByEname(@Param("ename") ename: String): MutableList<Employee>

    @Query("FROM Employee WHERE mgr = :mgr")
    fun findByAllColumByMgr(@Param("mgr") mgr: String): MutableList<Employee>

    @Query("FROM Employee WHERE job = :job")
    fun findByAllColumByJob(@Param("job") job: String): MutableList<Employee>

    @Query("FROM Employee WHERE sal = :sal")
    fun findByAllColumBySal(@Param("sal") sal: Double): MutableList<Employee>

    @Query("FROM Employee WHERE commission_pct = :commission_pct")
    fun findByAllColumByCommission_pct(@Param("commission_pct") commission_pct: Double): MutableList<Employee>

    @Query("FROM Employee WHERE deptno = :deptno")
    fun findByAllColumByDeptno(@Param("deptno") deptno: Int): MutableList<Employee>

    @Query("FROM Employee WHERE hiredate = :hiredate")
    fun findByAllColumByHiredate(@Param("hiredate") hiredate: String): MutableList<Employee>





}

    // Use the `save` method instead of `saveAll`
//    override fun <S : Employee?> save(entity: S): S



