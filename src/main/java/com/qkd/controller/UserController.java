package com.qkd.controller;
import com.github.pagehelper.PageInfo;
import com.qkd.constant.DescribableEnum;
import com.qkd.entity.PageRequest;
import com.qkd.entity.User;
import com.qkd.service.UserService;
import com.qkd.util.JsonResult;
import com.qkd.util.PageUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2018/1/9.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/find")
    @ResponseBody
    public Map<String,Object> find(User user, HttpServletRequest request){

        Map<String,Object> map = new HashedMap();
        System.out.println("你已通过springMVC进入controller方法。。。。");
        logger.info("你已通过springMVC进入controller方法。。。。");
        System.out.println("用户信息： "+user);
        User loginuser = userService.findByUsernameAndPwd(user.getName(),user.getPwd());
        System.out.println(loginuser);
        if(loginuser != null){
            map.put("result","1");
        }else {
            map.put("result","99999");
        }
        return map;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ModelAndView logout(){
        try {
            userService.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("/login");
        return mav;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public JsonResult getUser(PageRequest pageRequest, User user) {
        PageInfo<User> page = PageUtil.getPage(pageRequest);
        page = userService.listPage(page, user);

        return new JsonResult(DescribableEnum.SUCCESS, page);
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public Map<String, Object> saveUser(User user) {

        Map<String, Object> map = new HashedMap();
        if(user.getId() == null){
            boolean isSuccess = userService.saveNewUser(user);
            map.put("result",isSuccess);
        }else{
            boolean isSuccess = userService.updateUser(user);
            map.put("result",isSuccess);
        }
        return map;
    }

    @RequestMapping("/removeUser")
    @ResponseBody
    public Map<String, Object> removeUser(User user) {

        Map<String, Object> map = new HashedMap();
        Integer id = user.getId();
        boolean isSuccess = userService.removeUserById(id);
        map.put("result",isSuccess);

        return map;
    }

}
