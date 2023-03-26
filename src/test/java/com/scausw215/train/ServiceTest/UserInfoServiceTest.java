package com.scausw215.train.ServiceTest;

import com.scausw215.train.common.Result;
import com.scausw215.train.entity.request.UserRegisterRequest;
import com.scausw215.train.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class UserInfoServiceTest {
    @Autowired
    UserInfoService userInfoService;

    @Test
    public void testRegisterUnit() {

    }
}
