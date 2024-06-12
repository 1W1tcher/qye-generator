package com.qye.web.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest
class CosManagerTest {

    @Resource
    private CosManager cosManager;
    @Test
    void deleteObject() {
        cosManager.deleteObject("/test/1.png");
    }

    @Test
    void deleteObjects() {
        cosManager.deleteObjects(Arrays.asList("test/1.png","test/2.png"));
    }

    @Test
    void deleteDir() {
        cosManager.deleteDir("/test/");
    }
}