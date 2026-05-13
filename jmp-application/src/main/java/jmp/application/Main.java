package jmp.application;

import jmp.bank.api.Bank;
// import jmp.cloud.bank.impl.CentralBank; //task 25
// import jmp.cloud.bank.impl.InvestmentBank;
// import jmp.cloud.bank.impl.RetailBank;
// import jmp.cloud.service.impl.ServiceImlp;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.User;
import jmp.service.api.Service;
import jmp.service.api.exception.SubscriptionNotFoundException;
import java.util.ServiceLoader;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        var user1 = new User("Ivan", "Ivanov", LocalDate.of(2020, 1, 1));
        var user2 = new User("Petr", "Petrov", LocalDate.of(1945, 5, 10));

        System.out.println("Users created:");
        System.out.println(user1);
        System.out.println(user2);

        // Bank bank1 = new RetailBank(); task25
        // Bank bank2 = new InvestmentBank();
        // Bank bank3 = new CentralBank();
        Bank bank1 = loadBank("RetailBank");
        Bank bank2 = loadBank("InvestmentBank");
        Bank bank3 = loadBank("CentralBank");

        BankCard card1 = bank1.createBankCard(user1, BankCardType.CREDIT);
        BankCard card11 = bank1.createBankCard(user1, BankCardType.CREDIT);
        BankCard card12 = bank1.createBankCard(user1, BankCardType.CREDIT);
        BankCard card2 = bank2.createBankCard(user1, BankCardType.DEBIT);
        BankCard card3 = bank3.createBankCard(user2, BankCardType.CREDIT);

        // Service service = new ServiceImlp();

        Service service = ServiceLoader.load(Service.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No Service implementation found"));

        service.subscribe(card1);
        service.subscribe(card2);
        service.subscribe(card3);
        service.subscribe(card11);
        service.subscribe(card12);

        System.out.println();
        System.out.println("Cards created:");
        System.out.println(card1);
        System.out.println();
        System.out.println(card2);
        System.out.println();
        System.out.println(card3);

        var subscription1 = service.getSubscriptionByBankCardNumber(card1.getNumber())
                .orElseThrow(() -> new SubscriptionNotFoundException(card1.getNumber())); // task 21

        var subscription2 = service.getSubscriptionByBankCardNumber(card2.getNumber());

        var subscription3 = service.getSubscriptionByBankCardNumber(card3.getNumber());

        System.out.println();
        System.out.println("Subscription for card " + card1.getNumber() + ":");
        System.out.println(subscription1);
        System.out.println();
        System.out.println("Subscription for card " + card2.getNumber() + ":");
        System.out.println(subscription2);
        System.out.println();
        System.out.println("Subscription for card " + card3.getNumber() + ":");
        System.out.println(subscription3);

        System.out.println();
        System.out.println("All users:");
        service.getAllUsers().forEach(System.out::println);

        System.out.println();
        System.out.println("Average users age:");
        System.out.println(service.getAverageUsersAge());

        System.out.println();
        boolean payable1 = Service.isPayableUser(user1);
        System.out.println("Is user payable: " + payable1);

        boolean payable2 = Service.isPayableUser(user2);
        System.out.println("Is user payable: " + payable2);

        var retailSubscriptions = service.getAllSubscriptionsByCondition(
                s -> s.getBankcardNumber().startsWith("RET"));

        System.out.println();
        System.out.println("subscriptions with RET prefix:"); // task 22
        retailSubscriptions.forEach(System.out::println);

    }

    private static Bank loadBank(String implBankName) {
        for (Bank bank : ServiceLoader.load(Bank.class)) {
            if (bank.getClass().getSimpleName().equals(implBankName)) {
                return bank;
            }
        }
        throw new IllegalStateException("Bank impl not found: " + implBankName);
    }
}