package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.PageRequest;
import com.qkd.entity.Purchase;
import com.qkd.entity.StorMed;
import com.qkd.service.PurchaseService;
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
@RequestMapping("/purchase")
public class PurchaseController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "PurchaseService")
    private PurchaseService purchaseService;

    @Resource(name = "StorMedService")
    private StorMedService storMedService;

    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/getPurchase")
    @ResponseBody
    public JsonResult getPurchase(PageRequest pageRequest, Purchase purchase) {
        PageInfo<Purchase> page = PageUtil.getPage(pageRequest);
        page = purchaseService.listPage(page, purchase);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getExceptNoSub")
    @ResponseBody
    public JsonResult getExceptNoSub(PageRequest pageRequest, Purchase purchase) {
        PageInfo<Purchase> page = PageUtil.getPage(pageRequest);
        page = purchaseService.listPageExceptNoSub(page, purchase);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getPurchaseRow")
    @ResponseBody
    public List<Purchase> getPurchaseRow(Purchase purchase){
        List<Purchase> PurchaseList = purchaseService.getList(purchase);

        return PurchaseList;
    }

    @RequestMapping("/savePurchase")
    @ResponseBody
    public Map<String, Object> savePurchase(Purchase purchase) {

        Map<String, Object> map = new HashedMap();
        if(purchase.getId() == null){
            String useName = userService.getLoginName();
            purchase.setUserId(useName);
            boolean isSuccess = purchaseService.saveNewPurchase(purchase);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = purchaseService.updatePurchase(purchase);
            if(purchase.getStatus()!=null && purchase.getStatus().equals("通过")){
                StorMed storMed = new StorMed();
                storMed.setDepartId(purchase.getDepartId());
                storMed.setNmae(purchase.getMedId());
                storMed.setStock(purchase.getAmount());
                isSuccess = storMedService.updateStockByNmaeAndDepart(storMed);
            }
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removePurchase")
    @ResponseBody
    public Map<String, Object> removePurchase(Purchase purchase) {

        Map<String, Object> map = new HashedMap();
        Integer id = purchase.getId();
        boolean isSuccess = purchaseService.removePurchaseById(id);
        map.put("result",isSuccess);

        return map;
    }

}












