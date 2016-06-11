package ro.ucv.ace.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * This class is a utility class used to retrieve exception messages from a exceptionMessages file.
 *
 * @author Georgian Vladutu
 */
@Component
public class ExceptionMessageManager {

    @Autowired
    private Environment environment;

    /**
     * Returns the property value from the exceptionMessages file whose key is propName.
     *
     * @param propName name of the property
     * @return String
     */
    public String get(String propName) {
        return environment.getRequiredProperty(propName);
    }
}
