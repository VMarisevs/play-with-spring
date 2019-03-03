package com.vm.playwspring.controller;

import com.vm.playwspring.common.FutureResult;
import com.vm.playwspring.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import play.Logger;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Option;

import java.util.concurrent.CompletionStage;

@org.springframework.stereotype.Controller
public class HomeController extends Controller {

    @Autowired
    private HttpExecutionContext executionContext;

    @Autowired
    private SupportService supportService;

    public CompletionStage<Result> index() {
        Logger.info("Before FutureResult execution");
        return new FutureResult(executionContext) {

            @Override
            public Result execute() {
                Logger.info("index end-point called");
                return ok();
            }

        }.toPromise();
    }

    public CompletionStage<Result> verify(final Option<Integer> tenant) {
        Logger.info("Before FutureResult execution");
        return new FutureResult(executionContext) {

            @Override
            public Result execute() {

                if (tenant.isDefined()) {
                    Logger.info("Verify end-point request came from {} tenant", tenant.get());
                } else {
                    Logger.info("Verify end-point request with no tenant");
                }

                if (Logger.isDebugEnabled()) {
                    Logger.debug("All request headers \n {}", request().getHeaders().toMap());
                }

                supportService.verifyDatabaseConnection();

                return ok();
            }
        }.toPromise();
    }
}
