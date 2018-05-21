package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.Depart;

import java.util.List;

public interface DepartService {

    /**
     * 分页获取部门列表
     */
    public PageInfo<Depart> listPage(PageInfo page, Depart depart);

    /**
     * 获取不分页数据
     */
    public List<Depart> getList(Depart depart);

    /**
     *插入一条新的数据
     */
    public boolean saveNewDepart(Depart depart);

    /**
     * 更新部门数据
     */
    public boolean updateDepart(Depart depart);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeDepartById(Integer id);

}
