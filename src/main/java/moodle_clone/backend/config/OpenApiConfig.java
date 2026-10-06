package moodle_clone.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI moodleCloneApi() {
        return new OpenAPI()
                .info(new Info().title("Moodle Clone API").version("v1")
                        .description("Clone de Software internacional para Gestão Escolar ao Backend"));
    }
}