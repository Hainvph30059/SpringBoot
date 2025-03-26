package com.srping.identify_course.service;

import com.srping.identify_course.Repository.UserRepositoty;
import com.srping.identify_course.dto.request.AuthenticationRequest;
import com.srping.identify_course.exception.AppException;
import com.srping.identify_course.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepositoty userRepositoty;
    public boolean authenticate(AuthenticationRequest request) {
        var user = userRepositoty.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIT));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches((request.getPassword()), user.getPassword());
    }
}
