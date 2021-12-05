package com.example.loginpractice.facade;

import com.example.loginpractice.entity.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserFacade {

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null
                || !(authentication.getPrincipal() instanceof User))
            return null;

        return (User) authentication.getPrincipal();
    }
}