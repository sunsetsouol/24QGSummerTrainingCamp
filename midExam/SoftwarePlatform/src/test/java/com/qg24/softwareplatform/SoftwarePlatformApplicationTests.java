package com.qg24.softwareplatform;

import com.qg24.softwareplatform.po.dto.AuthSoftwareDTO;
import com.qg24.softwareplatform.service.impl.AuthorizationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
class SoftwarePlatformApplicationTests {

    @Test
    void contextLoads() throws IOException {
        AuthorizationServiceImpl authorizationService = new AuthorizationServiceImpl();
        ArrayList<AuthSoftwareDTO> list = new ArrayList<>();
        list.add(new AuthSoftwareDTO(1, "s1", 1));
        list.add(new AuthSoftwareDTO(2, "s2", 2));
        list.add(new AuthSoftwareDTO(3, "s3", 3));
//        String s = authorizationService.generateAuthFileAndUpload("111", list);
        System.out.println(".");
    }

}
