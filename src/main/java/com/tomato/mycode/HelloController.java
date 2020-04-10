package com.tomato.mycode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/index")
    public String index(){
        System.out.println("hello my name is HelloController-index,thread_id="+Thread.currentThread().getId()+" ! ");
        return "index";
    }
}
