package com.serviceproject.web.Service;

import com.serviceproject.web.dto.RegistrationDto;
import com.serviceproject.web.models.UserEntity;

public interface  UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
