package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.DicMed;

import java.util.List;

public interface DicMedService {

    /**
     * 分页获取列表
     */
    public PageInfo<DicMed> listPage(PageInfo page, DicMed dicMed);

    /**
     * 获取不分页数据
     */
    public List<DicMed> getList(DicMed dicMed);

    /**
     *插入一条新的数据
     */
    public boolean saveNewDicMed(DicMed dicMed);

    /**
     * 更新
     */
    public boolean updateDicMed(DicMed dicMed);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeDicMedById(Integer id);

}
