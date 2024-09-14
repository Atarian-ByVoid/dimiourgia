package com.dimiourgia.user;

import com.dimiourgia.user.dto.UserDTOValidation;

public class UserMapper {

    public static User toEntity(UserDTOValidation request) {
        User user = new User();
        user.setDocument(request.getDocument());
        user.setName(request.getName());
        user.setMotherName(request.getMotherName());
        user.setFatherName(request.getFatherName());
        user.setGender(request.getGender());
        user.setMaritalStatus(request.getMaritalStatus());
        user.setBirthDate(request.getBirthDate());
        user.setIssuingAgency(request.getIssuingAgency());
        user.setPoliticallyExposed(request.getPoliticallyExposed());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());
        user.encryptPassword();

        return user;
    }
}
