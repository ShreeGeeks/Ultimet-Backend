package com.ultimet.user.service;

import com.ultimet.user.UserServiceApplication;
import com.ultimet.user.base.BaseResponse;
import com.ultimet.user.entity.User;
import com.ultimet.user.mapper.UserMapper;
import com.ultimet.user.repository.UserRepository;
import com.ultimet.user.wrapper.request.LoginForm;
import com.ultimet.user.wrapper.response.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public BaseResponse login(LoginForm loginForm) {
        log.info("Executing login : {}", loginForm.getUsername());
        BaseResponse response = new BaseResponse();
        try {
            if (!loginForm.validate(response)) {
                return response;
            }
            Optional<User> optionalUser = userRepository.findByPhone(loginForm.getUsername());
            if (optionalUser.isEmpty()) {
                return response.set(302, "User not found");
            }
            User user = optionalUser.get();
            if (!Objects.equals(user.getPassword(), loginForm.getPassword())) {
                return response.set(302, "Invalid password");
            }

            String token = UUID.randomUUID().toString();
            UserServiceApplication.userTokenMap.put(token, user);

            UserDto userDto = userMapper.toDto(user);
            userDto.setToken(token);
            response.set(200, "Login Success", userDto);
        } catch (Exception e) {
            log.error("Exception while login : ", e);
            response.setSomethingWentWrong();
        }
        return response;
    }

    public BaseResponse logout(String token) {
        log.info("Executing logout()");
        BaseResponse response = new BaseResponse();
        try {
            if (!UserServiceApplication.userTokenMap.containsKey(token)) {
                return response.set(302, "User not logged in");
            }
            UserServiceApplication.userTokenMap.remove(token);
            response.set(200, "Logout Success");
        } catch (Exception e) {
            log.error("Exception while logout : ", e);
            response.setSomethingWentWrong();
        }
        return response;
    }
}
