package com.starter.app;

import cn.hutool.db.Db;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;

@Slf4j
public class MBP {

    private static final String URL = "jdbc:mysql://localhost:3306/myapp?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ZIjing123";

    //项目根目录
    private static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");
    private static final String AUTHOR = "hjw";
    private static final String OUT_PUT_DIR = PROJECT_ROOT_PATH + "/src/main/java";


    private static final String PKG_PARENT = "com.cool";
    private static final String PKG_ENTITY = "entity";
    private static final String PKG_SERVICE = "service";
    private static final String PKG_SERVICE_IMPL = "service.impl";
    private static final String PKG_MAPPER = "mapper";
    private static final String PKG_MAPPER_XML = "mapper.xml";
    private static final String PKG_CONTROLLER = "controller";
    private static final String PKG_XML_PATH = PROJECT_ROOT_PATH + "/src/main/resources/mapper2";
    private static final String PKG_VO = "";

//    private static final String[] TABLES = {"t_topic,t_user"};

    public static void main(String[] args) {

        // 1、数据源配置
        DataSourceConfig.Builder datasourceBuilder = new DataSourceConfig.Builder(
                URL, USERNAME, PASSWORD
        );



        FastAutoGenerator.create(datasourceBuilder)
                .globalConfig(builder -> {
                    builder.author(AUTHOR).fileOverride().commentDate("yyyy-MM-dd HH:mm").outputDir(OUT_PUT_DIR).disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder
                            .parent(PKG_PARENT)
                            .entity(PKG_ENTITY).service(PKG_SERVICE).serviceImpl(PKG_SERVICE_IMPL)
                            .mapper(PKG_MAPPER).xml(PKG_MAPPER_XML)
                            .controller(PKG_CONTROLLER)
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, PKG_XML_PATH)).build();

                })
                .strategyConfig(builder -> {
                    builder
//                            .addInclude(TABLES)
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .idType(IdType.ASSIGN_ID)

                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()

                            .serviceBuilder()
                            .convertServiceFileName(serviceName -> serviceName + "Service")

                            .controllerBuilder()
                            .enableRestStyle()

                            .enableHyphenStyle().build();;

                })
                .templateEngine(new FreemarkerTemplateEngine())

                .templateConfig(builder -> {
                    builder
                            .entity("/ftl/entity.java");
                })
                .execute();
    }

}
