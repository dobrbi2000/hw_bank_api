module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;

    exports jmp.cloud.service.impl;

    provides jmp.service.api.Service with
            jmp.cloud.service.impl.ServiceImpl;

}
