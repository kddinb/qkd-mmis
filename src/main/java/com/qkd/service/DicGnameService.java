package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.DicGname;

import java.util.List;

public interface DicGnameService {

    /**
     * 分页获取列表
     */
    public PageInfo<DicGname> listPage(PageInfo page, DicGname dicGname);

    /**
     * 获取不分页数据
     */
    public List<DicGname> getList(DicGname dicGname);

    /**
     * 根据String获取一条数据
     */
    public List<DicGname> getOneGname(String name);

    /**
     *插入一条新的数据
     */
    public boolean saveNewDicGname(DicGname dicGname);

    /**
     * 更新
     */
    public boolean updateDicGname(DicGname dicGname);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeDicGnameById(Integer id);

}
