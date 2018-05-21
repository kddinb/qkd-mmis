package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.Position;

import java.util.List;

public interface PositionService {

    /**
     * 分页获取列表
     */
    public PageInfo<Position> listPage(PageInfo page, Position position);

    /**
     * 获取不分页数据
     */
    public List<Position> getList(Position position);

    /**
     *插入一条新的数据
     */
    public boolean saveNewPosition(Position position);

    /**
     * 更新
     */
    public boolean updatePosition(Position position);

    /**
     * 根据ID删除一条数据
     */
    public boolean removePositionById(Integer id);

}
