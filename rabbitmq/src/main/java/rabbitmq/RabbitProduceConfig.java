package rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class RabbitProduceConfig {

    // 连接工厂
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(URI.create("amqp://admin:admin@192.168.159.131:5672"));
        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        cachingConnectionFactory.setPublisherReturns(true);
        return cachingConnectionFactory;
    }

    // RabbitTemplate
    @Bean
    @Autowired
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        return new RabbitTemplate(connectionFactory);
    }

    // RabbitAdmin
    @Bean
    @Autowired
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }


    // Queue
    @Bean
    public Queue queue(){
        return QueueBuilder.nonDurable("queue1").build();
    }

    @Bean
    public Exchange exchange(){
        return new DirectExchange("exchange1");
    }

    @Bean
    public Binding binding(){
        return new Binding("queue1", Binding.DestinationType.QUEUE, "exchange1", "routing1", null);
    }



}
