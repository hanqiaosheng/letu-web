package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UserCouponExample() {
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

        public Criteria andUserToCouponIdIsNull() {
            addCriterion("user_to_coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdIsNotNull() {
            addCriterion("user_to_coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdEqualTo(Long value) {
            addCriterion("user_to_coupon_id =", value, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdNotEqualTo(Long value) {
            addCriterion("user_to_coupon_id <>", value, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdGreaterThan(Long value) {
            addCriterion("user_to_coupon_id >", value, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_to_coupon_id >=", value, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdLessThan(Long value) {
            addCriterion("user_to_coupon_id <", value, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdLessThanOrEqualTo(Long value) {
            addCriterion("user_to_coupon_id <=", value, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdIn(List<Long> values) {
            addCriterion("user_to_coupon_id in", values, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdNotIn(List<Long> values) {
            addCriterion("user_to_coupon_id not in", values, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdBetween(Long value1, Long value2) {
            addCriterion("user_to_coupon_id between", value1, value2, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUserToCouponIdNotBetween(Long value1, Long value2) {
            addCriterion("user_to_coupon_id not between", value1, value2, "userToCouponId");
            return (Criteria) this;
        }

        public Criteria andUuserIdIsNull() {
            addCriterion("uuser_id is null");
            return (Criteria) this;
        }

        public Criteria andUuserIdIsNotNull() {
            addCriterion("uuser_id is not null");
            return (Criteria) this;
        }

        public Criteria andUuserIdEqualTo(Long value) {
            addCriterion("uuser_id =", value, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdNotEqualTo(Long value) {
            addCriterion("uuser_id <>", value, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdGreaterThan(Long value) {
            addCriterion("uuser_id >", value, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("uuser_id >=", value, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdLessThan(Long value) {
            addCriterion("uuser_id <", value, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdLessThanOrEqualTo(Long value) {
            addCriterion("uuser_id <=", value, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdIn(List<Long> values) {
            addCriterion("uuser_id in", values, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdNotIn(List<Long> values) {
            addCriterion("uuser_id not in", values, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdBetween(Long value1, Long value2) {
            addCriterion("uuser_id between", value1, value2, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUuserIdNotBetween(Long value1, Long value2) {
            addCriterion("uuser_id not between", value1, value2, "uuserId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdIsNull() {
            addCriterion("ucoupon_id is null");
            return (Criteria) this;
        }

        public Criteria andUcouponIdIsNotNull() {
            addCriterion("ucoupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andUcouponIdEqualTo(Long value) {
            addCriterion("ucoupon_id =", value, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdNotEqualTo(Long value) {
            addCriterion("ucoupon_id <>", value, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdGreaterThan(Long value) {
            addCriterion("ucoupon_id >", value, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ucoupon_id >=", value, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdLessThan(Long value) {
            addCriterion("ucoupon_id <", value, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdLessThanOrEqualTo(Long value) {
            addCriterion("ucoupon_id <=", value, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdIn(List<Long> values) {
            addCriterion("ucoupon_id in", values, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdNotIn(List<Long> values) {
            addCriterion("ucoupon_id not in", values, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdBetween(Long value1, Long value2) {
            addCriterion("ucoupon_id between", value1, value2, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUcouponIdNotBetween(Long value1, Long value2) {
            addCriterion("ucoupon_id not between", value1, value2, "ucouponId");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeIsNull() {
            addCriterion("u_create_time is null");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeIsNotNull() {
            addCriterion("u_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeEqualTo(Date value) {
            addCriterion("u_create_time =", value, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeNotEqualTo(Date value) {
            addCriterion("u_create_time <>", value, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeGreaterThan(Date value) {
            addCriterion("u_create_time >", value, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("u_create_time >=", value, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeLessThan(Date value) {
            addCriterion("u_create_time <", value, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("u_create_time <=", value, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeIn(List<Date> values) {
            addCriterion("u_create_time in", values, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeNotIn(List<Date> values) {
            addCriterion("u_create_time not in", values, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeBetween(Date value1, Date value2) {
            addCriterion("u_create_time between", value1, value2, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("u_create_time not between", value1, value2, "uCreateTime");
            return (Criteria) this;
        }

        public Criteria andUstateIsNull() {
            addCriterion("ustate is null");
            return (Criteria) this;
        }

        public Criteria andUstateIsNotNull() {
            addCriterion("ustate is not null");
            return (Criteria) this;
        }

        public Criteria andUstateEqualTo(Integer value) {
            addCriterion("ustate =", value, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateNotEqualTo(Integer value) {
            addCriterion("ustate <>", value, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateGreaterThan(Integer value) {
            addCriterion("ustate >", value, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("ustate >=", value, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateLessThan(Integer value) {
            addCriterion("ustate <", value, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateLessThanOrEqualTo(Integer value) {
            addCriterion("ustate <=", value, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateIn(List<Integer> values) {
            addCriterion("ustate in", values, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateNotIn(List<Integer> values) {
            addCriterion("ustate not in", values, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateBetween(Integer value1, Integer value2) {
            addCriterion("ustate between", value1, value2, "ustate");
            return (Criteria) this;
        }

        public Criteria andUstateNotBetween(Integer value1, Integer value2) {
            addCriterion("ustate not between", value1, value2, "ustate");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeIsNull() {
            addCriterion("u_redeem_code_end_time is null");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeIsNotNull() {
            addCriterion("u_redeem_code_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeEqualTo(Date value) {
            addCriterion("u_redeem_code_end_time =", value, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeNotEqualTo(Date value) {
            addCriterion("u_redeem_code_end_time <>", value, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeGreaterThan(Date value) {
            addCriterion("u_redeem_code_end_time >", value, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("u_redeem_code_end_time >=", value, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeLessThan(Date value) {
            addCriterion("u_redeem_code_end_time <", value, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("u_redeem_code_end_time <=", value, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeIn(List<Date> values) {
            addCriterion("u_redeem_code_end_time in", values, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeNotIn(List<Date> values) {
            addCriterion("u_redeem_code_end_time not in", values, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeBetween(Date value1, Date value2) {
            addCriterion("u_redeem_code_end_time between", value1, value2, "uRedeemCodeEndTime");
            return (Criteria) this;
        }

        public Criteria andURedeemCodeEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("u_redeem_code_end_time not between", value1, value2, "uRedeemCodeEndTime");
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