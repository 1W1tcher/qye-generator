package com.qye.maker.template.model;

import com.qye.maker.meta.Meta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TemplateMakerModelConfig {

    private List<ModelInfoConfig> models;

    private ModelGroupConfig modelGroupConfig;

    public void setModels(List<ModelInfoConfig> models) {
        this.models = models;
    }

    @NoArgsConstructor
    @Data
    public static class ModelInfoConfig{

        private String fieldName;

        private String type;

        private String description;

        private Object defaultValue;

        private String abbr;

        private String replaceText;
    }

    @Data
    public static class ModelGroupConfig{
        private String condition;

        private String groupKey;

        private String groupName;
    }
}
