package ro.ucv.ace.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Geo on 27.05.2016.
 */
@Getter
@Setter
public class UserImageDto {

    @Min(1)
    private int id;

    @NotNull
    private String image;
}
