package com.thebongcoder.Blog.Application.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDTO {

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;

    @NotBlank(message = "password cannot be blank")
    private String confirmPassword;

    private String about;
}
