package com.hpe.findlover.service.impl;

import com.hpe.findlover.StringBootMain;
import com.hpe.findlover.model.Taocan;
import com.hpe.findlover.model.UserBasic;
import com.hpe.findlover.model.UserOrder;
import com.hpe.findlover.service.TaocanService;
import com.hpe.findlover.service.UserOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StringBootMain.class)
@WebAppConfiguration
public class serviceTEST {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private TaocanService taocanService;
    @Test
    public void mapperTEST(){
        Taocan taocan=new Taocan();
        taocan.setActivityForm("a");
        taocanService.insertSelective(taocan);
        UserBasic userBasic=new UserBasic();
        userBasic.setId(100002);
        UserOrder order=new UserOrder();
//        order.setActivityTheme("求婚");
        order.setOrderId(1);
        order.setTaocanId(1);
//        order.setVersionId(1);
//        order.setVersionDisplayName("测试");
//
//        order.setUserId(userBasic.getId());
//        order.setState(0);
//        Calendar calendar=Calendar.getInstance();
//        order.setOrderTime(calendar.getTime());
//        Taocan taocan=taocanService.selectByPrimaryKey(order.getTaocanId());
//        if(order.getVersionId()==1){
//            order.setTotalPrice(taocan.getTaocanTypeAPrice());
//        }
//        if(order.getVersionId()==2){
//            order.setTotalPrice(taocan.getTaocanTypeBPrice());
//        }
//        if(order.getVersionId()==3){
//            order.setTotalPrice(taocan.getTaocanTypeCPrice());
//        }
//        order.setPrepayPrice(order.getTotalPrice()/2);
//        order.setPaidPrice(0);
//        order.setNotPayPrice(order.getTotalPrice());
        System.out.println(order);
        Boolean b =userOrderService.insert(order);
        System.out.println(b);
    }
    
}
