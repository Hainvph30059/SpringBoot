package com.srping.identify_course.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity // định nghĩa một entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @ElementCollection
     Set<String> roles;
}
