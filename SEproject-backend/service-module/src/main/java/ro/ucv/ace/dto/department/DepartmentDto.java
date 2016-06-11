package ro.ucv.ace.dto.department;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.professor.PreviewProfessorDto;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class DepartmentDto {

    private int id;

    private String name;

    private PreviewProfessorDto director;
}
