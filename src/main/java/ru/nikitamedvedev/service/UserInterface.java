package ru.nikitamedvedev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nikitamedvedev.domain.Customer;
import ru.nikitamedvedev.domain.Discount;

import java.io.PrintStream;

@Component
@RequiredArgsConstructor
public class UserInterface {

    private final DiscountService service;
    private final Reader input;
    private final PrintStream output;

    public void run() {
        boolean shouldContinue;
        do {
            Customer customer = readCustomer();
            Discount discount = service.calculateFor(customer);
            output.println("Has discount: " + discount.getHasDiscount());
            if (discount.getHasDiscount()) {
                output.println("Discount is: " + discount.getDiscount() + "%");
                output.println("Total discount is: " + discount.getDiscount() * customer.getPurchase() / 100);
            }
            shouldContinue = shouldContinue();
        } while (shouldContinue);

    }

    private boolean shouldContinue() {
        output.println("Continue (Y/N)?");
        char response = input.next().charAt(0);
        return response == 'Y';
    }

    private Customer readCustomer() {
        String temp = "M";
        output.println("Enter name:");
        String name = input.next();
        output.println("Enter age:");
        Integer age = input.nextInt();
        output.println("Enter sex (M/F):");
        temp = input.next();
        char sex = temp.charAt(0);
        output.println("How much money did the customer spend: ");
        Integer spentMoney = input.nextInt();
        output.println("How much years ago did the customer join us: ");
        Integer joined = input.nextInt();
        output.println("Enter good's price:");
        Integer purchase = input.nextInt();
        return Customer.builder()
                .fullName(name)
                .age(age)
                .sex(sex == 'M')
                .spentMoney(spentMoney)
                .joinedYearsAgo(joined)
                .purchase(purchase)
                .build();
    }

}
