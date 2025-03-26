package com.srping.identify_course.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data // tự động tạo getter, setter, contructer, equals and hascode, toString
@FieldDefaults(level = AccessLevel.PRIVATE) // đặt các field mặc định là private
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
     String username;
    @Size(min = 8, message = "INVALID_PASSWORD") // quy định độ dài tối thiểu
     String password;
     String firstName;
     String lastName;
     LocalDate dob;


}
