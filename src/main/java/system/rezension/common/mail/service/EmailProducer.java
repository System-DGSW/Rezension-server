// Producer
package system.rezension.common.mail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import system.rezension.common.mail.dto.EmailMessage;

@Component
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public void sendEmail(String to, String subject, String content) {
        EmailMessage message = new EmailMessage(to, subject, content);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}