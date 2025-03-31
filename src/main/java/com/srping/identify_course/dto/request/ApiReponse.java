package com.srping.identify_course.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // các field null sẽ không cần hiển thị
public class ApiReponse<T> {
    @Builder.Default
    private int code = 1000;

    private String message;
    private T result;
}
