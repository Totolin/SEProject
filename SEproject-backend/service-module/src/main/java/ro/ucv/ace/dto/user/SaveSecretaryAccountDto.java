package ro.ucv.ace.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * This class represents a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class SaveSecretaryAccountDto {

    @Size(min = 5, max = 30)
    protected String username;

    @Size(min = 5, max = 20)
    protected String password;

    @JsonIgnore
    protected String state;

    @JsonIgnore
    protected String type;
}
