package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.DataDet;
import org.entity.dto.OperateLog;
import org.entity.dto.Permission;
import org.entity.dto.Role;
import org.entity.dto.RoleToPermissionKey;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.DataDetServiceRead;
import org.service.cms.read.DataServiceRead;
import org.service.cms.read.PermissionServiceRead;
import org.service.cms.read.RoleServiceRead;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.RoleServiceWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
import org.util.OperateUtil;
import org.util.redis.ListTranscoder;
import org.util.redis.RedisService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**角色控制层
 *
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("cms/role")

public class RoleController {

	
	@Resource
	RoleServiceRead roleServiceRead;
	
	@Resource
	AppConfig AppConfig;
	
	@Resource
	PermissionServiceRead permissionServiceRead;
	
	@Resource
	DataDetServiceRead dataDetServiceRead;
	
	@Resource
	DataServiceRead dataServiceRead;
	@Resource
	RoleServiceWrite roleServiceWrite;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Autowired
	RedisService redisService;
	
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	/**
	 * 角色；列表页面
	 * @param model
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("roleJsp")
	public String roleJsp(Model model,@RequestParam(defaultValue = "1") Integer pageIndex) throws Exception{
		List<Role> roleList = roleServiceRead.findAllRole(pageIndex);
		Integer totalPage = roleServiceRead.findTotalPage();
		if(totalPage==0){
        	totalPage = 1;
        }
		model.addAttribute("roleList", roleList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", totalPage);
		return "role_list";
	}
	/**
	 * 新增角色jsp
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("roleAddJsp")
	public String roleAddJsp(Model model) throws Exception{
		
		return "detail/role_detail_add";
	}
	
	
	
	/*
	 * 新增ajax回调初始数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTree")
	public @ResponseBody JSONArray getTree()throws Exception{
		JSONArray jsonArray = new JSONArray();
		Map<Long,String> map = new HashMap<Long,String>();
		//通过redis获取数据字典信息
		ListTranscoder<DataDet> transcoder=new ListTranscoder<>();//序列化、反序列化工具
		List<DataDet> dataDets = transcoder.deserialize(redisService.get("permission".getBytes()));
		for(DataDet d:dataDets){
			map.put(d.getDataDetId(), d.getDataDetVal());
		}
		if(map.size()>0){
			for (Map.Entry<Long, String> entry : map.entrySet()) {
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("id", entry.getKey());
				jSONObject.put("pId", 0);
				jSONObject.put("name", entry.getValue());
				jSONObject.put("open", true);
				jsonArray.add(jSONObject);
				List<Permission> permissionList = permissionServiceRead.findPermissionByTypeId(entry.getKey());
				if(permissionList.size()>0){
					for(int j=0;j<permissionList.size();j++){
						JSONObject jSONObject1 = new JSONObject();
						Integer k = (int) (entry.getKey()*10+1+j);
						jSONObject1.put("id", k);
						jSONObject1.put("pId", entry.getKey());
						jSONObject1.put("name", permissionList.get(j).getPermissionName());
						jSONObject1.put("open", true);
						jSONObject1.put("perId", permissionList.get(j).getPermissionId());
						jsonArray.add(jSONObject1);
						List<Permission> sonPermissions = permissionServiceRead.findAllPermissionsByPid(permissionList.get(j).getPermissionId());
						if(sonPermissions.size()>0){
							for(int j2=0;j2<sonPermissions.size();j2++){
								JSONObject jSONObject2 = new JSONObject();
								Integer k2 = k*10+1+j2;
								jSONObject2.put("id", k2);
								jSONObject2.put("pId", k);
								jSONObject2.put("name", sonPermissions.get(j2).getPermissionName());
								jSONObject2.put("perId", sonPermissions.get(j2).getPermissionId());
								jsonArray.add(jSONObject2);
							}
						}
					}
					
				}
			}
		}
		return jsonArray;
	}
	
	
	/**
	 * 新增角色
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("roleAdd")
	public String roleAdd(HttpSession session,Integer roleState ,String roleName,String roleDescription,String pCheck) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		pCheck = pCheck.replace("undefined,", "");
		String[] mCheck = pCheck.split(",");
		Role role = new Role();
		role.setRoleCreateman(admin.getAdminId());
		role.setRoleDescription(roleDescription);
		//使用中
		role.setRoleState(roleState);
		
		if(!"".equals(pCheck)){
	    		//权限数量
	    		role.setRoleHasnum(mCheck.length);
	    	}else{
	    		role.setRoleHasnum(0);
	    	}
		
		role.setRoleName(roleName);
		Long roleId = roleServiceWrite.addRole(role); // 新增角色  返回主键
		if(!"".equals(pCheck)){
    		for(String l:mCheck){
    			RoleToPermissionKey rolePermission = new RoleToPermissionKey();
    			rolePermission.setRoleId(roleId);
    			rolePermission.setPermissionId(Long.valueOf(l));
    			roleServiceWrite.addRolePermission(rolePermission);
    		}
    	}
		
		String remark = OperateUtil.operateRole(nowAdmin.getAdminRealname(), 1, null,roleName );
		operateLog.setOperateRemark(remark);
        operateServiceWrite.addOperateLogs(operateLog);
		return "redirect:roleJsp.action";
	}
	
	/**
	 * 查看  详情
	 * @param model
	 * @param roleId
	 * @param flag 1 查看页面 2详情页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("roleDetail")
	public String roleDetail(Model model, Long roleId, Integer flag) throws Exception {
		Role role = roleServiceRead.findById(roleId);
		List<Permission> permission = permissionServiceRead.findAllPermisssion();
		String pCheck = "";
		//角色 权限关联数据
		List<RoleToPermissionKey> list = roleServiceRead.findByRId(roleId);
		
		if(list.size()>0){
			pCheck = "oldKey";
		}
		model.addAttribute("permission", permission);
		model.addAttribute("role", role);
		model.addAttribute("pCheck", pCheck);
		if(flag == 1){
			return "detail/role_detail_watch";
		}else{
			return "detail/role_detail_edit";
		}
	}
	
	
	
	/**
	 * 看权限
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("watchTree")
	public @ResponseBody JSONArray watchTree(long roleId)throws Exception{
		JSONArray jsonArray = new JSONArray();
		Map<Long,String> map = new HashMap<Long,String>();
		List<DataDet> dataDets = dataDetServiceRead.findParentPermission();//暂时的写法 从数据字典里取大权限类别   后期解决了就删
		for(DataDet d:dataDets){
			map.put(d.getDataDetId(), d.getDataDetVal());
		}
		if(map.size()>0){
			for (Map.Entry<Long, String> entry : map.entrySet()) {
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("id", entry.getKey());
				jSONObject.put("pId", 0);
				jSONObject.put("name", entry.getValue());
				jSONObject.put("open", true);
				jSONObject.put("chkDisabled", true);
				jsonArray.add(jSONObject);
				List<Permission> permissionList = permissionServiceRead.findPermissionByTypeId(entry.getKey());
				List<RoleToPermissionKey>  rpList = roleServiceRead.findByRId(roleId);
		    	List<Permission> myPlist = new ArrayList<Permission>();
		    	if(rpList.size()>0){
		    		for(RoleToPermissionKey r :rpList){
		    			Permission permission = permissionServiceRead.findByPId(r.getPermissionId());
		    			myPlist.add(permission);
		    		}
		    	}
				if(permissionList.size()>0){
					for(int j=0;j<permissionList.size();j++){
						JSONObject jSONObject1 = new JSONObject();
						Integer k = (int) (entry.getKey()*10+1+j);
						jSONObject1.put("id", k);
						jSONObject1.put("pId", entry.getKey());
						jSONObject1.put("name", permissionList.get(j).getPermissionName());
						jSONObject1.put("open", true);
						jSONObject1.put("chkDisabled", true);
						jSONObject1.put("perId", permissionList.get(j).getPermissionId());
						for(Permission  p :myPlist){
							if(p.getPermissionId().equals(permissionList.get(j).getPermissionId())){
								jSONObject1.put("checked", true);
								jSONObject1.put("chkDisabled", true);
							}
						}
						jsonArray.add(jSONObject1);
						List<Permission> sonPermissions = permissionServiceRead.findAllPermissionsByPid(permissionList.get(j).getPermissionId());
						if(sonPermissions.size()>0){
							for(int j2=0;j2<sonPermissions.size();j2++){
								JSONObject jSONObject2 = new JSONObject();
								Integer k2 = k*10+1+j2;
								jSONObject2.put("id", k2);
								jSONObject2.put("pId", k);
								jSONObject2.put("name", sonPermissions.get(j2).getPermissionName());
								jSONObject2.put("perId", sonPermissions.get(j2).getPermissionId());
								jSONObject2.put("chkDisabled", true);
								for(Permission  p :myPlist){
									if(p.getPermissionId().equals(sonPermissions.get(j2).getPermissionId())){
										jSONObject2.put("checked", true);
										jSONObject2.put("chkDisabled", true);
									}
								}
								jsonArray.add(jSONObject2);
							}
						}
					}
					
				}
			}
		}
		return jsonArray;
	}
	
	
	
    /**
	 * 编辑权限tree
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editTree")
	public @ResponseBody JSONArray editTree(long roleId)throws Exception{
		JSONArray jsonArray = new JSONArray();
		Map<Long,String> map = new HashMap<Long,String>();
		List<DataDet> dataDets = dataDetServiceRead.findParentPermission();//暂时的写法 从数据字典里取大权限类别   后期解决了就删
		for(DataDet d:dataDets){
			map.put(d.getDataDetId(), d.getDataDetVal());
		}
		if(map.size()>0){
			for (Map.Entry<Long, String> entry : map.entrySet()) {
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("id", entry.getKey());
				jSONObject.put("pId", 0);
				jSONObject.put("name", entry.getValue());
				jSONObject.put("open", true);
				jsonArray.add(jSONObject);
				List<Permission> permissionList = permissionServiceRead.findPermissionByTypeId(entry.getKey());
				List<RoleToPermissionKey>  rpList = roleServiceRead.findByRId(roleId);
		    	List<Permission> myPlist = new ArrayList<Permission>();
		    	if(rpList.size()>0){
		    		for(RoleToPermissionKey r :rpList){
		    			Permission permission = permissionServiceRead.findByPId(r.getPermissionId());
		    			myPlist.add(permission);
		    		}
		    	}
				if(permissionList.size()>0){
					for(int j=0;j<permissionList.size();j++){
						JSONObject jSONObject1 = new JSONObject();
						Integer k = (int) (entry.getKey()*10+1+j);
						jSONObject1.put("id", k);
						jSONObject1.put("pId", entry.getKey());
						jSONObject1.put("name", permissionList.get(j).getPermissionName());
						jSONObject1.put("open", true);
						jSONObject1.put("perId", permissionList.get(j).getPermissionId());
						for(Permission  p :myPlist){
							if(p.getPermissionId().equals(permissionList.get(j).getPermissionId())){
								jSONObject1.put("checked", true);
							}
						}
						jsonArray.add(jSONObject1);
						List<Permission> sonPermissions = permissionServiceRead.findAllPermissionsByPid(permissionList.get(j).getPermissionId());
						if(sonPermissions.size()>0){
							for(int j2=0;j2<sonPermissions.size();j2++){
								JSONObject jSONObject2 = new JSONObject();
								Integer k2 = k*10+1+j2;
								jSONObject2.put("id", k2);
								jSONObject2.put("pId", k);
								jSONObject2.put("name", sonPermissions.get(j2).getPermissionName());
								jSONObject2.put("perId", sonPermissions.get(j2).getPermissionId());
								for(Permission  p :myPlist){
									if(p.getPermissionId().equals(sonPermissions.get(j2).getPermissionId())){
										jSONObject2.put("checked", true);
									}
								}
								jsonArray.add(jSONObject2);
							}
						}
					}
					
				}
			}
		}
		
		return jsonArray;
	}
	
	
	
    /**
     * 编辑角色
     * @return
     * @throws Exception
     */
    @RequestMapping("updateRole")
    public String updateRole(HttpSession session ,Role role,String pCheck) throws Exception{
    	Admin adminsession = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(adminsession.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		if(!"oldKey".equals(pCheck)){
			pCheck = pCheck.replace("undefined,", "");
			String[] mCheck = pCheck.split(",");
			if("".equals(pCheck)){
				role.setRoleHasnum(0);
			}else{
				role.setRoleHasnum(mCheck.length);
			}
			List<RoleToPermissionKey> list = roleServiceRead.findByRId(role.getRoleId());
			if(null!=list&&list.size()>0){
				roleServiceWrite.deleteRtoP(role.getRoleId());
			}
			if(!"".equals(pCheck)){
				for(String l:mCheck){
					RoleToPermissionKey rolePermission = new RoleToPermissionKey();
					rolePermission.setRoleId(role.getRoleId());
					rolePermission.setPermissionId(Long.valueOf(l));
					roleServiceWrite.addRolePermission(rolePermission);
				}
			}
		}
		roleServiceWrite.updateRole(role);
		String remark = OperateUtil.operateRole(nowAdmin.getAdminRealname(), 2, null, role.getRoleName());
		operateLog.setOperateRemark(remark);
        operateServiceWrite.addOperateLogs(operateLog);
    	return "redirect:roleJsp.action";
    }
	
	
	/**
	 * 角色冻结解冻
	 * @param model
	 * @param roleId
	 * @param State 1 使用中 0冻结
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("roleFrozen")
	public String roleFrozen(HttpSession session,Model model, Long roleId, Integer roleState) throws Exception {
		Admin adminsession = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(adminsession.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		Role role =  roleServiceRead.findById(roleId);
		role.setRoleState(roleState);
		if(!roleServiceWrite.updateRole(role)){
			throw new Exception("更改失败");
		}else{
			if(roleState==1){
				String remark = OperateUtil.operateRole(nowAdmin.getAdminRealname(), 6, null, role.getRoleName());
				operateLog.setOperateRemark(remark);
			}
			if(roleState==0){
				String remark = OperateUtil.operateRole(nowAdmin.getAdminRealname(), 5, null, role.getRoleName());
				operateLog.setOperateRemark(remark);
			}
			operateServiceWrite.addOperateLogs(operateLog);
			return "redirect:roleJsp.action";
		}
	}
    
	
	
	 /**
	  * 检验角色名是否已经存在
	  * @param uname
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("checkRname")
	 public @ResponseBody MessageUtil checkRname(String rname)throws Exception{
	     MessageUtil messageUtil = new MessageUtil();
	     Role role = roleServiceRead.findByRName(rname);
	     if(role!=null){
	         messageUtil.setMessage("fail");
	     }else{
	         messageUtil.setMessage("success");
	     }
	     return messageUtil;
	 }
	 
	 /**
	  * 删除角色名
	  * @param roleId
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("deleteRole")
	 public @ResponseBody MessageUtil deleteRole(HttpSession session,Long roleId) throws Exception{
		 Admin adminsession = (Admin) session.getAttribute("admin");
			Admin nowAdmin = adminServiceRead.findAdminId(adminsession.getAdminId());
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
		 MessageUtil messageUtil = new MessageUtil();
		 Role role = roleServiceRead.findById(roleId);
		 role.setRoleState(-1);
		 List<Admin> adminList = adminServiceRead.findAllAdmin(null, null, null, null,null);
		 for(Admin admin : adminList){
			 for(Role role2 : admin.getRoles()){
				 if(role2!=null){
					 if(role2.getRoleName().equals(role.getRoleName())){
						 messageUtil.setMessage("管理员存在该角色，无法删除");
						 return messageUtil;
					 }
				 }
			 }
			
		 }
		 roleServiceWrite.deleteRole(role);
		 String remark = OperateUtil.operateRole(nowAdmin.getAdminRealname(), 3, null, role.getRoleName());
		 operateLog.setOperateRemark(remark);
		 operateServiceWrite.addOperateLogs(operateLog);
		 return messageUtil;
	 }
    
}
