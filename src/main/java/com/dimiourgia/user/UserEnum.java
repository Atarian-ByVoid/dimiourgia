package com.dimiourgia.user;

import lombok.Data;

@Data
public class UserEnum {

    public enum MaritalStatus {
        SINGLE("Single"),
        MARRIED("Married"),
        WIDOWED("Widowed"),
        LEGALLY_SEPARATED("Legally Separated"),
        DIVORCED("Divorced");

        public final String text;

        MaritalStatus(String maritalStatus) {
            text = maritalStatus;
        }
    }

    public enum Gender {
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other"),
        PREFER_NOT_TO_SAY("Prefer Not to Say");

        public final String text;

        Gender(String gender) {
            text = gender;
        }
    }

}