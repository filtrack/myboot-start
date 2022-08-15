package com.code.generation;

import org.springframework.beans.BeanUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class DaoSupport {

    private static final String RESOURCE = "mybatis.xml";

    private DaoSupport() {}

    private static DaoSupport instance;

    public static DaoSupport getInstance() {
        if(null == instance) {
            synchronized (DaoSupport.class) {
                if(null == instance)
                    instance = new DaoSupport();
            }
        }
        return instance;
    }



    public String getPropVal(String key) {
        return "com.sanq.product";
    }
}
