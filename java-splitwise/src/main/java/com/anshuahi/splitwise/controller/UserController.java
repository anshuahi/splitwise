package com.anshuahi.splitwise.controller;

import com.anshuahi.splitwise.model.User;
import com.anshuahi.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{phone}")
    public ResponseEntity<User> getUserByPhone(@PathVariable String phone){
        return ResponseEntity.ok(userService.getUser(phone));
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> getUserByPhoneOrName(@RequestParam String prefix) {
        return ResponseEntity.ok(userService.searchUsers(prefix));
    }
}
