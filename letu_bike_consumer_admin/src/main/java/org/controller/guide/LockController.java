package org.controller.guide;

import org.apache.log4j.Logger;
import org.entity.dto.*;
import org.service.cms.read.*;
import org.service.cms.write.GuideGroupServiceWrite;
import org.service.cms.write.UserServiceWrite;
import org.service.cms.write.BikeRentInfoServiceWrite;
import org.service.cms.write.FixedReturnServiceWrite;
import org.service.cms.write.BikeServiceWrite;
import org.service.lock.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.util.*;
import org.util.redis.RedisService;
import org.util.redis.ThreadMapUtil;

import javax.annotation.Resource;
import java.lang.management.LockInfo;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/lock")
public class LockController {
    @Resource
    GuideGroupServiceRead guideGroupServiceRead;
    @Resource
    GuideGroupServiceWrite guideGroupServiceWrite;
    @Resource
    SysParamentServiceRead sysParamentServiceRead;
    @Resource
    UserServiceRead userServiceRead;
    @Resource
    UserServiceWrite userServiceWrite;
    @Resource
    private RedisService redisService;
    @Resource
    BikeRentInfoServiceRead bikeRentInfoServiceRead;
    @Resource
    BikeRentInfoServiceWrite bikeRentInfoServiceWrite;
    @Resource
    FixedReturnServiceRead fixedReturnServiceRead;
    @Resource
    FixedReturnServiceWrite fixedReturnServiceWrite;
    @Resource
    BikeServiceWrite bikeServiceWrite;
    @Resource
    BikeLockInfoServiceRead bikeLockInfoServiceRead;
    @Resource
    ModelsServiceRead modelsServiceRead;
    @Resource
    LockService lockService;
    @Resource
    BikeServiceRead bikeServiceRead;
    @Resource
    DataDetServiceRead dataDetServiceRead;

    protected final MessageUtil messageUtil = new MessageUtil();

    //    当前类的logger日志
    private static Logger loggers = Logger.getLogger(LockController.class);
    @Resource
    AdminServiceRead adminServiceRead;

