package my.test.faceit.persistence.configuration;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PersistenceMappingConfig {
    @Bean("jpaJobMapper")
    public JpaJobMapper jpaJobMapper(){
        return Mappers.getMapper(JpaJobMapper.class);
    }
}
