package com.example.demo.controller

import com.example.demo.model.Employee
import com.example.demo.service.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import java.util.*


@RestController
class HelloController {

    @Autowired
    lateinit var helloService: HelloService

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

    @GetMapping("hello2")
    fun hello (
    ):MutableList<Employee>{
        return helloService.hello2()
    }

    @GetMapping("save")
    fun saveEmployee(
    ){
        helloService.saveEmployee()
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

    @PostMapping("upDataSalFormEmpNo")
    fun upDataSalFormEmpNo(
        @RequestBody emp: Map<String, Any> = mapOf(Pair("empNo",""),Pair("sal",""))
    ):String{
        println( emp["empNo"].toString())
        println( emp["sal"].toString().toDouble())
        helloService.upDataSalFormEmpNo(emp["empNo"].toString()?:"",emp["sal"].toString().toDouble())
        return "Update Success"
    }
}
