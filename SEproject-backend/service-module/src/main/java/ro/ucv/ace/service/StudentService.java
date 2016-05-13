package ro.ucv.ace.service;

import ro.ucv.ace.dto.StudentGrade;
import ro.ucv.ace.dto.StudentInfoDto;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;

import java.util.List;

/**
 * Created by Geo on 28.04.2016.
 */
public interface StudentService {

    List<StudentGrade> getAllGrades(Integer id) throws ServiceEntityNotFoundException;

    StudentInfoDto getStudentInfo(Integer id) throws ServiceEntityNotFoundException;
}
