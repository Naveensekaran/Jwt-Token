package com.example.JwtToken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.JwtToken.common.APIResponse;
import com.example.JwtToken.dto.LoginRequestDto;
import com.example.JwtToken.dto.SignUpRequestDto;
import com.example.JwtToken.service.LoginService;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        APIResponse apiResponse = loginService.signUp(signUpRequestDto);
        return ResponseEntity.status(apiResponse.getStatus())
        .body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDto loginRequestDto){
        APIResponse apiResponse = loginService.login(loginRequestDto);
        return ResponseEntity.status(apiResponse.getStatus())
        .body(apiResponse);
    }

    @GetMapping("/privateApi")
    public ResponseEntity<APIResponse> privateApi(){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData("This is private api");

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
