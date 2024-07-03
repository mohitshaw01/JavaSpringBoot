package com.ApiProject.ApiProject.Controller;

import com.ApiProject.ApiProject.Dto.LoginDto;
import com.ApiProject.ApiProject.Dto.ResponseDto;
import com.ApiProject.ApiProject.Service.UserService;
import com.ApiProject.ApiProject.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> LoginUser(@RequestBody LoginDto userData){
        System.out.println("Inside Login");
        return userService.loginUser(userData);
    }
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> SignUp(@RequestBody UserModel userData){
        return userService.signUp(userData);
    }
}
