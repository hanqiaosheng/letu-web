package org.controller.cms;


import org.component.AppConfig;
import org.entity.dto.*;
import org.service.cms.read.*;
import org.service.cms.write.GuideAgencyServiceWrite;
import org.service.cms.write.LookAgencyServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.UploadImageUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("cms/guideAgency")
public class GuideAgencyController {

    @Resource
    AppConfig appConfig;
    @Resource
    GuideAgencyServiceRead guideAgencyServiceRead;
    @Resource
    GuideAgencyServiceWrite guideAgencyServiceWrite;
    @Resource
    GuideGroupServiceRead guideGroupServiceRead;
    @Resource
    CityServiceRead cityServiceRead;
    @Resource
    ProvinceServiceRead provinceServiceRead;
    @Resource
    AdminServiceRead adminServiceRead;
    @Resource
    RoleServiceRead roleServiceRead;
    @Resource
    LookAgencyServiceRead lookAgencyServiceRead;
    @Resource
    LookAgencyServiceWrite lookAgencyServiceWrite;

    /**
     * 旅行社列表
     *
     * @param session
     * @param model
     * @param pageIndex
     * @param guideAgency
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(HttpSession session, Model model, @RequestParam(defaultValue = "1") Integer pageIndex,
                       GuideAgencyInfos guideAgency,
                       Long lRidingCount, Long rRidingCount)
            throws Exception {
        Long channelId = (Long) session.getAttribute("currChannelId");
        Admin admin = (Admin) session.getAttribute("admin");

        //判断是否为管理员
        boolean is_super = checkAdminByRole(admin,"管理员");
        List<Long> agencyIds = null;
        if (!is_super) {
            agencyIds = lookAgencyServiceRead.findByAdmin(admin.getAdminId());
        }
        List<GuideAgencyInfos> guideAgencyList = guideAgencyServiceRead.findAll(guideAgency, agencyIds, lRidingCount, rRidingCount);
        //没有解决联表查问题前，先用笨办法
        for (GuideAgencyInfos guideAgencyInfos : guideAgencyList) {
            Admin cAdmin = adminServiceRead.findAdminId(guideAgencyInfos.getGuideAgencyCreatorId());
            guideAgencyInfos.setCreatorAdmin(cAdmin);
            Admin pAdmin = adminServiceRead.findAdminId(guideAgencyInfos.getGuideAgencyPrincipalId());
            guideAgencyInfos.setPrincipalAdmin(pAdmin);
            Admin mAdmin = adminServiceRead.findAdminId(guideAgencyInfos.getGuideAgencyManagerId());
            guideAgencyInfos.setManagerAdmin(mAdmin);
        }

        Integer totalPage = 1;
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("pageSize", appConfig.getPage_size_web());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("guideAgencyList", guideAgencyList);
        return "guideAgency_list";
    }

    /**
     * 跳转添加旅行社页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("addJsp")
    public String addJsp(HttpSession session, Model model) throws Exception {
        modelInit(model, null);
        return "detail/guideAgency_add";
    }

    /**
     * 添加旅行社
     *
     * @param guideAgency
     * @return
     * @throws Exception
     */
    @RequestMapping("add")
    public String add(HttpSession session, GuideAgencyInfos guideAgency) throws Exception {
        //获取当前的创建者admin
        Admin createAdmin = (Admin) session.getAttribute("admin");
        guideAgency.setGuideAgencyCreatorId(createAdmin.getAdminId());
        Long guideAgencyId = guideAgencyServiceWrite.add(guideAgency);
        if (guideAgency.getGuideAgencyManagerId() != null) {
            AdminLookAgencyKey adminLookAgencyKey = new AdminLookAgencyKey();
            adminLookAgencyKey.setAdminId(guideAgency.getGuideAgencyManagerId());
            adminLookAgencyKey.setAgencyId(guideAgencyId);
            lookAgencyServiceWrite.add(adminLookAgencyKey);
        }
        return "redirect:list.action";
    }

    /**
     * 跳转编辑旅行社页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("editJsp")
    public String editJsp(Long guideAgencyId, Model model) throws Exception {
        modelInit(model, guideAgencyId);
        return "detail/guideAgency_add";
    }

    /**
     * 编辑旅行社
     *
     * @param guideAgency
     * @return
     * @throws Exception
     */
    @RequestMapping("edit")
    public String edit(GuideAgencyInfos guideAgency) throws Exception {
        guideAgencyServiceWrite.update(guideAgency);
        return "redirect:list.action";
    }

    /**
     * 删除旅行社
     *
     * @param guideAgencyId
     * @return
     * @throws Exception
     */
    @RequestMapping("delete")
    public @ResponseBody
    String delete(Long guideAgencyId) throws Exception {
        List<AdminLookAgencyKey> adminLookAgencyKeys = lookAgencyServiceRead.findKeyByAgency(guideAgencyId);
        for(AdminLookAgencyKey adminLookAgencyKey:adminLookAgencyKeys){
            lookAgencyServiceWrite.del(adminLookAgencyKey);
        }
        guideAgencyServiceWrite.deleteById(guideAgencyId);
        return "success";
    }

