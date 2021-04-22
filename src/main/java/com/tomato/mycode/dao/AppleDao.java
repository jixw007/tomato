package com.tomato.mycode.dao;

import com.tomato.mycode.entity.Apple;
import com.tomato.mycode.mapper.AppleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AppleDao {
    @Autowired
    public AppleMapper appleMapper;

    public Apple queryAppleInfo(Long appleId) {
        return appleMapper.queryApple(appleId);
    }

    public List<Map<String, String>> loadUserInfo() {
        return appleMapper.loadUserInfo();
    }
}
