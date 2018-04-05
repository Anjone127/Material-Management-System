package cn.edu.zucc.anjone.mrp.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zucc.anjone.mrp.system.mapper.UserMapper;
import cn.edu.zucc.anjone.mrp.system.model.User;

public class MyRealm extends AuthorizingRealm {
	
    @Autowired	
    private UserMapper userMapper;
    
    /** 
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用 
     */ 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        String role = userMapper.selectRoleNameByUserId(username);
        r.add(role);
        info.setRoles(r);
        return info;
	}
    /** 
     * 认证回调函数, 登录时调用 
     */ 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userid = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        User u = userMapper.selectByUserId(userid);
        if(u==null||!u.getUserPassword().equals(password))
        	return null;
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(userid , password, this.getName());
        return aInfo;
	}

}
