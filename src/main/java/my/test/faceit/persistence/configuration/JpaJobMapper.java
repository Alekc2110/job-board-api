package my.test.faceit.persistence.configuration;

import my.test.faceit.domain.model.Job;
import my.test.faceit.persistence.entity.JobEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JpaJobMapper {
    JobEntity modelToEntity(Job job);
    List<JobEntity> modelToEntity(List<Job> job);
    Job entityToModel(JobEntity entity);
    List<Job> entityToModel(List<JobEntity> entity);
}
