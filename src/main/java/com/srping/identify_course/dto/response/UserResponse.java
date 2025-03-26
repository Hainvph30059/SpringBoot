package com.srping.identify_course.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data // tự động tạo getter, setter, contructer, equals and hascode, toString
@FieldDefaults(level = AccessLevel.PRIVATE) // đặt các field mặc định là private
public class UserResponse {
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
