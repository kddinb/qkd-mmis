package com.qkd.controller;
import com.qkd.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    /**
     * 跳转到主页面
     *
     * @return
     */
    @RequestMapping(value = "/gotoMain", method = RequestMethod.GET)
    public ModelAndView gotoMain() {
        ModelAndView mv = new ModelAndView();
        System.out.print("用户名  "+userService.getLoginName());
        mv.addObject("username", userService.getLoginName());
        mv.setViewName("main/main");
        return mv;
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    @RequestMapping(value = "/getloginName")
    @ResponseBody
    public Map<String, Object> getloginName() {
        Map<String, Object> map = new HashedMap();
        String loginName = userService.getLoginName();
        map.put("result",loginName);
        return map;
    }

    /**
     * 获取菜单
     */
    @RequestMapping(value = "/menu")
    @ResponseBody
    public List menu() {
        List list = userService.menu();
        return list;
    }

    /**
     *跳往职位目录页面
     */
    @RequestMapping(value = "/page/goPosition")
    public ModelAndView goPosition() {
        ModelAndView mav = new ModelAndView("/position/position");
        return mav;
    }

    /**
     *跳往部门目录页面
     */
    @RequestMapping(value = "/page/goDepart")
    public ModelAndView goDepart() {
        ModelAndView mav = new ModelAndView("/depart/depart");
        return mav;
    }

    /**
     *跳往人员目录页面
     */
    @RequestMapping(value = "/page/goUser")
    public ModelAndView goUser() {
        ModelAndView mav = new ModelAndView("/user/user");
        return mav;
    }

    /**
     *跳往药品类型维护页面
     */
    @RequestMapping(value = "/page/goDicType")
    public ModelAndView goDicType() {
        ModelAndView mav = new ModelAndView("/dicType/dic-type");
        return mav;
    }

    /**
     *跳往生产厂家维护页面
     */
    @RequestMapping(value = "/page/goSupplier")
    public ModelAndView goSupplier() {
        ModelAndView mav = new ModelAndView("/supplier/supplier");
        return mav;
    }

    /**
     *跳往生产厂家信息页面
     */
    @RequestMapping(value = "/page/goSupplierDetail")
    public ModelAndView goSupplierDetail() {
        ModelAndView mav = new ModelAndView("/supplier/supplier-detail");
        return mav;
    }

    /**
     *跳往药理维护页面
     */
    @RequestMapping(value = "/page/goDicPhar")
    public ModelAndView goDicPhar() {
        ModelAndView mav = new ModelAndView("dicPhar1/dic-phar");
        return mav;
    }

    /**
     *跳往通用名维护页面
     */
    @RequestMapping(value = "/page/goDicGname")
    public ModelAndView goDicGname() {
        ModelAndView mav = new ModelAndView("dicGname/dic-gname");
        return mav;
    }

    /**
     *跳往药品目录页面
     */
    @RequestMapping(value = "/page/goDicMed")
    public ModelAndView goDicMed(){
        ModelAndView mav = new ModelAndView("dicMed/dic-med");
        return mav;
    }

    /**
     *跳往药库药品目录页面
     */
    @RequestMapping(value = "/page/goStorMed")
    public ModelAndView goStorMed(){
        ModelAndView mav = new ModelAndView("storMed/stor-med");
        return mav;
    }

    /**
     *跳往新增药库药品目录页面
     */
    @RequestMapping(value = "/page/goAddStorMed")
    public ModelAndView goAddStorMed(){
        ModelAndView mav = new ModelAndView("storMed/stor-med-add");
        return mav;
    }

    /**
     *跳往药房药品目录页面
     */
    @RequestMapping(value = "/page/goPharmacyMed")
    public ModelAndView goPharmacyMed(){
        ModelAndView mav = new ModelAndView("pharmacyMed/pharmacy-med");
        return mav;
    }

    /**
     *跳往药库采购页面
     */
    @RequestMapping(value = "/page/goPurchase")
    public ModelAndView goPurchase(){
        ModelAndView mav = new ModelAndView("purchase/purchase");
        return mav;
    }

    /**
     *跳往药库采购审批页面
     */
    @RequestMapping(value = "/page/goPurchaseApprove")
    public ModelAndView goPurchaseApprove(){
        ModelAndView mav = new ModelAndView("purchase/purchase-approve");
        return mav;
    }

    /**
     *跳往药房申领页面
     */
    @RequestMapping(value = "/page/goApply")
    public ModelAndView goApply(){
        ModelAndView mav = new ModelAndView("apply/apply");
        return mav;
    }

    /**
     *跳往药房申领审批页面
     */
    @RequestMapping(value = "/page/goApplyApprove")
    public ModelAndView goApplyApprove(){
        ModelAndView mav = new ModelAndView("apply/apply-approve");
        return mav;
    }

    /**
     *跳往药库审批驳回页面
     */
    @RequestMapping(value = "/page/goBackReason")
    public ModelAndView goBackReason() {
        ModelAndView mav = new ModelAndView("/purchase/purchase-back");
        return mav;
    }

    /**
     *跳往开药页面
     */
    @RequestMapping(value = "/page/goPrescription")
    public ModelAndView goPrescription(){
        ModelAndView mav = new ModelAndView("prescription/prescription");
        return mav;
    }

    /**
     *跳往发药页面
     */
    @RequestMapping(value = "/page/goPrescriptionApprove")
    public ModelAndView goPrescriptionApprove(){
        ModelAndView mav = new ModelAndView("prescription/prescription-approve");
        return mav;
    }

    @RequestMapping(value = "/page/findByTeacher")
    public ModelAndView findByTeacher() {
        ModelAndView mav = new ModelAndView("/homework/homework_teacher");
        return mav;
    }

}
