package jmp.service.api;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;

import java.util.Optional;
import java.util.List;

public interface Service {
    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String bankCardNumber);

    List<User> getAllUsers();
}
