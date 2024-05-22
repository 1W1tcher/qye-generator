package com.qye.maker.template.model;

import lombok.Builder;
import lombok.Data;

/**
 * 文件过滤配置
 */
@Data
@Builder
public class FileFilterConfig {

    /**
     * 过滤范围
     */
    String range;
    /**
     * 过滤规则
     */
    String rule;
    /**
     * 过滤值
     */
    String value;

}
