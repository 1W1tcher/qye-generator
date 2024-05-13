package com.qye.maker.meta;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.unit.DataSizeUtil;
import cn.hutool.core.util.StrUtil;
import com.qye.maker.meta.enums.FileGenerateTypeEnum;
import com.qye.maker.meta.enums.FileTypeEnum;
import com.qye.maker.meta.enums.ModelTypeEnum;
import freemarker.template.utility.StringUtil;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class MetaValidator {
    public static void doValidAndFill(Meta meta){
        //基础信息校验和默认值
        ValidAndFillMetaRoot(meta);

        //fileConfig 校验和默认值
        ValidAndFillFileConfig(meta);

        //modelConfig 校验和默认值
        ValidAndFillModelConfig(meta);

    }

    private static void ValidAndFillModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if(CollUtil.isEmpty(modelInfoList)){
            return;
        }
        for(Meta.ModelConfig.ModelInfo modelInfo:modelInfoList){
            String fieldName = modelInfo.getFieldName();
            if(StrUtil.isBlank(fieldName)){
                throw new MetaException("未填写 fieldName");
            }

            String modelInfoType = modelInfo.getType();
            if(StrUtil.isEmpty(modelInfoType)){
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }
        }
    }

    private static void ValidAndFillFileConfig(Meta meta) {
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }

        String sourceRootPath = fileConfig.getSourceRootPath();
        if(StrUtil.isBlank(sourceRootPath)){
            throw new MetaException("未填写 sourceRootPath");
        }

        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath= ".source/" +
                FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if(StrUtil.isEmpty(inputRootPath)){
            fileConfig.setInputRootPath(defaultInputRootPath);
        }

        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputRootPath="generated";
        if(StrUtil.isEmpty(outputRootPath)){
            fileConfig.setOutputRootPath(defaultOutputRootPath);
        }

        String fileConfigType = fileConfig.getType();
        String defaultType=FileTypeEnum.DIR.getValue();
        if(StrUtil.isEmpty(fileConfigType)){
            fileConfig.setType(defaultType);
        }

        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (CollUtil.isEmpty(fileInfoList)) {
            return;
        }
        for(Meta.FileConfig.FileInfo fileInfo:fileInfoList){

            String inputPath=fileInfo.getInputPath();
            if(StrUtil.isBlank(inputPath)){
                throw new MetaException("未填写 inputPath");
            }

            String outputPath = fileInfo.getOutputPath();
            if(StrUtil.isEmpty(outputPath)){
                fileInfo.setOutputPath(inputPath);
            }

            String type = fileInfo.getType();
            if(StrUtil.isBlank(type)){
                if(StrUtil.isBlank(FileUtil.getSuffix(inputPath))){
                    fileInfo.setType(FileTypeEnum.DIR.getValue());
                } else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());
                }
            }

            String generateType = fileInfo.getGenerateType();
            if(StrUtil.isBlank(generateType)){
                if(inputPath.endsWith(".ftl")){
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                } else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }
        }
    }

    private static void ValidAndFillMetaRoot(Meta meta) {
        String name = StrUtil.blankToDefault(meta.getName(),"my-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(),"我的模版代码生成器");
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(),"com.qye");
        String version = StrUtil.emptyToDefault(meta.getVersion(),"1.0");
        String author = StrUtil.emptyToDefault(meta.getAuthor(),"qye");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(),DateUtil.now());
        meta.setName(name);
        meta.setDescription(description);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setAuthor(author);
        meta.setCreateTime(createTime);
    }
}
