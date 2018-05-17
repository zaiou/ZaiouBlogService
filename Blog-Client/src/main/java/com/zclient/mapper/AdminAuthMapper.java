package com.zclient.mapper;

import com.zclient.model.AdminAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminAuth record);

    AdminAuth selectByPrimaryKey(Integer id);

    List<AdminAuth> selectAll();

    int updateByPrimaryKey(AdminAuth record);

    AdminAuth login(@Param("username") String username, @Param("password") String password);

    AdminAuth getAdminUserInfoByUsername(String username);
}