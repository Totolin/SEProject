package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.dto.user.PreviewAccountDto;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class ProfessorInfoDto {

    private int id;

    private String ssn;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private DepartmentDto department;

    private String office;

    private String position;

    private PreviewAccountDto account;
}
