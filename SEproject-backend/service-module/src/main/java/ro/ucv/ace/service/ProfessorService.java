package ro.ucv.ace.service;

import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.dto.professor.SaveProfessorDto;
import ro.ucv.ace.dto.professor.UpdateProfessorDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 15.05.2016.
 */
public interface ProfessorService {

    List<ProfessorDto> getAll();

    ProfessorDto getById(Integer id) throws ServiceEntityNotFoundException;

    void save(SaveProfessorDto saveProfessorDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    void update(UpdateProfessorDto updateProfessorDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;

    void delete(Integer id) throws ServiceEntityNotFoundException;
}
