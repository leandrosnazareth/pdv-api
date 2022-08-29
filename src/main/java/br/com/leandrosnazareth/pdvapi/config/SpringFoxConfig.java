package br.com.leandrosnazareth.pdvapi.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {

    public static final String PRODUCT_TAG = "Product";
    public static final String SALE_TAG = "Sale";
    public static final String PAYMENT_TAG = "Payment";
    public static final String USER_TAG = "User";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    // Adicionamos ApiKey para incluir o JWT como um cabeçalho de autorização:
    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.leandrosnazareth.pdvapi"))
                .paths(regex("/api/pdv.*"))
                .build()
                .tags(new Tag(PRODUCT_TAG, "Api REST PDV tag Product"))
                .tags(new Tag(SALE_TAG, "Api REST PDV tag Sale"))
                .tags(new Tag(PAYMENT_TAG, "Api REST Payment"))
                .tags(new Tag(USER_TAG, "Api REST User"))
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "PDV API REST",
                "API REST sistema de vendas em PDV.",
                "1.0",
                "Terms of Service",
                new Contact("Leandro Nazareth", "https://www.linkedin.com/in/leandrosnazareth",
                        "leandrosnazareth@gmail.com"),
                "GNU GENERAL PUBLIC LICENSE",
                "https://www.gnu.org/licenses/gpl-3.0.pt-br.html", Collections.emptyList());
    }

    // Em seguida, vamos configurar o JWT SecurityContext global AuthorizationScope
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}