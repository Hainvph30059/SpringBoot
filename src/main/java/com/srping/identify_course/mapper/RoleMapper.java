package com.srping.identify_course.mapper;

import com.srping.identify_course.Entity.Permission;
import com.srping.identify_course.Entity.Role;
import com.srping.identify_course.dto.request.PermissionRequets;
import com.srping.identify_course.dto.request.RoleRequets;
import com.srping.identify_course.dto.response.PermissionResponse;
import com.srping.identify_course.dto.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequets request);
    RoleResponse toRoleResponse(Role role);
}
