package jmp.application;

import jmp.bank.api.Bank;
import jmp.cloud.bank.impl.CentralBank;
import jmp.cloud.bank.impl.InvestmentBank;
import jmp.cloud.bank.impl.RetailBank;
import jmp.cloud.service.impl.ServiceImlp;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.User;
import jmp.service.api.Service;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        var user1 = new User("Ivan", "Ivanov", LocalDate.of(1990, 1, 1));
        var user2 = new User("Petr", "Petrov", LocalDate.of(1991, 5, 10));

        System.out.println("Users created:");
        System.out.println(user1);
        System.out.println(user2);

        Bank bank1 = new RetailBank();
        Bank bank2 = new InvestmentBank();
        Bank bank3 = new CentralBank();

        BankCard card1 = bank1.createBankCard(user1, BankCardType.CREDIT);
        BankCard card2 = bank2.createBankCard(user1, BankCardType.DEBIT);
        BankCard card3 = bank3.createBankCard(user2, BankCardType.CREDIT);

        Service service = new ServiceImlp();

        service.subscribe(card1);
        service.subscribe(card2);
        service.subscribe(card3);

        System.out.println();
        System.out.println("Cards created:");
        System.out.println(card1);
        System.out.println();
        System.out.println(card2);
        System.out.println();
        System.out.println(card3);

        var subscription1 = service.getSubscriptionByBankCardNumber(card1.getNumber());
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

    }
}