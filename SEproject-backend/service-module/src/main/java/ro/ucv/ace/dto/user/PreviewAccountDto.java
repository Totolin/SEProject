package ro.ucv.ace.dto.user;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class PreviewAccountDto {

    private String username;

    private String state;

    private String type;
}
