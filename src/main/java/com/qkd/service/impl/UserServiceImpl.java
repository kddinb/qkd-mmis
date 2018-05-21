package com.qkd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qkd.dao.UserMapper;
import com.qkd.entity.User;
import com.qkd.service.UserService;
import com.qkd.common.ContextHolder;
import com.qkd.constant.ParamConstants;
import org.springframework.stereotype.Service;
import com.qkd.util.JacksonUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitelon on 2017-03-30
 * 用户service实现类
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource(name = "UserMapper")
    private UserMapper userMapper;

    /**
     * 分页获取部门列表
     */
    @Override
    public PageInfo<User> listPage(PageInfo page, User user) {
        //分页查询,紧跟着的第一个方法会被分页
        PageHelper.startPage(page);
        List<User> list = userMapper.listAll(user);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *获取不分页数据
     */
    public List<User> getList(User user){
        List<User> list = userMapper.listAll(user);
        return list;
    }

    /**
     * 插入新部门
     */
    public boolean saveNewUser(User user){
        return userMapper.saveNewUser(user);
    }

    /**
     * 更新部门数据
     */
    public boolean updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据Id删除一条数据
     */
    public boolean removeUserById(Integer id){
        return userMapper.deleteByPrimaryKey(id);
    }

    public User findByUsernameAndPwd(String name, String pwd) {
        User user;

        user = userMapper.findByUsernameAndPwd(name, pwd);
        if(user != null){
            ContextHolder.getSession().setAttribute(ParamConstants.LOCAL_CLINET_USER, user);// 存放用户对象到session中
        }
        return user;
    }

    public void logout(){
        HttpSession session = ContextHolder.getSession();
        if (session != null) {
            session.removeAttribute(ParamConstants.LOCAL_CLINET_USER);
            session.invalidate();
        }
    }

    public String getLoginName(){
        try {
            User user = ContextHolder.getSessionUser();
            if (user == null)
                return "";
            return user.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public String getUserPower() {
        try {
            User user = ContextHolder.getSessionUser();
            if (user == null)
                return "";
            return user.getPowerStatus();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public List menu (){
        User user = ContextHolder.getSessionUser();
        String powerStatus = user.getPowerStatus();
        String departId = user.getDepartId();
        String menuJson = "";
//        if(ParamConstants.USER_TYPE_0.equals(userType)){//学生
//            menuJson = "[{\"name\":\"学生菜单\",\"url\":\"\",\"class\":\"glyphicon-education\",\"child\":[{\"name\":\"作业列表\",\"url\":\"/study/homework/page/list?publishStatus=1\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
//        }else if(ParamConstants.USER_TYPE_1.equals(userType)){//老师
//            menuJson = "[{\"name\":\"老师菜单\",\"url\":\"\",\"class\":\"glyphicon-education\",\"child\":[{\"name\":\"我的发布\",\"url\":\"/study/homework/page/findByTeacher\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
//        }
        if((departId.equals("药事科") && powerStatus.equals("主任")) || (departId.equals("院长室")) || (powerStatus.equals("超级管理员"))){
            menuJson = "[{\"name\":\"管理员菜单\",\"url\":\"\",\"class\":\"glyphicon-user\"," +
                    "\"child\":[{\"name\":\"我的目录\",\"url\":\"\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"职位目录维护\",\"url\":\"/view/page/goPosition\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"部门目录\",\"url\":\"/view/page/goDepart\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"人员目录\",\"url\":\"/view/page/goUser\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药品类型维护\",\"url\":\"/view/page/goDicType\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药品药理维护\",\"url\":\"/view/page/goDicPhar\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"通用名维护\",\"url\":\"/view/page/goDicGname\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药品目录\",\"url\":\"/view/page/goDicMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药库药品目录\",\"url\":\"/view/page/goStorMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房药品目录\",\"url\":\"/view/page/goPharmacyMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药库采购申请\",\"url\":\"/view/page/goPurchase\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药库采购审批\",\"url\":\"/view/page/goPurchaseApprove\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房申领申请\",\"url\":\"/view/page/goApply\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房申领审批\",\"url\":\"/view/page/goApplyApprove\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"开药\",\"url\":\"/view/page/goPrescription\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"发药\",\"url\":\"/view/page/goPrescriptionApprove\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药品供应商维护\",\"url\":\"/view/page/goSupplier\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
        }else if(departId.equals("药事科") && powerStatus.equals("副主任")){
            menuJson = "[{\"name\":\"管理员菜单\",\"url\":\"\",\"class\":\"glyphicon-user\"," +
                    "\"child\":[{\"name\":\"我的目录\",\"url\":\"\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药品类型维护\",\"url\":\"/view/page/goDicType\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药品药理维护\",\"url\":\"/view/page/goDicPhar\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"通用名维护\",\"url\":\"/view/page/goDicGname\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药品目录\",\"url\":\"/view/page/goDicMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药库药品目录\",\"url\":\"/view/page/goStorMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房药品目录\",\"url\":\"/view/page/goPharmacyMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药库采购申请\",\"url\":\"/view/page/goPurchase\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药库采购审批\",\"url\":\"/view/page/goPurchaseApprove\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房申领申请\",\"url\":\"/view/page/goApply\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房申领审批\",\"url\":\"/view/page/goApplyApprove\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"开药\",\"url\":\"/view/page/goPrescription\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"发药\",\"url\":\"/view/page/goPrescriptionApprove\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药品供应商维护\",\"url\":\"/view/page/goSupplier\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
        }else if(departId.equals("药事科") && powerStatus.equals("药库管理员")){
            menuJson = "[{\"name\":\"管理员菜单\",\"url\":\"\",\"class\":\"glyphicon-user\"," +
                    "\"child\":[{\"name\":\"我的目录\",\"url\":\"\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药库药品目录\",\"url\":\"/view/page/goStorMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药库采购申请\",\"url\":\"/view/page/goPurchase\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房申领审批\",\"url\":\"/view/page/goApplyApprove\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
        }else if(departId.equals("药事科") && powerStatus.equals("药房管理员")){
            menuJson = "[{\"name\":\"管理员菜单\",\"url\":\"\",\"class\":\"glyphicon-user\"," +
                    "\"child\":[{\"name\":\"我的目录\",\"url\":\"\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"开药\",\"url\":\"/view/page/goPrescriptionApprove\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房药品目录\",\"url\":\"/view/page/goPharmacyMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"药房申领申请\",\"url\":\"/view/page/goApply\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
        }else{
            menuJson = "[{\"name\":\"管理员菜单\",\"url\":\"\",\"class\":\"glyphicon-user\"," +
                    "\"child\":[{\"name\":\"我的目录\",\"url\":\"\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
                    "{\"name\":\"开药\",\"url\":\"/view/page/goPrescription\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
        }
//        menuJson = "[{\"name\":\"管理员菜单\",\"url\":\"\",\"class\":\"glyphicon-user\"," +
//
//                "\"child\":[{\"name\":\"我的发布\",\"url\":\"\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//
//                "{\"name\":\"人事信息\",\"url\":\"\",\"class\":\"glyphicon-book\"," +
//                "\"child\":[{\"name\":\"职位目录维护\",\"url\":\"/view/page/goPosition\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"部门目录\",\"url\":\"/view/page/goDepart\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"人员目录\",\"url\":\"/view/page/goUser\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}," +
//
//                "{\"name\":\"职位目录维护\",\"url\":\"/view/page/goPosition\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"部门目录\",\"url\":\"/view/page/goDepart\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"人员目录\",\"url\":\"/view/page/goUser\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"药品类型维护\",\"url\":\"/view/page/goDicType\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"药品药理维护\",\"url\":\"/view/page/goDicPhar\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"通用名维护\",\"url\":\"/view/page/goDicGname\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"药品目录\",\"url\":\"/view/page/goDicMed\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}," +
//                "{\"name\":\"药品供应商维护\",\"url\":\"/view/page/goSupplier\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
        List menuList = JacksonUtil.json2Bean(menuJson,ArrayList.class);
        return menuList;
    }

}