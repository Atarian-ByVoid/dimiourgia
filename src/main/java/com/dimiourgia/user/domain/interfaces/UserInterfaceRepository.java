package com.dimiourgia.user.domain.interfaces;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.dimiourgia.user.User;

public interface UserInterfaceRepository {

    UserDetails loadUserByUsername(String email);

    Optional<User> findByDocumentAndEmail(String document, String email);

    User save(User user);

}
