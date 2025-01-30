package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;

@CrossOrigin
@RestController
@RequestMapping("/zeebe")
public class ZeebeController {
    @Autowired
    private ZeebeClient zeebeClient;
    @PostMapping("/close/{correlationKey}")
    public void closeCase(@PathVariable String correlationKey) {
        zeebeClient.newPublishMessageCommand()
            .messageName("Message_clore_dossier")
            .correlationKey(correlationKey)
            .send()
            .join();
    }
}
