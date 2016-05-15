package ro.ucv.ace.service;

import ro.ucv.ace.dto.UserCreateDto;
import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by ctotolin on 05-May-16.
 */
public interface UserManagementService {
    List<UserDto> getAllUsers();

    UserDto getById(Integer id) throws ServiceEntityNotFoundException;

    void addUser(UserCreateDto user) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    void deleteUser(Integer id) throws ServiceEntityNotFoundException;
}
