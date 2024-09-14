package com.dimiourgia.user.dto;

import java.util.Date;

import com.dimiourgia.user.UserEnum.Gender;
import com.dimiourgia.user.UserEnum.MaritalStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTOValidation {

    @NotBlank(message = "Document cannot be blank", groups = UserValidation.Create.class)
    @Size(max = 15, message = "Document length must be up to 15 characters")
    private String document;

    @NotBlank(message = "Name cannot be blank", groups = UserValidation.Create.class)
    @Size(max = 150, message = "Name length must be up to 150 characters")
    private String name;

    @Size(max = 150, message = "Mother's name length must be up to 150 characters", groups = UserValidation.Create.class)
    private String motherName;

    @Size(max = 150, message = "Father's name length must be up to 150 characters", groups = UserValidation.Create.class)
    private String fatherName;

    @NotNull(message = "Gender cannot be null", groups = UserValidation.Create.class)
    private Gender gender;

    @NotNull(message = "Marital Satus cannot be null", groups = UserValidation.Create.class)
    private MaritalStatus maritalStatus;

    @Past(message = "Birth date must be a past date", groups = UserValidation.Create.class)
    private Date birthDate;

    @Size(max = 20, message = "Issuing agency length must be up to 20 characters", groups = UserValidation.Create.class)
    private String issuingAgency;

    private Boolean politicallyExposed = false;

    @NotBlank(message = "Email cannot be blank", groups = UserValidation.Create.class)
    @Email(message = "Email should be valid")
    @Size(max = 150, message = "Email length must be up to 150 characters")
    private String email;

    @NotBlank(message = "Phone number cannot be blank", groups = UserValidation.Create.class)
    private String phone;

    @NotBlank(message = "Password cannot be blank", groups = UserValidation.Create.class)
    private String password;

    public class UserValidation {

        public interface Create {

        }

    }

}
