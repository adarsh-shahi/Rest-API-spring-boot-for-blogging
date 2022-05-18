package com.example.blog.Controllers;

import com.example.blog.Payloads.UserDto;
import com.example.blog.Services.Implementation.UserServiceImpl;
import com.example.blog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST - create user
    @PostMapping("/")
    private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // PUT - update user
    @PutMapping("/{id}")
    private ResponseEntity<UserDto> updateUser(@PathVariable("id") int id,@Valid @RequestBody UserDto userDto){
        UserDto updateUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    // DELETE = delete user
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return new ResponseEntity<>(Map.of("message", "user deleted successfully "), HttpStatus.OK);
    }

    // GET - get a user or all users

    @GetMapping("/")
    private ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDto> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }



}
