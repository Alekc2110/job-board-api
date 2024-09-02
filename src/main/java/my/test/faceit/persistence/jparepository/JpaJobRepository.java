package my.test.faceit.persistence.jparepository;

import my.test.faceit.domain.model.LocationStatistics;
import my.test.faceit.persistence.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaJobRepository extends JpaRepository<JobEntity, Long> {
    @Query(value = """
            SELECT * FROM  job_entity je
                  INNER JOIN tags t2 on je.id = t2.job_entity_id
                  WHERE t2.tags IN (
                          SELECT t.tags FROM job_entity jb
                          INNER JOIN tags t on jb.id = t.job_entity_id
                          GROUP BY t.tags
                          ORDER BY count(*) desc
                          LIMIT 5)
                  LIMIT 10
            """, nativeQuery = true)
    List<JobEntity> findTop10PopularByTags();

    @Query("""
            SELECT NEW my.test.faceit.domain.model.LocationStatistics(je.location, count(je)) FROM
                       JobEntity je
                       GROUP BY je.location
                       ORDER BY count(je) DESC
            """)
    List<LocationStatistics> getLocationStatistics();
}
