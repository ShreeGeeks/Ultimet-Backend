package com.ultimet.user.wrapper.response;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phone;

    private String employeeId;

    private String email;

    private String token;

    private RoleDto roleDto;
}
