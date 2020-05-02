package com.chd9.manage.utils;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
public class TableSet<T> extends Page {

    public TableSet(int current, int size) {
        super(current, size);
    }

    public TableSet setRecords(List records) {
        return (TableSet)super.setRecords(records);
    }

    public Response response(){
        Response r = new Response(0,this.getRecords(),"","");
        r.setCount((int)this.getTotal());
        return r;
    }

}
