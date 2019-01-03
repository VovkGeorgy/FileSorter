package by.home.fileSorter;

import by.home.fileSorter.config.AppConfig;
import by.home.fileSorter.service.SorterRunnerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * SorterRunner class
 */
public class SorterRunner {
    /**
     * SorterRunner method with which it all begin
     *
     * @param args - arguments which send by commend line at startup
     */
    public static void main(String[] args) {
        System.out.println("WORK!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(SorterRunnerService.class).runSorter();
    }
}
