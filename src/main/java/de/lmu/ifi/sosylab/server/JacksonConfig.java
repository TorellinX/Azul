package de.lmu.ifi.sosylab.server;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

  @Bean
  @Primary
  public ObjectMapper customJson(){
    return new Jackson2ObjectMapperBuilder()
        .indentOutput(true)
        .failOnEmptyBeans(false)
        .serializationInclusion(Include.NON_NULL)
        .modules(new Jdk8Module())
        .build();
  }
}
