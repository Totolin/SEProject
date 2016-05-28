package ro.ucv.ace.dto.department;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Geo on 28.05.2016.
 */
@Getter
@Setter
public class SaveDepartmentDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String name;
}
