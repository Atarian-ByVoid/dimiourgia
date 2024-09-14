package com.dimiourgia.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.dimiourgia.user.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("""
            SELECT NEW com.dimiourgia.infrastructure.adapter.authentication.AuthDetails(
            u.document, u.disabled, u.email, u.password
            )
            FROM User u
            WHERE LOWER(u.email) = LOWER(:email)
            """)
    Optional<UserDetails> loadUserByUsername(@Param("email") String email);

    Optional<User> findByDocumentAndEmail(String document, String email);

    Optional<User> findByDocument(String userDocument);

}
