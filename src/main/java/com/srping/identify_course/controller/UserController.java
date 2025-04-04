package com.srping.identify_course.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.srping.identify_course.dto.request.ApiReponse;
import com.srping.identify_course.dto.request.UserCreationRequest;
import com.srping.identify_course.dto.request.UserUpdateRequest;
import com.srping.identify_course.dto.response.UserResponse;
import com.srping.identify_course.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // định nghĩa một controller
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiReponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        // @RequestBody gán thông tin nhận được từ controller vào Object
        // @Valid thông báo cho spring rằng đối tượng phải được validation
        ApiReponse<UserResponse> apiReponse = new ApiReponse<UserResponse>();
        apiReponse.setResult(userService.createUser(userCreationRequest));
        return apiReponse;
    }

    @GetMapping
    ApiReponse<List<UserResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // log.info("User Name: {}", authentication.getName());
        // authentication.getAuthorities().forEach(grantedAuthority -> log.info("GrantedAuthority: {}",
        // grantedAuthority.getAuthority()));
        return ApiReponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/myInfo")
    ApiReponse<UserResponse> getMyInfo() {
        return ApiReponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }
}
