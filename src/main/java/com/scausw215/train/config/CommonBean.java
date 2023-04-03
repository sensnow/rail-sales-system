package com.scausw215.train.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @description 公共配置类
 * @author sensnow
 */
@Configuration
public class CommonBean {

    /**
     * 获取列车类型列表
     * @return 列车类型列表
     */
    @Bean("trainNameList")
    public ArrayList<String> getTrainNameList(){
        ArrayList<String> trainNameList = new ArrayList<>();
        trainNameList.add("高铁动车");
        trainNameList.add("普通列车");
        trainNameList.add("火车");
        return trainNameList;
    }
}
