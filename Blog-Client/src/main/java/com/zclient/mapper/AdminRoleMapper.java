package com.zclient.mapper;

import com.zclient.model.AdminRole;

import java.util.List;
import java.util.Set;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    List<AdminRole> selectAll();

    int updateByPrimaryKey(AdminRole record);

    Set<String> getRolesByUsername(String username);
}