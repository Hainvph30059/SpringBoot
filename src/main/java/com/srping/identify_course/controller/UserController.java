package com.srping.identify_course.controller;

import com.srping.identify_course.Entity.User;
import com.srping.identify_course.dto.request.ApiReponse;
import com.srping.identify_course.dto.request.UserCreationRequest;
import com.srping.identify_course.dto.request.UserUpdateRequest;
import com.srping.identify_course.dto.response.UserResponse;
import com.srping.identify_course.mapper.UserMapper;
import com.srping.identify_course.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //định nghĩa một controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiReponse<User> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        //@RequestBody gán thông tin nhận được từ controller vào Object
        //@Valid thông báo cho spring rằng đối tượng phải được validation
        ApiReponse<User> apiReponse = new ApiReponse<>();
        apiReponse.setResult(userService.createUser(userCreationRequest));
        return apiReponse;
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId) {
        return userService.findUserById(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId,@RequestBody UserUpdateRequest request) {
      return   userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }
}
