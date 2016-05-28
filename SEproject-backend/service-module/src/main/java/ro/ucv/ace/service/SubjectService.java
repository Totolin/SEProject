package ro.ucv.ace.service;

import ro.ucv.ace.dto.subject.PreviewSubjectDto;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
public interface SubjectService {

    List<PreviewSubjectDto> getAllSubjects();
}
