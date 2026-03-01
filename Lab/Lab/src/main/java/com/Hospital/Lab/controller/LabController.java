package com.Hospital.Lab.controller;

import com.Hospital.Lab.model.BloodReport;
import com.Hospital.Lab.model.UrineReport;
import com.Hospital.Lab.service.MessagingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabController {
    MessagingService messagingService;
    LabController(MessagingService messagingService){
        this.messagingService=messagingService;
    }
    @PostMapping("/postBloodReport")
    public ResponseEntity<String> sendReport(@RequestBody BloodReport bloodReport){
        String response=messagingService.send(bloodReport);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/postUrineReport")
    public ResponseEntity<String> sendReport(@RequestBody UrineReport urineReport){
        String response=messagingService.send(urineReport);
        return ResponseEntity.ok(response);
    }
}
