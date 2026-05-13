module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;

    exports jmp.cloud.bank.impl;

    provides jmp.bank.api.Bank with
            jmp.cloud.bank.impl.RetailBank,
            jmp.cloud.bank.impl.CentralBank,
            jmp.cloud.bank.impl.InvestmentBank; // task 24
}
