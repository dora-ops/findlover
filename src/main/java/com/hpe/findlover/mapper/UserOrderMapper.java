package com.hpe.findlover.mapper;

import com.hpe.findlover.model.UserOrder;
import com.hpe.util.BaseTkMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserOrderMapper extends BaseTkMapper<UserOrder> {

    List<UserOrder> selectByTime(Map<String,Object> params);
}