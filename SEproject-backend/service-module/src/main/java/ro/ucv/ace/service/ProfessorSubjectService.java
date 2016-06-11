package ro.ucv.ace.service;

import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.dto.professor.UpdateStudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoWithGradeDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating ProfessorSubject objects.
 *
 * @author Cristian Totolin
 */
public interface ProfessorSubjectService {

    /**
     * Saves the grade of a subject that a student has.
     *
     * @param saveStudentGradeDto contains the subjectId, the studentId and the grade
     * @throws ServiceEntityAlreadyExistsException if the grade already exists
     * @throws ServiceEntityNotFoundException      if the student or subject are not found
     */
    void saveGrade(SaveStudentGradeDto saveStudentGradeDto) throws ServiceEntityAlreadyExistsException, ServiceEntityNotFoundException;

    /**
     * Updates the grade of a subject that a student has
     *
     * @param updateStudentGradeDto contains the subjectId, the studentId and the grade
     * @throws ServiceEntityNotFoundException if the student or the subject are not found
     */
    void updateGrade(UpdateStudentGradeDto updateStudentGradeDto) throws ServiceEntityNotFoundException;

    /**
     * Returns all subjects that are taught by the professor whose id is professorId.
     *
     * @param professorId id of the professor
     * @return list of PreviewSubjectDto
     */
    List<PreviewSubjectDto> getAllSubjectsByProfessor(Integer professorId);

    /**
     * Returns all groups which have the subject whose id is subjectId as one of their subjects and they are taught by
     * the professor whose id is professorId.
     *
     * @param professorId id of the professor
     * @param subjectId   id of the subject
     * @return list of PreviewGroupDto
     */
    List<PreviewGroupDto> getAllGroupsByProfessorAndSubject(Integer professorId, Integer subjectId);

    /**
     * Returns all Students which have the subject whose id is subjectId as one of their subjects and are part of the
     * group whose id is groupId.
     *
     * @param subjectId id of the subject
     * @param groupId   id of the group
     * @return list of StudentInfoWithGradeDto
     */
    List<StudentInfoWithGradeDto> getAllByGroup(Integer subjectId, Integer groupId);
}
