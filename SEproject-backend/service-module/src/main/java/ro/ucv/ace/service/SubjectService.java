package ro.ucv.ace.service;

import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.subject.SaveSubjectDto;
import ro.ucv.ace.dto.subject.UpdateSubjectDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating Subject objects.
 *
 * @author Cristian Totolin
 */
public interface SubjectService {

    /**
     * Returns all subject.
     *
     * @return list of PreviewSubjectDto
     */
    List<PreviewSubjectDto> getAllSubjects();

    /**
     * Returns the subject whose id is id.
     *
     * @param id id of the subject
     * @return PreviewSubjectDto
     * @throws ServiceEntityNotFoundException if the subject is not found
     */
    PreviewSubjectDto getById(int id) throws ServiceEntityNotFoundException;

    /**
     * Deletes the subject whose id is id.
     *
     * @param id id of the subject
     * @throws ServiceEntityNotFoundException if the subject is not found
     */
    void delete(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Saves the subject into the repository.
     *
     * @param saveSubjectDto subject to be saved
     * @throws ServiceEntityAlreadyExistsException if the subject already exists
     * @throws ServiceForeignKeyNotFoundException  if any of the subject components are not found
     */
    void save(SaveSubjectDto saveSubjectDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    /**
     * Updates the subject whose id is id.
     *
     * @param id               id of the subject
     * @param updateSubjectDto subject to be updated
     * @throws ServiceEntityNotFoundException     if the subject is not found
     * @throws ServiceForeignKeyNotFoundException if any of the subject components are not found
     */
    void update(Integer id, UpdateSubjectDto updateSubjectDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;
}
