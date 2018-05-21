package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.PageRequest;
import com.qkd.entity.DicMed;
import com.qkd.service.DicMedService;
import com.qkd.util.JsonResult;
import com.qkd.util.PageUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/dicMed")
public class DicMedController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "DicMedService")
    private DicMedService dicMedService;

    @RequestMapping("/getDicMed")
    @ResponseBody
    public JsonResult getDicType(PageRequest pageRequest, DicMed dicMed) {
        PageInfo<DicMed> page = PageUtil.getPage(pageRequest);
        page = dicMedService.listPage(page, dicMed);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/saveDicMed")
    @ResponseBody
    public Map<String, Object> saveDicType(DicMed dicMed) {

        Map<String, Object> map = new HashedMap();
        if(dicMed.getId() == null){
            boolean isSuccess = dicMedService.saveNewDicMed(dicMed);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = dicMedService.updateDicMed(dicMed);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removeDicMed")
    @ResponseBody
    public Map<String, Object> removeDicType(DicMed dicMed) {

        Map<String, Object> map = new HashedMap();
        Integer id = dicMed.getId();
        boolean isSuccess = dicMedService.removeDicMedById(id);
        map.put("result",isSuccess);

        return map;
    }

}












