package ro.ucv.ace.enums;

import lombok.Getter;

/**
 * This enum contains all the possible roles that a user can have in our application.
 *
 * @author Georgian Vladutu
 */
@Getter
public enum UserType {
    ADMIN("ADMIN"), STUDENT("STUDENT"), PROFESSOR("PROFESSOR"), SECRETARY("SECRETARY");

    private final String type;

    UserType(String type) {
        this.type = type;
    }
}
