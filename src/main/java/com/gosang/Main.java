package com.gosang;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //创建对象
        AutoGenerator generator = new AutoGenerator();

        /*数据源*/
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mmall");
        generator.setDataSource(dataSourceConfig);

        /*全局配置*/
        GlobalConfig globalConfig = new GlobalConfig();
        //获取工程的绝对路径
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        //设置作者
        globalConfig.setAuthor("gosang");
        //设置生成文件夹打开与否
        globalConfig.setOpen(false);
        //接口名字：类名+sService开头
        globalConfig.setServiceName("%sService");
        generator.setGlobalConfig(globalConfig);

        /*包信息*/
        PackageConfig packageConfig = new PackageConfig();
        //生成文件放的位置的父包
        packageConfig.setParent("com.gosang");
        //实体类报名
        packageConfig.setEntity("entity");
        //mapper包名
        packageConfig.setMapper("mapper");
        //service层包名
        packageConfig.setService("service");
        //Service的是实现类的包名
        packageConfig.setServiceImpl("service.impl");
        //控制层包名
        packageConfig.setController("controller");
        generator.setPackageInfo(packageConfig);

        /*策略配置*/
        StrategyConfig strategyConfig = new StrategyConfig();
        //设置表（全部表都要生成的话，要么不写，要么全部写上）
        //strategyConfig.setInclude("account","user","book");
        //设置下划线转驼峰，表名转类名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //设置下划线转驼峰，字段名转成员变量名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否添加lombok注解
        strategyConfig.setEntityLombokModel(true);
        //自动填充
        TableFill tableFill1 = new TableFill("create_time", FieldFill.INSERT);
        TableFill tableFill2 = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> list = Arrays.asList(tableFill1,tableFill2);
        strategyConfig.setTableFillList(list);
        generator.setStrategy(strategyConfig);
        generator.execute();
    }
}
