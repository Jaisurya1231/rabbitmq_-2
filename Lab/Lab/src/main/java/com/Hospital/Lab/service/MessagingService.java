package com.Hospital.Lab.service;

import com.Hospital.Lab.model.BloodReport;
import com.Hospital.Lab.model.UrineReport;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {
    RabbitTemplate template;
    MessagingService(RabbitTemplate template){
        this.template=template;
    }
    public String send(BloodReport bloodReport){
        if(bloodReport.getArea().equalsIgnoreCase("ameerpet"))
            template.convertAndSend("doc_exchange","hyd.ameerpet.bloodtest",bloodReport);
        else if(bloodReport.getArea().equalsIgnoreCase("madapur"))
            template.convertAndSend("doc_exchange","hyd.madapur.bloodtest",bloodReport);
        return "successfully sent";
    }
    public String send(UrineReport urineReport){
        if(urineReport.getArea().equalsIgnoreCase("ameerpet"))
            template.convertAndSend("doc_exchange","hyd.ameerpet.bloodtest",urineReport);
        else if(urineReport.getArea().equalsIgnoreCase("madapur"))
            template.convertAndSend("doc_exchange","hyd.madapur.bloodtest",urineReport);
        return "successfully sent";
    }
}
