package com.trecco.dzp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile({"dev", "test"})// 设置 dev test 环境开启 prod 环境就关闭了
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.trecco.dzp.controller"))//表示swagger需要扫描的包
				.paths(PathSelectors.any())//表示路径选择器匹配所有路径
				.build();
	}

	//swagger页面上的一些展示信息
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("Spring Boot 测试使用 Swagger2 构建RESTful API")
				//创建人
				.contact("dzp")
				//版本号
				.version("1.0")
				//描述
				.description("API 描述")
				.build();
	}
}