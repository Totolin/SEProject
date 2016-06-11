package ro.ucv.ace.dto.professor;

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
public class UpdateProfessorDto extends SaveProfessorDto {

    @Min(1)
    private int id;
}
