package org.controller.cms;


import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.*;
import org.service.cms.read.*;
import org.service.cms.write.CouponToUserServiceWrite;
import org.service.cms.write.GuideGroupServiceWrite;
import org.service.cms.write.GuideGroupUserWrite;
import org.service.cms.write.UserServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.*;


@Controller
@Scope("prototype")
@RequestMapping("cms/guideGroup")
public class GuideGroupController {

    @Resource
    AppConfig appConfig;
    @Resource
    GuideGroupServiceRead guideGroupServiceRead;
    @Resource
    GuideGroupServiceWrite guideGroupServiceWrite;
    @Resource
    GuideAgencyServiceRead guideAgencyServiceRead;
    @Resource
    AdminServiceRead adminServiceRead;
    @Resource
    RoleServiceRead roleServiceRead;
    @Resource
    UserServiceRead userServiceRead;
    @Resource
    UserServiceWrite userServiceWrite;
    @Resource
    LookAgencyServiceRead lookAgencyServiceRead;
    @Resource
    CouponSchemeServiceRead couponSchemeServiceRead;
    @Resource
    BikeRentInfoServiceRead bikeRentInfoServiceRead;
    @Resource
    GuideGroupUserRead guideGroupUserRead;
    @Resource
    GuideGroupUserWrite guideGroupUserWrite;
    @Resource
    CouponToUserServiceWrite couponToUserServiceWrite;
    @Resource
    CouponToUserServiceRead couponToUserServiceRead;

    protected final MessageUtil messageUtil = new MessageUtil();

    //当前类的logger日志
    private static Logger loggers = Logger.getLogger(GuideGroupController.class);

    @RequestMapping("/list")
    public String list(HttpSession session, Model model, @RequestParam(defaultValue = "1") Integer pageIndex,
                       GuideGroupInfos guideGroupInfos,String guideTel,
                       Date lgroupStartTime,Date rgroupStartTime,
                       Integer lheadCount,Integer rheadCount,
                       Integer lridingCount,Integer rridingCount)
            throws Exception {
        Long channelId = (Long) session.getAttribute("currChannelId");
        Admin admin = (Admin) session.getAttribute("admin");
        ConstantUtil constantUtil = new ConstantUtil();
        Integer totalPage = 1;

        //判断是否为管理员
        boolean is_super = checkAdminByRole(admin,"管理员");

        List<GuideAgencyInfos> guideAgencyInfosList = new LinkedList<>();
        List<Long> agencyIds = null;
        if(is_super){
            guideAgencyInfosList = guideAgencyServiceRead.findByAdminId(null,null,null,false);
        }else{
            Set<Long> setAgencyIds = new HashSet<>();
            if (guideGroupInfos.getGuideGroupAgencyId() == null) {
                if (guideAgencyInfosList.size() != 0) {
                    for (GuideAgencyInfos guideAgency : guideAgencyInfosList) {
                        setAgencyIds.add(guideAgency.getGuideAgencyId());
                    }
                }
                List<Long> lookAgencys = lookAgencyServiceRead.findByAdmin(admin.getAdminId());
                setAgencyIds.addAll(lookAgencys);
            }
            agencyIds = new LinkedList<>(setAgencyIds);
            if(agencyIds.size()==0) {
                guideAgencyInfosList = guideAgencyServiceRead.findByAdminId(null, admin.getAdminId(), admin.getAdminId(), false);
                for(GuideAgencyInfos guideAgencyInfos:guideAgencyInfosList){
                    agencyIds.add(guideAgencyInfos.getGuideAgencyId());
                }
            }else{
                guideAgencyInfosList = guideAgencyServiceRead.findAll(null,agencyIds,null,null);
            }
        }

        if(guideTel!=null && !"".equals(guideTel)){
            Admin guideAdmin = adminServiceRead.findByTel(guideTel);
            guideGroupInfos.setGuideId(guideAdmin.getAdminId());
        }

        List<GuideGroupInfos> guideGroupList = guideGroupServiceRead.findAll(guideGroupInfos,agencyIds,lgroupStartTime,
                rgroupStartTime, lheadCount, rheadCount, lridingCount,rridingCount);

        //获取当前时间
        Date curDate = new Date();


        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("pageSize", appConfig.getPage_size_web());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("guideGroupInfos",guideGroupInfos);
        model.addAttribute("guideGroupList", guideGroupList);
        model.addAttribute("constantUtil",constantUtil);
        model.addAttribute("guideAgencyInfosList",guideAgencyInfosList);
        model.addAttribute("curDate",curDate);

        return "guideGroup_list";
    }

