package repositoryIT;

import my.test.faceit.api.configuration.ApiJobMapper;
import my.test.faceit.api.configuration.ApiMappingConfig;
import my.test.faceit.api.controller.JobController;
import my.test.faceit.api.scheduler.JobFetchScheduler;
import my.test.faceit.domain.service.JobService;
import my.test.faceit.persistence.configuration.JpaJobMapper;
import my.test.faceit.persistence.configuration.PersistenceMappingConfig;
import my.test.faceit.persistence.interfaces.JobRepository;
import my.test.faceit.persistence.jparepository.JobRepositoryImpl;
import my.test.faceit.persistence.jparepository.JpaJobRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(value = {ApiMappingConfig.class, PersistenceMappingConfig.class})
public class JobApiDataJpaITTestConfig {

    @Bean
    public JobFetchScheduler scheduler(){
        return Mockito.mock(JobFetchScheduler.class);
    }
    @Bean
    public JobService jobService(JobRepository jobRepository){
        return new JobService(jobRepository);
    }
    @Bean
    @Qualifier("jobRepository")
    public JobRepository jobRepository(JpaJobRepository jpaJobRepository, JpaJobMapper jpaJobMapper){
        return new JobRepositoryImpl(jpaJobRepository, jpaJobMapper);
    }
    @Bean
    public JobController jobController(JobService jobService, ApiJobMapper apiJobMapper){
        return new JobController(jobService, apiJobMapper);
    }
}
