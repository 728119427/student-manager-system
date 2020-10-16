package com.atguigu.studentmanager.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Page implements Serializable {

    private Integer page;//当前页面
    private Integer rows;//每页显示的数量

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * 获取分页查询偏移量
     * @return
     */
    public Integer getOffset(){
        return (page-1)*rows;
    }
}
