package com.iril.hp.test1.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value("${swagger.include.spring.endpoints}")
  private boolean includeSpringEndpoints;

  @Bean
  public Docket apis() {
    @SuppressWarnings("Guava")
    Predicate<RequestHandler> apis = includeSpringEndpoints ?
        RequestHandlerSelectors.any() : Predicates
        .not(RequestHandlerSelectors.basePackage("org.springframework"));

    return new Docket(DocumentationType.SWAGGER_2)
        .genericModelSubstitutes(java.util.Optional.class, com.google.common.base.Optional.class)
        .useDefaultResponseMessages(false)
        .produces(ImmutableSet.of(MediaType.APPLICATION_JSON_VALUE))
        .consumes(ImmutableSet.of(MediaType.APPLICATION_JSON_VALUE))
        .select()
        .apis(apis)
        .paths(PathSelectors.any())
        .build();
  }
}