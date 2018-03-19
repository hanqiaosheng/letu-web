package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserRedeemPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UserRedeemPlanExample() {
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
        
        //用户号码
        public Criteria andUserTelLike(String value) {
            addCriterion("user_tel like", value, "userTel");
            return (Criteria) this;
        }
        //方案名称
        public Criteria andRedeemPlanNameLike(String value) {
            addCriterion("plan_name like", value, "planName");
            return (Criteria) this;
        }
        //兑换码
        public Criteria andRedeemCodeLike(String value) {
            addCriterion("plan_redeem_code like", value, "planRedeemCode");
            return (Criteria) this;
        }
        

        public Criteria andUserRedeemPlanIdIsNull() {
            addCriterion("user_redeem_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdIsNotNull() {
            addCriterion("user_redeem_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdEqualTo(Long value) {
            addCriterion("user_redeem_plan_id =", value, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdNotEqualTo(Long value) {
            addCriterion("user_redeem_plan_id <>", value, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdGreaterThan(Long value) {
            addCriterion("user_redeem_plan_id >", value, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_redeem_plan_id >=", value, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdLessThan(Long value) {
            addCriterion("user_redeem_plan_id <", value, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdLessThanOrEqualTo(Long value) {
            addCriterion("user_redeem_plan_id <=", value, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdIn(List<Long> values) {
            addCriterion("user_redeem_plan_id in", values, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdNotIn(List<Long> values) {
            addCriterion("user_redeem_plan_id not in", values, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdBetween(Long value1, Long value2) {
            addCriterion("user_redeem_plan_id between", value1, value2, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUserRedeemPlanIdNotBetween(Long value1, Long value2) {
            addCriterion("user_redeem_plan_id not between", value1, value2, "userRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdIsNull() {
            addCriterion("urp_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdIsNotNull() {
            addCriterion("urp_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdEqualTo(Long value) {
            addCriterion("urp_user_id =", value, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdNotEqualTo(Long value) {
            addCriterion("urp_user_id <>", value, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdGreaterThan(Long value) {
            addCriterion("urp_user_id >", value, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("urp_user_id >=", value, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdLessThan(Long value) {
            addCriterion("urp_user_id <", value, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdLessThanOrEqualTo(Long value) {
            addCriterion("urp_user_id <=", value, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdIn(List<Long> values) {
            addCriterion("urp_user_id in", values, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdNotIn(List<Long> values) {
            addCriterion("urp_user_id not in", values, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdBetween(Long value1, Long value2) {
            addCriterion("urp_user_id between", value1, value2, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpUserIdNotBetween(Long value1, Long value2) {
            addCriterion("urp_user_id not between", value1, value2, "urpUserId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdIsNull() {
            addCriterion("urp_redeem_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdIsNotNull() {
            addCriterion("urp_redeem_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdEqualTo(Long value) {
            addCriterion("urp_redeem_plan_id =", value, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdNotEqualTo(Long value) {
            addCriterion("urp_redeem_plan_id <>", value, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdGreaterThan(Long value) {
            addCriterion("urp_redeem_plan_id >", value, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("urp_redeem_plan_id >=", value, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdLessThan(Long value) {
            addCriterion("urp_redeem_plan_id <", value, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdLessThanOrEqualTo(Long value) {
            addCriterion("urp_redeem_plan_id <=", value, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdIn(List<Long> values) {
            addCriterion("urp_redeem_plan_id in", values, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdNotIn(List<Long> values) {
            addCriterion("urp_redeem_plan_id not in", values, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdBetween(Long value1, Long value2) {
            addCriterion("urp_redeem_plan_id between", value1, value2, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpRedeemPlanIdNotBetween(Long value1, Long value2) {
            addCriterion("urp_redeem_plan_id not between", value1, value2, "urpRedeemPlanId");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeIsNull() {
            addCriterion("urp_create_time is null");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeIsNotNull() {
            addCriterion("urp_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeEqualTo(Date value) {
            addCriterion("urp_create_time =", value, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeNotEqualTo(Date value) {
            addCriterion("urp_create_time <>", value, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeGreaterThan(Date value) {
            addCriterion("urp_create_time >", value, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("urp_create_time >=", value, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeLessThan(Date value) {
            addCriterion("urp_create_time <", value, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("urp_create_time <=", value, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeIn(List<Date> values) {
            addCriterion("urp_create_time in", values, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeNotIn(List<Date> values) {
            addCriterion("urp_create_time not in", values, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeBetween(Date value1, Date value2) {
            addCriterion("urp_create_time between", value1, value2, "urpCreateTime");
            return (Criteria) this;
        }

        public Criteria andUrpCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("urp_create_time not between", value1, value2, "urpCreateTime");
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