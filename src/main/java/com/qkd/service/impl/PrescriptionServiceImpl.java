package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.PrescriptionMapper;
import com.qkd.entity.Prescription;
import com.qkd.service.PrescriptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("PrescriptionService")
public class PrescriptionServiceImpl implements PrescriptionService {

    @Resource(name = "PrescriptionMapper")
    private PrescriptionMapper prescriptionMapper;

    /**
     * 分页获取列表
     */
    @Override
    public PageInfo<Prescription> listPage(PageInfo page, Prescription prescription) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Prescription> list = prescriptionMapper.listAll(prescription);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取除待提交的数据
     */
    @Override
    public PageInfo<Prescription> listPageExceptNoSub(PageInfo page, Prescription prescription) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<Prescription> list = prescriptionMapper.listAll(prescription);
        List<Prescription> exceptNoSub = new ArrayList<Prescription>();
        int len = list.size();
        for(int i=0;i<len;i++){
            if(list.get(i).getStatus() != null){
                String status = list.get(i).getStatus();
                if(status.equals("已开药") || status.equals("已发药")){
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
    public List<Prescription> getList(Prescription prescription){
        List<Prescription> list = prescriptionMapper.listAll(prescription);
        return list;
    }

    /**
     * 插入
     */
    public boolean saveNewPrescription(Prescription prescription){
        return prescriptionMapper.saveNewPrescription(prescription);
    }

    /**
     * 更新
     */
    public boolean updatePrescription(Prescription prescription){
        return prescriptionMapper.updateByPrimaryKeySelective(prescription);
    }

    public boolean updateStatusBySerno(Prescription prescription){
        return prescriptionMapper.updateStatusBySerno(prescription);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removePrescriptionById(Integer id){
        return prescriptionMapper.deleteByPrimaryKey(id);
    }

}
