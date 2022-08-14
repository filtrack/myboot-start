package com.app.code_gen;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class SqlToJava {

    //数据库连接
    private static  String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static  String URL = "jdbc:mysql://localhost:3306/myapp?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static  String NAME = "root";
    private static  String PASS = "ZIjing123";

    //表名
    private static  String[] TABS = {};
    //表前缀[为空时输出库中所有表] (生成实体类类名时会去除)
    private static  String[] TAB_PRE = {"t_"};
    //是否生成GetSet方法
    private static  boolean GET_SET = true;
    //输出实体类的包
    private static  String PACKAGE = "cn.yang.model.dao";

    //作者
    private static  String AUTHOR = "hjw";



    private static Connection connection;
    private static Statement statement;
    private static String tableSchema;

    //其他参数
    private static String[] colnames;
    private static String[] colTypes;





    //类注解 占位符(表名):{}
    private static final String[] TAB_COMS = {"@Data","@TableName(\"{}\")"};

    //字段注解 占位符(字段名):{}
    private static final String[] COMMENTS = {"@TableField(\"{}\")"};


    private static boolean importDate = false;


    //需要导入的包 (java.util.Date 会自动判断 不需要添加)
    private static final String[] IMPORTS = {
            "com.baomidou.mybatisplus.annotation.IdType"
            , "com.baomidou.mybatisplus.annotation.TableField"
            , "com.baomidou.mybatisplus.annotation.TableId"
            , "com.baomidou.mybatisplus.annotation.TableName"
            , "lombok.Data"};


    public static SqlToJava init() {
        return new SqlToJava();
    }


    public SqlToJava initDB(String driver, String url, String username, String password){
        DRIVER = driver;
        URL = url;
        NAME = username;
        PASS = password;
        return this;
    }

    public SqlToJava tableInfo(String[] tables,String[] tabPres,boolean getset,String pkg){
        TABS = tables;
        TAB_PRE = tabPres;
        GET_SET = getset;
        PACKAGE = pkg;
        return this;

    }

    public SqlToJava authInfo(String author){
        this.AUTHOR = author;
        return this;
    }

    public void genEntity() throws Exception {
        connect();
        if(null==TABS || TABS.length<1){
            log.error("请设置表名");
        }
        for (String table : TABS) {
            toTableInfo(table);

        }
        log.info("生成成功!!");
    }


    private static void connect() throws Exception {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, NAME, PASS);
        statement = connection.createStatement();
        tableSchema = URL.split("\\?")[0].substring(URL.split("\\?")[0].lastIndexOf("/") + 1);
    }

    private static List<String> getAllTables() throws Exception {
        List<String> ls = new ArrayList<>();
        String sql = "SELECT TABLE_NAME FROM information_schema.tables WHERE table_schema='" + tableSchema + "'";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            ls.add(rs.getString("TABLE_NAME").toLowerCase());
        }
        return ls;
    }

    private static void toTableInfo(String tableName) throws Exception {
        String sql = "SELECT * FROM " + tableName + " LIMIT 1";
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int size = rsmd.getColumnCount();
        colnames = new String[size];
        colTypes = new String[size];
        for (int i = 0; i < size; i++) {
            colnames[i] = rsmd.getColumnName(i + 1);
            colTypes[i] = rsmd.getColumnTypeName(i + 1);
        }
        //规范表名
        String normTableName = normTableName(tableName);
        //获取该表注释
        String tableComment = getTableComment(tableName);
        //获取该表字段信息
        StringBuffer tempSb = getColsInfo(tableName);
        //生成GetSet方法
        if (GET_SET) {
            toGetSet(tempSb);
        }
        //生成Java文件
        toJavaFile(normTableName, getSb(tableName, normTableName, tableComment, tempSb));

        toService(normTableName,tableComment);
    }



    //规范表名
    private static String normTableName(String tableName) {
        if (TAB_PRE != null && TAB_PRE.length > 0) {
            for (String pre : TAB_PRE) {
                if (tableName.startsWith(pre.toLowerCase()) && !pre.toLowerCase().equals(tableName)) {
                    tableName = tableName.substring(pre.length());
                    break;
                }
            }
        }
        return capitalLetters(underlineToHump(tableName));
    }

    //生成java文件
    private static void toJavaFile(String tableName, StringBuffer content) throws Exception {
        String path = new File("").getAbsolutePath() + "/src/main/java/";
        if (PACKAGE != null && !PACKAGE.equals("")) {
            path += (PACKAGE+".entity").replace(".", "/") + "/";
        } else {
            path += "entity/";
        }
        dirExists(path);
        String outputPath = path + tableName + ".java";
        FileWriter fw = new FileWriter(outputPath);
        PrintWriter pw = new PrintWriter(fw);
        for (String s : content.toString().split("\r\n")) {
            pw.println(s);
            pw.flush();
        }
        pw.close();
    }

    //判断文件夹是否存在
    public static void dirExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    //获取表注释
    private static String getTableComment(String tableName) throws Exception {
        String str = "";
        String sql = "SELECT table_comment FROM information_schema.tables WHERE table_schema='" + tableSchema + "' AND table_name = '" + tableName + "'";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            str = rs.getString("table_comment");
            if (str == null) {
                str = "";
            } else {
                str = str.replace("\r\n", " ")
                        .replace("\n", " ")
                        .replace("\r", " ")
                        .trim();
            }
        }
        return str;
    }

    //生成类内容
    private static StringBuffer getColsInfo(String tableName) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\r\n");
            //字段注释
            String colComment = getColComment(tableName, colnames[i]);
            if (null != colComment && !"".equals(colComment)) {
                sb.append("    //").append(colComment).append("\r\n");
            }
            //注解
            for (String s : COMMENTS) {
                sb.append("    "+s.replace("{}", colnames[i])).append("\r\n");
            }
            //小驼峰
            String colsName = underlineToHump(colnames[i]);
            //类型
            String type = sqlToJavaType(colTypes[i]);
            sb.append("    private ").append(type).append(" ").append(colsName).append(";\r\n");
        }
        return sb;
    }

    //数据类型处理
    private static String sqlToJavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("TINYINT")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("SMALLINT")
                || sqlType.equalsIgnoreCase("MEDIUMINT")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("INT")
                || sqlType.equalsIgnoreCase("INTEGER")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("BIGINT")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("FLOAT")
                || sqlType.equalsIgnoreCase("DOUBLE")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("DATE")
                || sqlType.equalsIgnoreCase("TIME")
                || sqlType.equalsIgnoreCase("YEAR")
                || sqlType.equalsIgnoreCase("DATETIME")
                || sqlType.equalsIgnoreCase("TIMESTAMP")) {
            importDate = true;
            return "Date";
        }
        return "String";
    }

    //获取字段注释
    private static String getColComment(String tableName, String columnName) throws Exception {
        String str = "";
        String sql = "SELECT column_comment FROM information_schema.columns WHERE table_schema='" + tableSchema + "' AND TABLE_NAME='" + tableName + "' AND COLUMN_NAME='" + columnName + "'";

        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            str = rs.getString("column_comment");
            if (str == null) {
                str = "";
            } else {
                str = str.replace("\r\n", " ")
                        .replace("\n", " ")
                        .replace("\r", " ")
                        .trim();
            }
        }
        return str;
    }

    //生成GetSet方法
    private static void toGetSet(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\r\n");
            //小驼峰
            String colsName = underlineToHump(colnames[i]);
            //大驼峰
            String capitalName = capitalLetters(colsName);
            //类型
            String type = sqlToJavaType(colTypes[i]);
            sb.append("    public ").append(type).append(" get").append(capitalName).append("() {\r\n");
            sb.append("        return this.").append(colsName).append(";\r\n");
            sb.append("    }\r\n");
            sb.append("\r\n");
            sb.append("    public void set").append(capitalName).append("(").append(type).append(" ").append(colsName).append(") {\r\n");
            sb.append("        this.").append(colsName).append(" = ").append(colsName).append(";\r\n");
            sb.append("    }\r\n");
        }
    }

    //下划线命名转为小驼峰命名
    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] p = para.split("_");
        for (String s : p) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    //首字母大写
    public static String capitalLetters(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    //生成文本
    private static StringBuffer getSb(String tableName, String normTableName, String tableComment, StringBuffer colSb) {
        StringBuffer sb = new StringBuffer();
        if (PACKAGE != null && !PACKAGE.equals("")) {
            sb.append("package ").append(PACKAGE).append(";\r\n");
            sb.append("\r\n");
        }
        sb.append("import java.io.Serializable;\r\n");
        for (String s : IMPORTS) {
            sb.append("import ").append(s).append(";\r\n");
        }
        if (importDate) {
            sb.append("import java.util.Date;\r\n");
            importDate = false;
        }
        sb.append("\r\n");
        //注释
        sb.append("/**\r\n");
        sb.append(" * ").append(tableComment).append("\r\n");
        sb.append(" * @author ").append(AUTHOR).append("\r\n");
        sb.append(" * @date ").append(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date())).append("\r\n");
        sb.append(" */\r\n");
        //注解
        for (String s : TAB_COMS) {
            sb.append(s.replace("{}", tableName)).append("\r\n");
        }
        //实体类
        sb.append("public class ").append(normTableName).append(" implements Serializable").append(" {\r\n\n");
        sb.append("    private static final long serialVersionUID = 1L;\r\n");
        sb.append(colSb);
        sb.append("}");
        return sb;
    }


    private static void toService(String normTableName, String tableComment) throws IOException {
        String serviceName = normTableName+"Service";
        StringBuffer sb = new StringBuffer();
        if (PACKAGE != null && !PACKAGE.equals("")) {
            sb.append("package ").append(PACKAGE).append(".service;\r\n");
            sb.append("\r\n");
        }

        sb.append("import "+PACKAGE+".").append(normTableName+";\r\n");
        sb.append("import com.baomidou.mybatisplus.extension.service.IService;\r\n");

        sb.append("\r\n");
        //注释
        sb.append("/**\r\n");
        sb.append(" * ").append(tableComment).append("接口定义\r\n");
        sb.append(" * @author ").append(AUTHOR).append("\r\n");
        sb.append(" * @date ").append(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date())).append("\r\n");
        sb.append(" */\r\n");

        sb.append("public interface ").append(serviceName+" extends IService<"+normTableName+">").append(" {\r\n\n\n\n\n");
        sb.append("}");


        String path = new File("").getAbsolutePath() + "/src/main/java/";
        if (PACKAGE != null && !PACKAGE.equals("")) {
            path += (PACKAGE+".service").replace(".", "/") + "/";
        } else {
            path += "entity/";
        }
        dirExists(path);
        String outputPath = path + serviceName + ".java";
        FileWriter fw = new FileWriter(outputPath);
        PrintWriter pw = new PrintWriter(fw);
        for (String s : sb.toString().split("\r\n")) {
            pw.println(s);
            pw.flush();
        }
        pw.close();
    }



}
