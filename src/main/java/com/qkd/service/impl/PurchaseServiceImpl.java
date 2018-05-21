package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.PurchaseMapper;
import com.qkd.entity.Purchase;
import com.qkd.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("PurchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Resource(name = "PurchaseMapper")
    private PurchaseMapper purchaseMapper;

    /**
     * 分页获取列表
     */
    @Override
    public PageInfo<Purchase> listPage(PageInfo page, Purchase purchase) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Purchase> list = purchaseMapper.listAll(purchase);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取除待提交的数据
     */
    @Override
    public PageInfo<Purchase> listPageExceptNoSub(PageInfo page, Purchase purchase) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Purchase> list = purchaseMapper.listAll(purchase);
        List<Purchase> exceptNoSub = new ArrayList<Purchase>();
        int len = list.size();
        for(int i=0;i<len;i++){
            if(list.get(i).getStatus() != null){
                String status = list.get(i).getStatus();
                if(!status.equals("待提交") && !status.equals("驳回")){
                    exceptNoSub.add(list.get(i));
                }
            }
        }
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(exceptNoSub);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<Purchase> getList(Purchase purchase){
        List<Purchase> list = purchaseMapper.listAll(purchase);
        return list;
    }

    /**
     * 插入
     */
    public boolean saveNewPurchase(Purchase purchase){
        return purchaseMapper.saveNewPurchase(purchase);
    }

    /**
     * 更新
     */
    public boolean updatePurchase(Purchase purchase){
        return purchaseMapper.updateByPrimaryKeySelective(purchase);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removePurchaseById(Integer id){
        return purchaseMapper.deleteByPrimaryKey(id);
    }

}
