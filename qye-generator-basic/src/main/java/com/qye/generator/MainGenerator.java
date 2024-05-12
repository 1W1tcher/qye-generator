package com.qye.generator;

import com.qye.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import static com.qye.generator.StaticGenerator.copyFilesByHutool;

public class MainGenerator {
    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String inputRootPath = "D:/Code/Java Projects/qye-generator/qye-generator-demo-projects/acm-template-pro";
        String outputRootPath= "D:/Code/Java Projects/qye-generator";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath, "src/com/qye/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/com/qye/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, "gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

    }
    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("qye");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和：");
        doGenerate(mainTemplateConfig);
    }
}
