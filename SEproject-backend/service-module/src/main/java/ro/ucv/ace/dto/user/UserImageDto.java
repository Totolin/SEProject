package ro.ucv.ace.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This class represents a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class UserImageDto {

    @Min(1)
    private int id;

    @NotNull
    private String image;
}
