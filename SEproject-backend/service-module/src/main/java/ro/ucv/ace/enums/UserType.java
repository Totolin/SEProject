package ro.ucv.ace.enums;

import lombok.Getter;

/**
 * Created by Geo on 23.05.2016.
 */
@Getter
public enum UserType {
    ADMIN("ADMIN"), STUDENT("STUDENT"), PROFESSOR("PROFESSOR"), SECRETARY("SECRETARY");

    private final String type;

    UserType(String type) {
        this.type = type;
    }
}
