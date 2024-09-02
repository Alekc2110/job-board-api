package my.test.faceit.api.dto.remoteResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JobResponse {
    private String slug;
    private String company_name;
    private String title;
    private String description;
    private Boolean remote;
    private String url;
    private List<String> tags;
    private List<String> jobTypes;
    private String location;
    private Long created_at;
}
