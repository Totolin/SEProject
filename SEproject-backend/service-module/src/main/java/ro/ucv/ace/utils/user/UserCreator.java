package ro.ucv.ace.utils.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ModelMapper modelMapper;


    public User createUser(Person person, String type) {
        String password = randomString(10);

        User user = new User();
        user.setState("Active");
        user.setUsername(person.getSsn());
        user.setPassword(password);
        user.setType(type);
        user.setId(person.getId());

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
