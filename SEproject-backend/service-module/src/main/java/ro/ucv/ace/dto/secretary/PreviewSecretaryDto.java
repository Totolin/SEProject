package ro.ucv.ace.dto.secretary;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.person.PreviewPersonDto;
import ro.ucv.ace.dto.user.PreviewAccountDto;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class PreviewSecretaryDto extends PreviewPersonDto {

    PreviewAccountDto account;
}
