// Tự tạo một annotation
package com.srping.identify_course.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD }) // Annotation được dùng ở đâu
@Retention(RUNTIME) // Annotation được chạy khi nào
@Constraint(validatedBy = {DobValidator.class}) // nơi chứa logic validator là gì
public @interface DobConstraint {

    String message() default "Invalid Date of birth";

    int min() ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
