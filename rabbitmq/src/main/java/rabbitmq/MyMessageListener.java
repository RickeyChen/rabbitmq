package rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener {

    /*
    * Channel
    * amqp.coreMessage
    * messageing.Message
    *@Payload 注解参数就是msg body
    * */
    @RabbitListener(queues = "queue1")
    public void whenMessageCome(@Payload String messageStr){
        System.out.println(messageStr);
    }



}
