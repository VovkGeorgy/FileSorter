package by.home.fileSorter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class configure Spring Beans, include runner, properties, applicationContextProvider
 */
@Configuration
@ComponentScan("by.home.fileSorter.service")
@PropertySource("classpath:fileSorter.properties")
public class AppConfig {

}
