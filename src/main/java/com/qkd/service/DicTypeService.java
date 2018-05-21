package com.qkd.service;
import com.qkd.entity.DicType;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface DicTypeService {

    /**
     * 分页获取药品类型列表
     */
    public PageInfo<DicType> listPage(PageInfo page, DicType dicType);

    /**
     *获取不分页数据
     */
    public List<DicType> getlist(DicType dicType);

    /**
     *插入一条新的数据
     */
    public boolean saveNewDicType(DicType dicType);

    /**
     * 更新药品类型
     */
    public boolean updateDicType(DicType dicType);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeDicTypeById(Integer id);

}
