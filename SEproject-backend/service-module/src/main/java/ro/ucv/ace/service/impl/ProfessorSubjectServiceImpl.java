package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.ProfessorSubjectDao;
import ro.ucv.ace.dao.StudentDao;
import ro.ucv.ace.dao.StudentSubjectDao;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoForeignKeyNotFoundException;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;
import ro.ucv.ace.model.*;
import ro.ucv.ace.service.ProfessorSubjectService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Geo on 26.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class ProfessorSubjectServiceImpl implements ProfessorSubjectService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentSubjectDao studentSubjectDao;

    @Autowired
    private ProfessorSubjectDao professorSubjectDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public void grade(SaveStudentGradeDto saveStudentGradeDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        StudentSubject studentGrade = modelMapper.map(saveStudentGradeDto, StudentSubject.class);

        try {
            studentSubjectDao.save(studentGrade);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public List<PreviewSubjectDto> getAllSubjectsByProfessor(Integer professorId) {
        List<ProfessorSubject> professorSubjects = professorSubjectDao.findByProfessorId(professorId);
        Set<Subject> subjects = new HashSet<>();

        for (ProfessorSubject professorSubject : professorSubjects) {
            subjects.add(professorSubject.getSubject());
        }

        List<Subject> subjectList = new ArrayList<>();
        subjectList.addAll(subjects);

        return modelMapper.map(subjectList, new TypeToken<List<PreviewSubjectDto>>() {
        }.getType());
    }

    @Override
    public List<PreviewGroupDto> getAllGroupsByProfessorAndSubject(Integer professorId, Integer subjectId) {
        List<ProfessorSubject> professorSubjects = professorSubjectDao.findByProfessorIdAndSubjectId(professorId, subjectId);
        List<Group> groups = new ArrayList<>();

        for (ProfessorSubject professorSubject : professorSubjects) {
            groups.add(professorSubject.getGroup());
        }

        return modelMapper.map(groups, new TypeToken<List<PreviewGroupDto>>() {
        }.getType());
    }

    @Override
    public List<StudentInfoDto> getAllByGroup(Integer groupId) {
        List<Student> students = studentDao.findByGroup(groupId);

        return modelMapper.map(students, new TypeToken<List<StudentInfoDto>>() {
        }.getType());
    }

}
