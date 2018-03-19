package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class RedeemPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public RedeemPlanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(Long value) {
            addCriterion("plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(Long value) {
            addCriterion("plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(Long value) {
            addCriterion("plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(Long value) {
            addCriterion("plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(Long value) {
            addCriterion("plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<Long> values) {
            addCriterion("plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<Long> values) {
            addCriterion("plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(Long value1, Long value2) {
            addCriterion("plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(Long value1, Long value2) {
            addCriterion("plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNull() {
            addCriterion("plan_name is null");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNotNull() {
            addCriterion("plan_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNameEqualTo(String value) {
            addCriterion("plan_name =", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotEqualTo(String value) {
            addCriterion("plan_name <>", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThan(String value) {
            addCriterion("plan_name >", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("plan_name >=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThan(String value) {
            addCriterion("plan_name <", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThanOrEqualTo(String value) {
            addCriterion("plan_name <=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLike(String value) {
            addCriterion("plan_name like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotLike(String value) {
            addCriterion("plan_name not like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameIn(List<String> values) {
            addCriterion("plan_name in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotIn(List<String> values) {
            addCriterion("plan_name not in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameBetween(String value1, String value2) {
            addCriterion("plan_name between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotBetween(String value1, String value2) {
            addCriterion("plan_name not between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeIsNull() {
            addCriterion("plan_create_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeIsNotNull() {
            addCriterion("plan_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeEqualTo(Date value) {
            addCriterion("plan_create_time =", value, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeNotEqualTo(Date value) {
            addCriterion("plan_create_time <>", value, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeGreaterThan(Date value) {
            addCriterion("plan_create_time >", value, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_create_time >=", value, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeLessThan(Date value) {
            addCriterion("plan_create_time <", value, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_create_time <=", value, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeIn(List<Date> values) {
            addCriterion("plan_create_time in", values, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeNotIn(List<Date> values) {
            addCriterion("plan_create_time not in", values, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeBetween(Date value1, Date value2) {
            addCriterion("plan_create_time between", value1, value2, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_create_time not between", value1, value2, "planCreateTime");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeIsNull() {
            addCriterion("plan_redeem_code is null");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeIsNotNull() {
            addCriterion("plan_redeem_code is not null");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeEqualTo(String value) {
            addCriterion("plan_redeem_code =", value, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeNotEqualTo(String value) {
            addCriterion("plan_redeem_code <>", value, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeGreaterThan(String value) {
            addCriterion("plan_redeem_code >", value, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("plan_redeem_code >=", value, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeLessThan(String value) {
            addCriterion("plan_redeem_code <", value, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeLessThanOrEqualTo(String value) {
            addCriterion("plan_redeem_code <=", value, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeLike(String value) {
            addCriterion("plan_redeem_code like", value, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeNotLike(String value) {
            addCriterion("plan_redeem_code not like", value, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeIn(List<String> values) {
            addCriterion("plan_redeem_code in", values, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeNotIn(List<String> values) {
            addCriterion("plan_redeem_code not in", values, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeBetween(String value1, String value2) {
            addCriterion("plan_redeem_code between", value1, value2, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemCodeNotBetween(String value1, String value2) {
            addCriterion("plan_redeem_code not between", value1, value2, "planRedeemCode");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsIsNull() {
            addCriterion("plan_redeem_nums is null");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsIsNotNull() {
            addCriterion("plan_redeem_nums is not null");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsEqualTo(Integer value) {
            addCriterion("plan_redeem_nums =", value, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsNotEqualTo(Integer value) {
            addCriterion("plan_redeem_nums <>", value, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsGreaterThan(Integer value) {
            addCriterion("plan_redeem_nums >", value, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_redeem_nums >=", value, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsLessThan(Integer value) {
            addCriterion("plan_redeem_nums <", value, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsLessThanOrEqualTo(Integer value) {
            addCriterion("plan_redeem_nums <=", value, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsIn(List<Integer> values) {
            addCriterion("plan_redeem_nums in", values, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsNotIn(List<Integer> values) {
            addCriterion("plan_redeem_nums not in", values, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsBetween(Integer value1, Integer value2) {
            addCriterion("plan_redeem_nums between", value1, value2, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanRedeemNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_redeem_nums not between", value1, value2, "planRedeemNums");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdIsNull() {
            addCriterion("plan_coupon_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdIsNotNull() {
            addCriterion("plan_coupon_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdEqualTo(Long value) {
            addCriterion("plan_coupon_plan_id =", value, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdNotEqualTo(Long value) {
            addCriterion("plan_coupon_plan_id <>", value, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdGreaterThan(Long value) {
            addCriterion("plan_coupon_plan_id >", value, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("plan_coupon_plan_id >=", value, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdLessThan(Long value) {
            addCriterion("plan_coupon_plan_id <", value, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdLessThanOrEqualTo(Long value) {
            addCriterion("plan_coupon_plan_id <=", value, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdIn(List<Long> values) {
            addCriterion("plan_coupon_plan_id in", values, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdNotIn(List<Long> values) {
            addCriterion("plan_coupon_plan_id not in", values, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdBetween(Long value1, Long value2) {
            addCriterion("plan_coupon_plan_id between", value1, value2, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanCouponPlanIdNotBetween(Long value1, Long value2) {
            addCriterion("plan_coupon_plan_id not between", value1, value2, "planCouponPlanId");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeIsNull() {
            addCriterion("plan_online_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeIsNotNull() {
            addCriterion("plan_online_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeEqualTo(Date value) {
            addCriterion("plan_online_time =", value, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeNotEqualTo(Date value) {
            addCriterion("plan_online_time <>", value, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeGreaterThan(Date value) {
            addCriterion("plan_online_time >", value, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_online_time >=", value, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeLessThan(Date value) {
            addCriterion("plan_online_time <", value, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_online_time <=", value, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeIn(List<Date> values) {
            addCriterion("plan_online_time in", values, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeNotIn(List<Date> values) {
            addCriterion("plan_online_time not in", values, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeBetween(Date value1, Date value2) {
            addCriterion("plan_online_time between", value1, value2, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOnlineTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_online_time not between", value1, value2, "planOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeIsNull() {
            addCriterion("plan_offline_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeIsNotNull() {
            addCriterion("plan_offline_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeEqualTo(Date value) {
            addCriterion("plan_offline_time =", value, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeNotEqualTo(Date value) {
            addCriterion("plan_offline_time <>", value, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeGreaterThan(Date value) {
            addCriterion("plan_offline_time >", value, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_offline_time >=", value, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeLessThan(Date value) {
            addCriterion("plan_offline_time <", value, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_offline_time <=", value, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeIn(List<Date> values) {
            addCriterion("plan_offline_time in", values, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeNotIn(List<Date> values) {
            addCriterion("plan_offline_time not in", values, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeBetween(Date value1, Date value2) {
            addCriterion("plan_offline_time between", value1, value2, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanOfflineTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_offline_time not between", value1, value2, "planOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanStateIsNull() {
            addCriterion("plan_state is null");
            return (Criteria) this;
        }

        public Criteria andPlanStateIsNotNull() {
            addCriterion("plan_state is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStateEqualTo(Integer value) {
            addCriterion("plan_state =", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateNotEqualTo(Integer value) {
            addCriterion("plan_state <>", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateGreaterThan(Integer value) {
            addCriterion("plan_state >", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_state >=", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateLessThan(Integer value) {
            addCriterion("plan_state <", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateLessThanOrEqualTo(Integer value) {
            addCriterion("plan_state <=", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateIn(List<Integer> values) {
            addCriterion("plan_state in", values, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateNotIn(List<Integer> values) {
            addCriterion("plan_state not in", values, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateBetween(Integer value1, Integer value2) {
            addCriterion("plan_state between", value1, value2, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_state not between", value1, value2, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsIsNull() {
            addCriterion("plan_surplus_nums is null");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsIsNotNull() {
            addCriterion("plan_surplus_nums is not null");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsEqualTo(Integer value) {
            addCriterion("plan_surplus_nums =", value, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsNotEqualTo(Integer value) {
            addCriterion("plan_surplus_nums <>", value, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsGreaterThan(Integer value) {
            addCriterion("plan_surplus_nums >", value, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_surplus_nums >=", value, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsLessThan(Integer value) {
            addCriterion("plan_surplus_nums <", value, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsLessThanOrEqualTo(Integer value) {
            addCriterion("plan_surplus_nums <=", value, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsIn(List<Integer> values) {
            addCriterion("plan_surplus_nums in", values, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsNotIn(List<Integer> values) {
            addCriterion("plan_surplus_nums not in", values, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsBetween(Integer value1, Integer value2) {
            addCriterion("plan_surplus_nums between", value1, value2, "planSurplusNums");
            return (Criteria) this;
        }

        public Criteria andPlanSurplusNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_surplus_nums not between", value1, value2, "planSurplusNums");
            return (Criteria) this;
        }
        
        public Criteria andPlanReserveOnlineTimeIsNull() {
            addCriterion("plan_reserve_online_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeIsNotNull() {
            addCriterion("plan_reserve_online_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeEqualTo(Date value) {
            addCriterion("plan_reserve_online_time =", value, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeNotEqualTo(Date value) {
            addCriterion("plan_reserve_online_time <>", value, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeGreaterThan(Date value) {
            addCriterion("plan_reserve_online_time >", value, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_reserve_online_time >=", value, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeLessThan(Date value) {
            addCriterion("plan_reserve_online_time <", value, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_reserve_online_time <=", value, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeIn(List<Date> values) {
            addCriterion("plan_reserve_online_time in", values, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeNotIn(List<Date> values) {
            addCriterion("plan_reserve_online_time not in", values, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeBetween(Date value1, Date value2) {
            addCriterion("plan_reserve_online_time between", value1, value2, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOnlineTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_reserve_online_time not between", value1, value2, "planReserveOnlineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeIsNull() {
            addCriterion("plan_reserve_offline_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeIsNotNull() {
            addCriterion("plan_reserve_offline_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeEqualTo(Date value) {
            addCriterion("plan_reserve_offline_time =", value, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeNotEqualTo(Date value) {
            addCriterion("plan_reserve_offline_time <>", value, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeGreaterThan(Date value) {
            addCriterion("plan_reserve_offline_time >", value, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_reserve_offline_time >=", value, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeLessThan(Date value) {
            addCriterion("plan_reserve_offline_time <", value, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_reserve_offline_time <=", value, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeIn(List<Date> values) {
            addCriterion("plan_reserve_offline_time in", values, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeNotIn(List<Date> values) {
            addCriterion("plan_reserve_offline_time not in", values, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeBetween(Date value1, Date value2) {
            addCriterion("plan_reserve_offline_time between", value1, value2, "planReserveOfflineTime");
            return (Criteria) this;
        }

        public Criteria andPlanReserveOfflineTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_reserve_offline_time not between", value1, value2, "planReserveOfflineTime");
            return (Criteria) this;
        }
        
        public Criteria andPlanTypeIsNull() {
            addCriterion("plan_type is null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIsNotNull() {
            addCriterion("plan_type is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeEqualTo(Integer value) {
            addCriterion("plan_type =", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotEqualTo(Integer value) {
            addCriterion("plan_type <>", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeGreaterThan(Integer value) {
            addCriterion("plan_type >", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_type >=", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLessThan(Integer value) {
            addCriterion("plan_type <", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLessThanOrEqualTo(Integer value) {
            addCriterion("plan_type <=", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIn(List<Integer> values) {
            addCriterion("plan_type in", values, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotIn(List<Integer> values) {
            addCriterion("plan_type not in", values, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeBetween(Integer value1, Integer value2) {
            addCriterion("plan_type between", value1, value2, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_type not between", value1, value2, "planType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}