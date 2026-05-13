package jmp.service.api;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface Service {
    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String bankCardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition); // task 22

    static long calculateAge(User user) {
        return ChronoUnit.YEARS.between(
                user.getBirthday(),
                LocalDate.now());
    }

    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .mapToLong(Service::calculateAge) // task 20
                .average()
                .orElse(0.0);
    }

    static boolean isPayableUser(User user) {
        if (user == null || user.getBirthday() == null) {
            return false;
        }
        return calculateAge(user) >= 18;
    }
}
