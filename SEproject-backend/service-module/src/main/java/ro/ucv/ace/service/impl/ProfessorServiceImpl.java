package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.DepartmentDao;
import ro.ucv.ace.dao.EducationPlanDao;
import ro.ucv.ace.dao.ProfessorDao;
import ro.ucv.ace.dao.UserDao;
import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.dto.professor.ProfessorInfoDto;
import ro.ucv.ace.dto.professor.SaveProfessorDto;
import ro.ucv.ace.dto.professor.UpdateProfessorDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.user.PreviewAccountDto;
import ro.ucv.ace.enums.UserType;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.EducationPlan;
import ro.ucv.ace.model.Professor;
import ro.ucv.ace.model.Subject;
import ro.ucv.ace.model.User;
import ro.ucv.ace.service.ProfessorService;
import ro.ucv.ace.utils.mail.MailSender;
import ro.ucv.ace.utils.user.UserCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Geo on 15.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserCreator userCreator;

    @Autowired
    private EducationPlanDao educationPlanDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserDao userDao;

    @Override
    public List<ProfessorDto> getAll() {
        List<Professor> professors = professorDao.findAll();
        List<ProfessorDto> professorDtos = new ArrayList<>();

        for (Professor professor : professors) {
            try {
                User user = userDao.findOne(professor.getId());

                PreviewAccountDto accountDto = modelMapper.map(user, PreviewAccountDto.class);
                ProfessorDto professorDto = modelMapper.map(professor, ProfessorDto.class);
                professorDto.setAccount(accountDto);

                professorDtos.add(professorDto);

            } catch (DaoEntityNotFoundException e) {
                // ignored; professors must have an account
            }
        }

        return professorDtos;
    }

    @Override
    public ProfessorDto getById(Integer id) throws ServiceEntityNotFoundException {
        try {
            Professor professor = professorDao.findOne(id);
            User user = userDao.findOne(professor.getId());

            ProfessorDto professorDto = modelMapper.map(professor, ProfessorDto.class);
            PreviewAccountDto accountDto = modelMapper.map(user, PreviewAccountDto.class);
            professorDto.setAccount(accountDto);

            return professorDto;
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void save(SaveProfessorDto saveProfessorDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Professor professor = modelMapper.map(saveProfessorDto, Professor.class);
        String password;
        try {
            Professor saved = professorDao.save(professor);

            User user = userCreator.createUser(saved, UserType.PROFESSOR.getType());
            password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));

            userDao.save(user);

            mailSender.sendCreateAccountMail(professor.getEmail(), password, user.getUsername());
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void update(UpdateProfessorDto updateProfessorDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Professor professor = modelMapper.map(updateProfessorDto, Professor.class);
        String password;

        try {
            Professor updated = professorDao.update(id, professor);

            userDao.delete(updated.getId());

            User user = userCreator.createUser(updated, UserType.PROFESSOR.getType());

            password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));

            userDao.save(user);

            mailSender.sendUpdateAccountMail(professor.getEmail(), password, user.getUsername());
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        } catch (DaoEntityAlreadyExistsException e) {
            // ignored; account was just deleted
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        departmentDao.removeDirector(id);

        try {
            userDao.delete(id);
            professorDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public ProfessorInfoDto getProfessorInfo(Integer id) throws ServiceEntityNotFoundException {
        try {
            User user = userDao.findOne(id);
            Professor professor = professorDao.findOne(id);

            PreviewAccountDto accountDto = modelMapper.map(user, PreviewAccountDto.class);
            ProfessorInfoDto professorInfoDto = modelMapper.map(professor, ProfessorInfoDto.class);
            professorInfoDto.setAccount(accountDto);

            return professorInfoDto;
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public List<PreviewSubjectDto> getSubjectByProfessor(Integer professorId) {
        List<EducationPlan> educationPlans = educationPlanDao.findByProfessor(professorId);
        Set<Subject> subjects = educationPlans.stream().map(EducationPlan::getSubject).collect(Collectors.toSet());

        List<Subject> subjectList = new ArrayList<>();
        subjectList.addAll(subjects);

        return modelMapper.map(subjectList, new TypeToken<List<PreviewSubjectDto>>() {
        }.getType());
    }

}
