package my.test.faceit;

import my.test.faceit.api.scheduler.JobFetchScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobBoardApiApplication {
	@Autowired
	private JobFetchScheduler scheduler;

	public static void main(String[] args) {
		SpringApplication.run(JobBoardApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(){
		return (args -> {
			System.out.println("runner started!");
			scheduler.scheduleJobFetching();
		});
	}
}
