package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.subject.SaveProfessorSubjectDto;
import ro.ucv.ace.model.ProfessorSubject;

/**
 * Created by Geo on 28.05.2016.
 */
public class SaveProfessorSubjectMap extends PropertyMap<SaveProfessorSubjectDto, ProfessorSubject> {

    @Override
    protected void configure() {
        map().setId(0);
        map().getGroup().setId(source.getGroupId());
        map().getProfessor().setId(source.getProfessorId());
        map().getSubject().setId(source.getSubjectId());
        map().setEvaluationMethod(source.getEvaluationMethod());
    }
}
