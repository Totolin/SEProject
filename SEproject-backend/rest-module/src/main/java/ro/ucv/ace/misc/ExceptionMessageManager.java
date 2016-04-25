package ro.ucv.ace.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Geo on 25.04.2016.
 */
@Component
public class ExceptionMessageManager {

    @Autowired
    private Environment environment;

    public String get(String propName) {
        return environment.getRequiredProperty(propName);
    }
}
