package com.hpe.findlover.service;

import com.hpe.findlover.model.UserOrder;

import java.util.Date;
import java.util.List;

public interface UserOrderService extends BaseService<UserOrder>{
    List<UserOrder> selectByTime(Date startData,Date endData,Integer userId);
}
