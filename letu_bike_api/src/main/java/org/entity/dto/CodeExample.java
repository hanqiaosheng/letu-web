package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public CodeExample() {
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

        public Criteria andCodeIdIsNull() {
            addCriterion("code_id is null");
            return (Criteria) this;
        }

        public Criteria andCodeIdIsNotNull() {
            addCriterion("code_id is not null");
            return (Criteria) this;
        }

        public Criteria andCodeIdEqualTo(Long value) {
            addCriterion("code_id =", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotEqualTo(Long value) {
            addCriterion("code_id <>", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdGreaterThan(Long value) {
            addCriterion("code_id >", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("code_id >=", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLessThan(Long value) {
            addCriterion("code_id <", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLessThanOrEqualTo(Long value) {
            addCriterion("code_id <=", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdIn(List<Long> values) {
            addCriterion("code_id in", values, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotIn(List<Long> values) {
            addCriterion("code_id not in", values, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdBetween(Long value1, Long value2) {
            addCriterion("code_id between", value1, value2, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotBetween(Long value1, Long value2) {
            addCriterion("code_id not between", value1, value2, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodePhoneIsNull() {
            addCriterion("code_phone is null");
            return (Criteria) this;
        }

        public Criteria andCodePhoneIsNotNull() {
            addCriterion("code_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCodePhoneEqualTo(String value) {
            addCriterion("code_phone =", value, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneNotEqualTo(String value) {
            addCriterion("code_phone <>", value, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneGreaterThan(String value) {
            addCriterion("code_phone >", value, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("code_phone >=", value, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneLessThan(String value) {
            addCriterion("code_phone <", value, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneLessThanOrEqualTo(String value) {
            addCriterion("code_phone <=", value, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneLike(String value) {
            addCriterion("code_phone like", value, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneNotLike(String value) {
            addCriterion("code_phone not like", value, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneIn(List<String> values) {
            addCriterion("code_phone in", values, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneNotIn(List<String> values) {
            addCriterion("code_phone not in", values, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneBetween(String value1, String value2) {
            addCriterion("code_phone between", value1, value2, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodePhoneNotBetween(String value1, String value2) {
            addCriterion("code_phone not between", value1, value2, "codePhone");
            return (Criteria) this;
        }

        public Criteria andCodeNumIsNull() {
            addCriterion("code_num is null");
            return (Criteria) this;
        }

        public Criteria andCodeNumIsNotNull() {
            addCriterion("code_num is not null");
            return (Criteria) this;
        }

        public Criteria andCodeNumEqualTo(String value) {
            addCriterion("code_num =", value, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumNotEqualTo(String value) {
            addCriterion("code_num <>", value, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumGreaterThan(String value) {
            addCriterion("code_num >", value, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumGreaterThanOrEqualTo(String value) {
            addCriterion("code_num >=", value, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumLessThan(String value) {
            addCriterion("code_num <", value, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumLessThanOrEqualTo(String value) {
            addCriterion("code_num <=", value, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumLike(String value) {
            addCriterion("code_num like", value, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumNotLike(String value) {
            addCriterion("code_num not like", value, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumIn(List<String> values) {
            addCriterion("code_num in", values, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumNotIn(List<String> values) {
            addCriterion("code_num not in", values, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumBetween(String value1, String value2) {
            addCriterion("code_num between", value1, value2, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeNumNotBetween(String value1, String value2) {
            addCriterion("code_num not between", value1, value2, "codeNum");
            return (Criteria) this;
        }

        public Criteria andCodeStateIsNull() {
            addCriterion("code_state is null");
            return (Criteria) this;
        }

        public Criteria andCodeStateIsNotNull() {
            addCriterion("code_state is not null");
            return (Criteria) this;
        }

        public Criteria andCodeStateEqualTo(Integer value) {
            addCriterion("code_state =", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateNotEqualTo(Integer value) {
            addCriterion("code_state <>", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateGreaterThan(Integer value) {
            addCriterion("code_state >", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("code_state >=", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateLessThan(Integer value) {
            addCriterion("code_state <", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateLessThanOrEqualTo(Integer value) {
            addCriterion("code_state <=", value, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateIn(List<Integer> values) {
            addCriterion("code_state in", values, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateNotIn(List<Integer> values) {
            addCriterion("code_state not in", values, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateBetween(Integer value1, Integer value2) {
            addCriterion("code_state between", value1, value2, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeStateNotBetween(Integer value1, Integer value2) {
            addCriterion("code_state not between", value1, value2, "codeState");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNull() {
            addCriterion("code_type is null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNotNull() {
            addCriterion("code_type is not null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeEqualTo(Integer value) {
            addCriterion("code_type =", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotEqualTo(Integer value) {
            addCriterion("code_type <>", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThan(Integer value) {
            addCriterion("code_type >", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("code_type >=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThan(Integer value) {
            addCriterion("code_type <", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("code_type <=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIn(List<Integer> values) {
            addCriterion("code_type in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotIn(List<Integer> values) {
            addCriterion("code_type not in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeBetween(Integer value1, Integer value2) {
            addCriterion("code_type between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("code_type not between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeIsNull() {
            addCriterion("code_createtime is null");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeIsNotNull() {
            addCriterion("code_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeEqualTo(Date value) {
            addCriterion("code_createtime =", value, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeNotEqualTo(Date value) {
            addCriterion("code_createtime <>", value, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeGreaterThan(Date value) {
            addCriterion("code_createtime >", value, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("code_createtime >=", value, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeLessThan(Date value) {
            addCriterion("code_createtime <", value, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("code_createtime <=", value, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeIn(List<Date> values) {
            addCriterion("code_createtime in", values, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeNotIn(List<Date> values) {
            addCriterion("code_createtime not in", values, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeBetween(Date value1, Date value2) {
            addCriterion("code_createtime between", value1, value2, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("code_createtime not between", value1, value2, "codeCreatetime");
            return (Criteria) this;
        }

        public Criteria andCodeIpIsNull() {
            addCriterion("code_ip is null");
            return (Criteria) this;
        }

        public Criteria andCodeIpIsNotNull() {
            addCriterion("code_ip is not null");
            return (Criteria) this;
        }

        public Criteria andCodeIpEqualTo(String value) {
            addCriterion("code_ip =", value, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpNotEqualTo(String value) {
            addCriterion("code_ip <>", value, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpGreaterThan(String value) {
            addCriterion("code_ip >", value, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpGreaterThanOrEqualTo(String value) {
            addCriterion("code_ip >=", value, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpLessThan(String value) {
            addCriterion("code_ip <", value, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpLessThanOrEqualTo(String value) {
            addCriterion("code_ip <=", value, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpLike(String value) {
            addCriterion("code_ip like", value, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpNotLike(String value) {
            addCriterion("code_ip not like", value, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpIn(List<String> values) {
            addCriterion("code_ip in", values, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpNotIn(List<String> values) {
            addCriterion("code_ip not in", values, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpBetween(String value1, String value2) {
            addCriterion("code_ip between", value1, value2, "codeIp");
            return (Criteria) this;
        }

        public Criteria andCodeIpNotBetween(String value1, String value2) {
            addCriterion("code_ip not between", value1, value2, "codeIp");
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