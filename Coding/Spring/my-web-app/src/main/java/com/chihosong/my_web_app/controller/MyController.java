package com.chihosong.my_web_app.controller;

import com.chihosong.my_web_app.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/sign-in")
    public String testPostRequest() {
        return myService.makePostRequestAndGetToken();
    }

    @GetMapping("/get-project")
    public String testGetRequest() {
        return myService.makeAuthenticatedGetRequest();
    }
}