module jmp.application {
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires jmp.dto;

    uses jmp.bank.api.Bank;
    uses jmp.service.api.Service;
}
