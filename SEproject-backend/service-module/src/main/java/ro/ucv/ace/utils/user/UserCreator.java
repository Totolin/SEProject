package ro.ucv.ace.utils.user;

import org.springframework.stereotype.Component;
import ro.ucv.ace.model.Person;
import ro.ucv.ace.model.User;

import java.security.SecureRandom;

/**
 * Created by Geo on 22.05.2016.
 */
@Component
public class UserCreator {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private SecureRandom rnd = new SecureRandom();


    public User createUser(int personId, String ssn, String type) {
        String password = randomString(10);

        User user = new User();
        Person person = new Person();
        person.setId(personId);
        user.setPassword(password);
        user.setUsername(ssn);
        user.setState("Active");
        user.setType(type);
        user.setPerson(person);

        return user;
    }

    private String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
