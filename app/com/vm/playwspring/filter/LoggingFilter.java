package com.vm.playwspring.filter;

import akka.stream.Materializer;
import kamon.Kamon;
import kamon.context.Context;
import kamon.context.Key;
import kamon.context.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import play.Logger;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;
import scala.compat.java8.JFunction;
import scala.compat.java8.OptionConverters;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class LoggingFilter extends Filter {

    @Autowired
    public LoggingFilter(Materializer mat) {
        super(mat);
    }

    @Override
    public CompletionStage<Result> apply(Function<Http.RequestHeader, CompletionStage<Result>> nextFilter,
        Http.RequestHeader requestHeader) {

        Optional<String> tenant = requestHeader.getHeaders().get("X-Tenant");


        Context context = Context.create()
            .withKey(Key.broadcastString("tenant"), OptionConverters.toScala(tenant));

        Storage.Scope scope = Kamon.storeContext(context);

        long startTime = System.currentTimeMillis();

        return Kamon.withContext(context,
            JFunction.func(() -> {

                return nextFilter.apply(requestHeader)
                    .thenApply(result -> {
                        Logger.info("context size {} ", context.entries().size());
                        long endTime = System.currentTimeMillis();
                        long requestTime = endTime - startTime;

                        Logger.info("{} {} took {}ms and returned {}",
                            requestHeader.method(), requestHeader.uri(), requestTime, result.status());

                        return result.withHeader("Request-Time", "" + requestTime);
                    });

            }));

    }
}