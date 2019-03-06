package ru.nikitamedvedev.service;

import lombok.RequiredArgsConstructor;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;
import ru.nikitamedvedev.domain.Customer;
import ru.nikitamedvedev.domain.Discount;

@Component
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountCalculator calculator;

    public Discount calculateFor(Customer customer) {
        Discount discount = Discount.builder()
                .hasDiscount(true)
                .build();
        try {
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");

            kSession.setGlobal("calculator", calculator);
            kSession.insert(customer);
            kSession.insert(discount);
            kSession.fireAllRules();

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return discount;
    }
}
