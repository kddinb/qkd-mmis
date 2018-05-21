package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.PageRequest;
import com.qkd.entity.Position;
import com.qkd.service.PositionService;
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
@RequestMapping("/position")
public class PositionController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "PositionService")
    private PositionService positionService;

    @RequestMapping("/getPosition")
    @ResponseBody
    public JsonResult getPosition(PageRequest pageRequest, Position position) {
        PageInfo<Position> page = PageUtil.getPage(pageRequest);
        page = positionService.listPage(page, position);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getPositionRow")
    @ResponseBody
    public List<Position> getPositionRow(Position position){
        List<Position> positionList = positionService.getList(position);

        return positionList;
    }


    @RequestMapping("/savePosition")
    @ResponseBody
    public Map<String, Object> savePosition(Position position) {

        Map<String, Object> map = new HashedMap();
        if(position.getId() == null){
            boolean isSuccess = positionService.saveNewPosition(position);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = positionService.updatePosition(position);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removePosition")
    @ResponseBody
    public Map<String, Object> removePosition(Position position) {

        Map<String, Object> map = new HashedMap();
        Integer id = position.getId();
        boolean isSuccess = positionService.removePositionById(id);
        map.put("result",isSuccess);

        return map;
    }

}