    /**
     * 代还车
     */
    @RequestMapping("/finish")
    MessageUtil finish(String guideId, Long userId, Long groupId) throws Exception {
        FixedReturn fixedReturn1 = null;
        Map<String, Object> data = new HashMap<String, Object>();

        User user = userServiceRead.findById(userId);
        BikeRentInfo rentInfo = bikeRentInfoServiceRead.findNotFinishByUserId(userId);
        Bike bike = bikeServiceRead.findBikeByBikeId(rentInfo.getRentInfoBikeId());
        BikeLockInfo lockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
        Models models = modelsServiceRead.findModelsById(bike.getBikeModelsId()); // 查车的型号
        loggers.info("代还车-团id:" + groupId + "车code:" + bike.getBikeCode() + "订单id:" + rentInfo.getRentInfoId());


        if(!"".equals(lockInfo.getBikeLockType())&&null!=lockInfo.getBikeLockType()&&1==lockInfo.getBikeLockType()&&lockInfo.getBikeLockStatus()==1){
            //半自动锁，未上锁直接点击临时上锁
            loggers.info("未上锁=====");
            data.put("judgeState", 12);
            messageUtil.setData(data);
            messageUtil.setMessage("请先关锁后还车");
            messageUtil.setCode(12);
            return messageUtil;
        }
        Integer isfar = 1;//判断距离结果(0代表可以1代表距离太远)
        if(models.getModelsIsfixedPoint()==1) {//如果是为定点还车
            List<DataDet> typeList = dataDetServiceRead.findDataDetByDataId((long) 7);//获取误差距离
            try {
                if ("" != redisService.get("r2" + lockInfo.getBikeLockCode()) && null != redisService.get("r2" + lockInfo.getBikeLockCode())) {
                    isfar = 0;
                    redisService.del("r2" + lockInfo.getBikeLockCode());
                    loggers.info(lockInfo.getBikeLockCode() + "电子围栏还车成功");
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                redisService.closeJedis();
            }
            if (isfar == 1) {
                data.put("judgeState", 8);
                messageUtil.setData(data);
                messageUtil.setCode(8);
                messageUtil.setMessage("请在站点内还车");
                return messageUtil;
            }
        }

        //全自动锁，结束行程时发送关锁命令，并线程等待
        if (lockInfo.getBikeLockType() == null || lockInfo.getBikeLockType() == 0) {
            //将数据存入redis 功能标志/车ID/订单ID
            redisService.set(lockInfo.getBikeLockCode(), "4," + bike.getBikeId() + "," + rentInfo.getRentInfoId() + "," + 0 + "," + 0, 60000);
            //关闭jedis
            redisService.closeJedis();
            if (!sendCommand(bike.getBikeLock(), 0, 2)) {
                messageUtil.setCode(0);
                data.put("judgeState", 0);
                messageUtil.setData(data);
                messageUtil.setMessage("关锁失败");
                return messageUtil;
            }

            ThreadMapUtil.threadMap.put(lockInfo.getBikeLockCode(), this);
            synchronized (this) {
                this.wait(15000);
            }
            try {
                if ("" != redisService.get("r" + lockInfo.getBikeLockCode()) && null != redisService.get("r" + lockInfo.getBikeLockCode())) {
                    //锁返回信息
                    String rdata = redisService.get("r" + lockInfo.getBikeLockCode());
                    if ("success".equals(rdata)) {
                        //如果是定点还车
                        if (models.getModelsIsfixedPoint() == 1) {
                            if (null != fixedReturn1) {
                                fixedReturnServiceWrite.update(fixedReturn1, 2);//更新站点车辆数量
                                bike.setBikeFixedReturnId(fixedReturn1.getFixedReturnId());//更新车所在站点
                                rentInfo.setRentEndFixedId(fixedReturn1.getFixedReturnId());//结束所在还车点
                            }
                        }
                        //还车成功删除用户开锁记录
                        redisService.del("userOpen" + lockInfo.getBikeLockCode());
                        messageUtil.setCode(1);
                        data.put("judgeState", 1);
                        messageUtil.setMessage("success");
                    } else if ("4".equals(rdata) || "5".equals(rdata)) {
                        //关锁受阻
                        data.put("judgeState", 10);
                        messageUtil.setCode(10);
                    } else if ("1".equals(rdata) || "3".equals(rdata)) {
                        //关锁超时
                        data.put("judgeState", 11);
                        messageUtil.setCode(11);
                    }
                } else {
                    data.put("judgeState", 11);
                    messageUtil.setCode(11);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                redisService.del("r" + lockInfo.getBikeLockCode());
                redisService.closeJedis();
            }
        } else if (!"".equals(lockInfo.getBikeLockType()) && lockInfo.getBikeLockType() == 1) {//半自动锁
            try {
                if (lockInfo.getBikeLockStatus() == 0) { //锁上
                    //半自动锁
                    Date nowDay = new Date();
                    Long rideTime = DateUtil.minuteDiff1(rentInfo.getRentStarttime(), nowDay);
                    rentInfo.setRentEndtime(nowDay); // 还车时间
                    rentInfo.setRentLongtime(Integer.parseInt(rideTime.toString()));
                    // 还车方格id
//                    String latLng = userLongitude + "," + userAtitude;
//                    String blockCode = BlockUtil.getBlockCode(latLng);
//                    //Long rentEndBlockId = blockWxServiceRead.findBlockId(blockCode);
//                    rentInfo.setRentEndBlock(blockCode);
//                    rentInfo.setRentEndlat(userAtitude);
//                    rentInfo.setRentEndlng(userLongitude);
                    if (models.getModelsIsfixedPoint() == 1) {
                        if (null != fixedReturn1) {
                            rentInfo.setRentEndFixedId(fixedReturn1.getFixedReturnId());//结束所在还车点
                        }
                    }

                    //更改车辆经纬度
                    bike.setBikeState(0);// 空闲中
                    //bike.setBikeBlockId(rentEndBlockId);
//                    bike.setBikeBlock(blockCode);
                    bike.setBikeLastRentTime(new Date());
                    lockService.sendLocationMessage(lockInfo.getBikeLockCode());
                    //如果是定点还车
                    if (models.getModelsIsfixedPoint() == 1) {
                        if (null != fixedReturn1) {
                            fixedReturnServiceWrite.update(fixedReturn1, 2);
                            bike.setBikeFixedReturnId(fixedReturn1.getFixedReturnId());//更新车所在站点
                        }
                    }
                    bike.setBikeTemporarylocktime(null);

                    messageUtil.setCode(1);
                    redisService.del("userOpen" + lockInfo.getBikeLockCode());
                    data.put("judgeState", 1);
                    messageUtil.setMessage("还车成功");
                }
            } catch (Exception e) {
                loggers.error("异常信息", e);
            }

        }

        bikeServiceWrite.updateBike(bike);
        rentInfo.setRentPrice(BigDecimal.ZERO);
        rentInfo.setRentState(1);
        bikeRentInfoServiceWrite.updateBikeRentInfo(rentInfo);
        user.setUserState(0);
        userServiceWrite.updateUser(user);

        if (null != rentInfo) {
            if (null != rentInfo.getRentEndlng() && null != rentInfo.getRentEndlat()) {
                String endName = GetLocationUtil.getAdd(rentInfo.getRentEndlng().toString(), rentInfo.getRentEndlat().toString());
                if (null == endName) {
                    endName = "暂无信息";
                }
                rentInfo.setEndFixedName(endName);
            }
        }
        data.put("bikeRentInfo", rentInfo);
        messageUtil.setData(data);
        return messageUtil;
    }

    /**
     * 代开锁
     */
    @RequestMapping("/unlock")
    MessageUtil unlockAgent(String openid, Long userId, String bikeCode, Long groupId) throws Exception {
        loggers.info("代开锁的车辆编号为===" + bikeCode);
        Map<String, Object> data = new HashMap<String, Object>();

        Bike bike = bikeServiceRead.findBikeByBikeCode(bikeCode);
        User user = userServiceRead.findById(userId);

        //检查bike/user/lock
        check(bike, user);
        if (1 != messageUtil.getCode()) {   //车子不能租借
            return messageUtil;
        }

        Models models = modelsServiceRead.findModelsById(bike.getBikeModelsId());
        BikeLockInfo lockInfo = bike.getBikeLock();


        //开锁
        unlock(lockInfo, models, bike, data);
        if(messageUtil.getCode()!=1){//开锁失败
            return messageUtil;
        }

        //更新车辆用户状态并生成租赁订单
        BikeRentInfo bikeRentInfo = update(bike, user, models, lockInfo ,groupId,1);



        data.put("bike", bike);
        data.put("models", models);
        data.put("bikeRentInfo", bikeRentInfo);
        messageUtil.setData(data);
        return messageUtil;
    }


    /**
     * 检查车辆车锁和用户
     */
    public MessageUtil check(Bike bike, User user) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        if (null == bike) { // 为null
            messageUtil.setCode(0);
            data.put("judgeState", 0);
            messageUtil.setData(data);
            messageUtil.setMessage("没有该车辆信息");
            return messageUtil;
        }
        switch (bike.getBikeState()) {
            case 1:
                messageUtil.setCode(0);
                data.put("judgeState", 0);
                messageUtil.setData(data);
                messageUtil.setMessage("该车已被租借，请您选择其他的车辆出行");
                return messageUtil;
            case 2:
                messageUtil.setCode(0);
                data.put("judgeState", 0);
                messageUtil.setData(data);
                messageUtil.setMessage("该车已被租借，请您选择其他的车辆出行");
                return messageUtil;
            case 3:
                messageUtil.setCode(0);
                data.put("judgeState", 0);
                messageUtil.setData(data);
                messageUtil.setMessage("该车正在维护中，请您选择其他的车辆出行");
                return messageUtil;
            case 4:
                messageUtil.setCode(0);
                data.put("judgeState", 0);
                messageUtil.setData(data);
                messageUtil.setMessage("该车已被锁定，请您选择其他的车辆出行");
                return messageUtil;
        }
        loggers.info("===获取代开锁的用户Id===" + user.getUserId());
        loggers.info("===获取代开锁的用户账号===" + user.getUserTel());
        loggers.info("===获取代开锁的用户状态===" + user.getUserState());
        if (0 != user.getUserState()) { // 若用户不是在空闲状态
            if (1 == user.getUserState()) { // 如果是租借状态
                messageUtil.setMessage("还有订单未完成");
                messageUtil.setCode(4);
                data.put("judgeState", 4);
                messageUtil.setData(data);
                return messageUtil;

            } else { // 用户被删除删除
                messageUtil.setCode(5);
                data.put("judgeState", 5);
                messageUtil.setMessage("账号已被删除");
            }
        }
        Double lowpower = Double.valueOf(sysParamentServiceRead.findByName("lowpower").getSysParamentValue());
        //低电量提示
        BikeLockInfo lockInfo = bike.getBikeLock();
        loggers.info("===获取锁编号===" + lockInfo.getBikeLockCode());
        loggers.info("===获取锁电量===" + lockInfo.getBikeLockVoltage());
        if (null != lockInfo.getBikeLockVoltage() && !"".equals(lockInfo.getBikeLockVoltage()) && lockInfo.getBikeLockVoltage() < lowpower) {
            loggers.info("低电量");
            messageUtil.setCode(7);
            data.put("judgeState", 7);
            messageUtil.setData(data);
            messageUtil.setMessage("电量不足");
            return messageUtil;
        }
        if (!lockService.judgeLockConnet(lockInfo.getBikeLockCode())) {
            loggers.info("锁未连接上");
            messageUtil.setCode(0);
            data.put("judgeState", 0);
            messageUtil.setData(data);
            messageUtil.setMessage("该车车锁故障，请更换车辆出行或稍后再试");
            return messageUtil;
        }
        messageUtil.setMessage("可以租借");
        data.put("judgeState", 1);
        messageUtil.setCode(1);
        messageUtil.setData(data);
        return messageUtil;
    }

    /**
     * 更新状态
     */
    BikeRentInfo update(Bike bike, User user, Models models, BikeLockInfo lockInfo,Long groupId,int way) throws Exception {
        user.setUserState(1);// 更改人物状态
        if (null == user.getUserFistusebikeid()) {
            user.setUserFistusebikeid(bike.getBikeId());
            user.setUserFistusebiketime(new Date());
        }
        user.setUserLastusebiketime(new Date());
        userServiceWrite.updateUser(user);
        loggers.info("更改用户状态====");

        BikeRentInfo bikeRentInfo = new BikeRentInfo();// 更改订单状态
        bikeRentInfo.setRentInfoBikeId(bike.getBikeId());
        bikeRentInfo.setRentBikeChannelId(models.getModelsChannelId());
        if (bike.getBikeFixedReturnId() != null && 0 != bike.getBikeFixedReturnId()) {
            bikeRentInfo.setRentStartFixedId(bike.getBikeFixedReturnId());//起始还车点
        } else {
            BikeRentInfo recentBikeRenInfo = bikeRentInfoServiceRead.findBikeRentInfoByBikeId(bike.getBikeId()).get(0);
            if (recentBikeRenInfo != null) {
                if (recentBikeRenInfo.getRentEndFixedId() != null) {
                    bikeRentInfo.setRentStartFixedId(recentBikeRenInfo.getRentEndFixedId());//起始还车点
                }
            }
        }
        loggers.info("添加起始还车点====");
        bikeRentInfo.setRentGuideGroupId(groupId);
        bikeRentInfo.setRentUnlockWay(way);
        bikeRentInfo.setRentIsvillager(0);//游客
        bikeRentInfo.setRentInfoUserId(user.getUserId());
        bikeRentInfo.setRentStarttime(new Date());
        bikeRentInfo.setRentStartlat(bike.getBikeAtitude());
        bikeRentInfo.setRentStartlng(bike.getBikeLongitude());
        if (null != bikeRentInfo) {
            if (null != bikeRentInfo.getRentStartlng() && null != bikeRentInfo.getRentStartlat()) {
                String startName = GetLocationUtil.getAdd(bikeRentInfo.getRentStartlng().toString(), bikeRentInfo.getRentStartlat().toString());
                if (null == startName) {
                    startName = "暂无信息";
                }
                bikeRentInfo.setStartFixedName(startName);
            }
        }
        bikeRentInfo.setRentState(2); // 未完成
        // 订单号 = "标志"+时间+车辆id+用户id
        String rentOrderCode = "Z" + DateUtil.format03(new Date()) + bike.getBikeId() + user.getUserId();
        bikeRentInfo.setRentOrderCode(rentOrderCode);
        //bikeRentInfo.setRentStartBlockId(bike.getBikeBlockId()); // 方格
        bikeRentInfo.setRentStartBlock(bike.getBikeBlock()); // 方格
        bikeRentInfo.setRentInfoId(bikeRentInfoServiceWrite.addRentInfo(bikeRentInfo));
        loggers.info("生成订单=====" + bikeRentInfo.getRentInfoId());
        bike.setBikeState(2); // 更改 bike 状态
        bike.setBikeRentNum(bike.getBikeRentNum() + 1);// 租借次数
        bikeServiceWrite.updateBike(bike);// 更新

        //默认开锁成功将数据存入redis 功能标志/用户ID/车ID/订单ID
        redisService.set(lockInfo.getBikeLockCode(), "1," + user.getUserId() + "," + bike.getBikeId() + "," + bikeRentInfo.getRentInfoId(), 60000);
        redisService.closeJedis();
        return bikeRentInfo;
    }

    /**
     * 开锁过程
     */
    MessageUtil unlock(BikeLockInfo lockInfo, Models models, Bike bike, Map data) throws Exception {
        ThreadMapUtil.threadMap.put(lockInfo.getBikeLockCode(), this);
        synchronized (this) {
            if (!sendCommand(lockInfo, 1, 0)) {
                messageUtil.setCode(0);
                data.put("judgeState", 0);
                messageUtil.setData(data);
                messageUtil.setMessage("该车车锁故障，请更换车辆出行或稍后再试");
                return messageUtil;
            }
            loggers.info("发送开锁命令成功 等待10秒结束=====");
            this.wait(10000); //15s超时
            loggers.info("发送开锁命令成功 等待10秒结束=====");
        }
        try {
            String rdata = redisService.get("r" + lockInfo.getBikeLockCode());//锁返回信息
            if ("" != rdata && null != rdata) {
                loggers.info("锁返回信息=====");
                if ("success".equals(rdata)) {
                    loggers.info("开锁成功=====");
                    //如果是定点还车
                    if (models.getModelsIsfixedPoint() == 1) {
                        Bike nowBike = bikeServiceRead.findBikeByBikeId(bike.getBikeId());
                        FixedReturn nowFixedReturn = fixedReturnServiceRead.findFixedById(nowBike.getBikeFixedReturnId());
                        if (null != nowFixedReturn) {
                            fixedReturnServiceWrite.update(nowFixedReturn, 1);
                        }
                        nowBike.setBikeFixedReturnId((long) 0);//离开站点为0
                        bikeServiceWrite.updateBike(nowBike);// 更新
                    }
                    data.put("judgeState", 1);
                    messageUtil.setCode(1);
                    messageUtil.setData(data);
                    messageUtil.setMessage("开锁成功");
                } else if ("4".equals(rdata) || "5".equals(rdata)) {
                    loggers.info("开锁失败=====");
                    messageUtil.setCode(0);
                    data.put("judgeState", 0);
                    messageUtil.setData(data);
                    messageUtil.setMessage("开锁失败，车锁受阻，请确保车辆插销未被阻挡再开锁");
                } else if ("1".equals(rdata) || "3".equals(rdata)) {
                    loggers.info("开锁失败=====");
                    messageUtil.setCode(0);
                    data.put("judgeState", 0);
                    messageUtil.setData(data);
                    messageUtil.setMessage("开锁超时，该车可能有故障，请选择其它车租赁或稍后再试");
                }
            } else {
                loggers.info("开锁失败=====");
                messageUtil.setCode(0);
                data.put("judgeState", 0);
                messageUtil.setData(data);
                messageUtil.setMessage("开锁超时，该车可能有故障，请选择其它车租赁或稍后再试");
            }
        } catch (Exception e) {
            // TODO: handle exception
            loggers.error("异常信息", e);
        } finally {
            redisService.del("r" + lockInfo.getBikeLockCode());
            redisService.closeJedis();
        }

        return messageUtil;
    }

    /**
     * 发送开锁命令
     */
    public boolean sendCommand(BikeLockInfo lockInfo, Integer flag, Integer isTemporary) throws Exception {
        if (0 == flag) {
            if (lockService.sendLockMessage(lockInfo.getBikeLockCode())) {// 发送关锁指令
                return true;
            } else {
                return false;
            }
        } else if (1 == flag) {
            if (lockService.sendUnlockMessage(lockInfo.getBikeLockCode())) {// 发送开锁指令
                if (null != lockInfo.getBikeLockType() && !"".equals(lockInfo.getBikeLockType()) && lockInfo.getBikeLockType() == 1) {
                    redisService.set("userOpen" + lockInfo.getBikeLockCode(), "open");
                }
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}