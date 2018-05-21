package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.DicMed;
import com.qkd.entity.PageRequest;
import com.qkd.entity.StorMed;
import com.qkd.service.DicMedService;
import com.qkd.service.StorMedService;
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
@RequestMapping("/storMed")
public class StorMedController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "StorMedService")
    private StorMedService storMedService;
    @Resource(name = "DicMedService")
    private DicMedService dicMedService;

    @RequestMapping("/getStorMed")
    @ResponseBody
    public JsonResult getStorMed(PageRequest pageRequest, StorMed storMed) {
        if(storMed.getDepartId() == null || "".equals(storMed.getDepartId())){
            storMed.setDepartId("西药库");
        }
        PageInfo<StorMed> page = PageUtil.getPage(pageRequest);
        page = storMedService.listPage(page, storMed);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getStorMedFilt")
    @ResponseBody
    public JsonResult getStorMedFilt(PageRequest pageRequest, StorMed storMed) {
        if(storMed.getDepartId() == null || "".equals(storMed.getDepartId())){
            storMed.setDepartId("西药库");
        }
        PageInfo<StorMed> page = PageUtil.getPage(pageRequest);
        page = storMedService.listFiltPage(page, storMed);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getAllStorMed")
    @ResponseBody
    public JsonResult getAllStorMed(PageRequest pageRequest, StorMed storMed) {
        PageInfo<StorMed> page = PageUtil.getPage(pageRequest);
        page = storMedService.listPage(page, storMed);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getStorMedRow")
    @ResponseBody
    public List<StorMed> getStorMedRow(StorMed storMed){
        List<StorMed> storMedList = storMedService.getList(storMed);

        return storMedList;
    }

    @RequestMapping("/saveStorMed")
    @ResponseBody
    public Map<String, Object> saveStorMed(StorMed storMed) {

        Map<String, Object> map = new HashedMap();
        if(storMed.getId() == null){
            boolean isSuccess = storMedService.saveNewStorMed(storMed);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = storMedService.updateStorMed(storMed);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/updateStockByNmaeAndDepart")
    @ResponseBody
    public boolean updateStockByNmaeAndDepart(StorMed storMed) {

        boolean isSuccess = storMedService.updateStockByNmaeAndDepart(storMed);
        return isSuccess;
    }

    @RequestMapping("/removeStorMed")
    @ResponseBody
    public Map<String, Object> removeStorMed(StorMed storMed) {

        Map<String, Object> map = new HashedMap();
        Integer id = storMed.getId();
        boolean isSuccess = storMedService.removeStorMedById(id);
        map.put("result",isSuccess);

        return map;
    }

    @RequestMapping("/synDicMed")
    @ResponseBody
    public Map<String, Object> synDicMed() {
        StorMed storMed = new StorMed();
        DicMed dicMed = new DicMed();
        List<StorMed> storMedList = storMedService.getList(storMed);
        List<DicMed> dicMedList = dicMedService.getList(dicMed);
        int stlen = storMedList.size();
        int diclen = dicMedList.size();
        int flag = 0;
        int wm = 0;
        int cm = 0;
        Map<String, Object> map = new HashedMap();
        boolean isSuccess = false;
        Integer stormedId = null;
        for(int i=0;i<diclen;i++){
            DicMed dicMed1 = dicMedList.get(i);
            flag = 0;
            wm = 0;
            cm = 0;
            stormedId = null;
            for(int j=0;j<stlen;j++){
                StorMed storMed1 = storMedList.get(j);
                stormedId = storMed1.getId();
                if(dicMed1.getNAME().equals(storMed1.getNmae())){
                    StorMed updataStorMed = new StorMed();
                    updataStorMed.setId(stormedId);
                    updataStorMed.setCode(dicMed1.getCode());
                    updataStorMed.setEfid(dicMed1.getEfid());
                    updataStorMed.setGnameId(dicMed1.getGnameId());
                    updataStorMed.setPharId(dicMed1.getPharId());
                    updataStorMed.setTypeId(dicMed1.getTypeId());
                    updataStorMed.setPackNum(dicMed1.getPackNum());
                    updataStorMed.setPackUnit(dicMed1.getPackUnit());
                    updataStorMed.setSupplierId(dicMed1.getSupplierId());
                    storMedService.updateStorMed(updataStorMed);
                    if(storMed1.getDepartId().equals("西药库")){
                        wm = 1;
                    }else if(storMed1.getDepartId().equals("中药库")){
                        cm = 1;
                    }
                    flag += 1;
                }
            }
            if(flag == 1){
                if(dicMed1.getwmUsed().equals("可用")&&dicMed1.getcmUsed().equals("可用")){
                    StorMed saveStorMed = new StorMed();
                    saveStorMed.setNmae(dicMed1.getNAME());
                    saveStorMed.setCode(dicMed1.getCode());
                    saveStorMed.setEfid(dicMed1.getEfid());
                    saveStorMed.setGnameId(dicMed1.getGnameId());
                    saveStorMed.setPharId(dicMed1.getPharId());
                    saveStorMed.setTypeId(dicMed1.getTypeId());
                    saveStorMed.setPackNum(dicMed1.getPackNum());
                    saveStorMed.setPackUnit(dicMed1.getPackUnit());
                    saveStorMed.setRemark(dicMed1.getRemark());
                    saveStorMed.setStock((long)0);
                    saveStorMed.setRetailPrice(dicMed1.getRetailPrice());
                    saveStorMed.setSupplierId(dicMed1.getSupplierId());
                    if(wm == 0){
                        saveStorMed.setDepartId("西药库");
                    }else if(cm == 0){
                        saveStorMed.setDepartId("中药库");
                    }
                    storMedService.saveNewStorMed(saveStorMed);
                }
            }
            if(flag == 0){
                StorMed saveStorMed = new StorMed();
                saveStorMed.setNmae(dicMed1.getNAME());
                saveStorMed.setCode(dicMed1.getCode());
                saveStorMed.setEfid(dicMed1.getEfid());
                saveStorMed.setGnameId(dicMed1.getGnameId());
                saveStorMed.setPharId(dicMed1.getPharId());
                saveStorMed.setTypeId(dicMed1.getTypeId());
                saveStorMed.setPackNum(dicMed1.getPackNum());
                saveStorMed.setPackUnit(dicMed1.getPackUnit());
                saveStorMed.setRemark(dicMed1.getRemark());
                saveStorMed.setStock((long)0);
                saveStorMed.setRetailPrice(dicMed1.getRetailPrice());
                saveStorMed.setSupplierId(dicMed1.getSupplierId());
                if(dicMed1.getcmUsed().equals("可用")){
                    saveStorMed.setDepartId("中药库");
                    storMedService.saveNewStorMed(saveStorMed);
                }
                if(dicMed1.getwmUsed().equals("可用")){
                    saveStorMed.setDepartId("西药库");
                    storMedService.saveNewStorMed(saveStorMed);
                }
            }else{
                flag = 0;
            }
        }
        isSuccess = true;
        map.put("result",isSuccess);
        return map;
    }

}












