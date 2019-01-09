package by.home.fileSorter;

import by.home.fileSorter.config.AppConfig;
import by.home.fileSorter.service.SorterRunnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * SorterRunner class
 */
@Slf4j
public class SorterRunner {
    /**
     * SorterRunner method with which it all begin
     *
     * @param args - arguments which send by commend line at startup
     */
    public static void main(String[] args) {
        log.info("Application start");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(SorterRunnerService.class).runSorter();
    }
}
