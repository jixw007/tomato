package com.tomato.mycode.service;

import com.tomato.mycode.entity.Apple;
import com.tomato.mycode.untils.dataroute.RouteSource;

import java.util.List;
import java.util.Map;

public interface ApppleService {
    @RouteSource("db1")
    Apple getAppleById(Long appleId);

    @RouteSource("db2")
    Apple getApple2ById(Long appleId);

    @RouteSource("db1")
    List<Map<String, String>> loadUserInfo();
}
