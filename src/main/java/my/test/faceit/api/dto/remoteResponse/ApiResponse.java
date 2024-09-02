package my.test.faceit.api.dto.remoteResponse;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private List<JobResponse> data;
    private Links links;
    private Meta meta;
}
