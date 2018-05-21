package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.PharmacyMed;

import java.util.List;

public interface PharmacyMedService {

    /**
     * 分页获取列表
     */
    public PageInfo<PharmacyMed> listPage(PageInfo page, PharmacyMed pharmacyMed);

    /**
     * 获取不分页数据
     */
    public List<PharmacyMed> getList(PharmacyMed pharmacyMed);

    /**
     * 分页获取有库存的数据
     */
    public PageInfo<PharmacyMed> listAllHaveStock(PageInfo page, PharmacyMed pharmacyMed);

    /**
     *插入一条新的数据
     */
    public boolean saveNewPharmacyMed(PharmacyMed pharmacyMed);

    /**
     * 更新
     */
    public boolean updatePharmacyMed(PharmacyMed pharmacyMed);

    /**
     * 根据ID删除一条数据
     */
    public boolean removePharmacyMedById(Integer id);

    /**
     * 根据名称和药房更新库存
     */
    public boolean updateStockByNmaeAndDepart(PharmacyMed pharmacyMed);

    public boolean reduceStockByNmaeAndDepart(PharmacyMed pharmacyMed);

}
