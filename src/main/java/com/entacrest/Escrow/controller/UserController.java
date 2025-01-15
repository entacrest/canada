package com.entacrest.Escrow.controller;


import com.entacrest.Escrow.model.User;
import com.entacrest.Escrow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {



    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "register", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);

///
    }
}
