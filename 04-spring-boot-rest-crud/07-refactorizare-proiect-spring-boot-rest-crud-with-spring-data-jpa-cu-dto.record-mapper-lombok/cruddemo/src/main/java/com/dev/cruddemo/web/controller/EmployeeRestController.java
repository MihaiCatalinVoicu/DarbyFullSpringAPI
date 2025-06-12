package com.dev.cruddemo.web.controller;

import com.dev.cruddemo.domain.model.Employee;
import com.dev.cruddemo.service.EmployeeService;
import com.dev.cruddemo.web.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService service;

    @Autowired
    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public List<EmployeeDto> findAll() {
        return service.findAll().stream()
                .map(EmployeeDto.Mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        return service.findById(id)
                .map(EmployeeDto.Mapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = EmployeeDto.Mapper.toModel(employeeDto);
        employee.setId(null); // Force to save, instead of update
        return EmployeeDto.Mapper.toDto(service.save(employee));
    }

    @PutMapping("/employees/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        if (!service.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        Employee employee = EmployeeDto.Mapper.toModel(employeeDto);
        employee.setId(id);
        return EmployeeDto.Mapper.toDto(service.save(employee));
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        service.deleteEmployeeById(id);
    }
}
