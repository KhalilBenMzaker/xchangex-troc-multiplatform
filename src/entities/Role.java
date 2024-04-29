package entities;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ROLE_USER("[\"ROLE_USER\"]"),
    ROLE_ADMIN("[\"ROLE_ADMIN\"]"),
    ROLE_PROSELLER("[\"ROLE_PROSELLER\"]");

    private final String symfonyRole;

    Role(String symfonyRole) {
        this.symfonyRole = symfonyRole;
    }

    public String getSymfonyRole() {
        return symfonyRole;
    }

    public static Role fromSymfonyRole(String symfonyRole) {
        for (Role role : Role.values()) {
            if (role.getSymfonyRole().equals(symfonyRole)) {
                return role;
            }
        }
        return null;
    }
}