package com.qye.web.model.dto.generator;

import com.qye.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 制作代码生成器请求
 *
 * @author <a href="https://github.com/1W1tcher">qye</a>
 */
@Data
public class GeneratorMakeRequest implements Serializable {

    /**
     * 元信息
     */
    private Meta meta;

    /**
     * 数据模型
     */
    private String zipFilePath;
    private static final long serialVersionUID = 1L;
}