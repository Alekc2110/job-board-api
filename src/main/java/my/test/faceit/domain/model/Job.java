package my.test.faceit.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Job {
    private Long id;
    private String slug;
    private String companyName;
    private String title;
    private String description;
    private Boolean remote;
    private String url;
    private List<String> tags;
    private List<String> jobTypes;
    private String location;
    private Long createdAt;
}
