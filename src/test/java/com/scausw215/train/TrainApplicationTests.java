package com.scausw215.train;

import com.scausw215.train.entity.DTO.TrainInfoDTO;
import com.scausw215.train.service.TrainInfoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class TrainApplicationTests {

    @Resource
    private TrainInfoService trainInfoService;

    @Test
    void contextLoads() {

    }



}
