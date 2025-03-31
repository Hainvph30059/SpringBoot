package com.srping.identify_course.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.srping.identify_course.Entity.Role;
import com.srping.identify_course.dto.request.RoleRequets;
import com.srping.identify_course.dto.response.RoleResponse;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequets request);

    RoleResponse toRoleResponse(Role role);
}
