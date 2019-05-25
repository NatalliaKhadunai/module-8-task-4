package com.example.mentoring.task4server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @RequestMapping("/employee/{employeeName}/salary")
    public int getEmployeeSalary(@PathVariable String employeeName) {
        return employeeService.getEmployeeSalary(employeeName);
    }
}
