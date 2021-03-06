package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.EducationPlanDao;
import ro.ucv.ace.dao.StudentDao;
import ro.ucv.ace.dao.StudentSubjectDao;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.dto.professor.UpdateStudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoWithGradeDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
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
    private EducationPlanDao educationPlanDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public void saveGrade(SaveStudentGradeDto saveStudentGradeDto) throws ServiceEntityAlreadyExistsException, ServiceEntityNotFoundException {

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
    public void updateGrade(UpdateStudentGradeDto updateStudentGradeDto) throws ServiceEntityNotFoundException {

        try {
            studentSubjectDao.updateGrade(updateStudentGradeDto.getStudentId(), updateStudentGradeDto.getSubjectId(),
                    updateStudentGradeDto.getGrade());
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

    }

    @Override
    public List<PreviewSubjectDto> getAllSubjectsByProfessor(Integer professorId) {
        List<EducationPlan> educationPlans = educationPlanDao.findByProfessor(professorId);
        Set<Subject> subjects = new HashSet<>();

        for (EducationPlan educationPlan : educationPlans) {
            subjects.add(educationPlan.getSubject());
        }

        List<Subject> subjectList = new ArrayList<>();
        subjectList.addAll(subjects);

        return modelMapper.map(subjectList, new TypeToken<List<PreviewSubjectDto>>() {
        }.getType());
    }

    @Override
    public List<PreviewGroupDto> getAllGroupsByProfessorAndSubject(Integer professorId, Integer subjectId) {
        List<EducationPlan> educationPlans = educationPlanDao.findByProfessorAndSubject(professorId, subjectId);
        List<Group> groups = new ArrayList<>();

        for (EducationPlan educationPlan : educationPlans) {
            groups.add(educationPlan.getGroup());
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

}
