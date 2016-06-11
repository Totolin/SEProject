package ro.ucv.ace.service;

import ro.ucv.ace.dto.secretary.PreviewSecretaryDto;
import ro.ucv.ace.dto.secretary.SaveSecretaryDto;
import ro.ucv.ace.dto.secretary.UpdateSecretaryDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating Secretary objects.
 *
 * @author Georgian Vladutu
 */
public interface SecretaryService {

    /**
     * Returns all secretaries.
     *
     * @return list of PreviewSecretaryDto
     */
    List<PreviewSecretaryDto> getAllSecretaries();

    /**
     * Returns the secretary whose id is id.
     *
     * @param id id of the secretary
     * @return PreviewSecretaryDto
     * @throws ServiceEntityNotFoundException if the secretary is not found
     */
    PreviewSecretaryDto getById(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Saves the secretary into the repository.
     *
     * @param saveSecretaryDto secretary to be saved
     * @throws ServiceEntityAlreadyExistsException if the secretary already exists
     * @throws ServiceForeignKeyNotFoundException  if any of the secretary components are not found
     */
    void save(SaveSecretaryDto saveSecretaryDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    /**
     * Deletes the secretary whose id is id.
     *
     * @param id id of the secretary
     * @throws ServiceEntityNotFoundException if the secretary is not found
     */
    void delete(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Updates the secretary whose id is id.
     *
     * @param updateSecretaryDto secretary to be updated
     * @param id                 id of the secretary
     * @throws ServiceEntityNotFoundException     if the secretary is not found
     * @throws ServiceForeignKeyNotFoundException if any of the secretary components are not found
     */
    void update(UpdateSecretaryDto updateSecretaryDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;

}
