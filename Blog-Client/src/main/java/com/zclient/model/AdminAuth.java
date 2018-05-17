package com.zclient.model;

public class AdminAuth {
    private Integer id;

    private String adminname;

    private String adminpasswd;

    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getAdminpasswd() {
        return adminpasswd;
    }

    public void setAdminpasswd(String adminpasswd) {
        this.adminpasswd = adminpasswd == null ? null : adminpasswd.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}