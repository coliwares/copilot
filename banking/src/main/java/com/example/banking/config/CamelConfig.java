package com.example.banking.config;

import org.apache.camel.Configuration;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.banking.model.Account;
import com.example.banking.request.TransferRequest;
import com.example.banking.service.TransferService;

@Configuration
public class CamelConfig extends RouteBuilder {

    @Autowired
    private TransferService transferService;

    @Override
    public void configure() throws Exception {
        from("direct:transfer")
                .process(exchange -> {
                    TransferRequest request = exchange.getIn().getBody(TransferRequest.class);
                    Account from = new Account(request.getFromAccount(), 1000);
                    Account to = new Account(request.getToAccount(), 500);
                    boolean success = transferService.transfer(from, to, request.getAmount());
                    exchange.getIn().setBody(success);
                });
    }
}
