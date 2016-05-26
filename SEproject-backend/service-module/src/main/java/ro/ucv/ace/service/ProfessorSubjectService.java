package ro.ucv.ace.service;

import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 26.05.2016.
 */
public interface ProfessorSubjectService {

    void grade(SaveStudentGradeDto professorGradeDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    List<PreviewSubjectDto> getAllSubjectsByProfessor(Integer professorId);

    List<PreviewGroupDto> getAllGroupsByProfessorAndSubject(Integer professorId, Integer subjectId);

    List<StudentInfoDto> getAllByGroup(Integer groupId);
}
