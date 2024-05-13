package com.qye.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.qye.maker.generator.JarGenerator;
import com.qye.maker.generator.ScriptGenerator;
import com.qye.maker.generator.file.DynamicFileGenerator;
import com.qye.maker.meta.Meta;
import com.qye.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator extends GenerateTemplate{
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不要输出dist了");
    }
}
