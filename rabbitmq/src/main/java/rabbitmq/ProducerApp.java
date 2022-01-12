package rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.charset.StandardCharsets;

public class ProducerApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitProduceConfig.class);

        final RabbitTemplate template = context.getBean(RabbitTemplate.class);

        final MessageProperties messageProperties = MessagePropertiesBuilder
                .newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setContentEncoding("utf-8")
                .setHeader("key1", "value1")
                .build();

        final Message message = MessageBuilder
                .withBody("hahaha".getBytes(StandardCharsets.UTF_8))
                .andProperties(messageProperties)
                .build();

        for (int i = 10; i > 0; i--){
            template.convertAndSend("exchange1", "routing1", message);
        }


        context.close();

    }

}
