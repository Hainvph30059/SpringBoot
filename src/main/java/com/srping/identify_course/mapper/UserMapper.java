package com.srping.identify_course.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.srping.identify_course.Entity.User;
import com.srping.identify_course.dto.request.UserCreationRequest;
import com.srping.identify_course.dto.request.UserUpdateRequest;
import com.srping.identify_course.dto.response.UserResponse;

@Mapper(componentModel = "spring") // khai báo để dùng trong spring
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request); // map data từ request sang user

    UserResponse toUserResponse(User user);
}
