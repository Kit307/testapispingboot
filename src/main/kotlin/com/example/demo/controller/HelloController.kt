package com.example.demo.controller

import com.example.demo.model.Department
import com.example.demo.model.Employee
import com.example.demo.repository.DepartmentRepository
import com.example.demo.repository.EmployeeRepository
import com.example.demo.service.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import java.util.*


@RestController
@CrossOrigin("http://localhost:3000", "http://172.16.0.79:3000")
class HelloController {

    @Autowired
    lateinit var helloService: HelloService



    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @GetMapping("emp/{empNo}")
    fun getEmpByEmpNo(@PathVariable ("empNo") empNo:String): Employee{
        return  helloService.getEmployeeByEmpNo(empNo)
    }
    @GetMapping("emp/{empNo}/{mgr}")
    fun getEmpByEmpNo(@PathVariable ("empNo") empNo:String, @PathVariable("mgr") mgr:String): Employee{
        return  helloService.getEmployeeByEmpNoAndMgr(empNo,mgr)
    }
    @GetMapping("grade/{score}")
    fun hello(
        @PathVariable("score") score:String,
    ):String{
        return helloService.calGrad(score)
    }

    @GetMapping("cal")
    fun cal(
        @RequestParam num:String,
    ):String{
        return helloService.calNumber(num)
    }

    @GetMapping("getAllEmp")
    fun getAllEmp (
    ):MutableList<Employee>{
        return helloService.getAllEmp()
    }

    @GetMapping("save")
    fun saveEmployee(
    ){
        helloService.saveEmployee()
    }

    @GetMapping("findByAllColumns/empno/{empno}")
    fun findByAllColumns(
        @PathVariable empno:String
    ):MutableList<Employee>{
        return helloService.findByAllColumByEmpNO(empno)
    }

    @GetMapping("findByAllColumns/mgr/{mgr}")
    fun findByAllColumnsByMgr(
        @PathVariable mgr:String
    ):MutableList<Employee>{
        return helloService.findByAllColumByMgr(mgr)
    }

    @GetMapping("findByAllColumns/ename/{ename}")
    fun findByAllColumnsByEname(
        @PathVariable ename:String
    ):MutableList<Employee>{
        return helloService.findByAllColumByEname(ename)
    }

    @GetMapping("findByAllColumns/job/{job}")
    fun findByAllColumnsByJob(
        @PathVariable job:String
    ):MutableList<Employee>{
        return helloService.findByAllColumByJob(job)
    }

    @GetMapping("findByAllColumns/hiredate/{hiredate}")
    fun findByAllColumnsByHiredate(
        @PathVariable hiredate:String
    ):MutableList<Employee>{
        return helloService.findByAllColumByHiredate(hiredate)
    }

    @GetMapping("findByAllColumns/sal/{sal}")
    fun findByAllColumnsBySal(
        @PathVariable sal:Double
    ):MutableList<Employee>{
        return helloService.findByAllColumBySal(sal)
    }

    @GetMapping("findByAllColumns/commission_pct/{commission_pct}")
    fun findByAllColumnsByCommission_pct(
        @PathVariable commission_pct:Double
    ):MutableList<Employee>{
        return helloService.findByAllColumByCommission_pct(commission_pct)
    }

    @GetMapping("findByAllColumns/deptno/{deptno}")
    fun findByAllColumnsByDeptno(
        @PathVariable deptno:Int
    ):MutableList<Employee>{
        return helloService.findByAllColumByDeptno(deptno)
    }

    @GetMapping("getDepartment")
    fun getDepartment(
    ):MutableList<Department>{
        return helloService.getDepartment()
    }

    @GetMapping("getTime")
    fun getTime(
    ):Date{
        return Date()
    }





    @PostMapping("savePost")
    fun saveEmployee2(
        @RequestBody employee:Employee
    ){
        employee.hiredate = Timestamp(Date().time)
        helloService.saveEmployee2(employee)
    }

    @PostMapping("delByEmpNo/")
    fun deleteEmployee(
        @RequestBody empNo: Map<String, String> = mapOf(Pair("empNo",""))

    ):String{
        helloService.deleteEmployeeByEmpNo(empNo["empNo"]?:"")
        return "Delete Success"
    }

    @GetMapping("empMgr/{mgr}")
    fun getMemberByMgrId(
        @PathVariable ("mgr") mgr:String
    ): Map<String,Any> {
        var dataEmp = helloService.getMemberByMgrId(mgr)
        var sumSalary = 0.0
        for (emp in dataEmp){
            sumSalary += emp.sal?:0.0
            println(emp.sal)
        }
        var dataEmployee = mapOf(Pair("Sum",sumSalary), Pair("Data",helloService.getMemberByMgrId(mgr)))
        return dataEmployee
    }

    @PostMapping("upDateDataFormEmpNo")
    fun upDateDataFormEmpNo(
        @RequestBody emp: Map<String, Any>
    ):String{
        var dataEmp = helloService.getEmployeeByEmpNo(emp["empNo"] as String)
        dataEmp.ename = (emp["ename"]?:dataEmp.ename).toString()
        dataEmp.job = (emp["job"]?:dataEmp.job).toString()
        dataEmp.mgr = (emp["mgr"]?:dataEmp.mgr).toString()
        dataEmp.hiredate = Timestamp(Date().time)
        dataEmp.sal = (emp["sal"]?:dataEmp.sal).toString().toDouble()
        dataEmp.commission_pct = (emp["commission_pct"]?:dataEmp.commission_pct).toString().toDouble()
        dataEmp.deptno = (emp["deptno"]?:dataEmp.deptno) as Int
        helloService.saveEmployee2(dataEmp)
        return "Update Success"

        //generate body for postman


    }



}
