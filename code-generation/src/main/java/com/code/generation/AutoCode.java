package com.code.generation;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Slf4j
public class AutoCode {



    private static Connection connection;
    private static Statement statement;
    private static String tableSchema;

    //数据库连接
    private static  String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static  String URL = "jdbc:mysql://localhost:3306/myapp?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static  String NAME = "root";
    private static  String PASS = "ZIjing123";
    private static  String AUTHOR = "HJW";
    //输出实体类的包
    private static  String PKG = "cn.hjw.app";
    private static  String PKG_ENTITY = "entity";
    private static  String PKG_MAPPER = "mapper2";
    private static  String PKG_SERVICE= "service";
    private static  String PKG_SERVICEIMPL = "service.impl";

    //表名
    private static  String[] TABS = {};
    //表前缀[为空时输出库中所有表] (生成实体类类名时会去除)
    private static  String[] TAB_PRE = {"t_"};
    private static String SHOW_COLUMN_INFO ="select column_name,column_comment,data_type,column_key from information_schema.columns where table_name = '${tableName}' ";
    private final String REPLACE_TABLE_NAME = "${tableName}";


    private final String SOURCE_PATH = new File("").getAbsolutePath() + "/src/main/java/";
    private final String MAPPER_PATH = new File("").getAbsolutePath() + "/src/main/resources/";
    private static  String templatePath = null;
    private static FreemarkerUtil mFreemarker = FreemarkerUtil.getInstance();

    static {
        templatePath = AutoCode.class.getClassLoader().getResource("templates").getPath();
    }

    public static AutoCode init() {
        return new AutoCode();
    }


    public AutoCode initDB(String driver, String url, String username, String password){
        DRIVER = driver;
        URL = url;
        NAME = username;
        PASS = password;
        return this;
    }

    public AutoCode tableInfo(String[] tables,String[] tabPres,boolean getset,String pkg){
        TABS = tables;
        TAB_PRE = tabPres;
        PKG = pkg;
        return this;
    }


    public void genCode() throws Exception {
        //连接数据库
        connect();
        if(null==TABS || TABS.length<1){
            log.error("请设置表名");
            return;
        }

        for (String table : TABS) {
            TableInfo tableInfo = tableInfo(table);


            generateEntity(tableInfo);
            generateMapper(tableInfo);
            generateService(tableInfo);
            generateServiceImpl(tableInfo);
            generateMapperXML(tableInfo);

        }


    }




    private void generateEntity(TableInfo tableInfo) throws Exception {
        String packageName = SOURCE_PATH+ PKG.replace(".", File.separator)+"/"+PKG_ENTITY;;
        Map map = new HashMap();
        map.put("table",tableInfo);
        map.put("author",AUTHOR);
        map.put("nowDate", new Date());
        map.put("desc",tableInfo.getClassComment());
        map.put("entityPackage", PKG +"."+PKG_ENTITY);
        String fileName = tableInfo.getUpClassName();
        map.put("className", tableInfo.getUpClassName());
        mFreemarker.tempWriter(templatePath,"entity.ftl", packageName, fileName+".java", map);
    }
    private void generateMapper(TableInfo tableInfo) throws Exception {
        String packageName = SOURCE_PATH+ PKG.replace(".", File.separator)+"/"+PKG_MAPPER;
        Map map = new HashMap();
        map.put("author",AUTHOR);
        map.put("nowDate", new Date());
        map.put("desc",tableInfo.getClassComment());
        map.put("mapperPackage", PKG +"."+PKG_MAPPER);
        map.put("entityPackage", PKG +"."+PKG_ENTITY);
        String fileName = tableInfo.getUpClassName()+"Mapper";
        map.put("entityName",tableInfo.getUpClassName());
        map.put("className",fileName);
        mFreemarker.tempWriter(templatePath,"mapper.ftl", packageName, fileName+".java", map);
    }
    private void generateService(TableInfo tableInfo) throws Exception {
        String packageName = SOURCE_PATH+ PKG.replace(".", File.separator)+"/"+PKG_SERVICE;
        Map map = new HashMap();
        map.put("author",AUTHOR);
        map.put("nowDate", new Date());
        map.put("desc",tableInfo.getClassComment());
        map.put("servicePackage", PKG +"."+PKG_SERVICE);
        map.put("entityPackage", PKG +"."+PKG_ENTITY);
        String fileName = tableInfo.getUpClassName()+"Service";
        map.put("entityName",tableInfo.getUpClassName());
        map.put("className",fileName);
        mFreemarker.tempWriter(templatePath,"service.ftl", packageName, fileName+".java", map);
    }
    private void generateServiceImpl(TableInfo tableInfo) throws Exception {
        String packageName = SOURCE_PATH+ PKG.replace(".", File.separator)+"/"+PKG_SERVICEIMPL;
        Map map = new HashMap();
        map.put("author",AUTHOR);
        map.put("nowDate", new Date());
        map.put("desc",tableInfo.getClassComment());
        map.put("serviceImplPackage", PKG +"."+PKG_SERVICEIMPL);
        map.put("mapperPackage", PKG +"."+PKG_MAPPER);
        map.put("entityPackage", PKG +"."+PKG_ENTITY);
        map.put("servicePackage", PKG +"."+PKG_SERVICE);
        String serviceName = tableInfo.getUpClassName()+"Service";
        String fileName = serviceName+"Impl";
        map.put("serviceName",serviceName);
        String entityMapper = tableInfo.getUpClassName()+"Mapper";
        map.put("entityMapper",entityMapper);
        map.put("entityName",tableInfo.getUpClassName());
        map.put("className",fileName);
        mFreemarker.tempWriter(templatePath,"serviceImpl.ftl", packageName, fileName+".java", map);
    }

