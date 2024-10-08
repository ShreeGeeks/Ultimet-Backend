package com.ultimet.user.controller;

import com.ultimet.user.base.BaseController;
import com.ultimet.user.base.BaseResponse;
import com.ultimet.user.service.UserService;
import com.ultimet.user.wrapper.request.UserForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/test")
    public String test1() {
        return "Hi there, welcome to ulti-met";
    }

    @PostMapping("/registerUser")
    public BaseResponse registerUser(@RequestBody UserForm userForm) {
        log.info("Executing registerUser() with : {}", userForm);
        return userService.registerUser(userForm);
    }

    @GetMapping(value="/search")
    public BaseResponse search(@RequestParam String email, @RequestParam String phone){
        log.info("search : {}, {}", phone, email);
        return userService.search(email, phone);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Hi there");
        return "SUCCESS";
    }

    @GetMapping("/user/{userId}")
    public BaseResponse findUser(@PathVariable Integer userId){
        System.out.println("Hi there for finding user");
        return userService.findUser(userId);
    }

    @PostMapping("/updateUser")
    public BaseResponse updateUser(@RequestBody UserForm userForm){
        System.out.println("Hi there for finding user");
        return userService.updateUser(userForm);

    }
}
