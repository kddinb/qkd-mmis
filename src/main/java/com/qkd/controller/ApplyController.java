package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.PageRequest;
import com.qkd.entity.Apply;
import com.qkd.entity.PharmacyMed;
import com.qkd.entity.StorMed;
import com.qkd.service.ApplyService;
import com.qkd.service.PharmacyMedService;
import com.qkd.service.StorMedService;
import com.qkd.service.UserService;
import com.qkd.util.JsonResult;
import com.qkd.util.PageUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/apply")
public class ApplyController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "ApplyService")
    private ApplyService applyService;

    @Resource(name = "PharmacyMedService")
    private PharmacyMedService pharmacyMedService;

    @Resource(name = "StorMedService")
    private StorMedService storMedService;

    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/getApply")
    @ResponseBody
    public JsonResult getApply(PageRequest pageRequest, Apply apply) {
        PageInfo<Apply> page = PageUtil.getPage(pageRequest);
        page = applyService.listPage(page, apply);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getExceptNoSub")
    @ResponseBody
    public JsonResult getExceptNoSub(PageRequest pageRequest, Apply apply) {
        PageInfo<Apply> page = PageUtil.getPage(pageRequest);
        page = applyService.listPageExceptNoSub(page, apply);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getApplyRow")
    @ResponseBody
    public List<Apply> getApplyRow(Apply apply){
        List<Apply> ApplyList = applyService.getList(apply);

        return ApplyList;
    }

    @RequestMapping("/saveApply")
    @ResponseBody
    public Map<String, Object> saveApply(Apply apply) {

        Map<String, Object> map = new HashedMap();
        if(apply.getId() == null){
            String useName = userService.getLoginName();
            apply.setUserId(useName);
            boolean isSuccess = applyService.saveNewApply(apply);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = applyService.updateApply(apply);
            if(apply.getStatus()!=null && apply.getStatus().equals("通过")){
                PharmacyMed pharmacyMed = new PharmacyMed();
                pharmacyMed.setDepartId(apply.getDepartId());
                pharmacyMed.setNmae(apply.getMedId());
                pharmacyMed.setStock(apply.getAmount());
                isSuccess = pharmacyMedService.updateStockByNmaeAndDepart(pharmacyMed);
                StorMed storMed = new StorMed();
                if(apply.getDepartId().equals("西药房")){
                    storMed.setDepartId("西药库");
                }else{
                    storMed.setDepartId("中药库");
                }
                storMed.setNmae(apply.getMedId());
                storMed.setStock(apply.getAmount());
                isSuccess = storMedService.reduceStockByNmaeAndDepart(storMed);
            }
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removeApply")
    @ResponseBody
    public Map<String, Object> removeApply(Apply apply) {

        Map<String, Object> map = new HashedMap();
        Integer id = apply.getId();
        boolean isSuccess = applyService.removeApplyById(id);
        map.put("result",isSuccess);

        return map;
    }

}












