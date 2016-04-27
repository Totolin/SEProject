package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.SectionDao;
import ro.ucv.ace.model.Section;

import java.util.Optional;

/**
 * This class implements SectionDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class SectionDaoImpl extends JpaDaoImpl<Section, String> implements SectionDao {

    @Override
    public Optional<Section> existenceCondition(Section section) {
        return streamAll()
                .where(s -> s.getId().equals(section.getId()))
                .findAny();
    }
}
