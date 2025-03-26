package com.srping.identify_course.service;

import com.srping.identify_course.Entity.User;
import com.srping.identify_course.Repository.UserRepositoty;
import com.srping.identify_course.dto.request.UserCreationRequest;
import com.srping.identify_course.dto.request.UserUpdateRequest;
import com.srping.identify_course.dto.response.UserResponse;
import com.srping.identify_course.enums.Role;
import com.srping.identify_course.exception.AppException;
import com.srping.identify_course.exception.ErrorCode;
import com.srping.identify_course.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        //Mã hóa password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10); // sử dụng interface được cung cấp sẵn
        user.setPassword(passwordEncoder.encode(user.getPassword())); // mã hóa password

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
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
