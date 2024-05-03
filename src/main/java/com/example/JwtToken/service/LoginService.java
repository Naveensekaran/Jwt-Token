package com.example.JwtToken.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JwtToken.common.APIResponse;
import com.example.JwtToken.dto.LoginRequestDto;
import com.example.JwtToken.dto.SignUpRequestDto;
import com.example.JwtToken.entity.UserEntity;
import com.example.JwtToken.repository.UserRepo;
import com.example.JwtToken.util.JwtUtils;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    public APIResponse signUp(SignUpRequestDto signUpRequestDto) {
      APIResponse apiResponse = new APIResponse();
        
        //validation

        //dto to entity
        UserEntity userEntity = new UserEntity();
        userEntity.setName(signUpRequestDto.getName());
        userEntity.setEmailId(signUpRequestDto.getEmailId());
        userEntity.setGender(signUpRequestDto.getGender());
        userEntity.setIsActive("true");
        userEntity.setPassword(signUpRequestDto.getPassword());
        userEntity.setPhoneNumber(signUpRequestDto.getPhoneNumber());
        //store entity
        userEntity = userRepo.save(userEntity);

        //generate token
        String token = jwtUtils.generateJwt(userEntity);

        Map<String,Object> data = new HashMap();
        data.put("accessToken", token);
        apiResponse.setData(data);
        //return
      return apiResponse;
    }

    public APIResponse login(LoginRequestDto loginRequestDto) {

      APIResponse apiResponse = new APIResponse();

      //validate

      //verify user exist with given username and password
      UserEntity user = userRepo.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDto.getEmailId(),loginRequestDto.getPassword());
      if(user == null){
        apiResponse.setData("User login failed");
        return apiResponse;
      }

      String token = jwtUtils.generateJwt(user);

      Map<String,Object> data = new HashMap();
      data.put("accessToken", token);
      apiResponse.setData(data);
      
      return apiResponse;
    }
    
}
