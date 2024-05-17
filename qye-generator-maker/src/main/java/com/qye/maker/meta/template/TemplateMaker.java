package com.qye.maker.meta.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.qye.maker.meta.Meta;
import com.qye.maker.meta.enums.FileGenerateTypeEnum;
import com.qye.maker.meta.enums.FileTypeEnum;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板制作工具
 */
public class TemplateMaker {
    public static void main(String[] args) {
        //一、输入信息
        //1.项目的基本信息
        String name="acm-template-generator";
        String description="ACM 示例模版生成器";

        //2.输入文件信息
        String projectPath=System.getProperty("user.dir");
        //要挖坑的项目根目录
        String sourceRootPath=FileUtil.getAbsolutePath(new File(projectPath).getParentFile()) + File.separator + "qye-generator-demo-projects/acm-template";
        sourceRootPath = sourceRootPath.replaceAll("\\\\","/");
        //要挖坑的文件
        String fileInputPath = "src/com/qye/acm/MainTemplate.java";
        String fileOutputPath = fileInputPath + ".ftl";

        //3.输入模型参数信息
        Meta.ModelConfig.ModelInfo modelInfo=new Meta.ModelConfig.ModelInfo();
        modelInfo.setFieldName("outputText");
        modelInfo.setType("String");
        modelInfo.setDefaultValue("sum = ");

        //二、使用字符串替换、生成模板文件
        String fileInputAbsolutePath= sourceRootPath + File.separator +fileInputPath;
        String fileContent = FileUtil.readUtf8String(fileInputAbsolutePath);
        String replacement = String.format("${%s}",modelInfo.getFieldName());
        String newFileContent = StrUtil.replace(fileContent,"Sum:",replacement);

        //输出模板文件
        String fileOutputAbsolutePath=sourceRootPath+File.separator+fileOutputPath;
        FileUtil.writeUtf8String(newFileContent,fileOutputAbsolutePath);

        //三、生成配置文件
        String metaOutputPath= sourceRootPath + File.separator + "meta.json";

        //1.构造参数配置对象
        Meta meta=new Meta();
        meta.setName(name);
        meta.setDescription(description);

        Meta.FileConfig fileConfig= new Meta.FileConfig();
        meta.setFileConfig(fileConfig);
        fileConfig.setSourceRootPath(sourceRootPath);
        List<Meta.FileConfig.FileInfo> fileInfoList = new ArrayList<>();
        fileConfig.setFiles(fileInfoList);

        Meta.FileConfig.FileInfo fileInfo=new Meta.FileConfig.FileInfo();
        fileInfo.setInputPath(fileInputPath);
        fileInfo.setOutputPath(fileOutputPath);
        fileInfo.setType(FileTypeEnum.FILE.getValue());
        fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
        fileInfoList.add(fileInfo);

        Meta.ModelConfig modelConfig = new Meta.ModelConfig();
        meta.setModelConfig(modelConfig);
        List<Meta.ModelConfig.ModelInfo> modelInfoList = new ArrayList<>();
        modelConfig.setModels(modelInfoList);
        modelInfoList.add(modelInfo);

        //2.输出元信息文件
        FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(meta),metaOutputPath);

    }


}
