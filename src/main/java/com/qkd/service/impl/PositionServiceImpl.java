package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.PositionMapper;
import com.qkd.entity.Position;
import com.qkd.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("PositionService")
public class PositionServiceImpl implements PositionService {

    @Resource(name = "PositionMapper")
    private PositionMapper positionMapper;

    /**
     * 分页获取列表
     */
    @Override
    public PageInfo<Position> listPage(PageInfo page, Position position) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Position> list = positionMapper.listAll(position);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<Position> getList(Position position){
        List<Position> list = positionMapper.listAll(position);
        return list;
    }

    /**
     * 插入
     */
    public boolean saveNewPosition(Position position){
        return positionMapper.saveNewPosition(position);
    }

    /**
     * 更新
     */
    public boolean updatePosition(Position position){
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removePositionById(Integer id){
        return positionMapper.deleteByPrimaryKey(id);
    }

}
