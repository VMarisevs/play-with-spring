package com.vm.playwspring.main;

import com.vm.playwspring.common.TransactionalExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.vm.playwspring"})
public class PlaySpringDIConfiguration {

    @Bean
    public TransactionalExecutor transactionalExecutor() {
        return new TransactionalExecutor();
    }
}
