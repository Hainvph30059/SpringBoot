package com.srping.identify_course.Entity;

import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity // định nghĩa một entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id // Anotation gán thuộc tính là id
    String name;

    String description;

    @ManyToMany
    Set<Permission> permissions;
}
