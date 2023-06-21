package com.ucm.myproject.config.service;

import com.ucm.myproject.config.domain.UserDetails;

public interface ValidationService {

    boolean validateUserDetails(UserDetails details);
}
