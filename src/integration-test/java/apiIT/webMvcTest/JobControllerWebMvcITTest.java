package apiIT.webMvcTest;

import my.test.faceit.api.configuration.ApiMappingConfig;
import my.test.faceit.domain.model.Job;
import my.test.faceit.persistence.interfaces.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test, using WebMvcTest module, with mocking tomcat server.
 * To test integration from controller to repository(mock) layer
 */
@WebMvcTest
@ContextConfiguration(classes = {ApiTestConfig.class, ApiMappingConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class JobControllerWebMvcITTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JobRepository jobRepository;


    @Test
    @DisplayName("Should return pages of all jobs with default page size 1, jobs number 5")
    public void getAllJobsDefaultBehaviourTest() throws Exception {
        //given
        Page<Job> jobEntities = getPageOfJobEntitiesInMockDB();
        when(jobRepository.findAll(any())).thenReturn(jobEntities);

        //when
        mockMvc.perform(get("/api/jobs").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                //then
                .andExpect(content().contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.pageable.pageSize", is(5)));
    }


    private Page<Job> getPageOfJobEntitiesInMockDB() {
        List<Job> list = List.of(Job.builder().id(1L).remote(true).title("economist").createdAt(12355666L).companyName("Company1").build(),
                Job.builder().id(2L).remote(false).title("economist").createdAt(12354777L).companyName("Company2").build(),
                Job.builder().id(3L).remote(true).title("economist").createdAt(12358999L).companyName("Company3").build(),
                Job.builder().id(4L).remote(true).title("economist").createdAt(12355555L).companyName("Company4").build(),
                Job.builder().id(5L).remote(false).title("economist").createdAt(123557722L).companyName("Company5").build());
        return new PageImpl<>(list);
    }

}
