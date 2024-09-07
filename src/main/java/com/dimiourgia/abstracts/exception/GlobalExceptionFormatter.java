package com.dimiourgia.abstracts.exception;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class GlobalExceptionFormatter {

    public Map<String, Object> formatFieldInfos(
            Map<String, Object> fieldInfos,
            Object target,
            FieldError error) {
        Class<?> fieldType = null;
        String fieldName = error.getField();
        Object rejectedValue = error.getRejectedValue();

        fieldInfos.put("invalid", rejectedValue);

        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            fieldType = field.getType();
        } catch (NoSuchFieldException ignored) {
        }

        Class<?> classToCheck = fieldType;

        if (Objects.requireNonNull(fieldType)
                .isEnum()) {
            classToCheck = Enum.class;
        }

        switch (classToCheck.getSimpleName()) {
            case "Enum":
                Object[] enumConstants = fieldType.getEnumConstants();
                String expectedEnums = "";

                for (int i = 0; i < enumConstants.length; i++) {
                    if (i == 0) {
                        expectedEnums = expectedEnums + enumConstants[i].toString();
                        continue;
                    }
                    expectedEnums = expectedEnums + "|" + enumConstants[i].toString();
                }

                fieldInfos.put("expected", expectedEnums);
                break;

            case "Date":
            case "LocalDate":
                fieldInfos.put("expected", "yyyy-mm-dd");
                break;

            case "DateTime":
            case "LocalDateTime":
                fieldInfos.put("expected", "yyyy-mm-ddThh:ii:ss");
                break;

            default:
                fieldInfos.put("expected", error.getDefaultMessage());
                break;
        }

        return fieldInfos;
    }
}
