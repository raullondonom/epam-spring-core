package co.com.raullondono;

import co.com.raullondono.config.AppConfig;
import co.com.raullondono.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    @Autowired
    private static TrainingService trainingService;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        trainingService.selectTraining(1L);

        Scanner input = new Scanner(System.in);
    }

}
