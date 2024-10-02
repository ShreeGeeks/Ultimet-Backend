package com.ultimet.user.service;

import com.ultimet.user.base.BaseResponse;
import com.ultimet.user.entity.User;
import com.ultimet.user.mapper.UserMapper;
import com.ultimet.user.repository.UserRepository;
import com.ultimet.user.wrapper.request.UserForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public BaseResponse findUser(Integer userId){
        BaseResponse response = new BaseResponse();
        try{
            Optional<User> userOptional = userRepository.findById(userId);
            if (!userOptional.isPresent()) {
              return  response.set(404, "User not found", null);
            }
            User user = userOptional.get();
            response.set(200, "User found", userMapper.toDto(user));
        } catch (Exception e) {
            response.setInternalServerError();
        }
        return response;
    }

    public BaseResponse updateUser(UserForm userForm){
        BaseResponse response = new BaseResponse();
        try{

            Optional<User> user = userRepository.findById(userForm.getId());
            if (!user.isPresent()) {
                response.set(404, "User not found");
                return response;
            }
            // Get the user entity
            User existingUser = user.get();

            // Update the user fields from the userForm
            existingUser.setFirstName(userForm.getFirstName());
            existingUser.setLastName(userForm.getLastName());

            // Save the updated user back to the database
            User updatedUser = userRepository.save(existingUser);

            // Return a successful response with updated user data
            response.set(200, "User updated successfully", userMapper.toDto(updatedUser));

        } catch (Exception e) {
            // Log the error and return an internal server error response
            log.error("Error while updating user: {}", e.getMessage(), e);
            response.setInternalServerError();
        }

        return response;
    }


}
