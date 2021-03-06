package com.project.support.config;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;
/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/6/4  10:35
 * @Version 1.0
 **/
@Configuration    // 配置注解，自动在本类上下文加载一些环境变量信息
@EnableSwagger2   // 使swagger2生效
@EnableWebMvc
@ComponentScan(basePackages = {"com.project.*.control"})  //需要扫描的包路径
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("business-api")
                .select()   // 选择那些路径和api会生成document
                //.apis(RequestHandlerSelectors.basePackage("com.project.*.control"))
                //.paths(paths())
                .apis(RequestHandlerSelectors.any())  // 对所有api进行监控
                .paths(PathSelectors.any())   // 对所有路径进行监控
                .build()
                .securityContexts(securityContexts());
    }

    private Predicate<String> paths() {
        return or(regex("/*.*"));
    }


    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .forPaths(PathSelectors.regex("/*.*"))
                        .build()
        );
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring 中使用Swagger2构建RESTful API")
                .termsOfServiceUrl("http://blog.csdn.net/yangshijin1988")
                .description("此API提供接口调用")
                .license("License Version 2.0")
                .licenseUrl("http://blog.csdn.net/yangshijin1988")
                .version("2.0").build();
    }
}