    /**
     * 跳转添加旅行社页面
     * @return
     * @throws Exception
     */
    @RequestMapping("addJsp")
    public String addJsp(Model model)throws Exception{
        modelInit(model,null,"add");
        return "detail/guideGroup_add";
    }

    /**
     * 添加旅游团
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("add")
    public String add(HttpSession session,GuideGroupInfos guideGroupInfos,String guideTel,MultipartFile excel)throws Exception{
        //获取当前操作的旅行社
        Admin agencyAdmin = (Admin)session.getAttribute("admin");
        guideGroupInfos.setGuideGroupCreatorId(agencyAdmin.getAdminId());
        List<GuideAgencyInfos> guideAgencyInfosList = guideAgencyServiceRead.findByAdminId(null,agencyAdmin.getAdminId(),null,false);
        if(guideAgencyInfosList.size()==1){
            guideGroupInfos.setGuideGroupAgencyId(guideAgencyInfosList.get(0).getGuideAgencyId());
        }
        //获取导游的id
        Admin guideAdmin = adminServiceRead.findByTel(guideTel);
        if(guideAdmin!=null){
            guideGroupInfos.setGuideId(guideAdmin.getAdminId());
        }
        Long guideGroupId = guideGroupServiceWrite.add(guideGroupInfos);
        //跟据excel写入旅游团与用户的关联表以及user表
        List<User> users = getTouristFromExcel(excel);
        importTourist(guideGroupId,users);
        return "redirect:list.action";
    }


    @RequestMapping(value = "/checkImport", method = RequestMethod.POST)
    public @ResponseBody  MessageUtil checkImport(@RequestParam("file") MultipartFile excel)throws Exception{
        List<User> users = getTouristFromExcel(excel);
        Map data = new HashMap();
        List<User> errUsers = new ArrayList<>();
        /**
         * 错误码:
         * 1. 0-->手机号错误
         */
        List<Integer> errCodes = new ArrayList<>();
        for(User user:users){
            String tel = user.getUserTel();
            String idCard = user.getUserIdcard();
            boolean fTel = true;
            boolean fId = true;
            if(tel!=null&&!"".equals(tel)){
                if(!CheckUtil.checkTel(tel)){
                    fTel=false;
                }
            }
            if(idCard!=null&&!"".equals(idCard)){
//                if(!CheckUtil.checkIdCard(idCard,user.getUserRealname())){
//                    fId=false;
//                }
                if(!CheckUtil.IsIDcard(idCard)){
                    fId=false;
                }
            }
            if(!fTel && fId){
                errUsers.add(user);
                errCodes.add(0);
            }else if(fTel && !fId){
                errUsers.add(user);
                errCodes.add(1);
            }else if(!fTel && !fId){
                errUsers.add(user);
                errCodes.add(2);
            }
        }
        if(errUsers.size()==0){
            messageUtil.setMessage("success");
            return messageUtil;
        }
        data.put("errUsers",errUsers);
        data.put("errCodes",errCodes);
        messageUtil.setMessage("error");
        messageUtil.setData(data);
        return messageUtil;
    }

    /**
     * 跳转编辑旅游团页面
     * @return
     * @throws Exception
     */
    @RequestMapping("editJsp")
    public String editJsp(Long guideGroupId,Model model)throws Exception{
        modelInit(model,guideGroupId,"edit");
        return "detail/guideGroup_add";
    }

    /**
     * 编辑旅游团
     * @param guideGroupInfos
     * @return
     * @throws Exception
     */
    @RequestMapping("edit")
    public String edit(GuideGroupInfos guideGroupInfos,String guideTel)throws Exception{
        guideGroupServiceWrite.update(guideGroupInfos);
        return "redirect:list.action";
    }

    /**
     * 删除旅游团
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("delete")
    public @ResponseBody
    String delete(Long guideGroupId)throws Exception{
        guideGroupServiceWrite.deleteById(guideGroupId);
        return "success";
    }


    /**
     * 旅游团详情
     * @param guideGroupId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("detail")
    public String detail(Long guideGroupId,Model model,String phone,String idCard,String name)throws Exception{
        //查询导游信息
        GuideGroupInfos guideGroupInfos=guideGroupServiceRead.findById(guideGroupId);
        Long guide_id = guideGroupInfos.getGuideId();
        Admin adminGuide = adminServiceRead.findAdminId(guide_id);
        ConstantUtil constantUtil = new ConstantUtil();
        /**
         * 游客的信息:
         * 1. 未进行租赁的游客 从user表中读取
         * 2. 进行租赁的游客 从bike_rent_info 中读取
         */
        //从关联表中取出所有游客id
        List<Long> userIds = guideGroupUserRead.findByGuideGroupId(guideGroupId);
        List<User> unRentUsers = new LinkedList<>();
        List<BikeRentInfo> rentUsers = new LinkedList<>();
        for(Long userId:userIds){
            BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findByUserId(userId,phone,idCard,name);
            if(bikeRentInfo!=null){
                rentUsers.add(bikeRentInfo);
            }else{
                User user = userServiceRead.findByIdCondition(userId,phone,idCard,name);
                if(user!=null){
                    unRentUsers.add(user);
                }
            }
        }
        //获取当前时间
        Date curDate = new Date();
        model.addAttribute("adminGuide",adminGuide);
        model.addAttribute("unRentUsers",unRentUsers);
        model.addAttribute("rentUsers",rentUsers);
        model.addAttribute("guideGroupInfos",guideGroupInfos);
        model.addAttribute("constantUtil",constantUtil);
        model.addAttribute("curDate",curDate);
        return "detail/guideGroup_detail";
    }

    @RequestMapping("touristRentInfo")
    public String touristRentInfo(Long userId,Model model) throws Exception{
        BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findByUserId(userId,null,null,null);
        model.addAttribute("bikeRentInfo",bikeRentInfo);
        return "detail/guideGroup_tourist_rent";
    }

    @RequestMapping("editTouristJsp")
    public String editTouristJsp(Long userId,Long guideGroupId,Model model) throws Exception{
        User user = userServiceRead.findById(userId);
        model.addAttribute("user",user);
        model.addAttribute("guideGroupId",guideGroupId);
        return "detail/guideGroup_tourist";
    }

    @RequestMapping("editTourist")
    public String editTourist(User user,Long guideGroupId,RedirectAttributes redirectAttributes) throws Exception{
        userServiceWrite.updateUser(user);
        redirectAttributes.addAttribute("guideGroupId",guideGroupId);
        return "redirect:detail.action";
    }
    /**
     * 发券功能
     * @param guideGroupId
     * @return
     * @throws Exception
     *
     */
    @RequestMapping("couponFab")
    public @ResponseBody
    String couponFab(Long guideGroupId)throws Exception{
        GuideGroupInfos guideGroupInfos = guideGroupServiceRead.findByGroupId(guideGroupId);
        /**
         * 1. 生成二维码图片(二维码跳转链接先写死,后边改进)
         * 2. 将图片存储在服务器上
         * 3. 将二维码信息(图片路径等)插入到coupon_info中
         */
        Date curDate = new Date();
        String qrText = appConfig.getBase_path_weixin()+"/html/registFromGuide.html?groupId="+guideGroupId+"&"+"couponSchemeId="+guideGroupInfos.getGuideGroupCouponId();
        String qrName = guideGroupId+"_"+guideGroupInfos.getCouponScheme().getPlanName()+"_"+curDate.getTime()+".png";
        String qrPath = appConfig.getBase_path_resource();
        boolean is_success = QRCodeUtil.makeSimple(qrText,qrPath,qrName);
        if(!is_success){
            return "failure";
        }else {
            String url = qrPath+"/"+qrName;
            guideGroupInfos.setGuideGroupCouponInfo(url);
            /**
             * 1. 将券发送给游客(coupon_to_user)
             * 2. 更改发券状态和发券时间
             * 3. 添加券的路径信息
             */
            //查询旅游团下的全部用户
            List<Long> userIds = guideGroupUserRead.findByGuideGroupId(guideGroupId);
            List<User> users = userServiceRead.findByIds(userIds);

            for(User user:users){
                CouponToUser couponToUser = couponToUserServiceRead.findByIds(guideGroupInfos.getGuideGroupCouponId(),user.getUserId());
                if(couponToUser==null){
                    couponToUser = new CouponToUser();
                    couponToUser.setUserId(user.getUserId());
                    couponToUser.setPlanId(guideGroupInfos.getGuideGroupCouponId());
                    couponToUser.setState(1);
                    couponToUser.setCreateTime(curDate);
                    couponToUserServiceWrite.add(couponToUser);
                }
            }
            guideGroupInfos.setGuideGroupCouponState(1);
            guideGroupInfos.setGuideGroupCouponPublishtime(curDate);
            guideGroupServiceWrite.update(guideGroupInfos);
        }
        return "success";
    }
    /**
     * 骑行统计
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("ridingStats")
    public String ridingStats(Model model)throws Exception{
        List<Integer> arr = new ArrayList<Integer>();
        Date date = DateUtil.getCurrentDay();//当天零点
        Integer increase = null;
        for(int i = 0; i < 7; i++){
            increase = userServiceRead.userCount(DateUtil.minusDateNum(date, i));
            arr.add(increase);
        }
        Integer newuser = userServiceRead.userCount(date);
        model.addAttribute("arr", arr);
        model.addAttribute("newuser",newuser);
        return "riding_stats";
    }

    /**
     * 初始化添加和编辑页面的model
     * @param model
     * @param guideGroupId
     */
    private void modelInit(Model model,Long guideGroupId,String actionName){
        try {
            GuideGroupInfos guideGroup = new GuideGroupInfos();
            Admin guideAdmin = new Admin();
            if(guideGroupId!=null){
                guideGroup = guideGroupServiceRead.findById(guideGroupId);
                guideAdmin = adminServiceRead.findAdminId(guideGroup.getGuideId());
            }
            //读取骑行券方案
            List<CouponScheme> couponSchemeList = couponSchemeServiceRead.findAllPlan();
            model.addAttribute("guideGroup", guideGroup);
            model.addAttribute("actionName",actionName);
            model.addAttribute("guideAdmin",guideAdmin);
            model.addAttribute("couponSchemeList",couponSchemeList);
        }catch (Exception e){
            //todo
        }

    }

    /**
     * 从excel中获取游客信息
     * @param excel
     * @return
     * @throws Exception
     */
    private List<User> getTouristFromExcel(MultipartFile excel) throws Exception{
        List<User> users = new ArrayList<>();
        if (!excel.isEmpty()) {
            InputStream inputStream = excel.getInputStream();//获取流
            String filename = excel.getOriginalFilename();//文件名
            String extensionName = filename.substring(filename.lastIndexOf(".")+1, filename.length());
            users = ReadWriteExcelUtil.exportTourist(inputStream,extensionName,0);//读取游客列表
        }
        return users;
    }
    /**
     * 1. 将获取到的用户更新到用户列表
     * 2. 添加关联表记录
     * @param guideGroupId
     * @param users
     */
    private void importTourist(Long guideGroupId,List<User> users) throws Exception{
        for(User user: users){
            //将用户与旅游团关联
            GuideGroupUser guideGroupUser = new GuideGroupUser();
            guideGroupUser.setGuideGroupId(guideGroupId);
            //先查看用户是否已注册(现在先根据电话)
            User oldUser = userServiceRead.findUserByPhone(user.getUserTel());
            if(oldUser==null){
                Long userId = userServiceWrite.addUser(user);
                guideGroupUser.setUserId(userId);
            }else{
                guideGroupUser.setUserId(oldUser.getUserId());
            }
            guideGroupUserWrite.add(guideGroupUser);
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

