package my.test.faceit.api.dto.remoteResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Meta {
    private int curPage;
    private int from;
    private String path;
    private int perPage;
    private int to;
    private String terms;
    private String info;
}
