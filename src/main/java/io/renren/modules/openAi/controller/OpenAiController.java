package io.renren.modules.openAi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open")
public class OpenAiController {



    // 获取 access_token
    @GetMapping("/access_token")
    public void getOpenAiAccessToken() {

    }
}
