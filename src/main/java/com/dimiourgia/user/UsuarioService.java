package com.dimiourgia.user;

import org.springframework.stereotype.Service;

import com.dimiourgia.user.domain.interfaces.UserInterfaceRepository;
import com.dimiourgia.user.dto.UserDTOValidation;

import lombok.Data;

@Service
@Data
public class UsuarioService {

    private final UserInterfaceRepository userInterfaceRepository;

    public void create(UserDTOValidation request) {
        userInterfaceRepository.findByDocumentAndEmail(request.getDocument(), request.getEmail());
        User user = UserMapper.toEntity(request);
        userInterfaceRepository.save(user);
    }

}
