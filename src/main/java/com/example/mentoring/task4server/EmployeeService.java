package com.example.mentoring.task4server;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(EmployeeService.class);

    private List<Employee> employees;
    private List<EmployeeSalary> employeesSalaries;
    private Gson gson;

    public EmployeeService() {
        this.gson = new Gson();
        try {
            this.employees = loadEmployees();
            this.employeesSalaries = loadEmployeesSalaries();
        }
        catch (URISyntaxException | IOException e) {
            logger.error(e);
        }
    }

    private List<Employee> loadEmployees() throws URISyntaxException, IOException {
        String employeesJson = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("employees.json").toURI())));
        Employee[] employeesFromJson = gson.fromJson(employeesJson, Employee[].class);
        return Arrays.stream(employeesFromJson).collect(toList());
    }

    private List<EmployeeSalary> loadEmployeesSalaries() throws URISyntaxException, IOException {
        String employeesSalariesJson = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("employees-salary.json").toURI())));
        EmployeeSalary[] employeesSalariesFromJson = gson.fromJson(employeesSalariesJson, EmployeeSalary[].class);
        return Arrays.stream(employeesSalariesFromJson).collect(toList());
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public int getEmployeeSalary(String employeeName) {
        return employeesSalaries.stream().filter(employeeSalary -> employeeName.equals(employeeSalary.getName())).findFirst().map(EmployeeSalary::getSalary).get();
    }
}
