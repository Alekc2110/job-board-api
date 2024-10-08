package my.test.faceit.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class JobDto {
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
