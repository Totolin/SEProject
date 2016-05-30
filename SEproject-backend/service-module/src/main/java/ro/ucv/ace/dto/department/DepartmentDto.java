package ro.ucv.ace.dto.department;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.professor.PreviewProfessorDto;

/**
 * Created by Geo on 15.05.2016.
 */
@Getter
@Setter
public class DepartmentDto {

    private int id;

    private String name;

    private PreviewProfessorDto director;
}
