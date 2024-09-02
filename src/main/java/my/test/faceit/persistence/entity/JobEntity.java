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
    @Column(unique = true)
    private String slug;
    @Column(name = "company_name")
    private String companyName;
    private String title;

    @Lob
    private String description;

    private Boolean remote;
    private String url;

    @ElementCollection
    @CollectionTable(
            name = "tags",
            joinColumns = @JoinColumn(name = "job_entity_id")
    )
    @Column(name = "tags")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(
            name = "job_types",
            joinColumns = @JoinColumn(name = "job_entity_id")
    )
    @Column(name = "job_types")
    private List<String> jobTypes;

    private String location;
    @Column(name = "created_at")
    private Long createdAt;
}
