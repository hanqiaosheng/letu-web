package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsurancePriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public InsurancePriceExample() {
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

        public Criteria andInPriceIdIsNull() {
            addCriterion("in_price_id is null");
            return (Criteria) this;
        }

        public Criteria andInPriceIdIsNotNull() {
            addCriterion("in_price_id is not null");
            return (Criteria) this;
        }

        public Criteria andInPriceIdEqualTo(Long value) {
            addCriterion("in_price_id =", value, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdNotEqualTo(Long value) {
            addCriterion("in_price_id <>", value, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdGreaterThan(Long value) {
            addCriterion("in_price_id >", value, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("in_price_id >=", value, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdLessThan(Long value) {
            addCriterion("in_price_id <", value, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdLessThanOrEqualTo(Long value) {
            addCriterion("in_price_id <=", value, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdIn(List<Long> values) {
            addCriterion("in_price_id in", values, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdNotIn(List<Long> values) {
            addCriterion("in_price_id not in", values, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdBetween(Long value1, Long value2) {
            addCriterion("in_price_id between", value1, value2, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceIdNotBetween(Long value1, Long value2) {
            addCriterion("in_price_id not between", value1, value2, "inPriceId");
            return (Criteria) this;
        }

        public Criteria andInPriceNameIsNull() {
            addCriterion("in_price_name is null");
            return (Criteria) this;
        }

        public Criteria andInPriceNameIsNotNull() {
            addCriterion("in_price_name is not null");
            return (Criteria) this;
        }

        public Criteria andInPriceNameEqualTo(String value) {
            addCriterion("in_price_name =", value, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameNotEqualTo(String value) {
            addCriterion("in_price_name <>", value, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameGreaterThan(String value) {
            addCriterion("in_price_name >", value, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameGreaterThanOrEqualTo(String value) {
            addCriterion("in_price_name >=", value, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameLessThan(String value) {
            addCriterion("in_price_name <", value, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameLessThanOrEqualTo(String value) {
            addCriterion("in_price_name <=", value, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameLike(String value) {
            addCriterion("in_price_name like", value, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameNotLike(String value) {
            addCriterion("in_price_name not like", value, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameIn(List<String> values) {
            addCriterion("in_price_name in", values, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameNotIn(List<String> values) {
            addCriterion("in_price_name not in", values, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameBetween(String value1, String value2) {
            addCriterion("in_price_name between", value1, value2, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInPriceNameNotBetween(String value1, String value2) {
            addCriterion("in_price_name not between", value1, value2, "inPriceName");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeIsNull() {
            addCriterion("in_update_time is null");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeIsNotNull() {
            addCriterion("in_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeEqualTo(Date value) {
            addCriterion("in_update_time =", value, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeNotEqualTo(Date value) {
            addCriterion("in_update_time <>", value, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeGreaterThan(Date value) {
            addCriterion("in_update_time >", value, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("in_update_time >=", value, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeLessThan(Date value) {
            addCriterion("in_update_time <", value, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("in_update_time <=", value, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeIn(List<Date> values) {
            addCriterion("in_update_time in", values, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeNotIn(List<Date> values) {
            addCriterion("in_update_time not in", values, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("in_update_time between", value1, value2, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("in_update_time not between", value1, value2, "inUpdateTime");
            return (Criteria) this;
        }

        public Criteria andInPriceIsNull() {
            addCriterion("in_price is null");
            return (Criteria) this;
        }

        public Criteria andInPriceIsNotNull() {
            addCriterion("in_price is not null");
            return (Criteria) this;
        }

        public Criteria andInPriceEqualTo(BigDecimal value) {
            addCriterion("in_price =", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceNotEqualTo(BigDecimal value) {
            addCriterion("in_price <>", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceGreaterThan(BigDecimal value) {
            addCriterion("in_price >", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("in_price >=", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceLessThan(BigDecimal value) {
            addCriterion("in_price <", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("in_price <=", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceIn(List<BigDecimal> values) {
            addCriterion("in_price in", values, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceNotIn(List<BigDecimal> values) {
            addCriterion("in_price not in", values, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("in_price between", value1, value2, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("in_price not between", value1, value2, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceStateIsNull() {
            addCriterion("in_price_state is null");
            return (Criteria) this;
        }

        public Criteria andInPriceStateIsNotNull() {
            addCriterion("in_price_state is not null");
            return (Criteria) this;
        }

        public Criteria andInPriceStateEqualTo(Integer value) {
            addCriterion("in_price_state =", value, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateNotEqualTo(Integer value) {
            addCriterion("in_price_state <>", value, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateGreaterThan(Integer value) {
            addCriterion("in_price_state >", value, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("in_price_state >=", value, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateLessThan(Integer value) {
            addCriterion("in_price_state <", value, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateLessThanOrEqualTo(Integer value) {
            addCriterion("in_price_state <=", value, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateIn(List<Integer> values) {
            addCriterion("in_price_state in", values, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateNotIn(List<Integer> values) {
            addCriterion("in_price_state not in", values, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateBetween(Integer value1, Integer value2) {
            addCriterion("in_price_state between", value1, value2, "inPriceState");
            return (Criteria) this;
        }

        public Criteria andInPriceStateNotBetween(Integer value1, Integer value2) {
            addCriterion("in_price_state not between", value1, value2, "inPriceState");
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