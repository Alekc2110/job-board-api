package my.test.faceit.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slug;
    @Column(name = "company_name")
    private String companyName;
    private String title;

    @Lob
    private String description;

    private Boolean remote;
    private String url;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    private List<String> jobTypes;

    private String location;
    @Column(name = "created_at")
    private Long createdAt;
}
