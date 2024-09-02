package my.test.faceit.api.configuration;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiMappingConfig {
    @Bean("apiJobMapper")
    public ApiJobMapper apiJobMapper(){
        return Mappers.getMapper(ApiJobMapper.class);
    }

    @Bean("remoteResponseApiMapper")
    public RemoteResponseApiMapper remoteResponseApiMapper(){
        return Mappers.getMapper(RemoteResponseApiMapper.class);
    }
}
