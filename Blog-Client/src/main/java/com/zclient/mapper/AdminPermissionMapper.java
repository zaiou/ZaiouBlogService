package com.zclient.mapper;

import com.zclient.model.AdminPermission;

import java.util.List;
import java.util.Set;

public interface AdminPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminPermission record);

    AdminPermission selectByPrimaryKey(Integer id);

    List<AdminPermission> selectAll();

    int updateByPrimaryKey(AdminPermission record);

    Set<String> getPermissionsByUsername(String username);
}