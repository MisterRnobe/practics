package ru.nikitamedvedev.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.nikitamedvedev.domain.Customer;
import ru.nikitamedvedev.domain.Discount;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

    @Mock
    private DiscountCalculator calculator;
    @InjectMocks
    private DiscountService service;

    @Before
    public void setUp() throws Exception {
        when(calculator.calculate(anyInt(), anyInt())).thenReturn(10d);
    }

    @Test
    public void shouldNotApplyForAgeLessThan14() {
        //given
        Customer application = Customer.builder()
                .age(10)
                .build();
        //when
        Discount discount = service.calculateFor(application);
        //then
        assertFalse(discount.getHasDiscount());

    }

    @Test
    public void shouldNotApplyForMaleIfRatioIsLessThan100() {
        Customer application = Customer.builder()
                .age(20)
                .sex(true)
                .spentMoney(1000)
                .build();

        Discount discount = service.calculateFor(application);

        assertFalse(discount.getHasDiscount());
    }

    @Test
    public void shouldNotApplyForFemaleIfRatioIsLessThan80() {
        Customer application = Customer.builder()
                .age(50)
                .sex(false)
                .spentMoney(1000)
                .build();

        Discount discount = service.calculateFor(application);

        assertFalse(discount.getHasDiscount());
    }

    @Test
    public void shouldCalculate() {
        Customer customer = Customer.builder()
                .age(50)
                .sex(false)
                .spentMoney(1000)
                .joinedYearsAgo(1)
                .build();

        Discount discount = service.calculateFor(customer);

        assertEquals(10d, discount.getDiscount(), 0.1d);
    }

    @Test
    public void shouldNotHaveDiscountIfDiscountIsLessThan50() {
        Customer customer = Customer.builder()
                .age(50)
                .sex(false)
                .spentMoney(1000)
                .joinedYearsAgo(1)
                .purchase(20)
                .build();

        Discount discount = service.calculateFor(customer);

        assertFalse(discount.getHasDiscount());
    }

    @Test
    public void shouldHaveDiscountAndCalculateDiscountAmount() {
        Customer customer = Customer.builder()
                .age(50)
                .sex(false)
                .spentMoney(10000)
                .joinedYearsAgo(4)
                .purchase(10000)
                .build();

        Discount discount = service.calculateFor(customer);

        assertTrue(discount.getHasDiscount());
        assertEquals(10d, discount.getDiscount(), 0.1d);
    }
}