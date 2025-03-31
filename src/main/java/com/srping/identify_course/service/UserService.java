package com.srping.identify_course.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srping.identify_course.Entity.User;
import com.srping.identify_course.Repository.RoleRepository;
import com.srping.identify_course.Repository.UserRepository;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {

        User user = userMapper.toUser(request);
        // Mã hóa password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10); // sử dụng interface được cung cấp sẵn
        user.setPassword(passwordEncoder.encode(user.getPassword())); // mã hóa password

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        // user.setRoles(roles);
        try {
            user = userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new AppException(ErrorCode.USER_EXISTSED);
        }
        return userMapper.toUserResponse(user);
    }
    // Tự động kiểm tra các authority có tiền tố ROLE_ở trước
    @PreAuthorize("hasRole('ADMIN')") // Chỉ cho phép user có role quy định được truy cập method
    // @PreAuthorize("hasAuthority('APPROVE_DATA')") // sử dụng cho các authority binh thường không có tiền tố ROLE_ ở
    // trước ví dụ các permission
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize(
            "returnObject.username == authentication.name") // Cho phép truy cập method nhung phải thỏa mãn điều kiện
    // quy định VD: User chỉ lấy được thông tin về acount
    // theo id khi id là id của user mới lấy được thông tin method trả về
    public UserResponse findUserById(String id) {
        return userMapper.toUserResponse(userRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"))); // tìm theo id nếu không thấy thì trả về thông báo
    }

    public UserResponse getMyInfo() {
        var context =
                SecurityContextHolder
                        .getContext(); // Thông tin khi đăng nhập thành công sẽ được lưu trong SecurityContextHolser
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIT));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIT));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
