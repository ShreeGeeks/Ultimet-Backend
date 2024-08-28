package com.ultimet.user.wrapper.request;

import com.ultimet.user.base.BaseResponse;
import lombok.Data;

@Data
public class LoginForm {

    private String username;

    private String password;

    public boolean validate(BaseResponse response) {
        if (this.username == null || this.username.isEmpty()) {
            response.set(302, "Username cannot be null or empty");
            return false;
        }
        if (this.password == null || this.password.isEmpty()) {
            response.set(302, "Password cannot be null or empty");
            return false;
        }
        return true;
    }
}
