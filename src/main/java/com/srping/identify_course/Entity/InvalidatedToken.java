package com.srping.identify_course.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity // định nghĩa một entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// Table lưu các token được logout trước khi hết hạn, và tự động xóa sau khi các token này hết hạn dựa vào expiryTime
// field
public class InvalidatedToken {
    @Id // Anotation gán thuộc tính là id
    String id;

    Date expiryTime;
}
