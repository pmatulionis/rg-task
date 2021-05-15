package co.uk.realisticgames.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * SwaggerConfig class.
 *
 * @author Paulius Matulionis
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Realistic Games Task",
                        "Realistic Games Task API to manage historic data of the car industry provider",
                        "1.0.0",
                        "",
                        new Contact("Paulius Matulionis", "", "p.matulionis@gmail.com"),
                        "",
                        "",
                        new ArrayList<>()
                ));
    }
}
