package com.tomato.mycode.controller;

import com.alibaba.fastjson.JSON;
import com.tomato.mycode.Shiro.AuthServiceImpl;
import com.tomato.mycode.entity.Apple;
import com.tomato.mycode.service.ApppleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    public ApppleService apppleService;
    @Autowired
    public AuthServiceImpl authServiceImpl;

    @Autowired
    private RedisTemplate redisTemplate;

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
        //http://localhost:8094/get_apple2?account=jixw&password=me&appleId=44
        String body = null;

        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String appleId=request.getParameter("appleId");
        logger.info("apple2:paramter: account={},password={},appleId={}", account, password, appleId);

        //redis
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("shanghai", "shanghai", 20, TimeUnit.SECONDS); // 新增, 设置失效时间
        valueOperations.set("hebei", "shijiazhuang2"); // 存在的话就是修改
        Object hebei = valueOperations.get("hebei");

        //如果不是登录页面,就要从COOKIE获取用户名和密码
        if (account == null) {
            Cookie[] cookies = request.getCookies();
            if (null == cookies) {
                System.out.println("apple2: cookies is null ! ");
            } else {
                System.out.println("apple2: have a cookie  ! ");
                for (Cookie cookie : cookies) {
                    System.out.println("--- cookie.name=" + cookie.getName() + ",cookie.value=" + cookie.getValue());
                    if(cookie.getName().equals("account")){
                        account = cookie.getValue();
                    }else if(cookie.getName().equals("password")){
                        password =  cookie.getValue();
                    }
                }
            }
        }

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60);
        System.out.println("hello my name is apple2,session id =" + session.getId() + " ! ");

        if(!checkSession(account, session)){
            body = JSON.toJSONString("目前已登录!");
            return body;
        }

        //加载数据
        List<Map<String, String>> listUserInfo =  apppleService.loadUserInfo();

        //登录验证
        //authServiceImpl.Longin(account, password);

        Apple apple = apppleService.getApple2ById(Long.parseLong(appleId));

        System.out.println("apple2: appleInfo=" + JSON.toJSONString(apple) + " ! ");

        //设置cookie
        Cookie cookie1 = new Cookie("account", account);
        cookie1.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("password", password);
        cookie2.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(cookie2);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        body = JSON.toJSONString(apple);
        return body;
    }

    public static boolean checkSession(String account, HttpSession session) {
        logger.info("checkSession: ---account={}", account);
        String accountOfSession = (String) session.getServletContext().getAttribute(account);
        if (accountOfSession == null) {
            logger.info("checkSession: ---account={}, accountOfSession is null !", account);
            session.getServletContext().setAttribute(account, session.getId());
            return true;
        } else {
            String sessionId = session.getId();
            logger.info("checkSession: ---sessionId={}, accountOfSession={}!", sessionId, accountOfSession);
            if (sessionId.equals(accountOfSession)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
