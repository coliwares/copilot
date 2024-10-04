package cl.colivares.kata.camel.process;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import cl.colivares.kata.camel.error.ErrorResponse;

@Component
public class ExceptionRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:exception")
            .routeId("exception")
            .process(exchange -> {
                String strCode = exchange.getMessage().getHeader("code", String.class);
                String strMessage = exchange.getMessage().getHeader("message", String.class);
                int intCode = strCode != null ? Integer.parseInt(strCode) : 409;
                Object exception = new ErrorResponse(intCode, strMessage, LocalDateTime.now());
                exchange.getIn().setHeader("CamelHttpResponseCode", constant(intCode));
                exchange.getIn().setHeader(Exchange.CONTENT_TYPE, constant("application/json"));
                exchange.getIn().setBody(exception);
            }).stop();
    }
    
}
