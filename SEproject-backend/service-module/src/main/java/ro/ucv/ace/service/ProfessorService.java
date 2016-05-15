package ro.ucv.ace.service;

import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;

import java.util.List;

/**
 * Created by Geo on 15.05.2016.
 */
public interface ProfessorService {

    List<ProfessorDto> getAll();

    ProfessorDto getById(Integer id) throws ServiceEntityNotFoundException;
}
