package ro.ucv.ace.dto.student;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Created by Geo on 14.05.2016.
 */
@Getter
@Setter
public class UpdateStudentDto extends SaveStudentDto {

    @Min(1)
    private int id;
}
