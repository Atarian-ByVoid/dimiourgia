package com.dimiourgia.infrastructure.adapter.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dimiourgia.user.domain.interfaces.UserInterfaceRepository;

@Service
public class CustomDetailsService implements UserDetailsService {

    private final UserInterfaceRepository interfaceRepository;

    public CustomDetailsService(UserInterfaceRepository interfaceRepository) {
        this.interfaceRepository = interfaceRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return interfaceRepository.loadUserByUsername(email);
    }

}
