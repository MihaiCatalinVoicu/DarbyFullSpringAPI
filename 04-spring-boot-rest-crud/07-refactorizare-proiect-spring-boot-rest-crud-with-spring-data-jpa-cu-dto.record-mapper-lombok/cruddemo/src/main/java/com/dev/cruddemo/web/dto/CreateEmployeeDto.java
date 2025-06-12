package com.dev.cruddemo.web.dto;

import com.dev.cruddemo.domain.model.Employee;

public record CreateEmployeeDto(
        String firstName,
        String lastName,
        String email
) {
    public static class Mapper {
        public static Employee toEntity(CreateEmployeeDto dto) {
            Employee employee = new Employee();
            employee.setFirstName(dto.firstName());
            employee.setLastName(dto.lastName());
            employee.setEmail(dto.email());
            return employee;
        }
    }
}