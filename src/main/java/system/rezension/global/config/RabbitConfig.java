package system.rezension.global.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // 큐 생성
    @Bean
    public Queue questionQueue() {
        return new Queue("question-email-queue", true); // durable true
    }

    // 익스체인지 생성
    @Bean
    public TopicExchange questionExchange() {
        return new TopicExchange("question-email-exchange");
    }

    // 큐와 익스체인지 바인딩
    @Bean
    public Binding questionBinding(Queue questionQueue, TopicExchange questionExchange) {
        return BindingBuilder.bind(questionQueue).to(questionExchange).with("question.email");
    }

    // RabbitTemplate 설정
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}