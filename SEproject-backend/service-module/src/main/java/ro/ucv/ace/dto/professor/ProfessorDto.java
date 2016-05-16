package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.department.DepartmentDto;

/**
 * Created by Geo on 15.05.2016.
 */
@Getter
@Setter
public class ProfessorDto {

    private int id;

    private String ssn;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String position;

    private String office;

    private DepartmentDto department;
}
