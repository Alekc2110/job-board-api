package apiIT.webMvcTest;

import my.test.faceit.api.configuration.ApiJobMapper;
import my.test.faceit.api.controller.JobController;
import my.test.faceit.domain.service.JobService;
import my.test.faceit.persistence.interfaces.JobRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ApiTestConfig {

    @Bean
    public JobRepository jobRepository(){
        return Mockito.mock(JobRepository.class);
    }

    @Bean
    public JobService jobService(JobRepository jobRepository){
        return new JobService(jobRepository);
    }

    @Bean
    public JobController jobController(JobService jobService, ApiJobMapper apiJobMapper){
        return new JobController(jobService, apiJobMapper);
    }

    @Bean
    public TestRestTemplate testRestTemplate(){
        return new TestRestTemplate();
    }
}
