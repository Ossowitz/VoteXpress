package ru.iooko.votingapp.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@UtilityClass
public class SecurityUtil {

    /**
     *  returns an AuthUser object that represents the currently authorized user.
     *      If the user is not authorized, the method returns null
     */
    public static AuthUser safeGet() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        return (principal instanceof AuthUser) ? (AuthUser) principal : null;
    }

    /**
     * returns the currently logged in user, if one exists.
     *      If the user is not authorized, the method throws a NullPointerException
     */
    public static AuthUser get() {
        return Objects.requireNonNull(safeGet(), "No authorized user found");
    }

    /**
     *  method returns the ID of the currently authorized user.
     *      If the user is not authorized, the method throws a NullPointerException
     */
    public static int authUserId() {
        return get().getUser().id();
    }
}