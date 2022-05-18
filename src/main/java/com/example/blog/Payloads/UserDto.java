package com.example.blog.Payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "username must be at least 4 characters")
    private String name;
    @Email(message = "email not valid")
    private String email;
    @NotEmpty
    @Size(min = 5, max = 15, message = "password length must be between 5 to 15 including spaces")
    private String password;
    @NotEmpty
    private String about;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
