package ru.nikitamedvedev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
@ComponentScan("ru.nikitamedvedev.service")
public class TestConfiguration {

    @Bean
    public InputStream stubInput() {
        String text = new StringBuilder()
                .append("Вася\n\r")
                .append("40\n\r")
                .append("M\n\r")
                .append("10000\n\r")
                .append("3\n\r")
                .append("25000\n\r")
                .append("N\n\r")
                .toString();
        return new ByteArrayInputStream(text.getBytes());
    }

    @Bean
    public PrintStream stubOutput(OutputStream outputStream) {
        return new PrintStream(outputStream);
    }

    @Bean
    public OutputStream stubOutputStream() {
        return new ByteArrayOutputStream();
    }

}
