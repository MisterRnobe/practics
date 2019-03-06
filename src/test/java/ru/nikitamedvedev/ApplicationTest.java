package ru.nikitamedvedev;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.nikitamedvedev.service.UserInterface;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    @Test
    public void shouldDisplayDiscount() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        UserInterface ui = context.getBean(UserInterface.class);

        ui.run();

        ByteArrayOutputStream stub = (ByteArrayOutputStream) context.getBean("stubOutputStream");
        String actual = new String(stub.toByteArray());
        String expected = new StringBuilder()
                .append("Enter name:\r\n")
                .append("Enter age:\r\n")
                .append("Enter sex (M/F):\r\n")
                .append("How much money did the customer spend: \r\n")
                .append("How much years ago did the customer join us: \r\n")
                .append("Enter good's price:\r\n")
                .append("Has discount: true\r\n")
                .append("Discount is: 17.59764009841553%\r\n")
                .append("Total discount is: 4399.410024603882\r\n")
                .append("Continue (Y/N)?\r\n")
                .toString();
        assertEquals(expected, actual);
    }
}