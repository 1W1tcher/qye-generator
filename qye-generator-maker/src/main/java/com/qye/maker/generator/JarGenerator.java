package com.qye.maker.generator;

import java.io.*;

public class JarGenerator {

    public static void doGenerate(String projectDir) throws InterruptedException, IOException {
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;

        ProcessBuilder processBuilder=new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process=processBuilder.start();

        InputStream inputStream=process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码："+exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("D:\\Code\\Java Projects\\qye-generator\\qye-generator-maker\\generated");
    }
}
