package ro.ucv.ace.dto.secretary;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class UpdateSecretaryDto extends SaveSecretaryDto {

    @Min(1)
    private int id;
}
