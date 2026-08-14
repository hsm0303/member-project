package com.example.member.controller;

import com.example.member.domain.Member;
import com.example.member.dto.HelloResponse;
import com.example.member.dto.MemberCreateRequest;
import com.example.member.dto.MemberUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //웹 요청 받아 응답하는 Controller임
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring!";
    }

    @GetMapping("/hello-json")
    public HelloResponse helloResponse() {
        return new HelloResponse("Hello Spring", 1);
    }

    @GetMapping("/hello-name")
    public String helloName(@RequestParam String name) {
        return "안녕하세요, " + name + "님!";
    }

}
