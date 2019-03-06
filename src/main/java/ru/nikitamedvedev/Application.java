package ru.nikitamedvedev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.nikitamedvedev.service.UserInterface;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@ComponentScan("ru.nikitamedvedev.service")
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Application.class);
        context.refresh();
        UserInterface userInterface = context.getBean(UserInterface.class);
        userInterface.run();
    }

    @Bean
    public InputStream inputStream() {
        return System.in;
    }

    @Bean
    public PrintStream outputStream() {
        return System.out;
    }
}
