package com.qye.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qye.web.model.entity.Generator;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author qye
 * @description 针对表【generator(代码生成器)】的数据库操作Mapper
 * @createDate 2024-05-26 16:35:44
 * @Entity com.qye.web.model.entity.Generator
 */
public interface GeneratorMapper extends BaseMapper<Generator> {
    @Select("SELECT id,distPath FROM generator WHERE isDelete=1")
    List<Generator> listDeletedGenerator();
}




