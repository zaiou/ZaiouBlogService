package com.zclient.shiro;

import com.zclient.model.AdminAuth;
import com.zclient.service.AdminAuthService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @Description: Realm数据管理
 * @Author: liubin
 * @Date: Created in 9:55 2018/4/22
 * @Modified by:
 */
public class ShiroDbRealm extends AuthorizingRealm {

        private final static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

        @Autowired
        private AdminAuthService adminAuthService;

        /**
         * @Description:为当前登陆成功的用户授予权限和角色，已经登陆成功了
         * @Author: liubin
         * @Date: 2018/4/22 10:05
         * @param :
         * @return
         */
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            String username = principalCollection.getPrimaryPrincipal().toString() ;
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo() ;
            Set<String> rolenames = adminAuthService.getRolesByUsername(username) ;
            Set<String> permissions = adminAuthService.getPermissionsByUsername(username) ;
            authorizationInfo.setRoles(rolenames); //设置用户的角色到SimpleAuthorizationInfo
            authorizationInfo.setStringPermissions(permissions); //设置用户的权限到 SimpleAuthorizationInfo
            return authorizationInfo;
    }

    /**
     * @Description:登陆验证
     * @Author: liubin
     * @Date: 2018/4/22 11:34
     * @param :
     * @return
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户账号
        String username = authenticationToken.getPrincipal().toString() ;
        AdminAuth adminAuth = adminAuthService.getAdminUserInfoByUsername(username);
        if (adminAuth != null){
            //将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数随便放一个就行了。
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(adminAuth.getAdminname(),adminAuth.getAdminpasswd(),
                    "a") ;
            return authenticationInfo ;
        }else{
            logger.info("该用户不存在");
            return  null ;
        }
    }
}
