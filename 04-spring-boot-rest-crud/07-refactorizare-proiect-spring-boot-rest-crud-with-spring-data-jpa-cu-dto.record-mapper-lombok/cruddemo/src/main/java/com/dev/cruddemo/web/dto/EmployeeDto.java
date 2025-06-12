package com.dev.cruddemo.web.dto;

import com.dev.cruddemo.domain.model.Employee;

public record EmployeeDto(Long id, String firstName, String lastName, String email) {

    public static class Mapper {
        public static EmployeeDto toDto(Employee emp) {
            return new EmployeeDto(
                    emp.getId(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getEmail()
            );
        }

        public static Employee toModel(EmployeeDto dto) {
            Employee employee = new Employee();
            employee.setId(dto.id);
            employee.setFirstName(dto.firstName);
            employee.setLastName(dto.lastName);
            employee.setEmail(dto.email);

            return employee;
        }

        public static Employee toModel(Employee emp) {
            return emp;
        }
    }
}
