package com.code.generation;

public class Test {

    public static void main(String[] args) throws Exception {
        AutoCode.init().initDB(
                        "com.mysql.cj.jdbc.Driver",
                        "jdbc:mysql://localhost:3306/myapp?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                        "root",
                        "ZIjing123")
                .tableInfo(
                        new String[]{"t_topic","t_user","t_launage"},
                        new String[]{"t_"},
                        false,
                        "com.hjw.app"
                )
                .genCode();
    }
}
