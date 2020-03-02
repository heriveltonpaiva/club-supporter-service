package br.com.hpaiva.clubsupporterservice.queue;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQPProperties {

    private String queueName;
    private String exchangeName;
    private String routingKey;

}
