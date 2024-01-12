package be.tftic.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("appName")
    public String applicationName(){
        return "demo spring boot";
    }

    @Bean
    public String audience(){
        return "TFTIC - Java cybersécu";
    }

}
