package com.tomato.mycode.Shiro;

import com.tomato.mycode.dao.AppleDao;
import com.tomato.mycode.service.AppleServiceImpl;
import com.tomato.mycode.service.ApppleService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class AuthRealm extends AuthorizingRealm {
    Map<String, String> userMap = new HashMap<>(16);

    {
        //加载数据
        //userMap.put("jixw", "me");
//        AppleServiceImpl apppleService = new AppleServiceImpl();
//        List<Map<String, String>> listUserMap = apppleService.loadUserInfo();
//        if(!listUserMap.isEmpty()){
//            for( Map<String, String> mapTemp: listUserMap){
//                userMap.putAll(mapTemp);
//            }
//        }
        super.setName("authRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //授权
        String userName = (String) principalCollection.getPrimaryPrincipal();
        // 从数据库或者缓存中获取角色数据
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }


    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)  {
        //认证
        // 1. 从主体传过来的认证信息中，获得用户名
        String userName = (String) authenticationToken.getPrincipal();

        // 2. 通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, "authRealm");
        return authenticationInfo;
    }

    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }
}
