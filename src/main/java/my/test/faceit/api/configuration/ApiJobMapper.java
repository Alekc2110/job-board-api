package my.test.faceit.api.configuration;

import my.test.faceit.api.dto.JobDto;
import my.test.faceit.domain.model.Job;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApiJobMapper {
    JobDto modelToDto(Job job);
    List<JobDto> modelToDto(List<Job> job);
    Job DtoToModel(JobDto entity);
    List<Job> DtoToModel(List<JobDto> entity);


}