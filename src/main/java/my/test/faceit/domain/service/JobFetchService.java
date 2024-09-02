package my.test.faceit.domain.service;

import lombok.RequiredArgsConstructor;
import my.test.faceit.domain.model.Job;
import my.test.faceit.persistence.interfaces.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobFetchService {

    private final JobRepository jobRepository;
    public void fetchAndSaveJobs(List<Job> list) {
        jobRepository.saveAll(list);
    }
}
