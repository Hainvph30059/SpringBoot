package com.srping.identify_course.mapper;

import org.mapstruct.Mapper;

import com.srping.identify_course.Entity.Permission;
import com.srping.identify_course.dto.request.PermissionRequets;
import com.srping.identify_course.dto.response.PermissionResponse;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequets request);

    PermissionResponse toPermissionResponse(Permission permission);
}
