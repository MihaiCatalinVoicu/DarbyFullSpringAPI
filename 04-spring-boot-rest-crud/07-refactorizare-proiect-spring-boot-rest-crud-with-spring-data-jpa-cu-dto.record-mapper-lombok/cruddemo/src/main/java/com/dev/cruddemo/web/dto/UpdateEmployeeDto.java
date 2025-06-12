package com.dev.cruddemo.web.dto;

import com.dev.cruddemo.domain.model.Employee;

public record UpdateEmployeeDto(
        String firstName,
        String lastName,
        String email
) {
    public static class Mapper {
        public static Employee toEntity(UpdateEmployeeDto dto, Employee existingEmployee) {
            existingEmployee.setFirstName(dto.firstName());
            existingEmployee.setLastName(dto.lastName());
            existingEmployee.setEmail(dto.email());
            return existingEmployee;
        }
    }
}