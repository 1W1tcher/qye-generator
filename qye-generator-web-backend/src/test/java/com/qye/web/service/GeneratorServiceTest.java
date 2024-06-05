package com.qye.web.service;

import com.qye.web.model.entity.Generator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeneratorServiceTest {
    @Resource
    private GeneratorService generatorService;

    @Test
    public void testInsert(){
        Generator generator=generatorService.getById(7L);
        for (int i = 0; i < 100000; i++) {
            generator.setId(null);
            generatorService.save(generator);

        }
    }
}