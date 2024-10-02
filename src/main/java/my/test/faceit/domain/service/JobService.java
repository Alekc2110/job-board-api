package my.test.faceit.domain.service;

import lombok.AllArgsConstructor;
import my.test.faceit.domain.model.Job;
import my.test.faceit.domain.model.LocationStatistics;
import my.test.faceit.persistence.interfaces.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JobService {
    private final JobRepository jobRepository;
    public Page<Job> getAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    public List<Job> getTop10PopularJobs() {
        return jobRepository.getTop10PopularJobs();
    }
    public List<LocationStatistics> getLocationStatistics() {
        return jobRepository.getLocationStatistics();
    }

    public void fetchAndSaveJobs(List<Job> list) {
        jobRepository.saveAll(list);
    }
}
