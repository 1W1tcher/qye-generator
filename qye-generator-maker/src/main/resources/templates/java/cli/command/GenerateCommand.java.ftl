package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.generator.MainGenerator;
import ${basePackage}.model.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

<#--生成选项-->
<#macro generateOption ident modelInfo>
${ident}@Option(names = {<#if modelInfo.abbr??>"-${modelInfo.abbr}",</#if> "--${modelInfo.fieldName}"}, arity = "0..1", <#if modelInfo.description??>description = "${modelInfo.description}",</#if> interactive = true, echo = true)
${ident}private ${modelInfo.type} ${modelInfo.fieldName} <#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#macro>

@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
<#list modelConfig.models as modelInfo>
    <#--有分组-->
    <#if modelInfo.groupKey??>
    /**
     * ${modelInfo.groupName}
     */
    static DataModel.${modelInfo.type} ${modelInfo.groupKey} = new DataModel.${modelInfo.type}();
    @Command(name = "${modelInfo.groupName}", description = "${modelInfo.description}")
    @Data
    public static class ${modelInfo.type}Command implements Runnable {
        <#list modelInfo.models as subModelInfo>
            <@generateOption ident="        " modelInfo=subModelInfo />
        </#list>
        @Override
        public void run(){
        <#list modelInfo.models as subModelInfo>
            ${modelInfo.groupKey}.${subModelInfo.fieldName} = ${subModelInfo.fieldName};
        </#list>
        }
    }
    <#else>
        <@generateOption ident="    " modelInfo=modelInfo />
    </#if>
</#list>
<#--    生成调用方法    -->
    public Integer call() throws Exception {
<#list modelConfig.models as modelInfo>
    <#if modelInfo.groupKey??>
        <#if modelInfo.condition??>
        if(${modelInfo.condition}){
            System.out.println("输入${modelInfo.groupName}配置:");
            CommandLine ${modelInfo.groupKey}commandLine = new CommandLine(${modelInfo.type}Command.class);
            ${modelInfo.groupKey}commandLine.execute(${modelInfo.allArgsStr});
        }
        <#else>
        System.out.println("输入${modelInfo.groupName}配置:");
        CommandLine ${modelInfo.groupKey}commandLine = new CommandLine(${modelInfo.type}Command.class);
        ${modelInfo.groupKey}commandLine.execute(${modelInfo.allArgsStr});
        </#if>
    </#if>
</#list>

<#--    填充数据模型对象    -->
    DataModel dataModel = new DataModel();
    BeanUtil.copyProperties(this, dataModel);
    <#list modelConfig.models as modelInfo>
        <#if modelInfo.groupKey??>
    dataModel.${modelInfo.groupKey} = ${modelInfo.groupKey};
        </#if>
    </#list>
    System.out.println("配置信息：" + dataModel);
    MainGenerator.doGenerate(dataModel);
    return 0;
    }
}