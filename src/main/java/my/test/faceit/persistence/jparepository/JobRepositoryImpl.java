package my.test.faceit.persistence.jparepository;

import my.test.faceit.domain.model.Job;
import my.test.faceit.domain.model.LocationStatistics;
import my.test.faceit.persistence.configuration.JpaJobMapper;
import my.test.faceit.persistence.entity.JobEntity;
import my.test.faceit.persistence.interfaces.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobRepositoryImpl implements JobRepository {

    private final JpaJobRepository repository;
    private final JpaJobMapper mapper;

    public JobRepositoryImpl(JpaJobRepository repository, @Qualifier("jpaJobMapper") JpaJobMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public Page<Job> findAll(Pageable pageable) {
        Page<JobEntity> entityPage = repository.findAll(pageable);
        List<Job> jobList = mapper.entityToModel(entityPage.getContent());
        return new PageImpl<>(jobList, pageable, entityPage.getTotalElements());
    }
    @Override
    public List<LocationStatistics> getLocationStatistics() {
        return repository.getLocationStatistics();
    }

    @Override
    public void saveAll(List<Job> list) {
        repository.saveAll(mapper.modelToEntity(list));
    }

    @Override
    public List<Job> getTop10PopularJobs() {
        return mapper.entityToModel(repository.findTop10PopularByTags());
    }
}
