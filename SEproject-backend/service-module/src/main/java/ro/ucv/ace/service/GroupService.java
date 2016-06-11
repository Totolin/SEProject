package ro.ucv.ace.service;

import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.group.SaveGroupDto;
import ro.ucv.ace.dto.group.UpdateGroupDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating Group objects.
 *
 * @author Georgian Vladutu
 */
public interface GroupService {

    /**
     * Returns all Group entities.
     *
     * @return list of PreviewGroupDto
     */
    List<PreviewGroupDto> getAllGroups();

    /**
     * Returns the group whose id is id.
     *
     * @param id id of the group
     * @return PreviewGroupDto
     * @throws ServiceEntityNotFoundException if the group is not found
     */
    PreviewGroupDto getById(int id) throws ServiceEntityNotFoundException;

    /**
     * Deletes the group whose id is id.
     *
     * @param id id of the group
     * @throws ServiceEntityNotFoundException if the group is not found
     */
    void delete(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Saves the group into the repository.
     *
     * @param saveGroupDto group to be saved
     * @throws ServiceEntityAlreadyExistsException if the group already exists
     * @throws ServiceForeignKeyNotFoundException  if any of the group components are not found
     */
    void save(SaveGroupDto saveGroupDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    /**
     * Updates the goup whose id is id.
     *
     * @param id             id of the group
     * @param updateGroupDto group to be updated
     * @throws ServiceEntityNotFoundException     if the group is not found
     * @throws ServiceForeignKeyNotFoundException if any of the group components are not found
     */
    void update(Integer id, UpdateGroupDto updateGroupDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;
}
