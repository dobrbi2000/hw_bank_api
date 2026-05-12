package jmp.cloud.service.impl;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;

import jmp.service.api.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;

import java.time.LocalDate;

public class ServiceImlp implements Service {

    private final Map<String, BankCard> bankCards = new HashMap<>();
    private final Map<String, Subscription> subscriptions = new HashMap<>();

    @Override
    public void subscribe(BankCard bankCard) {
        bankCards.put(bankCard.getNumber(), bankCard);

        var subscription = new Subscription(bankCard.getNumber(), LocalDate.now());

        subscriptions.put(bankCard.getNumber(), subscription);

    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String bankCardNumber) {
        return subscriptions.values()
                .stream()
                .filter(subscription -> subscription.getBankcardNumber().equals(bankCardNumber))
                .findFirst();

    }

    @Override
    public List<User> getAllUsers() {
        return bankCards.values()
                .stream()
                .map(BankCard::getUser)
                .distinct()
                .collect(Collectors.toList());
    }

}
