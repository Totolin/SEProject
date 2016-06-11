package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.student.StudentGradeDto;
import ro.ucv.ace.model.StudentSubject;

/**
 * This class is used by ModelMapper to map from StudentSubject class to StudentGradeDto class.
 *
 * @author Georgian Vladutu
 */
public class StudentGradeMap extends PropertyMap<StudentSubject, StudentGradeDto> {

    @Override
    protected void configure() {
        map(source.getSubject().getName(), destination.getSubjectName());
        map(source.getSubject().getCredits(), destination.getCredits());
        map(source.getGrade(), destination.getGrade());
        map(source.getSubject().getId(), destination.getSubjectId());
    }
}
