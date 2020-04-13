package com.tomato.mycode.controller;

import com.alibaba.fastjson.JSON;
import com.tomato.mycode.entity.Apple;
import com.tomato.mycode.dao.AppleDao;
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
    public AppleDao appleDao;

    @RequestMapping("/index")
    public String index(){
        System.out.println("hello my name is HelloController-index,thread_id="+Thread.currentThread().getId()+" ! ");
        return "index";
    }

    @RequestMapping("/get_apple")
    public ResponseEntity getApple(){
        System.out.println("hello my name is apple,thread_id="+Thread.currentThread().getId()+" ! ");
        Apple apple = appleDao.queryAppleInfo(11L);

        System.out.println("hello my name is apple,apple="+ JSON.toJSONString(apple)+" ! ");
        HttpStatus status = HttpStatus.OK;
        String body = JSON.toJSONString(apple);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(body, httpHeaders, status);
    }
}
