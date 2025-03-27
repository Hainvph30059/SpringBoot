package com.srping.identify_course.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


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
     Set<Permission> permissions ;
}
