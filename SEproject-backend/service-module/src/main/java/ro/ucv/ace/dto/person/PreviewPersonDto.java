package ro.ucv.ace.dto.person;

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
public class PreviewPersonDto extends SavePersonDto {

    @Min(1)
    protected int id;
}
