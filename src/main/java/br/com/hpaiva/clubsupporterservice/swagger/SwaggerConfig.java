package br.com.hpaiva.clubsupporterservice.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private static final String RESOURCES = "br.com.hpaiva.clubsupporterservice";

    @Value("${info.build.version}")
    private String appBuildVersion;

    @Value("${info.app.description}")
    private String appDescription;

    @Bean
    public Docket apiDoc() {

        final ApiInfo build =
                new ApiInfoBuilder()
                        .title("Welcome to Club Supporter Service ")
                        .description(appDescription)
                        .version(appBuildVersion)
                        .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(RESOURCES))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(build);
    }

    @Override
    protected void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}