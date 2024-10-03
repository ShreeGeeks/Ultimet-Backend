package com.ultimet.user.wrapper.request;

import lombok.Data;

@Data
public class UserForm {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phone;

    private String employeeId;

    private String email;

    private String password;

    private String role;
}
