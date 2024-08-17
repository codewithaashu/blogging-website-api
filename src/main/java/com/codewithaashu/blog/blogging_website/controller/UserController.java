package com.codewithaashu.blog.blogging_website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaashu.blog.blogging_website.Response.ApiResponse;
import com.codewithaashu.blog.blogging_website.Response.ApisResponse;
import com.codewithaashu.blog.blogging_website.payload.UserDto;
import com.codewithaashu.blog.blogging_website.service.UsersService;

import jakarta.validation.Valid;

@RestController // to create class to be Rest Controller
@RequestMapping("/api/user") // this link is the leading link of all apis
public class UserController {

    @Autowired
    private UsersService userService;// to access methods of service, we create an object

    // create user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> createUserController(@Valid @RequestBody UserDto userDto) {
        // pass to createuser service
        UserDto createdUser = userService.createUser(userDto);
        // create the response
        ApiResponse<UserDto> apiResponse = new ApiResponse<UserDto>(createdUser, true, "User successfully created.");
        // return response
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // get all user
    @GetMapping("")
    public ResponseEntity<ApisResponse<UserDto>> getAllUsersController() {
        // pass to getallusers service
        List<UserDto> users = userService.getAllUsers();
        // create the response
        ApisResponse<UserDto> apisResponse = new ApisResponse<UserDto>(users, "Successfully fetched all users",
                true);
        // return response
        return new ResponseEntity<>(apisResponse, HttpStatus.OK);
    }

    // get user by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserController(
            @PathVariable(value = "id") Integer id) {
        // pass to getuser service
        UserDto user = userService.getUser(id);
        // create the response
        return new ResponseEntity<>(new ApiResponse<UserDto>(user, true, "Successfully fetched"), HttpStatus.OK);
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApisResponse<UserDto>> deleteUserController(@PathVariable("id") Integer id) {
        // pass to deleteuser service
        List<UserDto> remainingUser = userService.deleteUser(id);
        // create the response
        return new ResponseEntity<>(new ApisResponse<UserDto>(remainingUser, "Successfully deleted", true),
                HttpStatus.OK);
    }

    // update user by id
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUserController(@PathVariable("id") Integer id,
            @Valid @RequestBody UserDto userDto) {
        // pass to updateuser service
        UserDto updateUser = userService.updateUser(id, userDto.getName(), userDto.getImage(),
                userDto.getAccountType());
        // create the response
        return new ResponseEntity<>(new ApiResponse<UserDto>(updateUser, true, "Updated successfully"), HttpStatus.OK);
    }

    // increase follower
    @PutMapping("/follow/{id}")
    public ResponseEntity<ApiResponse<UserDto>> increaseFollowerController(@PathVariable("id") Integer id) {
        // pass to increasefollower service
        UserDto user = userService.increaseFollower(id);
        // create the response
        return new ResponseEntity<>(new ApiResponse<UserDto>(user, true, "Followed successfully"), HttpStatus.OK);
    }

    // for login we create a route. after login, it will verify the credentials with
    // db and sent back to a token on the client
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUserController(@RequestBody UserDto userDto) {
        // pass to login service
        String token = userService.loginUser(userDto.getEmail(), userDto.getPassword());
        if (token == "FAILED") {
            return new ResponseEntity<>(new ApiResponse<String>(null, false, "Invalid Credentails"),
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new ApiResponse<String>(token, true, "Successfully login"), HttpStatus.OK);
    }
}
