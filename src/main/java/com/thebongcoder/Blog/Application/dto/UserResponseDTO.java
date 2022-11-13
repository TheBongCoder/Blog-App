package com.thebongcoder.Blog.Application.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

    private String name;

    private String email;

    private String password;

    private String about;
}
