package com.qye.maker.model;

import lombok.Data;

/**
 * 静态模板配置
 */
@Data
public class DataModel {
    private String author="qye";
    private String outputText="输出结果";
    private boolean loop=true;
}
