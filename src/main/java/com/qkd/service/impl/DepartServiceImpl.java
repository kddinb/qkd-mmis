package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.DepartMapper;
import com.qkd.entity.Depart;
import com.qkd.service.DepartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("DepartService")
public class DepartServiceImpl implements DepartService {

    @Resource(name = "DepartMapper")
    private DepartMapper departMapper;

    /**
     * 分页获取部门列表
     */
    @Override
    public PageInfo<Depart> listPage(PageInfo page, Depart depart) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Depart> list = departMapper.listAll(depart);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<Depart> getList(Depart depart){
        List<Depart> list = departMapper.listAll(depart);
        return list;
    }

    /**
     * 插入新部门
     */
    public boolean saveNewDepart(Depart depart){
        return departMapper.saveNewDepart(depart);
    }

    /**
     * 更新部门数据
     */
    public boolean updateDepart(Depart depart){
        return departMapper.updateByPrimaryKeySelective(depart);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeDepartById(Integer id){
        return departMapper.deleteByPrimaryKey(id);
    }

}
