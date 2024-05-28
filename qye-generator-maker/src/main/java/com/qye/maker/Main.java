package com.qye.maker;


//import com.qye.maker.cli.CommandExecutor;

import com.qye.maker.generator.main.GenerateTemplate;
import com.qye.maker.generator.main.MainGenerator;
import com.qye.maker.generator.main.ZipGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
//        GenerateTemplate generateTemplate=new MainGenerator();
        GenerateTemplate generateTemplate=new ZipGenerator();
        generateTemplate.doGenerate();
    }
}