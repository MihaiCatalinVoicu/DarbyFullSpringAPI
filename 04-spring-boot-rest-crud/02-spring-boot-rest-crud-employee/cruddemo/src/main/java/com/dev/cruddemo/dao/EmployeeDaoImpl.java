package com.dev.cruddemo.dao;

import com.dev.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private EntityManager manager;

    @Autowired
    public EmployeeDaoImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> query = manager.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {

        Employee employee = manager.find(Employee.class, id);

        return employee;
    }

    @Override
    public Employee save(Employee employee) {

        Employee dbEmployee = manager.merge(employee);

        return dbEmployee;
    }

    @Override
    public void deleteEmployeeById(int id) {

        Employee employee = manager.find(Employee.class, id);
        manager.remove(employee);
    }
}
