package com.srping.identify_course.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity // định nghĩa một entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id // Anotation gán thuộc tính là id
    @GeneratedValue(strategy = GenerationType.UUID) // Chuỗi ID generate ngẫu nhiên không trùng lặp
     String id;
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
}
