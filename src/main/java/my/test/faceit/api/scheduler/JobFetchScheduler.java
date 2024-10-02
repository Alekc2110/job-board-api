package my.test.faceit.api.scheduler;

import my.test.faceit.api.configuration.RemoteResponseApiMapper;
import my.test.faceit.api.dto.remoteResponse.ApiResponse;
import my.test.faceit.api.dto.remoteResponse.JobResponse;
import my.test.faceit.domain.model.Job;
import my.test.faceit.domain.service.JobService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class JobFetchScheduler {

    @Value("${remote.url}")
    private String remoteUrl;
    private final JobService jobService;
    private final RestTemplate restTemplate;
    private final RemoteResponseApiMapper mapper;

    public JobFetchScheduler(JobService jobService, RestTemplate restTemplate, @Qualifier("remoteResponseApiMapper") RemoteResponseApiMapper mapper) {
        this.jobService = jobService;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void scheduleJobFetching() {
        ApiResponse jobResponse = restTemplate.getForObject(remoteUrl, ApiResponse.class);

        if (jobResponse != null && jobResponse.getData() != null) {
            List<JobResponse> dataList = jobResponse.getData();

            List<Job> jobList = mapper.dtoToModel(dataList);
            jobService.fetchAndSaveJobs(jobList);
        }
    }
}
