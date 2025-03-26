package com.srping.identify_course.service;

import com.srping.identify_course.Entity.User;
import com.srping.identify_course.Repository.UserRepositoty;
import com.srping.identify_course.dto.request.UserCreationRequest;
import com.srping.identify_course.dto.request.UserUpdateRequest;
import com.srping.identify_course.dto.response.UserResponse;
import com.srping.identify_course.exception.AppException;
import com.srping.identify_course.exception.ErrorCode;
import com.srping.identify_course.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

     UserRepositoty userRepositoty;

     UserMapper userMapper;

    public User createUser(UserCreationRequest request){
        if(userRepositoty.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTSED);
        }

        User user = userMapper.toUser(request);

        return userRepositoty.save(user);
    }

    public List<User> getAllUsers(){
        return userRepositoty.findAll();
    }

    public UserResponse findUserById(String id){
        return userMapper.toUserResponse(userRepositoty.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"))); // tìm theo id nếu không thấy thì trả về thông báo
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){

        User user = userRepositoty.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepositoty.save(user));
    }

    public void deleteUser(String userId) {
        userRepositoty.deleteById(userId);
    }
}
