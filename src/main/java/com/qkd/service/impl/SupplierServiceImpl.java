package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.SupplierMapper;
import com.qkd.entity.Supplier;
import com.qkd.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("SupplierService")
public class SupplierServiceImpl implements SupplierService {

    @Resource(name = "SupplierMapper")
    private SupplierMapper supplierMapper;

    /**
     * 分页获取供应商列表
     */
    @Override
    public PageInfo<Supplier> listPage(PageInfo page, Supplier supplier) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Supplier> list = supplierMapper.listAll(supplier);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<Supplier> getList(Supplier supplier){
        List<Supplier> list = supplierMapper.listAll(supplier);
        return list;
    }

    /**
     * 插入新供应商
     */
    public boolean saveNewSupplier(Supplier supplier){
        return supplierMapper.saveNewsupplier(supplier);
    }

    /**
     * 更新供应商
     */
    public boolean updateSupplier(Supplier supplier){
        return supplierMapper.updateByPrimaryKeySelective(supplier);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeSupplierById(Integer id){
        return supplierMapper.deleteByPrimaryKey(id);
    }

}
