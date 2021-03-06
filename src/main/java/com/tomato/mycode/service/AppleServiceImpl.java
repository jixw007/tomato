package com.tomato.mycode.service;

import com.tomato.mycode.dao.AppleDao;
import com.tomato.mycode.entity.Apple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppleServiceImpl implements ApppleService {
    @Autowired
    public AppleDao appleDao;

    @Override
    public Apple getAppleById(Long appleId) {
        return appleDao.queryAppleInfo(appleId);
    }

    @Override
    public Apple getApple2ById(Long appleId) {
        return appleDao.queryAppleInfo(appleId);
    }

    @Override
    public List<Map<String, String>> loadUserInfo() {
        return appleDao.loadUserInfo();
    }
}
