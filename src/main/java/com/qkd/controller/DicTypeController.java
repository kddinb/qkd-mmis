package com.qkd.controller;
import com.qkd.entity.DicType;
import com.qkd.entity.PageRequest;
import com.qkd.service.DicTypeService;
import com.qkd.util.PageUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkd.util.JsonResult;
import com.qkd.constant.DescribableEnum;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dicType")
public class DicTypeController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "DicTypeService")
    private DicTypeService dicTypeService;

    @RequestMapping("/getDicType")
    @ResponseBody
    public JsonResult getDicType(PageRequest pageRequest, DicType dicType) {
        PageInfo<DicType> page = PageUtil.getPage(pageRequest);
        page = dicTypeService.listPage(page, dicType);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getDicTypeRow")
    @ResponseBody
    public List<DicType> getDicTypeRow(DicType dicType){
        List<DicType> dicTypeList = dicTypeService.getlist(dicType);
        return dicTypeList;
    }

    @RequestMapping("/saveDicType")
    @ResponseBody
    public Map<String, Object> saveDicType(DicType dicType) {

        Map<String, Object> map = new HashedMap();
        if(dicType.getId() == null){
            boolean isSuccess = dicTypeService.saveNewDicType(dicType);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = dicTypeService.updateDicType(dicType);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removeDicType")
    @ResponseBody
    public Map<String, Object> removeDicType(DicType dicType) {

        Map<String, Object> map = new HashedMap();
        System.out.println("...................."+dicType);
        Integer id = dicType.getId();
        boolean isSuccess = dicTypeService.removeDicTypeById(id);
        map.put("result",isSuccess);

        return map;
    }

}












