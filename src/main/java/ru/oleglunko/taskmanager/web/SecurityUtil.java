package ru.oleglunko.taskmanager.web;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.oleglunko.taskmanager.AuthorizedEmployee;

import static java.util.Objects.requireNonNull;

@UtilityClass
public class SecurityUtil {

    public static AuthorizedEmployee safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedEmployee) ? (AuthorizedEmployee) principal : null;
    }

    public static AuthorizedEmployee get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static int authEmployeeId() {
        return get().getId();
    }
}
