package com.srping.identify_course.dto.request;

import static org.hibernate.query.sqm.produce.function.StandardArgumentsValidators.min;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.srping.identify_course.validator.DobConstraint;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data // tự động tạo getter, setter, contructer, equals and hascode, toString
@FieldDefaults(level = AccessLevel.PRIVATE) // đặt các field mặc định là private
@Builder
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "INVALID_PASSWORD") // quy định độ dài tối thiểu
    String password;

    String firstName;
    String lastName;

    @DobConstraint(min = 16, message = "INVALID_DOB")
    LocalDate dob;
}
