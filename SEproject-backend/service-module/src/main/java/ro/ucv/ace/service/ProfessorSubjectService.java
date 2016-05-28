package ro.ucv.ace.service;

import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoWithGradeDto;
import ro.ucv.ace.dto.subject.PreviewProfessorSubjectDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.subject.SaveProfessorSubjectDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 26.05.2016.
 */
public interface ProfessorSubjectService {

    void grade(SaveStudentGradeDto professorGradeDto) throws ServiceEntityAlreadyExistsException, ServiceEntityNotFoundException;

    List<PreviewSubjectDto> getAllSubjectsByProfessor(Integer professorId);

    List<PreviewGroupDto> getAllGroupsByProfessorAndSubject(Integer professorId, Integer subjectId);

    List<StudentInfoWithGradeDto> getAllByGroup(Integer subjectId, Integer groupId);

    List<PreviewProfessorSubjectDto> getAllProfessorSubjects(Integer professorId);

    void save(SaveProfessorSubjectDto saveProfessorSubjectDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    void delete(Integer id) throws ServiceEntityNotFoundException;
}
