package ApiLayer.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**") // applies to all endpoints
            .allowedOrigins("*") // allows all origins — or "http://localhost:3000"
            .allowedMethods("*") // allows GET, POST, PUT, DELETE, etc.
            .allowedHeaders("*"); // allows all headers
    }
}

