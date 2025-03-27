package com.srping.identify_course.mapper;

import com.srping.identify_course.Entity.Permission;
import com.srping.identify_course.Entity.User;
import com.srping.identify_course.dto.request.PermissionRequets;
import com.srping.identify_course.dto.request.UserCreationRequest;
import com.srping.identify_course.dto.request.UserUpdateRequest;
import com.srping.identify_course.dto.response.PermissionResponse;
import com.srping.identify_course.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequets request);
    PermissionResponse toPermissionResponse(Permission permission);
}
