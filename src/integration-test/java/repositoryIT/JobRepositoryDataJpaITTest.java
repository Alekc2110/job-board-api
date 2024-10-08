package repositoryIT;

import my.test.faceit.JobBoardApiApplication;
import my.test.faceit.api.controller.JobController;
import my.test.faceit.api.dto.JobDto;
import my.test.faceit.persistence.entity.JobEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = {JobBoardApiApplication.class, JobApiDataJpaITTestConfig.class})
@ActiveProfiles("testh2")
public class JobRepositoryDataJpaITTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private JobController jobController;


    @Test
    @DisplayName("should return list of JobDto sorted by companyName")
    @Transactional
    public void getAllJobsDefaultBehaviourTest() {
        //given
        List<JobEntity> jobEntities = getListOfJobEntities();
        jobEntities.forEach(entityManager::persist);
        entityManager.flush();
        //when
        Page<JobDto> result = jobController.getAllJobs(2, 3, "companyName");
        List<JobDto> resultList = result.get().toList();
        //then
        assertAll(()-> {
            assertEquals(9, result.getTotalElements());
            assertEquals(3, result.getSize());
            assertThat(resultList.get(0)).isInstanceOf(JobDto.class);
            assertThat(resultList.get(0)).hasFieldOrProperty("companyName");
            assertThat(resultList.get(0))
                    .isEqualTo(JobDto.builder().remote(true).title("economist").createdAt(12358999L).companyName("3Company").build());
        });
    }

    private List<JobEntity> getListOfJobEntities() {
        return List.of(JobEntity.builder().remote(true).title("economist").createdAt(12355666L).companyName("1Company").build(),
                JobEntity.builder().remote(false).title("economist").createdAt(12354777L).companyName("2Company").build(),
                JobEntity.builder().remote(true).title("economist").createdAt(12358999L).companyName("3Company").build(),
                JobEntity.builder().remote(true).title("economist").createdAt(12355555L).companyName("4Company").build(),
                JobEntity.builder().remote(false).title("economist").createdAt(123557722L).companyName("5Company").build(),
                JobEntity.builder().remote(true).title("economist").createdAt(12354722424L).companyName("6Company").build(),
                JobEntity.builder().remote(true).title("economist").createdAt(1235272242L).companyName("7Company").build(),
                JobEntity.builder().remote(false).title("economist").createdAt(12355242477L).companyName("8Company").build(),
                JobEntity.builder().remote(true).title("economist").createdAt(12357895244L).companyName("9Company").build());
    }
}
