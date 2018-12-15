package by.home.fileSorter.config;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.impl.ErrorMessageBuilder;
import by.home.fileSorter.service.impl.ExceptionMessageBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *  Class configure Spring Beans, include entity classes, and their builders
 */
@Configuration
public class EntityConfig {

    @Bean
    @Scope("prototype")
    public ExceptionMessage exceptionMessage() {
        return new ExceptionMessage();
    }

    @Bean
    public ExceptionMessageBuilder exceptionMessageBuilder() {
        return new ExceptionMessageBuilder();
    }

    @Bean
    @Scope("prototype")
    public ErrorMessage errorMessage() {
        return new ErrorMessage();
    }

    @Bean
    public ErrorMessageBuilder errorMessageBuilder() {
        return new ErrorMessageBuilder();
    }
}
