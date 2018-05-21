package com.qkd.controller;

import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.Prescription;
import com.qkd.entity.PageRequest;
import com.qkd.entity.PharmacyMed;
import com.qkd.service.PrescriptionService;
import com.qkd.service.PharmacyMedService;
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
@RequestMapping("/prescription")
public class PrescriptionController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "PrescriptionService")
    private PrescriptionService prescriptionService;

    @Resource(name = "PharmacyMedService")
    private PharmacyMedService pharmacyMedService;

    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/getPrescription")
    @ResponseBody
    public JsonResult getPrescription(PageRequest pageRequest, Prescription Prescription) {
        PageInfo<Prescription> page = PageUtil.getPage(pageRequest);
        page = prescriptionService.listPage(page, Prescription);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getExceptNoSub")
    @ResponseBody
    public JsonResult getExceptNoSub(PageRequest pageRequest, Prescription prescription) {
        PageInfo<Prescription> page = PageUtil.getPage(pageRequest);
        page = prescriptionService.listPageExceptNoSub(page, prescription);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/getPrescriptionRow")
    @ResponseBody
    public List<Prescription> getPrescriptionRow(Prescription Prescription){
        List<Prescription> PrescriptionList = prescriptionService.getList(Prescription);

        return PrescriptionList;
    }

    @RequestMapping("/savePrescription")
    @ResponseBody
    public Map<String, Object> savePrescription(Prescription Prescription) {

        Map<String, Object> map = new HashedMap();
        if(Prescription.getId() == null){
            String useName = userService.getLoginName();
            Prescription.setUserId(useName);
            boolean isSuccess = prescriptionService.saveNewPrescription(Prescription);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = prescriptionService.updatePrescription(Prescription);
            if(Prescription.getStatus()!=null && Prescription.getStatus().equals("已发药")){
                PharmacyMed pharmacyMed = new PharmacyMed();
                pharmacyMed.setDepartId(Prescription.getDepartId());
                pharmacyMed.setNmae(Prescription.getMedId());
                pharmacyMed.setStock(Prescription.getAmount());
                isSuccess = pharmacyMedService.reduceStockByNmaeAndDepart(pharmacyMed);
            }
            map.put("result",isSuccess);
        }
        return map;
    }

    /**
     *根据serno更新status
     */
    @RequestMapping("/updateStatusBySerno")
    @ResponseBody
    public Map<String, Object> updateStatusBySerno(Prescription Prescription) {

        Map<String, Object> map = new HashedMap();
        boolean isSuccess = prescriptionService.updateStatusBySerno(Prescription);
        if(Prescription.getStatus()!=null && Prescription.getStatus().equals("已发药")){
            PharmacyMed pharmacyMed = new PharmacyMed();
            pharmacyMed.setDepartId(Prescription.getDepartId());
            pharmacyMed.setNmae(Prescription.getMedId());
            pharmacyMed.setStock(Prescription.getAmount());
            isSuccess = pharmacyMedService.reduceStockByNmaeAndDepart(pharmacyMed);
        }
        map.put("result",isSuccess);
        return map;
    }

    @RequestMapping("/removePrescription")
    @ResponseBody
    public Map<String, Object> removePrescription(Prescription Prescription) {

        Map<String, Object> map = new HashedMap();
        Integer id = Prescription.getId();
        boolean isSuccess = prescriptionService.removePrescriptionById(id);
        map.put("result",isSuccess);

        return map;
    }

}












