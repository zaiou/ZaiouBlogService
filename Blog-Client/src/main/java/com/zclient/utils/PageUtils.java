package com.zclient.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: 分页帮助类
 * @Author: liubin
 * @Date: Created in 22:43 2018/4/8
 * @Modified by:
 */
public class PageUtils {
    public static PageInfo change(Integer pageNum, Integer pageSize, Integer total, List list) {
        Page page = new Page(pageNum,pageSize);
        page.setTotal(total);
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(list);
        return pageInfo;
    }
}
