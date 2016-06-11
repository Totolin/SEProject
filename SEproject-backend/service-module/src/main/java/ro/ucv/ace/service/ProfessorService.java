package ro.ucv.ace.service;

import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.dto.professor.ProfessorInfoDto;
import ro.ucv.ace.dto.professor.SaveProfessorDto;
import ro.ucv.ace.dto.professor.UpdateProfessorDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating Professor objects.
 *
 * @author Georgian Vladutu
 */
public interface ProfessorService {

    /**
     * Returns a list of all Professor entities.
     *
     * @return list of ProfessorDto
     */
    List<ProfessorDto> getAll();

    /**
     * Returns the professor whose id is id.
     *
     * @param id id of the professor
     * @return ProfessorDto
     * @throws ServiceEntityNotFoundException if the professor is not found
     */
    ProfessorDto getById(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Saves the professor into the repository.
     *
     * @param saveProfessorDto professor to be saved
     * @throws ServiceEntityAlreadyExistsException if the professor already exists
     * @throws ServiceForeignKeyNotFoundException  if any of the professor components are not found
     */
    void save(SaveProfessorDto saveProfessorDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    /**
     * Updates the professor whose id is id.
     *
     * @param updateProfessorDto professor to be updated
     * @param id                 id of the professor
     * @throws ServiceEntityNotFoundException     if the professor is not found
     * @throws ServiceForeignKeyNotFoundException if any of the professor components are not found
     */
    void update(UpdateProfessorDto updateProfessorDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;

    /**
     * Deletes the professor whose id is id.
     *
     * @param id id of the professor
     * @throws ServiceEntityNotFoundException if the professor is not found
     */
    void delete(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Returns the professor whose id is id.
     *
     * @param id id of the professor
     * @return ProfessorInfoDto
     * @throws ServiceEntityNotFoundException if the professor is not found
     */
    ProfessorInfoDto getProfessorInfo(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Returns a list of Subject that the professor whose id is professorId teaches
     *
     * @param professorId id of the professor
     * @return list of PreviewSubjectDto
     */
    List<PreviewSubjectDto> getSubjectByProfessor(Integer professorId);
}
