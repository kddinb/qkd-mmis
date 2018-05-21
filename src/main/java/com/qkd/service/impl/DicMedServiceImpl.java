package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.DicMedMapper;
import com.qkd.entity.DicMed;
import com.qkd.service.DicMedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("DicMedService")
public class DicMedServiceImpl implements DicMedService {

    @Resource(name = "DicMedMapper")
    private DicMedMapper DicMedMapper;

    /**
     * 分页获取列表
     */
    @Override
    public PageInfo<DicMed> listPage(PageInfo page, DicMed dicMed) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<DicMed> list = DicMedMapper.listAll(dicMed);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<DicMed> getList(DicMed dicMed){
        List<DicMed> list = DicMedMapper.listAll(dicMed);
        return list;
    }

    /**
     * 插入
     */
    public boolean saveNewDicMed(DicMed dicMed){
        return DicMedMapper.saveNewDicMed(dicMed);
    }

    /**
     * 更新
     */
    public boolean updateDicMed(DicMed dicMed){
        return DicMedMapper.updateByPrimaryKeySelective(dicMed);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeDicMedById(Integer id){
        return DicMedMapper.deleteByPrimaryKey(id);
    }

}
