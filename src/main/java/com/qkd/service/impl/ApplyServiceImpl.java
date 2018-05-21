package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.ApplyMapper;
import com.qkd.entity.Apply;
import com.qkd.service.ApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("ApplyService")
public class ApplyServiceImpl implements ApplyService {

    @Resource(name = "ApplyMapper")
    private ApplyMapper applyMapper;

    /**
     * 分页获取列表
     */
    @Override
    public PageInfo<Apply> listPage(PageInfo page, Apply apply) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Apply> list = applyMapper.listAll(apply);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取除待提交的数据
     */
    @Override
    public PageInfo<Apply> listPageExceptNoSub(PageInfo page, Apply apply) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Apply> list = applyMapper.listAll(apply);
        List<Apply> exceptNoSub = new ArrayList<Apply>();
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
    public List<Apply> getList(Apply apply){
        List<Apply> list = applyMapper.listAll(apply);
        return list;
    }

    /**
     * 插入
     */
    public boolean saveNewApply(Apply apply){
        return applyMapper.saveNewApply(apply);
    }

    /**
     * 更新
     */
    public boolean updateApply(Apply apply){
        return applyMapper.updateByPrimaryKeySelective(apply);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeApplyById(Integer id){
        return applyMapper.deleteByPrimaryKey(id);
    }

}
