package com.qkd.util;

import com.github.pagehelper.PageInfo;
import com.qkd.entity.PageRequest;

/**
 * Title: 分页工具类
 * Description:
 * Copyright: Copyright (c) 2017-03-31 下午2:39
 * Company: wondersgroup.com
 *
 * @author jerry.pan(潘健雷)
 * @version 1.0
 */
public class PageUtil {

    public static final int DEFAULT_PAGE_NUM = 1; //默认第几页

    public static final int DEFAULT_PAGE_SIZE = 100; //默认每页显示数

    private PageUtil() {
        super();
    }

    /**
     * 获取Page对象
     *
     * @param pageRequest
     * @return page
     * @author jerry.pan
     */
    public static PageInfo getPage(PageRequest pageRequest) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageRequest.getPage() == null ? PageUtil.DEFAULT_PAGE_NUM : pageRequest.getPage());
        pageInfo.setPageSize(pageRequest.getRows() == null ? PageUtil.DEFAULT_PAGE_SIZE : pageRequest.getRows());
        return pageInfo;
    }

}
