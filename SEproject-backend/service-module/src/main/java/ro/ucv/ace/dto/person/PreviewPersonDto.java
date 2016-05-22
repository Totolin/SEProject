package ro.ucv.ace.dto.person;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Created by Geo on 22.05.2016.
 */
@Getter
@Setter
public class PreviewPersonDto extends SavePersonDto {

    @Min(1)
    private int id;
}
