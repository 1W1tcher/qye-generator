package com.qye.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qye.web.model.dto.generator.GeneratorQueryRequest;
import com.qye.web.model.entity.Generator;
import com.qye.web.model.vo.GeneratorVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 生成器服务
 *
 * @author <a href="https://github.com/1W1tcher">qye</a>
 */
public interface GeneratorService extends IService<Generator> {

    /**
     * 校验
     *
     * @param generator
     * @param add
     */
    void validGenerator(Generator generator, boolean add);

    /**
     * 获取查询条件
     *
     * @param generatorQueryRequest
     * @return
     */
    QueryWrapper<Generator> getQueryWrapper(GeneratorQueryRequest generatorQueryRequest);


    /**
     * 获取生成器封装
     *
     * @param generator
     * @param request
     * @return
     */
    GeneratorVO getGeneratorVO(Generator generator, HttpServletRequest request);

    /**
     * 分页获取生成器封装
     *
     * @param generatorPage
     * @param request
     * @return
     */
    Page<GeneratorVO> getGeneratorVOPage(Page<Generator> generatorPage, HttpServletRequest request);
}
