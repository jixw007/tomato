package com.tomato.mycode.dao;

import com.tomato.mycode.Apple;
import com.tomato.mycode.mapper.AppleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppleDao {
    @Autowired
    public AppleMapper appleMapper;

    public Apple queryAppleInfo(Long appleId) {
        return appleMapper.queryApple(appleId);
    }
}
