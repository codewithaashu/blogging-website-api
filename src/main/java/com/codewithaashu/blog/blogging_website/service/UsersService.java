package com.codewithaashu.blog.blogging_website.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithaashu.blog.blogging_website.Entity.User;
import com.codewithaashu.blog.blogging_website.exceptions.ResourceNotFoundException;
import com.codewithaashu.blog.blogging_website.payload.UserDto;
import com.codewithaashu.blog.blogging_website.repository.UsersRepository;

@Service // to create class to be service class
// it is main file where all business logics are handle
public class UsersService {

    // create an object of UserRepository to access all operations on db
    @Autowired
    UsersRepository usersRepository;

    // to create user
    public UserDto createUser(UserDto userDto) {
        // request and response are basically payload type data and it is entire
        // different from entity.
        // repository only accept entity type data . so we have to change
        User user = new User();// create an empty user
        BeanUtils.copyProperties(userDto, user);// copy them in user
        // save user in db
        User savedUser = usersRepository.save(user);
        // change in response type
        UserDto savedUserDto = new UserDto();
        BeanUtils.copyProperties(savedUser, savedUserDto);
        return savedUserDto;
    }

    // to get all users
    public List<UserDto> getAllUsers() {
        // get all users from repository
        // repository data is in entity form
        List<User> users = usersRepository.findAll();
        // change in response type
        List<UserDto> userDtos = users.stream().map(user -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }).collect(Collectors.toList());
        return userDtos;
    }

    // to get user details
    public UserDto getUser(Integer id) {
        // get user from repository
        User user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", "id", id));
        // if id doesn't exist then throw exception by creating exception creating
        // object

        // change in response type
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    // delete user
    public List<UserDto> deleteUser(Integer id) {
        // get user. if user do not get, it throw error
        User user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", "id", id));
        // if we get user then it delete user
        usersRepository.delete(user);
        // after delete we get remaining user
        List<User> remainingUsers = usersRepository.findAll();
        // change in response type
        List<UserDto> userDtos = remainingUsers.stream().map(current -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(current, userDto);
            return userDto;
        }).collect(Collectors.toList());
        return userDtos;
    }

    // update user
    public UserDto updateUser(Integer id, String name, String image, String accountType) {
        // get user. if user do not get, it throw error
        User user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", "id", id));
        // if we get user , then set the user details in repository by userDto details
        user.setName(name != null ? name : user.getName());
        user.setImage(image != null ? image : user.getImage());
        user.setAccountType(accountType != null ? accountType : user.getAccountType());
        // save user in repository
        User updatedUser = usersRepository.save(user);
        // change user in userDto
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(updatedUser, userDto);
        return userDto;
    }

    // increase user followers
    public UserDto increaseFollower(Integer id) {
        // get user
        User user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", "id", id));
        // increase followers by 1
        user.setFollowers(user.getFollowers() + 1);
        // save user in repository
        User updatedUser = usersRepository.save(user);
        // change user in userDto
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(updatedUser, userDto);
        return userDto;
    }
}
