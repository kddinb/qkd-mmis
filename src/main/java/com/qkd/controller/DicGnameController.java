package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.PageRequest;
import com.qkd.entity.DicGname;
import com.qkd.service.DicGnameService;
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
@RequestMapping("/dicGname")
public class DicGnameController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "DicGnameService")
    private DicGnameService dicGnameService;

    @RequestMapping("/getDicGname")
    @ResponseBody
    public JsonResult getDicGname(PageRequest pageRequest, DicGname dicGname) {
        PageInfo<DicGname> page = PageUtil.getPage(pageRequest);
        page = dicGnameService.listPage(page, dicGname);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getDicGnameRow")
    @ResponseBody
    public List<DicGname> getDicGnameRow(DicGname dicGname){
        List<DicGname> dicGnameList = dicGnameService.getList(dicGname);

        return dicGnameList;
    }

    @RequestMapping("/getOneDicGname")
    @ResponseBody
    public List<DicGname> getOneDicGname(String name){
        List<DicGname> dicGnameList = dicGnameService.getOneGname(name);

        return dicGnameList;
    }

    @RequestMapping("/saveDicGname")
    @ResponseBody
    public Map<String, Object> saveDicGname(DicGname dicGname) {

        Map<String, Object> map = new HashedMap();
        if(dicGname.getId() == null){
            boolean isSuccess = dicGnameService.saveNewDicGname(dicGname);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = dicGnameService.updateDicGname(dicGname);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removeSupplier")
    @ResponseBody
    public Map<String, Object> removeSupplier(DicGname dicGname) {

        Map<String, Object> map = new HashedMap();
        Integer id = dicGname.getId();
        boolean isSuccess = dicGnameService.removeDicGnameById(id);
        map.put("result",isSuccess);

        return map;
    }

}












