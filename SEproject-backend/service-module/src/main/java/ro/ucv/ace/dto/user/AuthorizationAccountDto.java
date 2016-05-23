package ro.ucv.ace.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Geo on 23.05.2016.
 */
@Getter
@Setter
public class AuthorizationAccountDto {

    private String username;

    private String state;

    @JsonIgnore
    private String password;

    private String type;

    private String authorization;
}
