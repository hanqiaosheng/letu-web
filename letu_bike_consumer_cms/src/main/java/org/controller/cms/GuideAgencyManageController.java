

package org.controller.cms;


import org.component.AppConfig;
import org.entity.dto.*;
import org.service.cms.read.*;
import org.service.cms.write.AdminServiceWrite;
import org.service.cms.write.GuideGroupServiceWrite;
import org.service.cms.write.LookAgencyServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping("cms/guideAgencyManage")
public class GuideAgencyManageController {
    @Resource
    AppConfig appConfig;
    @Resource
    GuideAgencyServiceRead agencyServiceRead;
    @Resource
    AdminServiceRead adminServiceRead;
    @Resource
    AdminServiceWrite adminServiceWrite;
    @Resource
    LookAgencyServiceWrite lookAgencyServiceWrite;
    @Resource
    LookAgencyServiceRead lookAgencyServiceRead;
    @Resource
    RoleServiceRead roleServiceRead;
    @Resource
    GuideAgencyServiceRead guideAgencyServiceRead;

    @RequestMapping("/list")
    public String list(HttpSession session, Model model, @RequestParam(defaultValue = "1") Integer pageIndex,Admin searchAdmin) throws Exception {
        Long channelId = (Long) session.getAttribute("currChannelId");
        Admin admin = (Admin) session.getAttribute("admin");
        Integer totalPage = 1;

        //todo 用mybatis
        Role role = roleServiceRead.findByRName("旅行社管理员");
        List<Admin> agencyAdmins = adminServiceRead.findAllByCondition(null,null,searchAdmin,role.getRoleId());

        HashMap<Long,String> id_agencys = new HashMap<>();
        HashMap<Long,String> id_look_agencys = new HashMap<>();
        for(Admin agencyAdmin:agencyAdmins){
            List<GuideAgencyInfos> guideAgencyInfosList = guideAgencyServiceRead.findByAdminId(null,null,agencyAdmin.getAdminId(),false);
            String agencyName = "";
            if(guideAgencyInfosList!=null) {
                for (GuideAgencyInfos guideAgencyInfos : guideAgencyInfosList) {
                    if("".equals(agencyName)){
                        agencyName = guideAgencyInfos.getGuideAgencyName();
                    }else {
                        agencyName = agencyName + "，" + guideAgencyInfos.getGuideAgencyName();
                    }
                }
                if(!"".equals(agencyName)) {
                    id_agencys.put(agencyAdmin.getAdminId(), agencyName);
                }
            }

            List<Long> lookAgencyIds = lookAgencyServiceRead.findByAdmin(agencyAdmin.getAdminId());
            String lookAgencyName="";
            if(lookAgencyIds!=null) {
                for (Long agencyId : lookAgencyIds) {
                    GuideAgencyInfos lookAgency = guideAgencyServiceRead.findById(agencyId);
                    if(lookAgency!=null) {
                        if("".equals(lookAgencyName)){
                            lookAgencyName = lookAgency.getGuideAgencyName();
                        }else {
                            lookAgencyName = lookAgencyName + "，" + lookAgency.getGuideAgencyName();
                        }
                    }
                }
                if(!"".equals(lookAgencyName)) {
                    id_look_agencys.put(agencyAdmin.getAdminId(), lookAgencyName);
                }
            }
        }
        model.addAttribute("agencyAdmins",agencyAdmins);
        model.addAttribute("id_agencys",id_agencys);
        model.addAttribute("id_look_agencys",id_look_agencys);
        return "guideAgencyManage_list";
    }


    /**
     * 跳转设置旅行社管理员页面
     * @return
     * @throws Exception
     */
    @RequestMapping("editJsp")
    public String editJsp(Long agencyAdminId,Model model)throws Exception{
        //取出旅行社
        Admin agencyAdmin = adminServiceRead.findAdminId(agencyAdminId);
        List<GuideAgencyInfos> guideAgencys = agencyServiceRead.findByAdminId(null,null,agencyAdminId,true);
        List<Long> lookAgencyIds = lookAgencyServiceRead.findByAdmin(agencyAdminId);
        model.addAttribute("agencyAdmin",agencyAdmin);
        model.addAttribute("guideAgencys",guideAgencys);
        model.addAttribute("lookAgencyIds",lookAgencyIds);
        return "detail/guideAgencyManage_set";
    }

    /**
     * 设置旅行社管理员查看权限
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("edit")
    public String edit(Long[] agencyIds,Long agencyAdminId)throws Exception{
        if(agencyIds==null){
            agencyIds=new Long[0];
        }
        List<Long> curAgencyIds = lookAgencyServiceRead.findByAdmin(agencyAdminId);
        List<Long> belongAgencyIds = guideAgencyServiceRead.findIdsByAdminId(null,null,agencyAdminId,false);
        curAgencyIds.removeAll(belongAgencyIds);
        List<Long> existIds = new ArrayList<>();
        for(Long agencyId:agencyIds){
            if(!curAgencyIds.contains(agencyId)) {
                AdminLookAgencyKey adminLookAgency = new AdminLookAgencyKey();
                adminLookAgency.setAdminId(agencyAdminId);
                adminLookAgency.setAgencyId(agencyId);
                lookAgencyServiceWrite.add(adminLookAgency);
            }else{
                existIds.add(agencyId);
            }
        }
        for(Long curId:curAgencyIds){
            if(!existIds.contains(curId)){
                AdminLookAgencyKey adminLookAgency = new AdminLookAgencyKey();
                adminLookAgency.setAdminId(agencyAdminId);
                adminLookAgency.setAgencyId(curId);
                lookAgencyServiceWrite.del(adminLookAgency);
            }
        }
        return "redirect:list.action";
    }

    /**
     * 注销旅行社管理员
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("delete")
    public @ResponseBody
    String delete(Long guideGroupId)throws Exception{
        return "success";
    }
}
