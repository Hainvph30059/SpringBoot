package com.srping.identify_course.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.srping.identify_course.dto.request.ApiReponse;
import com.srping.identify_course.dto.request.RoleRequets;
import com.srping.identify_course.dto.response.RoleResponse;
import com.srping.identify_course.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // định nghĩa một controller
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiReponse<RoleResponse> create(@RequestBody RoleRequets request) {
        return ApiReponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiReponse<List<RoleResponse>> findAll() {
        return ApiReponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/role")
    ApiReponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiReponse.<Void>builder().build();
    }
}
