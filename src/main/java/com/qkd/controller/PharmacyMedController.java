package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.DicMed;
import com.qkd.entity.PageRequest;
import com.qkd.entity.PharmacyForm;
import com.qkd.entity.PharmacyMed;
import com.qkd.service.DicMedService;
import com.qkd.service.PharmacyMedService;
import com.qkd.util.JsonResult;
import com.qkd.util.PageUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pharmacyMed")
public class PharmacyMedController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "PharmacyMedService")
    private PharmacyMedService pharmacyMedService;
    @Resource(name = "DicMedService")
    private DicMedService dicMedService;

    @RequestMapping("/getPharmacyMed")
    @ResponseBody
    public JsonResult getPharmacyMed(PageRequest pageRequest, PharmacyMed pharmacyMed) {
        if(pharmacyMed.getDepartId() == null || "".equals(pharmacyMed.getDepartId())){
            pharmacyMed.setDepartId("西药房");
        }
        PageInfo<PharmacyMed> page = PageUtil.getPage(pageRequest);
        page = pharmacyMedService.listPage(page, pharmacyMed);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getAllPharmacyMed")
    @ResponseBody
    public JsonResult getAllPharmacyMed(PageRequest pageRequest, PharmacyMed pharmacyMed) {
        PageInfo<PharmacyMed> page = PageUtil.getPage(pageRequest);
        page = pharmacyMedService.listPage(page, pharmacyMed);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getHaveStockPharmacyMed")
    @ResponseBody
    public JsonResult getHaveStockPharmacyMed(PageRequest pageRequest, PharmacyMed pharmacyMed) {
        PageInfo<PharmacyMed> page = PageUtil.getPage(pageRequest);
        page = pharmacyMedService.listAllHaveStock(page, pharmacyMed);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getPharmacyMedRow")
    @ResponseBody
    public List<PharmacyMed> getPharmacyMedRow(PharmacyMed pharmacyMed){
        List<PharmacyMed> PharmacyMedList = pharmacyMedService.getList(pharmacyMed);

        return PharmacyMedList;
    }

    @RequestMapping("/savePharmacyMed")
    @ResponseBody
    public Map<String, Object> savePharmacyMed(@RequestBody List<PharmacyMed> pharmacyMedList) {

        Map<String, Object> map = new HashedMap();
        boolean isAllSuccess = true;
        int len = pharmacyMedList.size();
        for(int i=0;i<len;i++){
            PharmacyMed pharmacyMed = pharmacyMedList.get(i);
            boolean isSuccess = pharmacyMedService.saveNewPharmacyMed(pharmacyMed);
            if(isSuccess == false){
                isAllSuccess = false;
                break;
            }
        }
        map.put("result",isAllSuccess);
        return map;
    }

    @RequestMapping("/savePharmacyMed2")
    @ResponseBody
    public Map<String, Object> savePharmacyMed2(PharmacyMed pharmacyMed) {

        Map<String, Object> map = new HashedMap();
        boolean isSuccess = pharmacyMedService.saveNewPharmacyMed(pharmacyMed);
        map.put("result",isSuccess);
        return map;
    }

    @RequestMapping("/updateStockByNmaeAndDepart")
    @ResponseBody
    public boolean updateStockByNmaeAndDepart(PharmacyMed pharmacyMed) {

        boolean isSuccess = pharmacyMedService.updateStockByNmaeAndDepart(pharmacyMed);
        return isSuccess;
    }

    @RequestMapping("/removePharmacyMed")
    @ResponseBody
    public Map<String, Object> removePharmacyMed(PharmacyMed pharmacyMed) {

        Map<String, Object> map = new HashedMap();
        Integer id = pharmacyMed.getId();
        boolean isSuccess = pharmacyMedService.removePharmacyMedById(id);
        map.put("result",isSuccess);

        return map;
    }


}












