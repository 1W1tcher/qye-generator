package com.qye.maker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * 静态文件生成器
 */
public class StaticFileGenerator {


    /**
     * 拷贝文件（Hutool实现），把文件拷贝到目标目录
     * @param inputPath
     * @param outputPath
     */
    public  static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }
}
