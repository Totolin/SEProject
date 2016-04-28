package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.StudentDao;
import ro.ucv.ace.dto.StudentGrade;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.model.Student;
import ro.ucv.ace.model.StudentSubject;
import ro.ucv.ace.service.StudentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 28.04.2016.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StudentGrade> getAllGrades(String ssn) throws ServiceEntityNotFoundException {
        List<StudentGrade> studentGrades = new ArrayList<>();
        try {
            Student student = studentDao.findOne(ssn);
            for (StudentSubject studentSubject : student.getStudentSubjects()) {
                StudentGrade studentGrade = modelMapper.map(studentSubject, StudentGrade.class);
                studentGrades.add(studentGrade);
            }

        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        return studentGrades;
    }
}
