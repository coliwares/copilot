package com.example.copilot.camel_ms_banking_a.routes.a;

import java.time.LocalTime;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

//@Component
public class TimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessorComponent simpleLoggingProcessorComponent;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer")
                // .transform().constant("My message")
                // .transform().constant("TIme now is " + LocalTime.now())
                .log("${body}")
                .transform().constant("My constant message")
                .log("${body}")
                .bean(getCurrentTimeBean)
                .log("${body}")
                .process(new SimpleLoggingProcessor())
                .bean(simpleLoggingProcessorComponent)
                .to("log:first-timer");
    }

}

@Component
class GetCurrentTimeBean {

    public String getCurrentTime() {
        return "Time now is " + LocalTime.now();
    }

}

@Component
class SimpleLoggingProcessorComponent {
    private Logger logger = (Logger) LoggerFactory.getLogger(SimpleLoggingProcessorComponent.class);

    public void process(String message) {
        logger.info("SimpleLoggingProcessorComponent {}", message);
    }

}

class SimpleLoggingProcessor implements org.apache.camel.Processor {
    private Logger logger = (Logger) LoggerFactory.getLogger(SimpleLoggingProcessor.class);

    @Override
    public void process(org.apache.camel.Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }

}