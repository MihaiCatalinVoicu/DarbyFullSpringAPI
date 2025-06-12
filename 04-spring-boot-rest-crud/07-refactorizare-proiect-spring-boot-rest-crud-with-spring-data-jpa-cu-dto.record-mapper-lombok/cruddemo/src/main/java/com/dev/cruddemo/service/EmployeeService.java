package com.dev.cruddemo.service;

import com.dev.cruddemo.domain.model.Employee;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
public interface EmployeeService {

    @Transactional(readOnly = true)
    List<Employee> findAll();

    @Transactional(readOnly = true)
    Optional<Employee> findById(Long id);

    @Transactional
    Employee save(Employee employee);

    @Transactional
    void deleteEmployeeById(Long id);
}
