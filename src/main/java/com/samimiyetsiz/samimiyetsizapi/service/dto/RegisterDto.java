package com.samimiyetsiz.samimiyetsizapi.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

    @NotNull
    @Size(min = 1, max = 34)
    private String username;
    @NotNull
    @Size(min = 1, max = 34)
    private String password;
    @NotNull
    @Size(max = 255, min = 5)
    private String email;

}
