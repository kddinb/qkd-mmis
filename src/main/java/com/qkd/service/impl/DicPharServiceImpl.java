package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.DicPharMapper;
import com.qkd.entity.DicPhar;
import com.qkd.service.DicPharService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service("DicPharService")
public class DicPharServiceImpl implements DicPharService {

    @Resource(name = "DicPharMapper")
    private DicPharMapper dicPharMapper;

    /**
     * 分页获取列表
     */
    @Override
    public PageInfo<DicPhar> listPage(PageInfo page, DicPhar dicPhar) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<DicPhar> list = dicPharMapper.listAll(dicPhar);
        int size = list.size();
        for(int i = 0;i<size;i++){
            DicPhar dicPhar1 = list.get(i);
            Integer parent = dicPhar1.getParentId();
            if(parent != null){
                dicPhar1.set_parentId(parent);
            }
            dicPhar1.setState("open");
        }
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<DicPhar> getList(DicPhar dicPhar){
        List<DicPhar> list = dicPharMapper.listAll(dicPhar);
        int size = list.size();
        for(int i = 0;i<size;i++){
            DicPhar dicPhar1 = list.get(i);
            Integer parent = dicPhar1.getParentId();
            if(parent != null){
                dicPhar1.set_parentId(parent);
            }
            dicPhar1.setState("open");
        }

        return list;
    }

    public List<DicPhar> listPhar (DicPhar dicPhar){
        List<DicPhar> list = dicPharMapper.listAll(dicPhar);
        int size = list.size();
        for(int i = 0;i<size;i++){
            DicPhar dicPhar1 = list.get(i);
            Integer parent = dicPhar1.getParentId();
            dicPhar1.set_parentId(parent);
            dicPhar1.setState("open");
        }
        return list;
    }

    /**
     * 插入
     */
    public boolean saveNewDicPhar(DicPhar dicPhar){
        return dicPharMapper.saveNewDicPhar(dicPhar);
    }

    /**
     * 更新
     */
    public boolean updateDicPhar(DicPhar dicPhar){
        return dicPharMapper.updateByPrimaryKeySelective(dicPhar);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeDicPharById(Integer id){
        return dicPharMapper.deleteByPrimaryKey(id);
    }

}
