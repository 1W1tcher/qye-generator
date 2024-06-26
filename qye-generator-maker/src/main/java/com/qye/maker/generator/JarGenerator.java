package com.qye.maker.generator;

import java.io.*;
import java.util.Map;

public class JarGenerator {

    public static void doGenerate(String projectDir) throws InterruptedException, IOException {
        //清理之前的构建并打包
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;

        //这里一定要拆分
        ProcessBuilder processBuilder=new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));
        Map<String, String> environment = processBuilder.environment();
        System.out.println(environment);
        Process process=processBuilder.start();

        //读取命令的输出
        InputStream inputStream=process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }

        //等待命令执行完成
        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码："+exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("D:\\Code\\Java Projects\\qye-generator\\qye-generator-maker\\generated");
    }
}
