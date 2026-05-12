package jmp.service.api.exception;

public class SubscriptionNotFoundException extends RuntimeException {

    public SubscriptionNotFoundException(String bankCardNumber) {
        super("Subscription not found for bank card number: " + bankCardNumber);
    }
}
