package ro.ucv.ace.service;

import ro.ucv.ace.dto.user.UserDto;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;

/**
 * This interface provides methods for authentication.
 *
 * @author Georgian Vladutu
 */
public interface LoginService {

    /**
     * Returns the user whose username is the same as the method parameter.
     *
     * @param username username of the user
     * @return UserDto
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    UserDto getByUsername(String username) throws ServiceEntityNotFoundException;
}
