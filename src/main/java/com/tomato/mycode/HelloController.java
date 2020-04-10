package com.tomato.mycode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping("/index")
    public String index(){
        System.out.println("hello my name is HelloController-index,thread_id="+Thread.currentThread().getId()+" ! ");
        return "index";
    }

    @RequestMapping("/apple")
    public ModelAndView apple(){
        System.out.println("hello my name is apple,thread_id="+Thread.currentThread().getId()+" ! ");
        Apple apple = new Apple();
        apple.setColor("11");
        apple.setWeight(22);
        return new ModelAndView("appleinfo", "apple", apple);
    }
}
