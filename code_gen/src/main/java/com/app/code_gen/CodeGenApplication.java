package com.app.code_gen;


//@SpringBootApplication
public class CodeGenApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(CodeGenApplication.class, args);
//    }

    public static void main(String[] args) throws Exception {
        SqlToJava.init().initDB(
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/myapp?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                    "root",
                    "ZIjing123")
                .tableInfo(
                        new String[]{"t_topic"},
                        new String[]{"t_"},
                        false,
                        "com.test"
                ).authInfo("hjw")
                .genEntity();
    }
}
