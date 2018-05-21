package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.Supplier;
import com.qkd.entity.PageRequest;
import com.qkd.service.SupplierService;
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
@RequestMapping("/supplier")
public class SupplierController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "SupplierService")
    private SupplierService supplierService;

    @RequestMapping("/getSupplier")
    @ResponseBody
    public JsonResult getDicType(PageRequest pageRequest, Supplier supplier) {
        PageInfo<Supplier> page = PageUtil.getPage(pageRequest);
        page = supplierService.listPage(page, supplier);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getSupplierRow")
    @ResponseBody
    public List<Supplier> getDicTypeRow(Supplier supplier){
        List<Supplier> supplierList = supplierService.getList(supplier);

        return supplierList;
    }

    @RequestMapping("/saveSupplier")
    @ResponseBody
    public Map<String, Object> saveDicType(Supplier supplier) {

        Map<String, Object> map = new HashedMap();
        if(supplier.getId() == null){
            boolean isSuccess = supplierService.saveNewSupplier(supplier);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = supplierService.updateSupplier(supplier);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removeSupplier")
    @ResponseBody
    public Map<String, Object> removeDicType(Supplier supplier) {

        Map<String, Object> map = new HashedMap();
        Integer id = supplier.getId();
        boolean isSuccess = supplierService.removeSupplierById(id);
        map.put("result",isSuccess);

        return map;
    }

}












