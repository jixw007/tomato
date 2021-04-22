package com.tomato.mycode.Shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static final transient Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public String Longin(String account, String password) {
        if(account == null){
            log.info("AuthServiceImpl.Longin account is null");
            return "account is null";
        }

        if(password == null){
            log.info("AuthServiceImpl.Longin password is null");
            return "password is null";
        }

        AuthRealm authRealm = new AuthRealm();
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(authRealm);

        SecurityUtils.setSecurityManager(securityManager);

        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 获取对应的认证令牌
        AuthenticationToken token = new UsernamePasswordToken(account, password) ;
        // 做登录认证
        try {
            subject.login(token);
            log.info("AuthServiceImpl.Longin 登录成功....");
        } catch (UnknownAccountException e) {
            log.info("AuthServiceImpl.Longin 账号错误...");
        } catch (IncorrectCredentialsException e) {
            log.info("AuthServiceImpl.Longin 密码错误...");
        }

        //授权
        log.info("AuthServiceImpl.Longin isAuthenticated={}", subject.isAuthenticated());
        //角色
        subject.checkRole("admin");
        //权限
        subject.checkPermission("user:update");

        return "AuthServiceImpl.Longin sucess !";
    }
}
