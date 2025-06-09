package com.dev.cruddemo.service;

import com.dev.cruddemo.entity.Employee;

import java.util.List;
public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteEmployeeById(int id);
}
