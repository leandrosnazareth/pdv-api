package br.com.leandrosnazareth.pdvapi.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Tag;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {

    public static final String PRODUCT_TAG = "Product";
    public static final String SALE_TAG = "Sale";
    public static final String PAYMENT_TAG = "Payment";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.leandrosnazareth.pdvapi"))
                .paths(regex("/api/pdv.*"))
                .build()
                .tags(new Tag(PRODUCT_TAG, "Api REST PDV tag Product"))
                .tags(new Tag(SALE_TAG, "Api REST PDV tag Sale"))
                .tags(new Tag(PAYMENT_TAG, "Api REST Payment"))
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
}