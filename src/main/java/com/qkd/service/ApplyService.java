package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.Apply;

import java.util.List;

public interface ApplyService {

    /**
     * 分页获取列表
     */
    public PageInfo<Apply> listPage(PageInfo page, Apply apply);

    /**
     * 获取除待提交的数据
     */
    public PageInfo<Apply> listPageExceptNoSub(PageInfo page, Apply apply);

    /**
     * 获取不分页数据
     */
    public List<Apply> getList(Apply apply);

    /**
     *插入一条新的数据
     */
    public boolean saveNewApply(Apply apply);

    /**
     * 更新
     */
    public boolean updateApply(Apply apply);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeApplyById(Integer id);

}
