package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.PageRequest;
import com.qkd.entity.Depart;
import com.qkd.service.DepartService;
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
@RequestMapping("/Depart")
public class DepartController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "DepartService")
    private DepartService departService;

    @RequestMapping("/getDepart")
    @ResponseBody
    public JsonResult getDicType(PageRequest pageRequest, Depart depart) {
        PageInfo<Depart> page = PageUtil.getPage(pageRequest);
        page = departService.listPage(page, depart);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getDepartRow")
    @ResponseBody
    public List<Depart> getDepartRow(Depart depart){
        List<Depart> departList = departService.getList(depart);

        return departList;
    }

    @RequestMapping("/saveDepart")
    @ResponseBody
    public Map<String, Object> saveDicType(Depart depart) {

        Map<String, Object> map = new HashedMap();
        if(depart.getId() == null){
            boolean isSuccess = departService.saveNewDepart(depart);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = departService.updateDepart(depart);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removeDepart")
    @ResponseBody
    public Map<String, Object> removeDicType(Depart depart) {

        Map<String, Object> map = new HashedMap();
        Integer id = depart.getId();
        boolean isSuccess = departService.removeDepartById(id);
        map.put("result",isSuccess);

        return map;
    }

}












