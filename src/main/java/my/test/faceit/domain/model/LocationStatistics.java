package my.test.faceit.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationStatistics {
    private String city;
    private Long vacancyAmount;

    public LocationStatistics(String city, Long vacancyAmount) {
        this.city = city;
        this.vacancyAmount = vacancyAmount;
    }
}
