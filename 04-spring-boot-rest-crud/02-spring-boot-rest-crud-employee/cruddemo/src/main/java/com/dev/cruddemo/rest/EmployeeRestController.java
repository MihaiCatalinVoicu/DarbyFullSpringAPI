package com.dev.cruddemo.rest;

import com.dev.cruddemo.dao.EmployeeDao;
import com.dev.cruddemo.dao.EmployeeDaoImpl;
import com.dev.cruddemo.entity.Employee;
import com.dev.cruddemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService service;
    private ObjectMapper mapper;

    @Autowired
    public EmployeeRestController(EmployeeService service,ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id) {

        Employee employee = service.findById(id);

        if (employee == null )
            throw new RuntimeException("Employee with ID: " + id + " not found!");

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        theEmployee.setId(0); //Force to save, instead of update
        Employee dbEmployee = service.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = service.save(theEmployee);
        return dbEmployee;
    }

    @PatchMapping("/employees/{id}")
    public Employee patchEmployee(@PathVariable int id,
                                  @RequestBody Map<String, Object> patchPayload) {

        Employee employee = service.findById(id);
        if (employee == null)
            throw new RuntimeException("Employee id: " + id + " not found!");

        if (patchPayload.containsKey("id"))
            throw new RuntimeException("Employee ID not allowed in request body- " + id);

        Employee pachedEmployee = apply(patchPayload, employee);
        Employee dbEmployee = service.save(pachedEmployee);

        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee employee) {

        //Convert employee object to a JSON object node
        ObjectNode employeeNode = mapper.convertValue(employee, ObjectNode.class);

        //Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = mapper.convertValue(patchPayload, ObjectNode.class);

        //Merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return mapper.convertValue(employeeNode, Employee.class);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {

        service.deleteEmployeeById(id);
    }
}
