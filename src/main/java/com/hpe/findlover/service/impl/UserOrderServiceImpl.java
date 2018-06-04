package com.hpe.findlover.service.impl;

import com.hpe.findlover.mapper.UserOrderMapper;
import com.hpe.findlover.model.UserOrder;
import com.hpe.findlover.service.UserOrderService;
import com.hpe.util.BaseTkMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserOrderServiceImpl extends BaseServiceImpl<UserOrder> implements UserOrderService {
    private Logger logger = LogManager.getLogger(UserOrderServiceImpl.class);
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Override
    public BaseTkMapper<UserOrder> getMapper() {
        return userOrderMapper;
    }
    @Override
    public List<UserOrder> selectByTime(Date startData, Date endData,Integer userId){
        Map<String,Object> params=new HashMap<>();
        params.put("startData",startData);
        params.put("endData",endData);
        params.put("userId",userId);
        List<UserOrder> userOrders=userOrderMapper.selectByTime(params);
        return userOrders;
    }
}
