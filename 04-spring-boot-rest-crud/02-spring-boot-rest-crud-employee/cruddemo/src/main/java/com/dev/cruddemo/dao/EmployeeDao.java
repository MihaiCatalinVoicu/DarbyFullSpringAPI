package com.dev.cruddemo.dao;

import com.dev.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteEmployeeById(int id);
}
