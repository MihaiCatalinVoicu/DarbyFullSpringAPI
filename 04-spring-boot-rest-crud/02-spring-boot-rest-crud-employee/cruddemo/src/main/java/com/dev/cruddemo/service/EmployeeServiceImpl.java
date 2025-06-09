package com.dev.cruddemo.service;

import com.dev.cruddemo.dao.EmployeeDao;
import com.dev.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao dao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Employee> findAll() {
        return dao.findAll();
    }

    @Override
    public Employee findById(int id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return dao.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(int id) {
        dao.deleteEmployeeById(id);
    }
}
