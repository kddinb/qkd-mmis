package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.DicPhar;
import com.qkd.entity.PageRequest;
import com.qkd.service.DicPharService;
import com.qkd.util.JsonResult;
import com.qkd.util.PageUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dicPhar")
public class DicPharController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "DicPharService")
    private DicPharService dicPharService;

    @RequestMapping("/getDicPhar")
    @ResponseBody
    public JsonResult getDicPhar(PageRequest pageRequest, DicPhar dicPhar) {
        PageInfo<DicPhar> page = PageUtil.getPage(pageRequest);
        page = dicPharService.listPage(page, dicPhar);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getDicPharRow")
    @ResponseBody
    public List<DicPhar> getDicPharRow(DicPhar dicPhar){
        List<DicPhar> dicPharList = dicPharService.getList(dicPhar);

        return dicPharList;
    }

    @RequestMapping("/getDicPhar2")
    @ResponseBody
    public Map<String, Object> getDicPhar2(DicPhar dicPhar) {
        List<DicPhar> list = dicPharService.listPhar(dicPhar);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows",list);

        return map;
    }

    @RequestMapping("/saveDicPhar")
    @ResponseBody
    public Map<String, Object> saveDicPhar(DicPhar dicPhar) {

        Map<String, Object> map = new HashedMap();
        if(dicPhar.getId() == null){
            boolean isSuccess = dicPharService.saveNewDicPhar(dicPhar);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = dicPharService.updateDicPhar(dicPhar);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removeDicPhar")
    @ResponseBody
    public Map<String, Object> removeDicPhar(DicPhar dicPhar) {

        Map<String, Object> map = new HashedMap();
        Integer id = dicPhar.getId();
        boolean isSuccess = dicPharService.removeDicPharById(id);
        map.put("result",isSuccess);

        return map;
    }

}












