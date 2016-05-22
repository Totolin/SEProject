package ro.ucv.ace.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ro.ucv.ace.mapper.*;

/**
 * This class is used by the IoC Spring container as a source for bean definitions.
 *
 * @author Georgian Vladutu
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"ro.ucv.ace"})
public class ServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new StudentGradeMap());
        modelMapper.addMappings(new SaveScheduleMap());
        modelMapper.addMappings(new SaveStudentMap());
        modelMapper.addMappings(new SaveProfessorMap());
        modelMapper.addMappings(new SaveStudentGradeMap());
        modelMapper.addMappings(new SingleEducationPlanMap());
        modelMapper.addMappings(new SavePersonMap());

        return modelMapper;
    }
}
