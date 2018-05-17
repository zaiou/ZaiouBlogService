package com.zclient.mapper;

import com.zclient.model.AdminAuthRole;

import java.util.List;

public interface AdminAuthRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminAuthRole record);

    AdminAuthRole selectByPrimaryKey(Integer id);

    List<AdminAuthRole> selectAll();

    int updateByPrimaryKey(AdminAuthRole record);
}