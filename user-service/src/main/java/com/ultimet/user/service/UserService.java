package com.ultimet.user.service;

import com.ultimet.user.base.BaseResponse;
import com.ultimet.user.entity.User;
import com.ultimet.user.mapper.UserMapper;
import com.ultimet.user.repository.UserRepository;
import com.ultimet.user.wrapper.request.UserForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public BaseResponse registerUser(UserForm userForm) {
        BaseResponse response = new BaseResponse();
        try {
            User user = userRepository.save(userMapper.toEntity(userForm));
            response.set(200, "User registered successfully", userMapper.toDto(user));
        } catch (Exception e) {
            log.error("Error while registering user: {}", e.getMessage(), e);
            response.setInternalServerError();
        }
        return response;
    }


    public BaseResponse search(String email, String phone) {
        return null;
    }
}
