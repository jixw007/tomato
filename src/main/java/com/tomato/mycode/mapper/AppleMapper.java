package com.tomato.mycode.mapper;

import com.tomato.mycode.entity.Apple;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppleMapper {
    Apple queryApple(@Param("appleId") Long appleId) throws DataAccessException;

    List<Map<String, String>> loadUserInfo() throws DataAccessException;
}
