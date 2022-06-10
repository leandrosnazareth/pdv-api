package br.com.blsoft.pdvapi.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // .apis(RequestHandlerSelectors.basePackage("br.com.blsoft.pdvapi"))
                .apis(RequestHandlerSelectors.any())   
                // .paths(regex("/api.*"))
                .paths(PathSelectors.any())    
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "PDV API REST",
                "API REST sistema de vendas em PDV.",
                "1.0",
                "Terms of Service",
                new Contact("Leandro Nazareth", "www.linkedin.com/in/leandrosnazareth",
                        "leandrosnazareth@gmail.com"),
                "GNU GENERAL PUBLIC LICENSE",
                "https://www.gnu.org/licenses/gpl-3.0.pt-br.html", new ArrayList<VendorExtension>());
        return apiInfo;
    }
}