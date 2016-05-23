package ro.ucv.ace.dto.secretary;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.person.SavePersonDto;
import ro.ucv.ace.dto.user.SaveSecretaryAccountDto;

import javax.validation.Valid;

/**
 * Created by Geo on 23.05.2016.
 */
@Getter
@Setter
public class SaveSecretaryDto extends SavePersonDto {

    @Valid
    private SaveSecretaryAccountDto account;
}
