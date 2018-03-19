package org.component.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.entity.dto.Admin;
import org.entity.dto.AdminToRoleKey;
import org.entity.dto.Permission;
import org.entity.dto.Role;
import org.entity.dto.RoleToPermissionKey;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.PermissionServiceRead;
import org.service.cms.read.RoleServiceRead;
import org.springframework.stereotype.Service;

@Service("monitorRealm")
public class MonitorRealm extends AuthorizingRealm {
	/*
	 * @Autowired UserService userService;
	 * 
	 * @Autowired RoleService roleService;
	 * 
	 * @Autowired LoginLogService loginLogService;
	 */
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	RoleServiceRead roleServiceRead;
	
	@Resource
	PermissionServiceRead permissionServiceRead;
	
	public MonitorRealm() {
		super();

	}
	
	/**
	 *  验证当前Subject（可理解为当前用户）所拥有的权限，且给其授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		/* 这里编写授权代码 */
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录的用户名
        String username = (String) super.getAvailablePrincipal(principals);
        
    	List<String> roles = new ArrayList<String>();
    	Set<String> permissions = new HashSet<String>();
        try {
			Admin admin = adminServiceRead.findByName(username);
			if(admin!=null){
				//查当前拥有的角色
				List<AdminToRoleKey> rolecontact = roleServiceRead.findKeyByAId(admin.getAdminId());
				if(rolecontact!=null && rolecontact.size()>0){
					for (AdminToRoleKey ro : rolecontact) {
						Role role = roleServiceRead.findById(ro.getRoleId());;
						if(null!=role&&role.getRoleState()!=-1&&role.getRoleState()!=0){
							roles.add(role.getRoleName());
							List<RoleToPermissionKey> rpList = permissionServiceRead.findByRId(role.getRoleId());
							if(rpList!=null && rpList.size()>0){
								for (RoleToPermissionKey rp : rpList) {
									Permission permission = permissionServiceRead.findByPId(rp.getPermissionId());
									permissions.add(permission.getPermissionValue());
								}
							}
						}
					}
				}
			}else{
	            throw new AuthorizationException();
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//给用户设置角色
		info.addRoles(roles);
		//给当前用户设置权限
	    info.setStringPermissions(permissions);
		return info;

	}
	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		/* 这里编写认证代码 */
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Admin admin = null;
		try {
			admin = adminServiceRead.findByName(token.getUsername());
			this.setSession("admin", admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (admin != null) {
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin.getAdminUsername(),admin.getAdminPwd(), admin.getAdminUsername());
			 PrincipalCollection principals = info.getPrincipals();
			 clearCache(principals);
			return info;
		}
		return null;

	}

	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}
