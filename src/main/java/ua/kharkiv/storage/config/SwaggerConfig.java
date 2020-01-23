package ua.kharkiv.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

/**
 * Configures the Swagger to this application.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.kharkiv.storage"))
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(Optional.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple catalog company API.")
                .description("This is simple REST-full " +
                        "application to contain data about companies.")
                .version("0.0.1")
                .license("NO DATA")
                .contact(new Contact(null, null, "victrdm0394@gmail.com"))
                .build();
    }
}
