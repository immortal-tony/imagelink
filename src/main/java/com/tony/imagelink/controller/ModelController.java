package com.tony.imagelink.controller;

import com.tony.imagelink.mapper.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Description TODO
 * @Author hzf
 * @Date 2021/2/28 14:49
 */
@RestController
@RequestMapping(value = "model")
@Slf4j
public class ModelController {
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/slelctHotQueryModels")
    public Object slelctHotQueryModels(@RequestParam("modelId") Integer modelId, @RequestParam("collectionId") String collectionId) {
        return modelMapper.slelctHotQueryModels(modelId, collectionId);
    }

}
