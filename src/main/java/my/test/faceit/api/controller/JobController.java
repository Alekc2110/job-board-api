package my.test.faceit.api.controller;

import my.test.faceit.api.configuration.ApiJobMapper;
import my.test.faceit.api.dto.JobDto;
import my.test.faceit.domain.model.Job;
import my.test.faceit.domain.model.LocationStatistics;
import my.test.faceit.domain.service.JobService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
public class JobController {

    private final JobService jobService;
    private final ApiJobMapper mapper;


    public JobController(JobService jobService, @Qualifier("apiJobMapper") ApiJobMapper mapper) {
        this.jobService = jobService;
        this.mapper = mapper;
    }

    /**
     * @param page - number of pages
     * @param size - number of page size
     * @param sortBy - String parameter for sorting
     * @return Page<JobDto> with all jobs, limited by page size and sorted
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<JobDto> getAllJobs(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

        Page<Job> jobPage = jobService.getAllJobs(pageable);
        List<JobDto> jobDtos = mapper.modelToDto(jobPage.getContent());
        return new PageImpl<>(jobDtos, pageable, jobPage.getTotalElements());
    }

    /**
     * @return Top 10 popular jobs by Company names
     */
    @GetMapping("/top10")
    public List<JobDto> getTop10PopularJobs() {
        return mapper.modelToDto(jobService.getTop10PopularJobs());
    }

    /**
     * @return List of LocationStatistics objects with the name of city
     * and amount of vacancies
     */
    @GetMapping("/statistics")
    public List<LocationStatistics> getLocationStatistics() {
        return jobService.getLocationStatistics();
    }
}
