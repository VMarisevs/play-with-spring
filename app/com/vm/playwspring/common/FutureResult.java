package com.vm.playwspring.common;

import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

public abstract class FutureResult implements Supplier<Result> {

    private HttpExecutionContext executor;

    protected FutureResult(HttpExecutionContext executor) {
        this.executor = executor;
    }

    public abstract Result execute();

    public CompletionStage<Result> toPromise() {
        return CompletableFuture.supplyAsync(this, executor.current());
    }

    @Override
    public Result get() {
        return execute();
    }

}