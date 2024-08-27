package com.hedrobyte.customer_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CustomerDTO(
        Long id,

        @NotBlank(message = "Name is required.")
        @Size(max = 100, message = "Name can be up to 100 characters long.")
        String name,

        @NotBlank(message = "CPF is required.")
        @Size(min = 11, max = 11, message = "CPF must be 11 characters long.")
        String cpf,

        @Past(message = "Date of birth must be in the past.")
        LocalDate dateOfBirth,

        @NotBlank(message = "Email is required.")
        @Email(message = "Email must be valid.")
        @Size(max = 100, message = "Email can be up to 100 characters long.")
        String email
) {}
