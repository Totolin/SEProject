package ro.ucv.ace.dto.student;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.user.PreviewAccountDto;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class StudentInfoDto {

    private int id;

    private String ssn;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private PreviewGroupDto group;

    private String subgroup;

    private boolean paid;

    private PreviewAccountDto account;
}
