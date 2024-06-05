package com.qye.web.model.dto.generator;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 缓存代码生成器请求
 *
 * @author <a href="https://github.com/1W1tcher">qye</a>
 */
@Data
public class GeneratorCacheRequest implements Serializable {

    /**
     * id
     */
    private Long id;


    private static final long serialVersionUID = 1L;
}