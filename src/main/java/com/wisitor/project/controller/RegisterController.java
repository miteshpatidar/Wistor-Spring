package com.wisitor.project.controller;


import com.wisitor.project.dto.RegisterDTO;
import com.wisitor.project.model.User;
import com.wisitor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    private String registerUser(@RequestBody RegisterDTO dto){
        try {
            userService.save(dto);
            System.out.println("done");
            return "registerd";
        }
        catch (Exception e){
            return "Error occured" + e.getMessage();
        }
    }
}
