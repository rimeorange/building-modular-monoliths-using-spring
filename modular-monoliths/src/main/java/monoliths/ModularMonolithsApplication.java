package monoliths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

import monoliths.orders.OrderContextConfiguration;
import monoliths.shipments.ShipmentContextConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
public class ModularMonolithsApplication {

    public static final String PROPS_CONFIG_NAME = "spring.config.name: application, orders, shipments";

    @Configuration
    @EnableAutoConfiguration
    static class WebContextConfiguration {

    }

    public static void main(String[] args) {
        SpringApplication  application = new SpringApplicationBuilder()
                .properties(PROPS_CONFIG_NAME)
                .sources(ModularMonolithsApplication.class).web(WebApplicationType.NONE)
                .child(ShipmentContextConfiguration.class).web(WebApplicationType.NONE)
                .sibling(OrderContextConfiguration.class).web(WebApplicationType.NONE)
                .sibling(WebContextConfiguration.class).web(WebApplicationType.SERVLET)
                .build();

        application.run(args);
    }

}