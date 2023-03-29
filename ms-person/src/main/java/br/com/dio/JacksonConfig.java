package br.com.dio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;

@Configuration
public class JacksonConfig {


  @Bean
  public ObjectMapper objectMapper() {

    var mapper = new ObjectMapper();
    mapper.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
    mapper.configure(UNWRAP_SINGLE_VALUE_ARRAYS, true);
    mapper.configure(ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);
    mapper.configure(FAIL_ON_IGNORED_PROPERTIES, true);
    mapper.configure(FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
    mapper.configure(FAIL_ON_NULL_CREATOR_PROPERTIES, true);

    return mapper;
  }
}
