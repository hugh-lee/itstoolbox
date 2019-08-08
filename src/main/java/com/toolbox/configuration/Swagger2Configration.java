package com.toolbox.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configration {

	@Bean
	public Docket createRestApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.trade.riskcontrol.controller"))
                .paths(PathSelectors.any())
                .build();


	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("RESTful APIs手册")
				.description("提供查询APIs相关信息")
				//.termsOfServiceUrl("http://blog.didispace.com/")
				.version("1.0")
				.build();
	}

}