    private void generateMapperXML(TableInfo tableInfo) throws Exception {
        String packageName = MAPPER_PATH+"/"+PKG_MAPPER;
        Map map = new HashMap();
        map.put("table",tableInfo);
        map.put("mapperPackage", PKG +"."+PKG_MAPPER);
        map.put("entityPackage", PKG +"."+PKG_ENTITY);
        String fileName = tableInfo.getUpClassName()+"Mapper";
        String entityMapper = tableInfo.getUpClassName()+"Mapper";
        map.put("entityMapper",entityMapper);
        map.put("entityName",tableInfo.getUpClassName());
        mFreemarker.tempWriter(templatePath,"mapper_xml.ftl", packageName, fileName+".xml", map);
    }

    private  void connect() throws Exception {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, NAME, PASS);
        statement = connection.createStatement();
        tableSchema = URL.split("\\?")[0].substring(URL.split("\\?")[0].lastIndexOf("/") + 1);
    }


    /**
     * 解析表信息
     *
     * @param tableName
     * @return
     */
    private TableInfo tableInfo(String tableName) throws Exception {

        LinkedList<ColumnInfo> columnInfoList = new LinkedList<>();
        PreparedStatement columnPs = connection.prepareStatement(SHOW_COLUMN_INFO.replace(REPLACE_TABLE_NAME, tableName));
        ResultSet columnRs = columnPs.executeQuery();
        String primaryKey = "";
        while(columnRs.next()) {
            String tabColumnName = columnRs.getString(1);
            String columnComment = columnRs.getString(2);
            String tabColumnType = columnRs.getString(3);
            //小驼峰
            String lowerColumnName = StrUtils.lowerStr(tabColumnName);
            //大驼峰
            String upColumnName = StrUtils.upStr(lowerColumnName);

            String key = columnRs.getString(4);
            if (!ObjectUtils.isEmpty(key) && "PRI".equals(key)) {
                primaryKey = tabColumnName;
            }
            ColumnInfo columnInfo = new ColumnInfo(tabColumnName,tabColumnType,StrUtils.sqlTypeToJavaType(tabColumnType),columnComment,lowerColumnName,upColumnName);
            columnInfoList.add(columnInfo);
        }

        String lowerClassName = normTableName(tableName);
        String upClassName = StrUtils.upStr(lowerClassName);
        //获取该表注释
        String comment = getTableComment(tableName);
        TableInfo tableInfo = new TableInfo(primaryKey,tableName,lowerClassName,upClassName,comment,columnInfoList);
        return tableInfo;
    }

    /**
     * 规范表名（t_user_info -> UserInfo）
     * @param tableName
     * @return
     */
    private static String normTableName(String tableName) {
        if (TAB_PRE != null && TAB_PRE.length > 0) {
            for (String pre : TAB_PRE) {
                if (tableName.startsWith(pre.toLowerCase()) && !pre.toLowerCase().equals(tableName)) {
                    tableName = tableName.substring(pre.length());
                    break;
                }
            }
        }
        return StrUtils.lowerStr(tableName);
    }

    /**
     * 表注释
     * @param tableName
     * @return
     * @throws Exception
     */
    private  String getTableComment(String tableName) throws Exception {
        String str = "";
        String sql = "SELECT table_comment FROM information_schema.tables WHERE table_schema='" + tableSchema + "' AND table_name = '" + tableName + "'";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            str = rs.getString("table_comment");
            if (str == null) {
                str = "";
            } else {
                str = str.replace("\r\n", " ").replace("\n", " ").replace("\r", " ").trim();
            }
        }
        return str;
    }

}
