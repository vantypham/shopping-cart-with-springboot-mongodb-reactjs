package com.webstore.integration;

import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    public void sendEmail(String msg) {
        System.out.println("sending... " + msg);
    }
}
