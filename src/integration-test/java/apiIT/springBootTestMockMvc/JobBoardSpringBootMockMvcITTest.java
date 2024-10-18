package apiIT.springBootTestMockMvc;

import my.test.faceit.JobBoardApiApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = JobBoardApiApplication.class)
@ActiveProfiles("testh2")
@AutoConfigureMockMvc
@Sql(value = "/data-testh2.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class JobBoardSpringBootMockMvcITTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommandLineRunner commandLineRunner;

    @Test
    @DisplayName("Should return pages of all jobs default behavior")
    public void getAllJobsDefaultBehaviourTest() throws Exception {
        //when
        mockMvc.perform(get("/api/jobs")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
        //then
                .andExpect(content().contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.pageable.pageSize", is(5)))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[*].companyName")
                        .value(containsInAnyOrder("Company1","Company2","Company3","Company4","Company5")));
    }

    @Test
    @DisplayName("Should return pages of all jobs")
    public void getAllJobsTest() throws Exception {
        //when
        mockMvc.perform(get("/api/jobs")
                        .queryParam("size", String.valueOf(2))
                        .queryParam("page", String.valueOf(0))
                        .queryParam("sortBy", "slug")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                //then
                .andExpect(content().contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.pageable.pageSize", is(2)))
                .andExpect(jsonPath("$.content[*].slug")
                        .value(containsInAnyOrder("slug description5","slug description4")));
    }
}
