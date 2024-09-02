package my.test.faceit.api.configuration;

import my.test.faceit.api.dto.remoteResponse.JobResponse;
import my.test.faceit.domain.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface RemoteResponseApiMapper {
    @Mappings({
            @Mapping(source = "jobResponse.company_name", target = "companyName"),
            @Mapping(source = "jobResponse.created_at", target = "createdAt")
    })
    List<Job> dtoToModel(List<JobResponse> jobResponse);

    @Mappings({
            @Mapping(source = "jobResponse.company_name", target = "companyName"),
            @Mapping(source = "jobResponse.created_at", target = "createdAt")
    })
    Job dtoToModel(JobResponse jobResponse);
}
