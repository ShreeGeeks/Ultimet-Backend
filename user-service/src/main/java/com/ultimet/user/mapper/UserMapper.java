package com.ultimet.user.mapper;

import com.ultimet.user.entity.User;
import com.ultimet.user.wrapper.request.UserForm;
import com.ultimet.user.wrapper.response.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final RoleMapper roleMapper;

    public User toEntity(UserForm userForm) {
        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPhone(userForm.getPhone());
        user.setEmployeeId(userForm.getEmployeeId());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setEmployeeId(user.getEmployeeId());
        userDto.setEmail(user.getEmail());
        userDto.setRoleDto(roleMapper.toDto(user.getRole()));
        return userDto;
    }
}
