package com.example.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PROJECT_NAME: yeb-java
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/7/31 0031 22:16
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/employee/basic/hello02")
    public String hello02(){
        return "/employee/basic/hello02";
    }
    @GetMapping("/employee/advanced/hello03")
    public String hello03(){
        return "/employee/advanced/hello03";
    }

}
