package com.hpe.findlover.mapper;

import com.hpe.findlover.model.Taocan;
import com.hpe.util.BaseTkMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaocanMapper extends BaseTkMapper<Taocan> {
    List<Taocan> selectBySearchCondition(Map<String, Object> params);
}