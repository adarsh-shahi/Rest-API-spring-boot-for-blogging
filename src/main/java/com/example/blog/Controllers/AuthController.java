package com.example.blog.Controllers;

import com.example.blog.Entities.User;
import com.example.blog.Exceptions.ApiException;
import com.example.blog.Payloads.JwtAuthRequest;
import com.example.blog.Payloads.JwtAuthResponse;
import com.example.blog.Payloads.UserDto;
import com.example.blog.Security.JwtTokenHelper;
import com.example.blog.Services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    private ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
        authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        String token = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return  new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

          try{
              authenticationManager.authenticate(authenticationToken);
          }
          catch (BadCredentialsException e){
              throw  new ApiException("Invalid user name or password");
          }
    }

    // register new user
    @PostMapping("/register")
    private ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registeredUser = userService.registerNewUser(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

}
