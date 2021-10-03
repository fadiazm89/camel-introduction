package com.workshops.introduction;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:hello?period={{timer.period}}").routeId("hello")
                .transform().method("myBean", "saySomething")
          //TODO: Set a header in the message with value "java" and print into a new log
                .log("{{camel.springboot.name}}")
          //TODO: add to log component the feature to log all headers and properties
                .to("log:foo");


         //TODO: add a new route that reads files from data/inbox and move into data/outbox
    }

}
