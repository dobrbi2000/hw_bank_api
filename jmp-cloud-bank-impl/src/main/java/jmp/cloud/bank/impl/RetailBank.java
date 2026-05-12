package jmp.cloud.bank.impl;

import java.util.UUID;

import jmp.bank.api.Bank;
import jmp.dto.User;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.CreditBankCard;
import jmp.dto.DebitBankCard;

public class RetailBank implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {

        var cardNumber = "RET-" + UUID.randomUUID();
        if (cardType == BankCardType.CREDIT) {
            return new CreditBankCard(cardNumber, user);
        }
        return new DebitBankCard(cardNumber, user);
    }
}
