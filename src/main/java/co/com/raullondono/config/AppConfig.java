package co.com.raullondono.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "co.com.raullondono")
@PropertySource("classpath:application.properties")
public class AppConfig {


}
