package org.util;

import org.apache.poi.openxml4j.opc.PackageRelationship;

/**
 * 常用的状态常量
 */
public class ConstantUtil {
    /**
     * 导游优惠券状态
     * 0：未发券
     * 1：已发券
     */
    //导游优惠券 已发券状态
    private int GUIDE_COUPON_OPEN;
    //导游优惠券 未发券状态
    private int GUIDE_COUPON_CLOSE;

    /**
     * 导游团出团状态
     * 0：未出团
     * 1：已出团
     * 2：结束出团
     */
    private int GUIDE_GROUP_START;
    private int GUIDE_GROUP_NOT_START;
    private int GUIDE_GROUP_END;


    /**
     * 游客开锁方式
     * 0: 自己开锁(默认)
     * 1: 导游代开锁
     */
    private int GUIDE_UNLOCK_WAY_SELF;
    private int GUIDE_UNLOCK_WAY_GUIDE;

    /**
     * 用户验证状态
     * 0: 未验证
     * 1: 已验证
     */
    private int USER_VERIFY_YES;
    private int USER_VERIFY_NO;

    /**
     * 用户创建者类型
     * 0: 自己
     * 1: 旅行社
     * 2: 导游
     */
    private int USER_CREATOR_SELF;
    private int USER_CREATOR_AGENCY_TRAVEL;
    private int USER_CREATOR_GUIDE_TRAVEL;

    /**
     * 用户租赁骑行状态
     */
    private int USER_RENT_OPEN;
    private int USER_RENT_CLOSE;
    private int USER_RENT_NOT_START;

    /**
     * 券类型
     * 1. 抵用时间
     * 2. 抵用金额
     * 3. 完全免费
     */
    private int COUPON_TYPE_MONEY;
    private int COUPON_TYPE_TIME;
    private int COUPON_TYPE_FREE;

    public ConstantUtil(){
        GUIDE_COUPON_OPEN=1;
        GUIDE_COUPON_CLOSE=0;

        GUIDE_GROUP_NOT_START=0;
        GUIDE_GROUP_START=1;
        GUIDE_GROUP_END=2;

        GUIDE_UNLOCK_WAY_SELF=0;
        GUIDE_UNLOCK_WAY_GUIDE=1;

        USER_VERIFY_YES=0;
        USER_VERIFY_NO=1;

        USER_CREATOR_SELF=0;
        USER_CREATOR_AGENCY_TRAVEL=1;
        USER_CREATOR_GUIDE_TRAVEL=2;

        USER_RENT_NOT_START=0;
        USER_RENT_OPEN=2;
        USER_RENT_CLOSE=1;

        COUPON_TYPE_MONEY=0;
        COUPON_TYPE_TIME=1;
        COUPON_TYPE_FREE=3;
    }

    public int getGUIDE_COUPON_OPEN() {
        return GUIDE_COUPON_OPEN;
    }

    public int getGUIDE_COUPON_CLOSE() {
        return GUIDE_COUPON_CLOSE;
    }

    public int getGUIDE_GROUP_START() {
        return GUIDE_GROUP_START;
    }

    public int getGUIDE_GROUP_NOT_START() {
        return GUIDE_GROUP_NOT_START;
    }

    public int getGUIDE_UNLOCK_WAY_SELF() {
        return GUIDE_UNLOCK_WAY_SELF;
    }

    public int getGUIDE_UNLOCK_WAY_GUIDE() {
        return GUIDE_UNLOCK_WAY_GUIDE;
    }

    public int getUSER_VERIFY_YES() {
        return USER_VERIFY_YES;
    }

    public int getUSER_VERIFY_NO() {
        return USER_VERIFY_NO;
    }

    public int getUSER_CREATOR_SELF() {
        return USER_CREATOR_SELF;
    }

    public int getUSER_CREATOR_AGENCY_TRAVEL() {
        return USER_CREATOR_AGENCY_TRAVEL;
    }

    public int getUSER_CREATOR_GUIDE_TRAVEL() {
        return USER_CREATOR_GUIDE_TRAVEL;
    }

    public int getUSER_RENT_OPEN() {
        return USER_RENT_OPEN;
    }

    public int getUSER_RENT_CLOSE() {
        return USER_RENT_CLOSE;
    }

    public int getUSER_RENT_NOT_START() {
        return USER_RENT_NOT_START;
    }

    public int getGUIDE_GROUP_END() {
        return GUIDE_GROUP_END;
    }

    public int getCOUPON_TYPE_MONEY() {
        return COUPON_TYPE_MONEY;
    }

    public int getCOUPON_TYPE_TIME() {
        return COUPON_TYPE_TIME;
    }

    public int getCOUPON_TYPE_FREE() {
        return COUPON_TYPE_FREE;
    }
}
