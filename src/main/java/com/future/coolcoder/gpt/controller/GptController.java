package com.future.coolcoder.gpt.controller;

import com.alibaba.fastjson.JSON;
import com.future.coolcoder.gpt.entity.GptRequest;
import com.future.coolcoder.gpt.entity.GptRequestMessages;
import com.future.coolcoder.gpt.entity.GptResponse;
import com.future.coolcoder.gpt.entity.UserRequest;
import com.future.coolcoder.gpt.util.HttpUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

/**
 * @author Jon
 * @Description TODO
 * @ClassName GptController
 * @data 2023/8/24 13:31
 * @Version 1.0
 */
@RestController
public class GptController {

    @PostMapping(value = "askGpt")
    public GptResponse askGpt(@RequestBody UserRequest userRequest) throws IOException {

        System.out.println("userRequest = " + userRequest);
        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer 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");
        System.out.println("JSON.toJSONString(userRequest) = " + JSON.toJSONString(userRequest));


        GptRequest gptRequest = new GptRequest();
        gptRequest.setModel("gpt-3.5-turbo");
        GptRequestMessages gptRequestMessages = new GptRequestMessages();
        gptRequestMessages.setContent(userRequest.getContent());
        gptRequestMessages.setRole("user");
        List<GptRequestMessages> messagesList = new ArrayList<>();
        messagesList.add(gptRequestMessages);
        gptRequest.setMessages(messagesList);
        System.out.println("gptRequest = " + gptRequest);
        String askGptResult = HttpUtils.doPost("https://api.openai.com/v1/chat/completions", JSON.toJSONString(gptRequest), "json",header );
        System.out.println("askGptResult = " + askGptResult);
        GptResponse gptResponse = GptResponse.fromJson(askGptResult);
        return gptResponse;
    }



}
