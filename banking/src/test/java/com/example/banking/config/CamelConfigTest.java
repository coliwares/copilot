package com.example.banking.config;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.banking.model.Account;
import com.example.banking.request.TransferRequest;
import com.example.banking.service.TransferService;

@CamelSpringBootTest
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CamelConfigTest {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Mock
    private TransferService transferService;

    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    @Test
    public void testTransfer_SuccessfulTransfer() throws Exception {
        // Arrange
        TransferRequest request = new TransferRequest("123456789", "987654321", 500.0);
        Account from = new Account(request.getFromAccount(), 1000);
        Account to = new Account(request.getToAccount(), 500);
        Mockito.when(
                transferService.transfer(Mockito.any(Account.class), Mockito.any(Account.class), Mockito.anyDouble()))
                .thenReturn(true);

        // Act
        Exchange exchange = new DefaultExchange(camelContext);
        exchange.getIn().setBody(request);
        Exchange response = producerTemplate.send("direct:transfer", exchange);


        // Assert
        Mockito.verify(transferService).transfer(Mockito.eq(from), Mockito.eq(to),
                Mockito.eq(request.getAmount()));
        Assertions.assertEquals(from, accountCaptor.getAllValues().get(0));
        Assertions.assertEquals(to, accountCaptor.getAllValues().get(1));
        Assertions.assertTrue(response.getMessage().getBody(Boolean.class));
    }

    @Test
    public void testTransfer_FailedTransfer() throws Exception {
        // Arrange
        TransferRequest request = new TransferRequest("123456789", "987654321", 1500.0);
        Account from = new Account(request.getFromAccount(), 1000);
        Account to = new Account(request.getToAccount(), 500);
        Mockito.when(
                transferService.transfer(Mockito.any(Account.class), Mockito.any(Account.class), Mockito.anyDouble()))
                .thenReturn(false);

        // Act
        Exchange exchange = new DefaultExchange(camelContext);
        exchange.getIn().setBody(request);
        Exchange response = producerTemplate.send("direct:transfer", exchange);

        // Assert
 //       Mockito.verify(transferService).transfer(accountCaptor.capture(), accountCaptor.capture(),
 //               Mockito.eq(request.getAmount()));
        Assertions.assertEquals(from, accountCaptor.getAllValues().get(0));
        Assertions.assertEquals(to, accountCaptor.getAllValues().get(1));
        Assertions.assertFalse(response.getMessage().getBody(Boolean.class));
    }
}