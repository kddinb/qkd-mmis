package com.qkd.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qkd.constant.ParamConstants;
import com.qkd.entity.User;
;

/**
* @ClassName: ContextHolderUtils 
* @Description: 上下文工具类，用于在Server,Dao层获取当前登录用户信息等；
* @author  shenlx
 */
public class ContextHolder {
	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;

	}
	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;

	}

	/**
	 * 获取当前登录用户
	 * @return
     */
	public static final User getSessionUser() {
		HttpSession session = ContextHolder.getSession();
		User user = (User) session.getAttribute(ParamConstants.LOCAL_CLINET_USER);
		return user;
	}

	
//	/**
//	 * 获取当前登录科室
//	 */
//	public static final SysDeptEntity getSessionDept() {
//		HttpSession session = ContextHolder.getSession();
//		SysDeptEntity dept = (SysDeptEntity) session.getAttribute(ParamConstants.LOCAL_CLINET_DEPT);
//		return dept;
//	}
}
