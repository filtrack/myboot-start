package com.starter.app.domain.ext;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MyPage<T> extends Page<T> {

    public static <T> Page<T> of(long row, long pageSize, LinkedHashMap<String,String> orderMap) {
        Page<T> page = new Page<>(row, pageSize);
        if(!ObjectUtils.isEmpty(orderMap)){
            List<OrderItem> orderItemList = new ArrayList<>();
            orderMap.forEach((key,value)->{
                if(value.equals("desc")){
                    orderItemList.add(OrderItem.desc(key));
                }else{
                    orderItemList.add(OrderItem.asc(key));
                }
            });
            page.setOrders(orderItemList);
        }
        return page;
    }

}
