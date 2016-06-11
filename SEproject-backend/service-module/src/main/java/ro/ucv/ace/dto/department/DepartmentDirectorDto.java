package ro.ucv.ace.dto.department;

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
public class DepartmentDirectorDto {

    @Min(1)
    private int departmentId;

    @Min(1)
    private int professorId;
}
