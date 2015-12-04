package org.anyframe.cloud.auth.user;

import com.google.common.base.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
public class CommunityUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityUserApplication.class, args);
    }

    @Bean
    public Predicate<String> swaggerPaths() {
        return regex("/user.*|/sign.*|/log.*|/withdrawal.*|/cargo.*");
    }



    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("User API")
                .description("User API")
                .contact("Anyframe Cloud Edition")
                .license("Anyframe Cloud Ed.").version("1.0").build();
    }
}
