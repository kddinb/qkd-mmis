package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.StorMed;

import java.util.List;

public interface StorMedService {

    /**
     * 分页获取列表
     */
    public PageInfo<StorMed> listPage(PageInfo page, StorMed storMed);

    /**
     * 分页获取药房中不存在的数据
     */
    public PageInfo<StorMed> listFiltPage(PageInfo page, StorMed storMed);

    /**
     * 获取不分页数据
     */
    public List<StorMed> getList(StorMed storMed);

    /**
     *插入一条新的数据
     */
    public boolean saveNewStorMed(StorMed storMed);

    /**
     * 更新
     */
    public boolean updateStorMed(StorMed storMed);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeStorMedById(Integer id);

    /**
     * 根据名称和库房更新库存
     */
    public boolean updateStockByNmaeAndDepart(StorMed storMed);

    public boolean reduceStockByNmaeAndDepart(StorMed storMed);

}
