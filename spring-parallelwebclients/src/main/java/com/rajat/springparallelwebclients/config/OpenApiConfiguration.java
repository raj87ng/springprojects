package com.rajat.springparallelwebclients.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customeOpenAPI() {
        return new OpenAPI()/*.components(new Components().addParameters("myGlobalHeader",new HeaderParameter()
                .required(true).name("My-Global-Header").description("My Global Header").schema(new StringSchema())))*/
                .info(new Info().title("Web Client Apis")
                        .version("V1")
                        .description("Web Client Mono/Flux examples")
                        .termsOfService("http://swagger.io/terms")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    /*@Bean
    public OpenApiCustomiser customGlobalHeaderOpenAPIcustom(){
        return openApi -> openApi.getPaths()
                .values()
                .stream()
                .flatMap(pathItem -> pathItem.readOperations()
                        .stream()).forEach(operation -> operation.addParametersItem(
                                new HeaderParameter().$ref("#/components/parameters/myGlobalHeader")
                ));
    }*/
}


