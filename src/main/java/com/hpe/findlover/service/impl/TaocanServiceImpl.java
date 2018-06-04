package com.hpe.findlover.service.impl;

import com.hpe.findlover.mapper.TaocanMapper;
import com.hpe.findlover.model.SuccessStory;
import com.hpe.findlover.model.Taocan;
import com.hpe.findlover.service.TaocanService;
import com.hpe.util.BaseTkMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaocanServiceImpl extends BaseServiceImpl<Taocan> implements TaocanService{
    private Logger logger = LogManager.getLogger(TaocanServiceImpl.class);
    @Autowired
    private TaocanMapper taocanMapper;
    @Override
    public BaseTkMapper<Taocan> getMapper() {
        return taocanMapper;
    }
    @Override
    public List<Taocan> selectBySearchCondition(Map<String, Object> params){
        List<Taocan> taocans=taocanMapper.selectBySearchCondition(params);

        return taocans;
    }
}
