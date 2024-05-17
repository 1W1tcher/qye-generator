package com.qye.maker;


//import com.qye.maker.cli.CommandExecutor;

import com.qye.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator=new MainGenerator();
        mainGenerator.doGenerate();
    }
}