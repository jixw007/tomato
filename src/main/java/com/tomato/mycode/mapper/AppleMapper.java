package com.tomato.mycode.mapper;

import com.tomato.mycode.Apple;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

@Mapper
public interface AppleMapper {
    Apple queryApple(@Param("appleId") Long appleId) throws DataAccessException;
}
