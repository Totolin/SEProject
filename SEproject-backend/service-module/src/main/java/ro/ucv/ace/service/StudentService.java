package ro.ucv.ace.service;

import ro.ucv.ace.dto.student.SaveStudentDto;
import ro.ucv.ace.dto.student.StudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoDto;
import ro.ucv.ace.dto.student.UpdateStudentDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating Student objects.
 *
 * @author Georgian Vladutu
 */
public interface StudentService {

    /**
     * Returns the grades of the student whose id is id.
     *
     * @param id id of the student
     * @return list of StudentGradeDto
     * @throws ServiceEntityNotFoundException if the student is not found
     */
    List<StudentGradeDto> getAllGrades(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Returns the student's information whose id is id.
     *
     * @param id id of the student
     * @return StudentInfoDto
     * @throws ServiceEntityNotFoundException if the student is not found
     */
    StudentInfoDto getStudentInfo(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Returns all students.
     *
     * @return list of StudentInfoDto
     */
    List<StudentInfoDto> getAllStudents();

    /**
     * Saves the student into the repository.
     *
     * @param saveStudentDto student to be saved
     * @throws ServiceEntityAlreadyExistsException if the student already exists
     * @throws ServiceForeignKeyNotFoundException  if any of the student components are not found
     */
    void save(SaveStudentDto saveStudentDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    /**
     * Updates the student whose id is id.
     *
     * @param updateStudentDto student to be updated
     * @param id               id of the student
     * @throws ServiceEntityNotFoundException     if the student is not found
     * @throws ServiceForeignKeyNotFoundException if any of the student components are not found
     */
    void update(UpdateStudentDto updateStudentDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;

    /**
     * Deletes the student whose id is id.
     *
     * @param id id of the student
     * @throws ServiceEntityNotFoundException if the student is not found
     */
    void delete(Integer id) throws ServiceEntityNotFoundException;

}
