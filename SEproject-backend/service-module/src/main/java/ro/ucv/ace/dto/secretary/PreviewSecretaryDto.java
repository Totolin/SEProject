package ro.ucv.ace.dto.secretary;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.person.PreviewPersonDto;
import ro.ucv.ace.dto.user.PreviewAccountDto;

/**
 * Created by Geo on 23.05.2016.
 */
@Getter
@Setter
public class PreviewSecretaryDto extends PreviewPersonDto {

    PreviewAccountDto account;
}
