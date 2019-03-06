package ru.nikitamedvedev.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.nikitamedvedev.domain.Discount;

import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInterfaceTest {

    @Mock
    private DiscountService service;
    @Mock
    private Reader input;
    @Mock
    private PrintStream output;
    @InjectMocks
    private UserInterface userInterface;

    @Test
    public void shouldNotDisplayDiscountAmountIfDoesNotHaveDiscount() {
        when(input.next())
                .thenReturn("NAME")
                .thenReturn("M")
                .thenReturn("N");
        when(input.nextInt())
                .thenReturn(10)
                .thenReturn(10)
                .thenReturn(10)
                .thenReturn(1000);

        when(service.calculateFor(any()))
                .thenReturn(Discount.builder()
                        .hasDiscount(false)
                        .build());

        userInterface.run();

        verifyEnterMessages(1);
        verify(output).println("Has discount: false");
    }

    @Test
    public void shouldDisplayDiscountAmountIfHaveDiscount() {
        when(input.next())
                .thenReturn("NAME")
                .thenReturn("M")
                .thenReturn("N");
        when(input.nextInt())
                .thenReturn(10)
                .thenReturn(10)
                .thenReturn(10)
                .thenReturn(1000);

        when(service.calculateFor(any()))
                .thenReturn(Discount.builder()
                        .hasDiscount(true)
                        .discount(10d)
                        .build());

        userInterface.run();

        verifyEnterMessages(1);
        verify(output).println("Has discount: true");
        verify(output).println("Discount is: 10.0%");
        verify(output).println("Total discount is: 100.0");
    }

    @Test
    public void shouldNotDisplayDiscountThenDisplayDiscountForAnotherCustomer() {
        when(input.next())
                .thenReturn("NAME")
                .thenReturn("M")
                .thenReturn("Y")
                .thenReturn("NAME")
                .thenReturn("M")
                .thenReturn("N");
        when(input.nextInt())
                .thenReturn(10)
                .thenReturn(10)
                .thenReturn(10)
                .thenReturn(1000)
                .thenReturn(10)
                .thenReturn(10)
                .thenReturn(10)
                .thenReturn(1000);

        when(service.calculateFor(any()))
                .thenReturn(Discount.builder()
                        .hasDiscount(false)
                        .build())
                .thenReturn(Discount.builder()
                        .hasDiscount(true)
                        .discount(10d)
                        .build());


        userInterface.run();

        verifyEnterMessages(2);
        verify(output).println("Has discount: false");
        verify(output).println("Has discount: true");
        verify(output).println("Discount is: 10.0%");
        verify(output).println("Total discount is: 100.0");
    }

    private void verifyEnterMessages(int times) {
        verify(output, times(times)).println("Enter name:");
        verify(output, times(times)).println("Enter age:");
        verify(output, times(times)).println("Enter sex (M/F):");
        verify(output, times(times)).println("How much money did the customer spend: ");
        verify(output, times(times)).println("How much years ago did the customer join us: ");
        verify(output, times(times)).println("Enter good's price:");
    }
}