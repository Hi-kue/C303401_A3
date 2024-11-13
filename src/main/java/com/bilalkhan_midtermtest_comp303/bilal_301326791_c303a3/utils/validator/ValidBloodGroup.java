package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BloodGroupValidator.class)
@Documented
public @interface ValidBloodGroup {
    String message() default "Invalid blood group value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
