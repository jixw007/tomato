package com.tomato.mycode.controller;

import com.alibaba.fastjson.JSON;
import com.tomato.mycode.entity.Apple;
import com.tomato.mycode.dao.AppleDao;
import com.tomato.mycode.service.ApppleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    public ApppleService apppleService;

    @RequestMapping("/index")
    public String index() {
        System.out.println("hello my name is HelloController-index,thread_id=" + Thread.currentThread().getId() + " ! ");
        return "index";
    }

    @RequestMapping("/get_apple1")
    public ResponseEntity getApple1() {
        System.out.println("hello my name is apple1,thread_id=" + Thread.currentThread().getId() + " ! ");
        Apple apple = apppleService.getAppleById(11L);

        System.out.println("hello my name is apple1,apple=" + JSON.toJSONString(apple) + " ! ");
        HttpStatus status = HttpStatus.OK;
        String body = JSON.toJSONString(apple);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(body, httpHeaders, status);
    }

    @RequestMapping("/get_apple2")
    public ResponseEntity getApple2() {
        System.out.println("hello my name is apple2,thread_id=" + Thread.currentThread().getId() + " ! ");
        Apple apple = apppleService.getApple2ById(44L);

        System.out.println("hello my name is apple2,apple=" + JSON.toJSONString(apple) + " ! ");
        HttpStatus status = HttpStatus.OK;
        String body = JSON.toJSONString(apple);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(body, httpHeaders, status);
    }
}
