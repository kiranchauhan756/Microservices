package com.user.service.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "id cannot be null")
    @Digits(message = "only numeric values are allowed", integer = Integer.MAX_VALUE, fraction = 0)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
