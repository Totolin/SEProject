package ro.ucv.ace.dto.user;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.person.PreviewPersonDto;

/**
 * This is a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class UserDto extends PreviewPersonDto {

    AuthorizationAccountDto account;
}
