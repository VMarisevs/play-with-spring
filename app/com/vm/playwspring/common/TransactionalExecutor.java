package com.vm.playwspring.common;

import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public class TransactionalExecutor {

    @Transactional
    public <T> T execute(Supplier<T> function) {
        return function.get();
    }

}
