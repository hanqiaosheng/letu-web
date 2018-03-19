package org.entity.dto;

import java.util.ArrayList;
import java.util.List;


public class ChannelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ChannelExample() {
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

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(Long value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(Long value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(Long value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(Long value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<Long> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<Long> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(Long value1, Long value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNull() {
            addCriterion("channel_name is null");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNotNull() {
            addCriterion("channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNameEqualTo(String value) {
            addCriterion("channel_name =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotEqualTo(String value) {
            addCriterion("channel_name <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThan(String value) {
            addCriterion("channel_name >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_name >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThan(String value) {
            addCriterion("channel_name <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThanOrEqualTo(String value) {
            addCriterion("channel_name <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLike(String value) {
            addCriterion("channel_name like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotLike(String value) {
            addCriterion("channel_name not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIn(List<String> values) {
            addCriterion("channel_name in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotIn(List<String> values) {
            addCriterion("channel_name not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameBetween(String value1, String value2) {
            addCriterion("channel_name between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotBetween(String value1, String value2) {
            addCriterion("channel_name not between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameIsNull() {
            addCriterion("channel_charger_name is null");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameIsNotNull() {
            addCriterion("channel_charger_name is not null");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameEqualTo(String value) {
            addCriterion("channel_charger_name =", value, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameNotEqualTo(String value) {
            addCriterion("channel_charger_name <>", value, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameGreaterThan(String value) {
            addCriterion("channel_charger_name >", value, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_charger_name >=", value, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameLessThan(String value) {
            addCriterion("channel_charger_name <", value, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameLessThanOrEqualTo(String value) {
            addCriterion("channel_charger_name <=", value, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameLike(String value) {
            addCriterion("channel_charger_name like", value, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameNotLike(String value) {
            addCriterion("channel_charger_name not like", value, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameIn(List<String> values) {
            addCriterion("channel_charger_name in", values, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameNotIn(List<String> values) {
            addCriterion("channel_charger_name not in", values, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameBetween(String value1, String value2) {
            addCriterion("channel_charger_name between", value1, value2, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerNameNotBetween(String value1, String value2) {
            addCriterion("channel_charger_name not between", value1, value2, "channelChargerName");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelIsNull() {
            addCriterion("channel_charger_tel is null");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelIsNotNull() {
            addCriterion("channel_charger_tel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelEqualTo(String value) {
            addCriterion("channel_charger_tel =", value, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelNotEqualTo(String value) {
            addCriterion("channel_charger_tel <>", value, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelGreaterThan(String value) {
            addCriterion("channel_charger_tel >", value, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelGreaterThanOrEqualTo(String value) {
            addCriterion("channel_charger_tel >=", value, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelLessThan(String value) {
            addCriterion("channel_charger_tel <", value, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelLessThanOrEqualTo(String value) {
            addCriterion("channel_charger_tel <=", value, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelLike(String value) {
            addCriterion("channel_charger_tel like", value, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelNotLike(String value) {
            addCriterion("channel_charger_tel not like", value, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelIn(List<String> values) {
            addCriterion("channel_charger_tel in", values, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelNotIn(List<String> values) {
            addCriterion("channel_charger_tel not in", values, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelBetween(String value1, String value2) {
            addCriterion("channel_charger_tel between", value1, value2, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerTelNotBetween(String value1, String value2) {
            addCriterion("channel_charger_tel not between", value1, value2, "channelChargerTel");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidIsNull() {
            addCriterion("channel_charger_adminId is null");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidIsNotNull() {
            addCriterion("channel_charger_adminId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidEqualTo(Long value) {
            addCriterion("channel_charger_adminId =", value, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidNotEqualTo(Long value) {
            addCriterion("channel_charger_adminId <>", value, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidGreaterThan(Long value) {
            addCriterion("channel_charger_adminId >", value, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidGreaterThanOrEqualTo(Long value) {
            addCriterion("channel_charger_adminId >=", value, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidLessThan(Long value) {
            addCriterion("channel_charger_adminId <", value, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidLessThanOrEqualTo(Long value) {
            addCriterion("channel_charger_adminId <=", value, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidIn(List<Long> values) {
            addCriterion("channel_charger_adminId in", values, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidNotIn(List<Long> values) {
            addCriterion("channel_charger_adminId not in", values, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidBetween(Long value1, Long value2) {
            addCriterion("channel_charger_adminId between", value1, value2, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelChargerAdminidNotBetween(Long value1, Long value2) {
            addCriterion("channel_charger_adminId not between", value1, value2, "channelChargerAdminid");
            return (Criteria) this;
        }

        public Criteria andChannelStateIsNull() {
            addCriterion("channel_state is null");
            return (Criteria) this;
        }

        public Criteria andChannelStateIsNotNull() {
            addCriterion("channel_state is not null");
            return (Criteria) this;
        }

        public Criteria andChannelStateEqualTo(Integer value) {
            addCriterion("channel_state =", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotEqualTo(Integer value) {
            addCriterion("channel_state <>", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateGreaterThan(Integer value) {
            addCriterion("channel_state >", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_state >=", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateLessThan(Integer value) {
            addCriterion("channel_state <", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateLessThanOrEqualTo(Integer value) {
            addCriterion("channel_state <=", value, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateIn(List<Integer> values) {
            addCriterion("channel_state in", values, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotIn(List<Integer> values) {
            addCriterion("channel_state not in", values, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateBetween(Integer value1, Integer value2) {
            addCriterion("channel_state between", value1, value2, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelStateNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_state not between", value1, value2, "channelState");
            return (Criteria) this;
        }

        public Criteria andChannelLevelIsNull() {
            addCriterion("channel_level is null");
            return (Criteria) this;
        }

        public Criteria andChannelLevelIsNotNull() {
            addCriterion("channel_level is not null");
            return (Criteria) this;
        }

        public Criteria andChannelLevelEqualTo(Integer value) {
            addCriterion("channel_level =", value, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelNotEqualTo(Integer value) {
            addCriterion("channel_level <>", value, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelGreaterThan(Integer value) {
            addCriterion("channel_level >", value, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_level >=", value, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelLessThan(Integer value) {
            addCriterion("channel_level <", value, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelLessThanOrEqualTo(Integer value) {
            addCriterion("channel_level <=", value, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelIn(List<Integer> values) {
            addCriterion("channel_level in", values, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelNotIn(List<Integer> values) {
            addCriterion("channel_level not in", values, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelBetween(Integer value1, Integer value2) {
            addCriterion("channel_level between", value1, value2, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_level not between", value1, value2, "channelLevel");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdIsNull() {
            addCriterion("channel_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdIsNotNull() {
            addCriterion("channel_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdEqualTo(Long value) {
            addCriterion("channel_parent_id =", value, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdNotEqualTo(Long value) {
            addCriterion("channel_parent_id <>", value, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdGreaterThan(Long value) {
            addCriterion("channel_parent_id >", value, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("channel_parent_id >=", value, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdLessThan(Long value) {
            addCriterion("channel_parent_id <", value, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdLessThanOrEqualTo(Long value) {
            addCriterion("channel_parent_id <=", value, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdIn(List<Long> values) {
            addCriterion("channel_parent_id in", values, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdNotIn(List<Long> values) {
            addCriterion("channel_parent_id not in", values, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdBetween(Long value1, Long value2) {
            addCriterion("channel_parent_id between", value1, value2, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelParentIdNotBetween(Long value1, Long value2) {
            addCriterion("channel_parent_id not between", value1, value2, "channelParentId");
            return (Criteria) this;
        }

        public Criteria andChannelNumIsNull() {
            addCriterion("channel_num is null");
            return (Criteria) this;
        }

        public Criteria andChannelNumIsNotNull() {
            addCriterion("channel_num is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNumEqualTo(String value) {
            addCriterion("channel_num =", value, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumNotEqualTo(String value) {
            addCriterion("channel_num <>", value, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumGreaterThan(String value) {
            addCriterion("channel_num >", value, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumGreaterThanOrEqualTo(String value) {
            addCriterion("channel_num >=", value, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumLessThan(String value) {
            addCriterion("channel_num <", value, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumLessThanOrEqualTo(String value) {
            addCriterion("channel_num <=", value, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumLike(String value) {
            addCriterion("channel_num like", value, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumNotLike(String value) {
            addCriterion("channel_num not like", value, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumIn(List<String> values) {
            addCriterion("channel_num in", values, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumNotIn(List<String> values) {
            addCriterion("channel_num not in", values, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumBetween(String value1, String value2) {
            addCriterion("channel_num between", value1, value2, "channelNum");
            return (Criteria) this;
        }

        public Criteria andChannelNumNotBetween(String value1, String value2) {
            addCriterion("channel_num not between", value1, value2, "channelNum");
            return (Criteria) this;
        }
        
        public Criteria andChannelCityIdIsNull() {
            addCriterion("channel_city_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdIsNotNull() {
            addCriterion("channel_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdEqualTo(Long value) {
            addCriterion("channel_city_id =", value, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdNotEqualTo(Long value) {
            addCriterion("channel_city_id <>", value, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdGreaterThan(Long value) {
            addCriterion("channel_city_id >", value, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("channel_city_id >=", value, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdLessThan(Long value) {
            addCriterion("channel_city_id <", value, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdLessThanOrEqualTo(Long value) {
            addCriterion("channel_city_id <=", value, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdIn(List<Long> values) {
            addCriterion("channel_city_id in", values, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdNotIn(List<Long> values) {
            addCriterion("channel_city_id not in", values, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdBetween(Long value1, Long value2) {
            addCriterion("channel_city_id between", value1, value2, "channelCityId");
            return (Criteria) this;
        }

        public Criteria andChannelCityIdNotBetween(Long value1, Long value2) {
            addCriterion("channel_city_id not between", value1, value2, "channelCityId");
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