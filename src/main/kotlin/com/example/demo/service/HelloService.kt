package com.example.demo.service

import com.example.demo.model.Employee
import com.example.demo.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.script.ScriptEngineManager
import javax.transaction.Transactional

@Service
class HelloService {
    fun hello(name: String, lastName: String, code: String): String {
        return " $name $lastName $code"
    }


    @Autowired
    lateinit var employeeRepository: EmployeeRepository
    fun hello2(): MutableList<Employee> {
        return  employeeRepository.findAll()

    }

    fun getEmployeeByEmpNo(empNo: String): Employee {
        return employeeRepository.findByEmpNoEquals(empNo)
    }
    fun getEmployeeByEmpNoAndMgr(empNo: String,mgr:String): Employee {
        return employeeRepository.findByEmpNo(empNo,mgr)
    }

    fun saveEmployee(){
        var newEmployee = Employee(
            empNo = "8080",
            ename = "8080",
            job = "SALE",
            mgr = "566",
            hiredate = Date(),
            sal = 19000.00,
            commission_pct = 15000.00,
            deptno = 40
            )
        employeeRepository.save(newEmployee)
    }
    fun saveEmployee2( emp : Employee){
        employeeRepository.save(emp)
    }

    fun getMemberByMgrId(mgr: String) : MutableList<Employee> {
//        println(employeeRepository.findBymgr( mgr))
        return employeeRepository.findAllByMgr( mgr)
    }
    @Transactional
    fun deleteEmployeeByEmpNo(empNo: String){
        employeeRepository.deleteByEmpNo(empNo)
    }

    fun upDataSalFormEmpNo(empNo: String, sal: Double){
        var emp = employeeRepository.findByEmpNoEquals(empNo.toString())
        emp.sal = sal
        employeeRepository.save(emp)
    }
    fun calGrad(score: String): String {
        try {
            var scoreInt = score.toInt()
            when (scoreInt) {
                in 80..100 -> {
                    return "A"
                }

                in 70..79 -> {
                    return "B"
                }

                in 60..69 -> {
                    return "C"
                }

                in 50..59 -> {
                    return "D"
                }

                in 0..49 -> {
                    return "F"
                }

                else -> {
                    return "Error"
                }
            }
        } catch (e: Exception) {
            return "Error"
        }
    }

    fun calNumber(numInput: String): String {
        var numInput = numInput
        numInput = numInput.replace("x", "*")
        numInput = numInput.replace(",", "")
        numInput = numInput.replace("   ", "+")
        val manager = ScriptEngineManager()
        val engine = manager.getEngineByName("JavaScript")
        val result = engine.eval(numInput)
        return "$numInput = " + String.format("%.2f", result.run { toString().toDouble() })
    }

}