package ro.ucv.ace.service;

import ro.ucv.ace.dto.group.PreviewGroupDto;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
public interface GroupService {

    List<PreviewGroupDto> getAllGroups();
}
