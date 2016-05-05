package ro.ucv.ace.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ctotolin on 05-May-16.
 */
@Getter
@Setter
public class UserCreateDto {

    private String username;

    private String password;

    @JsonIgnore
    private String state;

    private String type;
}
