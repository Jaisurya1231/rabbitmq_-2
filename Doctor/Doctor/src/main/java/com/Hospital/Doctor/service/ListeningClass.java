package com.Hospital.Doctor.service;

import com.Hospital.Doctor.model.BloodReport;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RabbitListener(queues = "hyd.queue")
public class ListeningClass {
    @RabbitHandler
    public void handleBloodReport(BloodReport bloodReport, Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            System.out.println("Received BloodReport id: " + bloodReport.getId());

            channel.basicAck(deliveryTag,false);

        } catch (Exception e) {

            Map<String, Object> headers = message.getMessageProperties().getHeaders();

            // 2️⃣ RabbitMQ adds x-death header for retries (not "x-header")
            List<Map<String, Object>> xDeath = (List<Map<String, Object>>) headers.get("x-death");

            long retryCount = 0;

            if (xDeath != null && !xDeath.isEmpty()) {
                // xDeath is a List of Maps. "count" is a Long in the Map
                retryCount = (Long) xDeath.get(0).get("count");
            }

            System.out.println("Retry count so far: " + retryCount);

            if (retryCount >= 5) {
                // Send to DLQ
                channel.basicReject(deliveryTag, false); // false = do not requeue
                System.out.println("Message sent to DLQ after " + retryCount + " retries");
            } else {
                // Send to retry queue via DLX
                channel.basicNack(deliveryTag, false, false); // false = go to DLX
                System.out.println("Retrying message, attempt " + (retryCount + 1));
            }
        }
    }
}
