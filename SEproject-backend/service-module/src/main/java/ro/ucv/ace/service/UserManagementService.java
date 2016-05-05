package ro.ucv.ace.service;

import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;

import java.util.List;

/**
 * Created by ctotolin on 05-May-16.
 */
public interface UserManagementService {
    List<UserDto> getAllUsers();
    UserDto getByUsername(String username) throws ServiceEntityNotFoundException;
}
