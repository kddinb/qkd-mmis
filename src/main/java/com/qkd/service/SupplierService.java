package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.Supplier;

import java.util.List;

public interface SupplierService {

    /**
     * 分页获取供应商列表
     */
    public PageInfo<Supplier> listPage(PageInfo page, Supplier supplier);

    /**
     * 获取不分页数据
     */
    public List<Supplier> getList(Supplier supplier);

    /**
     *插入一条新的数据
     */
    public boolean saveNewSupplier(Supplier supplier);

    /**
     * 更新供应商
     */
    public boolean updateSupplier(Supplier supplier);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeSupplierById(Integer id);

}
