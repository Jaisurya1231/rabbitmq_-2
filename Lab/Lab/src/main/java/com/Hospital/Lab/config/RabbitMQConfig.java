package com.Hospital.Lab.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.BindParam;

@Configuration
public class RabbitMQConfig {
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("doc_exchange",true,false);
    }
    @Bean
    Queue hydqueue(){
        return QueueBuilder.durable("hyd.queue")
                .ttl(60000)
                .deadLetterExchange("dlx")
                .deadLetterRoutingKey("deadLetter_key")
                .build();
    }
    @Bean
    Binding hydBinding(){
        return BindingBuilder.bind(hydqueue())
                .to(topicExchange())
                .with("hyd.*.*");
    }
    @Bean
    Queue amerpetqueue(){
        return QueueBuilder.durable("hyd.ameerpet.queue")
                .ttl(60000)
                .deadLetterExchange("dlx")
                .deadLetterRoutingKey("deadLetter_key")
                .build();
    }
    @Bean
    Binding ameerpetBinding(){
        return BindingBuilder.bind(amerpetqueue())
                .to(topicExchange())
                .with("hyd.ameerpet.*");
    }
    @Bean
    Queue madapurqueue(){
        return QueueBuilder.durable("hyd.madapurt.queue")
                .ttl(60000)
                .deadLetterExchange("dlx")
                .deadLetterRoutingKey("deadLetter_key")
                .build();
    }
    @Bean
    Binding madapurBinding(){
        return BindingBuilder.bind(madapurqueue())
                .to(topicExchange())
                .with("hyd.madapur.*");
    }
    @Bean
    Queue bloodTestqueue(){
        return QueueBuilder.durable("hyd.bloodTest.queue")
                .ttl(60000)
                .deadLetterExchange("dlx")
                .deadLetterRoutingKey("deadLetter_key")
                .build();
    }
    @Bean
    Binding bloodTestBinding(){
        return BindingBuilder.bind(bloodTestqueue())
                .to(topicExchange())
                .with("*.*.bloodtest");
    }
    @Bean
    Queue urinTestqueue(){
        return QueueBuilder.durable("hyd.urinTest.queue")
                .ttl(60000)
                .deadLetterExchange("dlx")
                .deadLetterRoutingKey("deadLetter_key")
                .build();
    }
    @Bean
    Binding urinTestBinding(){
        return BindingBuilder.bind(urinTestqueue())
                .to(topicExchange())
                .with("*.*.urintest");
    }
    @Bean
    DirectExchange deadLetterExchange(){
        return new DirectExchange("dlx",true,false);
    }
    @Bean
    Queue deadLetterQueue(){
        return new Queue("deadLetterQueue",true);
    }
    @Bean
    Binding dlxBinding(){
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with("deadLetter_key");
    }
    @Bean
    MessageConverter messageConverter(){
        return new JacksonJsonMessageConverter();
    }
}
