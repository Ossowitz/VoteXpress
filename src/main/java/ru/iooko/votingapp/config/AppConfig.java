package ru.iooko.votingapp.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.iooko.votingapp.util.accessory.JsonUtil;

@Configuration
public class AppConfig {

    @Bean
    Module module() {
        return new Hibernate5Module();
    }

    @Autowired
    public void objectMapper(ObjectMapper mapper) {
        JsonUtil.setMapper(mapper);
    }
}
