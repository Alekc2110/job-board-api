package my.test.faceit.persistence.interfaces;

import my.test.faceit.domain.model.Job;
import my.test.faceit.domain.model.LocationStatistics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobRepository {
  Page<Job> findAll(Pageable pageable);
  List<Job> getTop10PopularJobs();
  List<LocationStatistics> getLocationStatistics();
  void saveAll(List<Job> list);
}
