package com.ultimet.user.mapper;

import com.ultimet.user.entity.Role;
import com.ultimet.user.wrapper.response.RoleDto;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