    /**
     * 旅行社详情
     *
     * @param guideAgencyId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("detail")
    public String detail(Long guideAgencyId, Model model) throws Exception {
        GuideAgencyInfos guideAgencyInfos = guideAgencyServiceRead.findById(guideAgencyId);
        //todo 用多表联查,现在的问题是两个属性类型相同,如何做映射
        //先用分别查找的办法
        Admin createAdmin = adminServiceRead.findAdminId(guideAgencyInfos.getGuideAgencyCreatorId());
        Admin principalAdmin = adminServiceRead.findAdminId(guideAgencyInfos.getGuideAgencyPrincipalId());
        Admin manageAdmin = adminServiceRead.findAdminId(guideAgencyInfos.getGuideAgencyManagerId());
        City city = cityServiceRead.findById(guideAgencyInfos.getGuideAgencyCityId());
        Province province = provinceServiceRead.findById(guideAgencyInfos.getGuideAgencyProvinceId());

        model.addAttribute("guideAgency", guideAgencyInfos);
        model.addAttribute("createAdmin", createAdmin);
        model.addAttribute("manageAdmin", manageAdmin);
        model.addAttribute("principalAdmin", principalAdmin);
        model.addAttribute("city", city);
        model.addAttribute("province", province);
        return "detail/guideAgency_detail";
    }

    /**
     * 定时更新任务
     * 1. 每隔一小时更新一次
     * 2. 更新总骑行人数
     * @throws Exception
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void crontabUpdate() throws Exception{
        List<GuideAgencyInfos> guideAgencyList = guideAgencyServiceRead.findAll(null, null, null, null);
        List<Long> agencyIds = new ArrayList<>();
        for(GuideAgencyInfos guideAgencyInfos:guideAgencyList){
            agencyIds.add(guideAgencyInfos.getGuideAgencyId());
            List<GuideGroupInfos> guideGroupInfosList = guideGroupServiceRead.findAll(null,agencyIds,null,null,null,null,null,null);
            if(guideGroupInfosList!=null) {
                int ridingCount = 0;
                for (GuideGroupInfos guideGroupInfos : guideGroupInfosList) {
                    if (guideGroupInfos.getGuideGroupRidingcount() != null) {
                        ridingCount += guideGroupInfos.getGuideGroupRidingcount();
                    }
                }
                guideAgencyInfos.setGuideAgencyRidingCount(Long.valueOf(ridingCount));
                guideAgencyServiceWrite.update(guideAgencyInfos);
            }
            agencyIds.clear();
        }
    }

    /**
     * add 和 edit的初始化
     *
     * @param model
     * @param guideAgencyId
     */
    private void modelInit(Model model, Long guideAgencyId) {
        try {
            List<City> cityList = cityServiceRead.findAll();
            List<Province> provinceList = provinceServiceRead.findAll();

            //todo -- 用mybatis
            //查找所有角色为旅行社的admin,并过滤出未使用的admin
            List<Admin> allAgencyAdmins = findAdminByRole("旅行社");
            List<Admin> principalAdmins = new LinkedList<>();
            for (Admin admin : allAgencyAdmins) {
                int principalNums = guideAgencyServiceRead.countByPrincipalId(admin.getAdminId());
                if (principalNums == 0) {
                    principalAdmins.add(admin);
                }
            }
            //查找所有角色为旅行社管理员的admin
            List<Admin> managerAdmins = findAdminByRole("旅行社管理员");
            GuideAgencyInfos guideAgency = new GuideAgencyInfos();
            City curCity = new City();
            Province curProvince = new Province();
            String actionName = "add";
            if (guideAgencyId != null) {
                guideAgency = guideAgencyServiceRead.findById(guideAgencyId);
                Admin cAdmin = adminServiceRead.findAdminId(guideAgency.getGuideAgencyCreatorId());
                guideAgency.setCreatorAdmin(cAdmin);
                Admin pAdmin = adminServiceRead.findAdminId(guideAgency.getGuideAgencyPrincipalId());
                guideAgency.setPrincipalAdmin(pAdmin);
                Admin mAdmin = adminServiceRead.findAdminId(guideAgency.getGuideAgencyManagerId());
                guideAgency.setManagerAdmin(mAdmin);

                curCity = cityServiceRead.findById(guideAgency.getGuideAgencyCityId());
                curProvince = provinceServiceRead.findById(guideAgency.getGuideAgencyProvinceId());

                actionName = "edit";
            }
            model.addAttribute("principalAdmins", principalAdmins);
            model.addAttribute("managerAdmins", managerAdmins);
            model.addAttribute("cityList", cityList);
            model.addAttribute("provinceList", provinceList);
            model.addAttribute("guideAgency", guideAgency);
            model.addAttribute("curCity", curCity);
            model.addAttribute("curProvince", curProvince);
            model.addAttribute("actionName", actionName);
        } catch (Exception e) {
            //todo
        }
    }

    /**
     *
     * @param checkAdmin
     * @param roleName
     * @return
     * @throws Exception
     */
    private boolean checkAdminByRole(Admin checkAdmin,String roleName) throws Exception{
        if(checkAdmin.getRoles()!=null && checkAdmin.getRoles().size()!=0){
            for(Role role:checkAdmin.getRoles()){
                if(role.getRoleName().equals(roleName)){
                    return true;
                }
            }
        }else{
            List<AdminToRoleKey> adminToRoleKeys = roleServiceRead.findKeyByAId(checkAdmin.getAdminId());
            for(AdminToRoleKey adminToRoleKey:adminToRoleKeys){
                if(adminToRoleKey.getAdminId().equals(checkAdmin.getAdminId())){
                    Role role = roleServiceRead.findById(adminToRoleKey.getRoleId());
                    if(role.getRoleName().equals(roleName)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param roleName
     * @return
     * @throws Exception
     */
    private List<Admin> findAdminByRole(String roleName) throws Exception{
        Role role = roleServiceRead.findByRName(roleName);
        List<Admin> admins = adminServiceRead.findAllAdmin(null, null, null,
                role.getRoleId(), null);
        return admins;
    }
}
