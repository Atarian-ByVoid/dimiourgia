package com.dimiourgia.integration;

import java.util.UUID;

import com.dimiourgia.abstracts.AbstractEntity;
import com.dimiourgia.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "integration_tb")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
public class Integration extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "key", nullable = false, unique = true)
    private String key;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_document")
    private User user;

}
