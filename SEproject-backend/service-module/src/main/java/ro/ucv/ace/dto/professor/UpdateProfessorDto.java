package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Created by Geo on 16.05.2016.
 */
@Getter
@Setter
public class UpdateProfessorDto extends SaveProfessorDto {

    @Min(1)
    private int id;
}
