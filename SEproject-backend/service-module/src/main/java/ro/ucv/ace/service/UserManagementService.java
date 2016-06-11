package ro.ucv.ace.service;

import ro.ucv.ace.dto.user.UserCreateDto;
import ro.ucv.ace.dto.user.UserDto;
import ro.ucv.ace.dto.user.UserImageDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating User objects.
 *
 * @author Cristian Totolin
 */
public interface UserManagementService {

    /**
     * Returns all users.
     *
     * @return list of UserDto
     */
    List<UserDto> getAllUsers();

    /**
     * Returns the user whose id is id.
     *
     * @param id id of the user
     * @return UserDto
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    UserDto getById(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Saves a user into the repository.
     *
     * @param user user to be saved
     * @throws ServiceEntityAlreadyExistsException if the user already exists
     * @throws ServiceForeignKeyNotFoundException  if any of ther user components are not found
     */
    void addUser(UserCreateDto user) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    /**
     * Deletes the user whose id is id.
     *
     * @param id id of the user
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    void deleteUser(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Uploads the profile image of a user.
     *
     * @param userImageDto contains the userId and the image encoded in Base64
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    void uploadImage(UserImageDto userImageDto) throws ServiceEntityNotFoundException;

    /**
     * Returns the profile picture of the user whose id is id.
     *
     * @param id id of the user
     * @return UserImageDto
     * @throws ServiceEntityNotFoundException if the user is not found
     */
    UserImageDto getImage(Integer id) throws ServiceEntityNotFoundException;
}
