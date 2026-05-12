package jmp.bank.api;

import jmp.dto.BankCard;
import jmp.dto.User;
import jmp.dto.BankCardType;

public interface Bank {
    BankCard createBankCard(User user, BankCardType cardType);

}
