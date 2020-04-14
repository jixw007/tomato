package com.tomato.mycode.service;

import com.tomato.mycode.dao.AppleDao;
import com.tomato.mycode.entity.Apple;
import com.tomato.mycode.untils.dataroute.RouteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppleServiceImpl implements ApppleService {
    @Autowired
    public AppleDao appleDao;

    @Override
    @RouteSource("db1")
    public Apple getAppleById(Long appleId) {
        return appleDao.queryAppleInfo(appleId);
    }

    @Override
    @RouteSource("db2")
    public Apple getApple2ById(Long appleId) {
        return appleDao.queryAppleInfo(appleId);
    }
}
