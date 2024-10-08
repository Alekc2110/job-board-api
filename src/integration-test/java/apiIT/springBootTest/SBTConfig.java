package apiIT.springBootTest;

import my.test.faceit.JobBoardApiApplication;
import my.test.faceit.api.scheduler.JobFetchScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {JobBoardApiApplication.class})
public class SBTConfig {
    @Autowired
    private JobFetchScheduler scheduler;

    @Bean
    public CommandLineRunner runner() {
        return (args -> {
            System.out.println("runner started!");
            scheduler.scheduleJobFetching();
        });
    }
}
