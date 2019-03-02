package com.b2w.starwars;

import com.b2w.starwars.api.StarWarsController;
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

import java.util.Collections;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * Configuracao do Swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private String version = "1.0";

    @Bean
    public Docket endpoints() {
        return
            new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("com.b2w.starwars")
                .select()
                .apis(basePackage("com.b2w.starwars.api"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Configura o swagger, acesse via <HOST>:<PORTA>/swagger-ui.html
     */
    @Bean
    public Docket api() {
        return
            new Docket(DocumentationType.SWAGGER_2) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage(StarWarsController.class.getPackage().getName())) //
                .paths(PathSelectors.any()) //
                .build()//
                .apiInfo(new ApiInfoBuilder().title("Desafio Técnico: B2W - Star Wars").description("API do desafio técnico proposto pela B2W").build());
    }

    private ApiInfo apiInfo() {
        return
            new ApiInfo(
                "Cliente REST API Star Wars",
                "API responsavel por disponibilizar servicos da API do Star Wars.",
                "Versao API " + version,
                "Termos de uso",
                new Contact("Leonardo Vieira", "", "leonardo.vieira2612@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }
}
