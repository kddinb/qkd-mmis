package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    /**
     * 分页获取列表
     */
    public PageInfo<Purchase> listPage(PageInfo page, Purchase purchase);

    /**
     * 获取除待提交的数据
     */
    public PageInfo<Purchase> listPageExceptNoSub(PageInfo page, Purchase purchase);

    /**
     * 获取不分页数据
     */
    public List<Purchase> getList(Purchase purchase);

    /**
     *插入一条新的数据
     */
    public boolean saveNewPurchase(Purchase purchase);

    /**
     * 更新
     */
    public boolean updatePurchase(Purchase purchase);

    /**
     * 根据ID删除一条数据
     */
    public boolean removePurchaseById(Integer id);

}
