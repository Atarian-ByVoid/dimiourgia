package com.dimiourgia.user;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.dimiourgia.user.UserEnum.Gender;
import com.dimiourgia.user.UserEnum.MaritalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user_tb", uniqueConstraints = { @UniqueConstraint(columnNames = { "document" }) })
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
public class User {

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 15, unique = true, nullable = false)
    private String document;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(name = "mother_name", length = 150, nullable = true)
    private String motherName;

    @Column(name = "father_name", length = 150)
    private String fatherName;

    @Column(length = 20, nullable = true)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "marital_status", length = 30, nullable = true)
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "birth_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "issuing_agency", length = 20, nullable = true)
    private String issuingAgency;

    @Column(name = "politically_exposed", nullable = true)
    private Boolean politicallyExposed = false;

    @Column(length = 150, unique = true, nullable = false)
    private String email;

    @Column(length = 15, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    private boolean disabled;

    public void encryptPassword() {
        if (password != null) {
            this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
        }
    }
}
