package com.srping.identify_course.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.srping.identify_course.dto.request.ApiReponse;
import com.srping.identify_course.dto.request.PermissionRequets;
import com.srping.identify_course.dto.response.PermissionResponse;
import com.srping.identify_course.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // định nghĩa một controller
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiReponse<PermissionResponse> create(@RequestBody PermissionRequets request) {
        return ApiReponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiReponse<List<PermissionResponse>> findAll() {
        return ApiReponse.<List<PermissionResponse>>builder()
                .result(permissionService.findAll())
                .build();
    }

    @DeleteMapping("/permission")
    ApiReponse<Void> delete(@PathVariable String name) {
        permissionService.delete(name);
        return ApiReponse.<Void>builder().build();
    }
}
