package com.tomato.mycode.controller;

import com.alibaba.fastjson.JSON;
import com.tomato.mycode.Shiro.AuthServiceImpl;
import com.tomato.mycode.entity.Apple;
import com.tomato.mycode.service.ApppleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ResponseEntity getApple1(HttpServletRequest request) {
        //http://localhost:8094/get_apple1
        HttpSession session = request.getSession();
        session.setAttribute("apple1","jixw");
        System.out.println("hello my name is apple1,session id=" + session.getId() + " ! ");

        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            System.out.println("hello my name is apple1,cookies is null ! ");
        }

        System.out.println("hello my name is apple1,thread_id=" + Thread.currentThread().getId() + " ! ");
        Apple apple = apppleService.getAppleById(11L);

        System.out.println("hello my name is apple1,apple=" + JSON.toJSONString(apple) + " ! ");
        HttpStatus status = HttpStatus.OK;
        String body = JSON.toJSONString(apple);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(body, httpHeaders, status);
    }

    @ResponseBody
    @RequestMapping("/get_apple2")
    public String getApple2(HttpServletRequest request, HttpServletResponse response) {
        //http://localhost:8094/get_apple2?account=jixw&password=me&appleId=222
        //HttpSession session = request.getSession();
        //System.out.println("hello my name is apple2,session id=" + session.getId() + " ! ");

        //coookie
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            System.out.println("apple2: cookies is null ! ");
        }else{
            System.out.println("apple2: have a cookie  ! ");
            for( Cookie cookie : cookies){
                System.out.println("--- cookie.name="+cookie.getName()+",cookie.value="+cookie.getValue());
            }
        }

        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String appleId=request.getParameter("appleId");
        logger.info("apple2: account={},password={},appleId={}", account, password, appleId);

        //登录验证
        AuthServiceImpl authServiceImpl = new AuthServiceImpl();
        authServiceImpl.Longin(account, password);

        Apple apple = apppleService.getApple2ById(Long.parseLong(appleId));

        System.out.println("apple2: appleInfo=" + JSON.toJSONString(apple) + " ! ");

        //设置cookie
        Cookie cookie_username = new Cookie("get_apple2", "jixw11");
        cookie_username.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(cookie_username);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        String body = JSON.toJSONString(apple);
        return body;
    }
}
