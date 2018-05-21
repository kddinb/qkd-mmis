package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.DicGnameMapper;
import com.qkd.entity.DicGname;
import com.qkd.service.DicGnameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("DicGnameService")
public class DicGnameServiceImpl implements DicGnameService {

    @Resource(name = "DicGnameMapper")
    private DicGnameMapper dicGnameMapper;

    /**
     * 分页获取通用名列表
     */
    @Override
    public PageInfo<DicGname> listPage(PageInfo page, DicGname dicGname) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<DicGname> list = dicGnameMapper.listAll(dicGname);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<DicGname> getList(DicGname dicGname){
        List<DicGname> list = dicGnameMapper.listAll(dicGname);
        return list;
    }

    /**
     *根据String获取一条数据
     */
    public List<DicGname> getOneGname(String name){
        List<DicGname> list = dicGnameMapper.getOneGname(name);
        return list;
    }

    /**
     * 插入新通用名
     */
    public boolean saveNewDicGname(DicGname dicGname){
        return dicGnameMapper.saveNewDicGname(dicGname);
    }

    /**
     * 更新通用名
     */
    public boolean updateDicGname(DicGname dicGname){
        return dicGnameMapper.updateByPrimaryKeySelective(dicGname);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeDicGnameById(Integer id){
        return dicGnameMapper.deleteByPrimaryKey(id);
    }

}
