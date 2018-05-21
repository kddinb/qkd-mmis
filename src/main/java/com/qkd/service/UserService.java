package com.qkd.service;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.User;
import java.util.List;

/**
 * Created by Vitelon on 2017-03-30
 * 用户service接口
 */
public interface UserService {

    /**
     * 	Created by Vitelon on 2017-03-22
     * 	根据用户名和密码查询用户
     * @param name
     * @param pwd
     * @return
     */
    public User findByUsernameAndPwd(String name, String pwd);

    /**
     * 退出
     */
    public void logout();

    /**
     * 获取登录用户名
     */
    public String getLoginName();

    /**
     * 获取登录用户权限
     */
    public String getUserPower();

    /**
     * 获取菜单
     */
    public List menu();

    /**
     * 分页获取人员列表
     */
    public PageInfo<User> listPage(PageInfo page, User user);

    /**
     * 获取不分页数据
     */
    public List<User> getList(User user);

    /**
     *插入一条新的数据
     */
    public boolean saveNewUser(User user);

    /**
     * 更新人员数据
     */
    public boolean updateUser(User user);

    /**
     * 根据ID删除一条数据
     */
    public boolean removeUserById(Integer id);

}
