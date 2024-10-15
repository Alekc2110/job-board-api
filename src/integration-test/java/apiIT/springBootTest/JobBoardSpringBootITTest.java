package apiIT.springBootTest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import my.test.faceit.JobBoardApiApplication;
import my.test.faceit.api.dto.JobDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Integration E2E test, using SpringBootTest, using real tomcat server.
 * To test integration with remote call to endpoint to test all components of application
 */
@SpringBootTest(classes = {JobBoardApiApplication.class,
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testh2")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class JobBoardSpringBootITTest {

    @LocalServerPort
    private int port;
    @Autowired
    private RestTemplate restTemplate;


    @Test
    @DisplayName("Should return pages of all jobs")
    public void getAllJobsDefaultBehaviourTest() {
        //given
        UriComponents build = UriComponentsBuilder.fromUriString("http://localhost:" + port + "/api/jobs")
                .queryParam("page", 5)
                .queryParam("size", 2).build();

        //when
        ResponseEntity<CustomPageImpl<JobDto>> responseEntity = restTemplate.exchange(build.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        PageImpl<JobDto> page = responseEntity.getBody();

        //then
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertEquals(2, page.getContent().size());
        Assertions.assertEquals(5, page.getNumber());
    }

    static class CustomPageImpl<T> extends PageImpl<T> {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public CustomPageImpl(@JsonProperty("content") List<T> content, @JsonProperty("number") int number,
                              @JsonProperty("size") int size, @JsonProperty("totalElements") Long totalElements,
                              @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                              @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort,
                              @JsonProperty("numberOfElements") int numberOfElements) {
            super(content, PageRequest.of(number, size), totalElements);

        }
        public CustomPageImpl(List<T> content, Pageable pageable, long total) {
            super(content, pageable, total);
        }
        public CustomPageImpl(List<T> content) {
            super(content);
        }
        public CustomPageImpl(){
            super(new ArrayList<>());
        }
    }
}
