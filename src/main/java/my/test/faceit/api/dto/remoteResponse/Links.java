package my.test.faceit.api.dto.remoteResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Links {
   private String first;
   private String last;
   private String prev;
   private String next;
}
