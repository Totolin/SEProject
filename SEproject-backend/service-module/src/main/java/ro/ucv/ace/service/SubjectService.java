package ro.ucv.ace.service;

import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.subject.SaveSubjectDto;
import ro.ucv.ace.dto.subject.UpdateSubjectDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
public interface SubjectService {

    List<PreviewSubjectDto> getAllSubjects();

    PreviewSubjectDto getById(int id) throws ServiceEntityNotFoundException;

    void delete(Integer id) throws ServiceEntityNotFoundException;

    void save(SaveSubjectDto saveSubjectDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    void update(Integer id,  UpdateSubjectDto updateSubjectDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;
}
