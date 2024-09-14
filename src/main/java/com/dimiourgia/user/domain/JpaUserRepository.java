package com.dimiourgia.user.domain;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.dimiourgia.abstracts.exception.MsErrorException;
import com.dimiourgia.user.User;
import com.dimiourgia.user.domain.interfaces.UserInterfaceRepository;
import com.dimiourgia.user.domain.repository.UserRepository;

import lombok.Data;

@Repository
@Data
public class JpaUserRepository implements UserInterfaceRepository {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.loadUserByUsername(email)
                .orElseThrow(() -> new MsErrorException(HttpStatus.NOT_FOUND,
                        "User don't have register in plataform.", "Not Found"));
    }

    @Override
    public Optional<User> findByDocumentAndEmail(String document, String email) {
        Optional<User> userExist = userRepository.findByDocumentAndEmail(document, email);
        if (userExist.isPresent()) {
            throw new MsErrorException(HttpStatus.FOUND, "Already registered user", "Found");
        }
        return userExist;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByDocument(String userDocument) {
        User user = userRepository.findByDocument(
                userDocument)
                .orElseThrow(() -> new MsErrorException(HttpStatus.NOT_FOUND,
                        "User not found for this document.", "Not Found"));
        return user;
    }

}