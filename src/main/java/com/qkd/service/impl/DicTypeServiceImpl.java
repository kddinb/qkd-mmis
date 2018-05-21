package com.qkd.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.qkd.service.DicTypeService;
import com.qkd.dao.DicTypeMapper;
import com.qkd.entity.DicType;
import com.qkd.common.ContextHolder;
import com.qkd.constant.ParamConstants;
import org.springframework.stereotype.Service;
import com.qkd.util.JacksonUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("DicTypeService")
public class DicTypeServiceImpl implements DicTypeService {

    @Resource(name = "DicTypeMapper")
    private DicTypeMapper dicTypeMapper;

    /**
     * 分页获取药品类型列表
     */
    @Override
    public PageInfo<DicType> listPage(PageInfo page, DicType dicType) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<DicType> list = dicTypeMapper.listAll(dicType);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<DicType> getlist(DicType dicType){
        List<DicType> list = dicTypeMapper.listAll(dicType);
        return list;
    }

    /**
     * 插入新药品类型
     */
    public boolean saveNewDicType(DicType dicType){
        return dicTypeMapper.saveNewDicType(dicType);
    }

    /**
     * 更新药品类型
     */
    public boolean updateDicType(DicType dicType){
        return dicTypeMapper.updateByPrimaryKeySelective(dicType);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeDicTypeById(Integer id){
        return dicTypeMapper.deleteByPrimaryKey(id);
    }

}
