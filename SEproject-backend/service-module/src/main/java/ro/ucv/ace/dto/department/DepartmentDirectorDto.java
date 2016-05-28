package ro.ucv.ace.dto.department;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Created by Geo on 28.05.2016.
 */
@Getter
@Setter
public class DepartmentDirectorDto {

    @Min(1)
    private int departmentId;

    @Min(1)
    private int professorId;
}
