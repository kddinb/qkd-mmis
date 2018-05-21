package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.DicPhar;

import java.util.List;

public interface DicPharService {

    /**
     * 分页获取列表
     */
    public PageInfo<DicPhar> listPage(PageInfo page, DicPhar dicPhar);

    /**
     * 获取不分页数据
     */
    public List<DicPhar> getList(DicPhar dicPhar);

    /**
     *插入一条新的数据
     */
    public boolean saveNewDicPhar(DicPhar dicPhar);

    /**
     * 更新
     */
    public boolean updateDicPhar(DicPhar dicPhar);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeDicPharById(Integer id);

    public List<DicPhar> listPhar(DicPhar dicPhar);

}
