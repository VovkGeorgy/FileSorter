package by.home.fileSorter.config;

import by.home.fileSorter.SorterRunner;
import by.home.fileSorter.service.ApplicationContextProvider;
import by.home.fileSorter.utils.PropertiesUtils;
import org.springframework.context.annotation.*;

/**
 * Class configure Spring Beans, include runner, properties, applicationContextProvider
 */
@Configuration
@ComponentScan("by.home.fileSorter")
@PropertySource("classpath:fileSorter.properties")
@Import({EntityConfig.class, FactoryConfig.class, FileServiceConfig.class, JsonServiceConfig.class, TxtServiceConfig.class})
public class AppConfig {

    @Bean
    public SorterRunner sorterRunner() {
        return new SorterRunner();
    }

    @Bean
    public PropertiesUtils propertiesUtils() {
        return new PropertiesUtils();
    }

    @Bean
    public ApplicationContextProvider applicationContextProvider() {
        return new ApplicationContextProvider();
    }

}
