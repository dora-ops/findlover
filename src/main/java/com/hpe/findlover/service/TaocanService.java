package com.hpe.findlover.service;

import com.hpe.findlover.model.Taocan;

import java.util.List;
import java.util.Map;

public interface TaocanService extends BaseService<Taocan>{
    List<Taocan> selectBySearchCondition(Map<String, Object> params);
}
