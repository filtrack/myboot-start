package com.code.generation;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AutoCode {

    //数据库连接
    private static  String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static  String URL = "jdbc:mysql://localhost:3306/myapp?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static  String NAME = "root";
    private static  String PASS = "ZIjing123";

    private static  String AUTHOR = "HJW";




    //表名
    private static  String[] TABS = {};
    //表前缀[为空时输出库中所有表] (生成实体类类名时会去除)
    private static  String[] TAB_PRE = {"t_"};

    //输出实体类的包
    private static  String PACKAGE = "cn.hjw.app";



    private static Connection connection;
    private static Statement statement;
    private static String tableSchema;

    private static String SHOW_COLUMN_INFO =
            "select column_name,column_comment,data_type,column_key from information_schema.columns where table_name = '${tableName}'";
    private final String REPLACE_TABLE_NAME = "${tableName}";


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
        PACKAGE = pkg;
        return this;
    }


    public void genCode() throws Exception {
        //连接数据库
        connect();
        if(null==TABS || TABS.length<1){
            log.error("请设置表名");
            return;
        }

        List<TableInfo> tableInfoList = new ArrayList<>();
        for (String table : TABS) {
            TableInfo tableInfo = tableInfo(table);
            tableInfoList.add(tableInfo);

            Map modelMap = new HashMap();
            modelMap.put("table",tableInfo);
            modelMap.put("author",AUTHOR);
            modelMap.put("desc",tableInfo.getClassComment());
            generateFile(modelMap,"entity",PACKAGE,tableInfo.getUpClassName());

        }


    }


    private static DaoSupport mDaoInstance = DaoSupport.getInstance();
    private static FreemarkerUtil mFreemarker = FreemarkerUtil.getInstance();

    //生成文件， 如果文件夹不存在， 创建
    public void generateFile(Map mRoot, String ftl, String path, String tableJavaName) throws Exception {

        String sourcePath = new File("").getAbsolutePath() + "/src/main/java/";
        //生成代码的包结构
        String packageName = sourcePath+PACKAGE.replace(".", File.separator);
        String entityPackage = packageName + File.separator + "entity";
        mRoot.put("nowDate", new Date());
        mRoot.put("entityPackage", PACKAGE+".entity");
        write(mRoot,"entity.ftl",entityPackage,tableJavaName + ".java");
    }

    private static void write(Map mRoot,String ftlName,String packageName,String fileName) throws Exception {
        String mTemplatePath = AutoCode.class.getClassLoader().getResource("templates").getPath();
        mFreemarker.tempWriter(mTemplatePath,ftlName, packageName, fileName, mRoot);
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

        List<ColumnInfo> columnInfoList = new ArrayList<>();
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


        String sql = "SELECT * FROM " + tableName + " LIMIT 1";
        ResultSet rs = statement.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
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
