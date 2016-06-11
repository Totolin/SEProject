package ro.ucv.ace.dto.department;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class UpdateDepartmentDto {

    @Min(1)
    private int id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @Min(1)
    private Integer directorId;
}
