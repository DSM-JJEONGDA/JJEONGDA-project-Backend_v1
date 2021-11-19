package com.example.loginpractice.facade;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserFacade {

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null
                || !(authentication.getPrincipal() instanceof User))
            return null;
        return (User) authentication.getPrincipal();
    }

    public static String getUserName() {
        if (getUser() == null) {
            return null;
        }

        return getUser().getUsername();
    }
}
