package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.StorMedMapper;
import com.qkd.entity.StorMed;
import com.qkd.service.StorMedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("StorMedService")
public class StorMedServiceImpl implements StorMedService {

    @Resource(name = "StorMedMapper")
    private StorMedMapper storMedMapper;

    /**
     * 分页获取列表
     */
    @Override
    public PageInfo<StorMed> listPage(PageInfo page, StorMed storMed) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<StorMed> list = storMedMapper.listAll(storMed);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 分页获取药房中不存在的数据
     */
    @Override
    public PageInfo<StorMed> listFiltPage(PageInfo page, StorMed storMed) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<StorMed> list = storMedMapper.listAllFilt(storMed);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<StorMed> getList(StorMed storMed){
        List<StorMed> list = storMedMapper.listAll(storMed);
        return list;
    }

    /**
     * 插入
     */
    public boolean saveNewStorMed(StorMed storMed){
        return storMedMapper.saveNewStorMed(storMed);
    }

    /**
     * 更新
     */
    public boolean updateStorMed(StorMed storMed){
        return storMedMapper.updateByPrimaryKeySelective(storMed);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeStorMedById(Integer id){
        return storMedMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据名称和库房更新库存
     */
    public boolean updateStockByNmaeAndDepart(StorMed storMed){
        return storMedMapper.updateStockByNmaeAndDepart(storMed);
    }

    public boolean reduceStockByNmaeAndDepart(StorMed storMed){
        return storMedMapper.reduceStockByNmaeAndDepart(storMed);
    }

}
