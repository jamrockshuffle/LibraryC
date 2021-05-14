package edu.bid.course.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    /**
     * Created by IntelliJ IDEA.
     * course.SwaggerConfiguration
     *
     * @Autor: Kolja
     * @Date: 05.05.2021
     * @Version: SwaggerConfiguration: 1.0
     */

    @Bean
    public OpenAPI authorOpenAPI(){

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Курсовий проєкт (Бібліотека)")
                                .version("1.0")
                                .contact(new Contact()
                                        .email("bidyuk.mykola@chnu.edu.ua")
                                        .name("Kolja")
                                        .url("http://localhost:1010"))
                );
    }

}
