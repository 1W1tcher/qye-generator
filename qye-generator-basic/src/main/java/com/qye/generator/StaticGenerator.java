package com.qye.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * 静态文件生成器
 */
public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath=System.getProperty("user.dir");

        String inputPath=projectPath+File.separator+"qye-generator-demo-projects"+ File.separator+"acm-template";
        String outputPath=projectPath;

        copyFilesByHutool(inputPath,outputPath);

    }

    /**
     * 拷贝文件（Hutool实现），吧文件拷贝到目标目录
     * @param inputPath
     * @param outputPath
     */
    public  static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }
}
