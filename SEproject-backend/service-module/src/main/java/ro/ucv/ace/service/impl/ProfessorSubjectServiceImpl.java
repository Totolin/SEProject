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
import ro.ucv.ace.dto.student.StudentInfoWithGradeDto;
import ro.ucv.ace.dto.subject.PreviewProfessorSubjectDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.subject.SaveProfessorSubjectDto;
import ro.ucv.ace.exception.*;
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
    public void grade(SaveStudentGradeDto saveStudentGradeDto) throws ServiceEntityAlreadyExistsException, ServiceEntityNotFoundException {

        try {
            StudentSubject studentSubject = studentSubjectDao.findByStudentAndSubject(saveStudentGradeDto.getStudentId(),
                    saveStudentGradeDto.getSubjectId());

            if (studentSubject.getGrade() != null) {
                throw new ServiceEntityAlreadyExistsException();
            }

            studentSubject.setGrade(saveStudentGradeDto.getGrade());
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
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
    public List<StudentInfoWithGradeDto> getAllByGroup(Integer subjectId, Integer groupId) {
        List<Student> students = studentDao.findByGroup(groupId);
        List<StudentInfoWithGradeDto> studentInfoWithGradeDtos = new ArrayList<>();

        for (Student student : students) {
            StudentInfoWithGradeDto studentInfoWithGradeDto = modelMapper.map(student, StudentInfoWithGradeDto.class);

            List<StudentSubject> studentSubjects = student.getStudentSubjects();
            for (StudentSubject studentSubject : studentSubjects) {
                if (studentSubject.getSubject().getId().equals(subjectId)) {
                    studentInfoWithGradeDto.setGrade(studentSubject.getGrade());
                    break;
                }
            }

            studentInfoWithGradeDtos.add(studentInfoWithGradeDto);
        }

        return studentInfoWithGradeDtos;
    }

    @Override
    public List<PreviewProfessorSubjectDto> getAllProfessorSubjects(Integer professorId) {
        List<ProfessorSubject> professorSubjects = professorSubjectDao.findByProfessorId(professorId);

        return modelMapper.map(professorSubjects, new TypeToken<List<PreviewProfessorSubjectDto>>() {
        }.getType());
    }

    @Override
    public void save(SaveProfessorSubjectDto saveProfessorSubjectDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        ProfessorSubject professorSubject = modelMapper.map(saveProfessorSubjectDto, ProfessorSubject.class);

        try {
            professorSubjectDao.save(professorSubject);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        try {
            professorSubjectDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

}
