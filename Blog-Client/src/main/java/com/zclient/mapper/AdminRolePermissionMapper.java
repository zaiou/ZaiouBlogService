package com.zclient.mapper;

import com.zclient.model.AdminRolePermission;

import java.util.List;

public interface AdminRolePermissionMapper {
    int deleteByPrimaryKey(Integer column1);

    int insert(AdminRolePermission record);

    AdminRolePermission selectByPrimaryKey(Integer column1);

    List<AdminRolePermission> selectAll();

    int updateByPrimaryKey(AdminRolePermission record);
}