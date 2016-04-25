package ro.ucv.ace.service;

import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;

/**
 * Created by ctotolin on 24-Apr-16.
 */
public interface LoginService {

    UserDto getByUsername(String username) throws ServiceEntityNotFoundException;
}
