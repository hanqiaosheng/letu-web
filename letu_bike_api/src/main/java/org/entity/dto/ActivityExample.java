package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ActivityExample() {
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
        
        //城市
        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }
        
        //城市编号
        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Long value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Long value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Long value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Long value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Long> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Long> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Long value1, Long value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionIsNull() {
            addCriterion("activity_instruction is null");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionIsNotNull() {
            addCriterion("activity_instruction is not null");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionEqualTo(String value) {
            addCriterion("activity_instruction =", value, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionNotEqualTo(String value) {
            addCriterion("activity_instruction <>", value, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionGreaterThan(String value) {
            addCriterion("activity_instruction >", value, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionGreaterThanOrEqualTo(String value) {
            addCriterion("activity_instruction >=", value, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionLessThan(String value) {
            addCriterion("activity_instruction <", value, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionLessThanOrEqualTo(String value) {
            addCriterion("activity_instruction <=", value, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionLike(String value) {
            addCriterion("activity_instruction like", value, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionNotLike(String value) {
            addCriterion("activity_instruction not like", value, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionIn(List<String> values) {
            addCriterion("activity_instruction in", values, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionNotIn(List<String> values) {
            addCriterion("activity_instruction not in", values, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionBetween(String value1, String value2) {
            addCriterion("activity_instruction between", value1, value2, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityInstructionNotBetween(String value1, String value2) {
            addCriterion("activity_instruction not between", value1, value2, "activityInstruction");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumIsNull() {
            addCriterion("activity_topnum is null");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumIsNotNull() {
            addCriterion("activity_topnum is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumEqualTo(Integer value) {
            addCriterion("activity_topnum =", value, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumNotEqualTo(Integer value) {
            addCriterion("activity_topnum <>", value, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumGreaterThan(Integer value) {
            addCriterion("activity_topnum >", value, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_topnum >=", value, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumLessThan(Integer value) {
            addCriterion("activity_topnum <", value, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumLessThanOrEqualTo(Integer value) {
            addCriterion("activity_topnum <=", value, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumIn(List<Integer> values) {
            addCriterion("activity_topnum in", values, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumNotIn(List<Integer> values) {
            addCriterion("activity_topnum not in", values, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumBetween(Integer value1, Integer value2) {
            addCriterion("activity_topnum between", value1, value2, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityTopnumNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_topnum not between", value1, value2, "activityTopnum");
            return (Criteria) this;
        }

        public Criteria andActivityUrlIsNull() {
            addCriterion("activity_url is null");
            return (Criteria) this;
        }

        public Criteria andActivityUrlIsNotNull() {
            addCriterion("activity_url is not null");
            return (Criteria) this;
        }

        public Criteria andActivityUrlEqualTo(String value) {
            addCriterion("activity_url =", value, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlNotEqualTo(String value) {
            addCriterion("activity_url <>", value, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlGreaterThan(String value) {
            addCriterion("activity_url >", value, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlGreaterThanOrEqualTo(String value) {
            addCriterion("activity_url >=", value, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlLessThan(String value) {
            addCriterion("activity_url <", value, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlLessThanOrEqualTo(String value) {
            addCriterion("activity_url <=", value, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlLike(String value) {
            addCriterion("activity_url like", value, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlNotLike(String value) {
            addCriterion("activity_url not like", value, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlIn(List<String> values) {
            addCriterion("activity_url in", values, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlNotIn(List<String> values) {
            addCriterion("activity_url not in", values, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlBetween(String value1, String value2) {
            addCriterion("activity_url between", value1, value2, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityUrlNotBetween(String value1, String value2) {
            addCriterion("activity_url not between", value1, value2, "activityUrl");
            return (Criteria) this;
        }

        public Criteria andActivityImageIsNull() {
            addCriterion("activity_image is null");
            return (Criteria) this;
        }

        public Criteria andActivityImageIsNotNull() {
            addCriterion("activity_image is not null");
            return (Criteria) this;
        }

        public Criteria andActivityImageEqualTo(String value) {
            addCriterion("activity_image =", value, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageNotEqualTo(String value) {
            addCriterion("activity_image <>", value, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageGreaterThan(String value) {
            addCriterion("activity_image >", value, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageGreaterThanOrEqualTo(String value) {
            addCriterion("activity_image >=", value, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageLessThan(String value) {
            addCriterion("activity_image <", value, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageLessThanOrEqualTo(String value) {
            addCriterion("activity_image <=", value, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageLike(String value) {
            addCriterion("activity_image like", value, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageNotLike(String value) {
            addCriterion("activity_image not like", value, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageIn(List<String> values) {
            addCriterion("activity_image in", values, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageNotIn(List<String> values) {
            addCriterion("activity_image not in", values, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageBetween(String value1, String value2) {
            addCriterion("activity_image between", value1, value2, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityImageNotBetween(String value1, String value2) {
            addCriterion("activity_image not between", value1, value2, "activityImage");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeIsNull() {
            addCriterion("activity_create_time is null");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeIsNotNull() {
            addCriterion("activity_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeEqualTo(Date value) {
            addCriterion("activity_create_time =", value, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeNotEqualTo(Date value) {
            addCriterion("activity_create_time <>", value, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeGreaterThan(Date value) {
            addCriterion("activity_create_time >", value, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_create_time >=", value, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeLessThan(Date value) {
            addCriterion("activity_create_time <", value, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("activity_create_time <=", value, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeIn(List<Date> values) {
            addCriterion("activity_create_time in", values, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeNotIn(List<Date> values) {
            addCriterion("activity_create_time not in", values, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeBetween(Date value1, Date value2) {
            addCriterion("activity_create_time between", value1, value2, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("activity_create_time not between", value1, value2, "activityCreateTime");
            return (Criteria) this;
        }

        public Criteria andActivityStateIsNull() {
            addCriterion("activity_state is null");
            return (Criteria) this;
        }

        public Criteria andActivityStateIsNotNull() {
            addCriterion("activity_state is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStateEqualTo(Integer value) {
            addCriterion("activity_state =", value, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateNotEqualTo(Integer value) {
            addCriterion("activity_state <>", value, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateGreaterThan(Integer value) {
            addCriterion("activity_state >", value, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_state >=", value, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateLessThan(Integer value) {
            addCriterion("activity_state <", value, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateLessThanOrEqualTo(Integer value) {
            addCriterion("activity_state <=", value, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateIn(List<Integer> values) {
            addCriterion("activity_state in", values, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateNotIn(List<Integer> values) {
            addCriterion("activity_state not in", values, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateBetween(Integer value1, Integer value2) {
            addCriterion("activity_state between", value1, value2, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityStateNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_state not between", value1, value2, "activityState");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdIsNull() {
            addCriterion("activity_city_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdIsNotNull() {
            addCriterion("activity_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdEqualTo(Long value) {
            addCriterion("activity_city_id =", value, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdNotEqualTo(Long value) {
            addCriterion("activity_city_id <>", value, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdGreaterThan(Long value) {
            addCriterion("activity_city_id >", value, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_city_id >=", value, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdLessThan(Long value) {
            addCriterion("activity_city_id <", value, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_city_id <=", value, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdIn(List<Long> values) {
            addCriterion("activity_city_id in", values, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdNotIn(List<Long> values) {
            addCriterion("activity_city_id not in", values, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdBetween(Long value1, Long value2) {
            addCriterion("activity_city_id between", value1, value2, "activityCityId");
            return (Criteria) this;
        }

        public Criteria andActivityCityIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_city_id not between", value1, value2, "activityCityId");
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