package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.Prescription;

import java.util.List;

public interface PrescriptionService {

    /**
     * 分页获取列表
     */
    public PageInfo<Prescription> listPage(PageInfo page, Prescription prescription);

    /**
     * 获取未发药和已发药数据
     */
    public PageInfo<Prescription> listPageExceptNoSub(PageInfo page, Prescription prescription);

    /**
     * 获取不分页数据
     */
    public List<Prescription> getList(Prescription prescription);

    /**
     *插入一条新的数据
     */
    public boolean saveNewPrescription(Prescription prescription);

    /**
     * 更新
     */
    public boolean updatePrescription(Prescription prescription);

    public boolean updateStatusBySerno(Prescription prescription);

    /**
     * 根据ID删除一条数据
     */
    public boolean removePrescriptionById(Integer id);

}
