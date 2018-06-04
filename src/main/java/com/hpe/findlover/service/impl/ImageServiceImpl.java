package com.hpe.findlover.service.impl;

import com.hpe.findlover.mapper.ImageMapper;
import com.hpe.findlover.mapper.TaocanMapper;
import com.hpe.findlover.model.Image;
import com.hpe.findlover.model.Taocan;
import com.hpe.findlover.service.ImageService;
import com.hpe.util.BaseTkMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl extends BaseServiceImpl<Image> implements ImageService{
    private Logger logger = LogManager.getLogger(ImageServiceImpl.class);
    @Autowired
    private ImageMapper imageMapper;
    @Override
    public BaseTkMapper<Image> getMapper() {
        return imageMapper;
    }
}
