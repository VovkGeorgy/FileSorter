package by.home.fileSorter.config;

import by.home.fileSorter.service.factory.FileMoverFactory;
import by.home.fileSorter.service.factory.FileParserFactory;
import by.home.fileSorter.service.factory.FileValidatorFactory;
import by.home.fileSorter.service.factory.MessageBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class configure Spring Beans, include factories beans
 */
@Configuration
public class FactoryConfig {

    @Bean
    public FileMoverFactory fileMoverFactory() {
        return new FileMoverFactory();
    }

    @Bean
    public FileParserFactory fileParserFactory() {
        return new FileParserFactory();
    }

    @Bean
    public MessageBuilderFactory messageBuilderFactory() {
        return new MessageBuilderFactory();
    }

    @Bean
    public FileValidatorFactory fileValidatorFactory() {
        return new FileValidatorFactory();
    }
}
