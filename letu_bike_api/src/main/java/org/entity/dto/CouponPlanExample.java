package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public CouponPlanExample() {
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

        public Criteria andCouponPlanIdIsNull() {
            addCriterion("coupon_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdIsNotNull() {
            addCriterion("coupon_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdEqualTo(Long value) {
            addCriterion("coupon_plan_id =", value, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdNotEqualTo(Long value) {
            addCriterion("coupon_plan_id <>", value, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdGreaterThan(Long value) {
            addCriterion("coupon_plan_id >", value, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("coupon_plan_id >=", value, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdLessThan(Long value) {
            addCriterion("coupon_plan_id <", value, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdLessThanOrEqualTo(Long value) {
            addCriterion("coupon_plan_id <=", value, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdIn(List<Long> values) {
            addCriterion("coupon_plan_id in", values, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdNotIn(List<Long> values) {
            addCriterion("coupon_plan_id not in", values, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdBetween(Long value1, Long value2) {
            addCriterion("coupon_plan_id between", value1, value2, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanIdNotBetween(Long value1, Long value2) {
            addCriterion("coupon_plan_id not between", value1, value2, "couponPlanId");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameIsNull() {
            addCriterion("coupon_plan_name is null");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameIsNotNull() {
            addCriterion("coupon_plan_name is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameEqualTo(String value) {
            addCriterion("coupon_plan_name =", value, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameNotEqualTo(String value) {
            addCriterion("coupon_plan_name <>", value, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameGreaterThan(String value) {
            addCriterion("coupon_plan_name >", value, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_plan_name >=", value, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameLessThan(String value) {
            addCriterion("coupon_plan_name <", value, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameLessThanOrEqualTo(String value) {
            addCriterion("coupon_plan_name <=", value, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameLike(String value) {
            addCriterion("coupon_plan_name like", value, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameNotLike(String value) {
            addCriterion("coupon_plan_name not like", value, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameIn(List<String> values) {
            addCriterion("coupon_plan_name in", values, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameNotIn(List<String> values) {
            addCriterion("coupon_plan_name not in", values, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameBetween(String value1, String value2) {
            addCriterion("coupon_plan_name between", value1, value2, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanNameNotBetween(String value1, String value2) {
            addCriterion("coupon_plan_name not between", value1, value2, "couponPlanName");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeIsNull() {
            addCriterion("coupon_plan_create_time is null");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeIsNotNull() {
            addCriterion("coupon_plan_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeEqualTo(Date value) {
            addCriterion("coupon_plan_create_time =", value, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeNotEqualTo(Date value) {
            addCriterion("coupon_plan_create_time <>", value, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeGreaterThan(Date value) {
            addCriterion("coupon_plan_create_time >", value, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("coupon_plan_create_time >=", value, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeLessThan(Date value) {
            addCriterion("coupon_plan_create_time <", value, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("coupon_plan_create_time <=", value, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeIn(List<Date> values) {
            addCriterion("coupon_plan_create_time in", values, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeNotIn(List<Date> values) {
            addCriterion("coupon_plan_create_time not in", values, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeBetween(Date value1, Date value2) {
            addCriterion("coupon_plan_create_time between", value1, value2, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("coupon_plan_create_time not between", value1, value2, "couponPlanCreateTime");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateIsNull() {
            addCriterion("coupon_plan_state is null");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateIsNotNull() {
            addCriterion("coupon_plan_state is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateEqualTo(Integer value) {
            addCriterion("coupon_plan_state =", value, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateNotEqualTo(Integer value) {
            addCriterion("coupon_plan_state <>", value, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateGreaterThan(Integer value) {
            addCriterion("coupon_plan_state >", value, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_plan_state >=", value, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateLessThan(Integer value) {
            addCriterion("coupon_plan_state <", value, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_plan_state <=", value, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateIn(List<Integer> values) {
            addCriterion("coupon_plan_state in", values, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateNotIn(List<Integer> values) {
            addCriterion("coupon_plan_state not in", values, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateBetween(Integer value1, Integer value2) {
            addCriterion("coupon_plan_state between", value1, value2, "couponPlanState");
            return (Criteria) this;
        }

        public Criteria andCouponPlanStateNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_plan_state not between", value1, value2, "couponPlanState");
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