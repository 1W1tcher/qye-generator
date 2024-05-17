package com.qye.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.qye.maker.generator.JarGenerator;
import com.qye.maker.generator.ScriptGenerator;
import com.qye.maker.generator.file.DynamicFileGenerator;
import com.qye.maker.meta.Meta;
import com.qye.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public abstract class GenerateTemplate {

    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

        //输出的根路径
        String projectPath=System.getProperty("user.dir");
        String outputPath=projectPath+ File.separator+"generated"+File.separator+meta.getName();
        if(!FileUtil.exist(outputPath)){
            FileUtil.mkdir(outputPath);
        }
        //1.从原始模版文件路径复制到生成的代码包中
        String sourceCopyDestPath = copySource(meta, outputPath);

        //2.代码生成
        generateCode(meta, outputPath);

        //3.打jar包
        String jarPath = buildJar(outputPath, meta);

        //4.封装脚本
        String shellOutputFilePath= buildScript(jarPath, outputPath);

        //5.生成精简版的文件包
        buildDist(outputPath, sourceCopyDestPath, jarPath,shellOutputFilePath);

    }

    protected  void buildDist(String outputPath, String sourceCopyDestPath, String jarPath,String shellOutputFilePath) {
        String distOutputPath= outputPath +"-dist";
        //拷贝jar包
        String targetAbsolutePath=distOutputPath+File.separator+"target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath= outputPath +File.separator+ jarPath;
        FileUtil.copy(jarAbsolutePath,targetAbsolutePath,true);
        //拷贝脚本文件
        FileUtil.copy(shellOutputFilePath,distOutputPath,true);
        FileUtil.copy(shellOutputFilePath +".bat",distOutputPath,true);
        //拷贝源模板文件
        FileUtil.copy(sourceCopyDestPath,distOutputPath,true);
    }

    protected  String buildScript(String jarPath, String outputPath) {
        String shellOutputFilePath= outputPath +File.separator+"generator";
        ScriptGenerator.doGenerate(shellOutputFilePath,jarPath);
        return shellOutputFilePath;
    }


    protected  String buildJar(String outputPath,Meta meta) throws InterruptedException, IOException {
        String jarName=String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath="target/"+jarName;
        JarGenerator.doGenerate(outputPath);
        return jarPath;
    }

    protected  void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
        ClassPathResource classPathResource=new ClassPathResource("");
        String inputResourcePath=classPathResource.getAbsolutePath();

        //Java 包的基础路径
        //com.qye
        String outputBasePackage= meta.getBasePackage();
        //com/qye
        String outputBasePackagePath= StrUtil.join("/",StrUtil.split(outputBasePackage,"."));
        //generated/src/main/java/com/qye
        String outputBaseJavaPackagePath= outputPath +File.separator+"src/main/java/"+outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        //model.DataModel
        inputFilePath=inputResourcePath+File.separator+"templates/java/model/DataModel.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //generator.MainGenerator
        inputFilePath=inputResourcePath+File.separator+"templates/java/generator/MainGenerator.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //cli.command.GenerateCommand
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //cli.command.ListCommand
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //cli.command.ConfigCommand
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //cli.CommandExecutor
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //cli.CommandExecutor
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //Main
        inputFilePath=inputResourcePath+File.separator+"templates/java/Main.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //generator.DynamicGenerator
        inputFilePath=inputResourcePath+File.separator+"templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //generator.StaticGenerator
        inputFilePath=inputResourcePath+File.separator+"templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);



        //pom.xml
        inputFilePath=inputResourcePath+File.separator+"templates/pom.xml.ftl";
        outputFilePath= outputPath +File.separator+"/pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);

        //README.md
        inputFilePath=inputResourcePath+File.separator+"templates/README.md.ftl";
        outputFilePath= outputPath +File.separator+"/README.md";
        DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath, meta);
    }

    protected  String copySource(Meta meta, String outputPath) {
        String sourceRootPath= meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath= outputPath +File.separator+".source";
        FileUtil.copy(sourceRootPath,sourceCopyDestPath,false);
        return sourceCopyDestPath;
    }
}
