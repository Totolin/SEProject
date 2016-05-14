package ro.ucv.ace.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Geo on 14.05.2016.
 */
@Getter
@Setter
public class SaveStudentDto {

    private String ssn;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private int groupId;

    private String subgroup;
}
