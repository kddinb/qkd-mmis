package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.PharmacyMedMapper;
import com.qkd.entity.PharmacyMed;
import com.qkd.service.PharmacyMedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("PharmacyMedService")
public class PharmacyMedServiceImpl implements PharmacyMedService {

    @Resource(name = "PharmacyMedMapper")
    private PharmacyMedMapper pharmacyMedMapper;

    /**
     * 分页获取列表
     */
    @Override
    public PageInfo<PharmacyMed> listPage(PageInfo page, PharmacyMed pharmacyMed) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<PharmacyMed> list = pharmacyMedMapper.listAll(pharmacyMed);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<PharmacyMed> getList(PharmacyMed pharmacyMed){
        List<PharmacyMed> list = pharmacyMedMapper.listAll(pharmacyMed);
        return list;
    }

    /**
     * 分页获取有库存的数据
     */
    @Override
    public PageInfo<PharmacyMed> listAllHaveStock(PageInfo page, PharmacyMed pharmacyMed) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<PharmacyMed> list = pharmacyMedMapper.listAllHaveStock(pharmacyMed);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 插入
     */
    public boolean saveNewPharmacyMed(PharmacyMed pharmacyMed){
        return pharmacyMedMapper.saveNewPharmacyMed(pharmacyMed);
    }

    /**
     * 更新
     */
    public boolean updatePharmacyMed(PharmacyMed pharmacyMed){
        return pharmacyMedMapper.updateByPrimaryKeySelective(pharmacyMed);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removePharmacyMedById(Integer id){
        return pharmacyMedMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据名称和药房更新库存
     */
    public boolean updateStockByNmaeAndDepart(PharmacyMed pharmacyMed){
        return pharmacyMedMapper.updateStockByNmaeAndDepart(pharmacyMed);
    }

    public boolean reduceStockByNmaeAndDepart(PharmacyMed pharmacyMed){
        return pharmacyMedMapper.reduceStockByNmaeAndDepart(pharmacyMed);
    }

}
