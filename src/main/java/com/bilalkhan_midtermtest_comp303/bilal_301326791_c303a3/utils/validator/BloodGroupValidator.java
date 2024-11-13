package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BloodGroupValidator implements ConstraintValidator<ValidBloodGroup, String> {
    public Set<String> VALID_BLOOD_GROUPS = Set.of(
            "A+", "A-",
            "B+", "B-",
            "AB+", "AB-",
            "O+", "O-"
    );
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;
        return VALID_BLOOD_GROUPS.contains(value);
    }
}
