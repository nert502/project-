package ApiLayer.Configurations;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
            "Library Management API",
            "API για διαχείριση βιβλίων και συγγραφέων",
            "1.0",
            "Free to use",
            new Contact("Your Name", "http://your-site.com", "your@email.com"),
            "API License",
            "http://your-site.com",
            Collections.emptyList()
        );
    }
}

