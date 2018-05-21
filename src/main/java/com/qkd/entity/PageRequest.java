package com.qkd.entity;

import java.io.Serializable;


public class PageRequest implements Serializable {


	private static final long serialVersionUID = 1L;

	private Integer page;//Easyui 第几页

    private Integer rows;//Easyui 页面大小

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
}
