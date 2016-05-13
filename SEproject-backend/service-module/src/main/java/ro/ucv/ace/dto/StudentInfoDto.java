package ro.ucv.ace.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Geo on 13.05.2016.
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

    private GroupDto group;

    private String subgroup;
}
