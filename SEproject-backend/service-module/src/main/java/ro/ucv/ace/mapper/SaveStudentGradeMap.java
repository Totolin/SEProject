package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.model.StudentSubject;

/**
 * Created by Geo on 22.05.2016.
 */
public class SaveStudentGradeMap extends PropertyMap<SaveStudentGradeDto, StudentSubject> {

    @Override
    protected void configure() {
        map().setId(0);
        map().setGrade(source.getGrade());
        map().getStudent().setId(source.getStudentId());
        map().getSubject().setId(source.getSubjectId());
    }
}
