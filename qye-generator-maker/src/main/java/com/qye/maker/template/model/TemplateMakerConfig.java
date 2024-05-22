package com.qye.maker.template.model;

import com.qye.maker.meta.Meta;
import lombok.Data;

/**
 * 模版制作配置
 */
@Data
public class TemplateMakerConfig {
    private Long id;
    private Meta meta = new Meta();
    private String originProjectPath;
    private TemplateMakerFileConfig fileConfig=new TemplateMakerFileConfig();
    private TemplateMakerModelConfig modelConfig = new TemplateMakerModelConfig();

}
