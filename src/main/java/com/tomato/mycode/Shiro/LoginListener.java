package com.tomato.mycode.Shiro;

import com.tomato.mycode.controller.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class LoginListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(LoginListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        logger.info("LoginListener.sessionCreated **** session.getId()={}", session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        logger.info("LoginListener.sessionDestroyed xxxx session.getId()={}", session.getId());
        synchronized (this) {
            session.getServletContext().setAttribute("jixw", null);
        }
        //todo
    }
}
