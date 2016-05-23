package ro.ucv.ace.dto.secretary;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Created by Geo on 23.05.2016.
 */
@Getter
@Setter
public class UpdateSecretaryDto extends SaveSecretaryDto {

    @Min(1)
    private int id;
}
