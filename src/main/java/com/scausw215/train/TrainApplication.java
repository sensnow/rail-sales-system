package com.scausw215.train;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Sen
 * @date 2023/3/24 11:02
 * @description 项目启动类
 */
@SpringBootApplication
@EnableWebMvc
@MapperScan("com.scausw215.train.mapper")
public class TrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainApplication.class, args);
    }

}
