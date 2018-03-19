package org.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class ExceptionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ExceptionsExample() {
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

        public Criteria andExceptionIdIsNull() {
            addCriterion("exception_id is null");
            return (Criteria) this;
        }

        public Criteria andExceptionIdIsNotNull() {
            addCriterion("exception_id is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionIdEqualTo(Long value) {
            addCriterion("exception_id =", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotEqualTo(Long value) {
            addCriterion("exception_id <>", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdGreaterThan(Long value) {
            addCriterion("exception_id >", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exception_id >=", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdLessThan(Long value) {
            addCriterion("exception_id <", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdLessThanOrEqualTo(Long value) {
            addCriterion("exception_id <=", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdIn(List<Long> values) {
            addCriterion("exception_id in", values, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotIn(List<Long> values) {
            addCriterion("exception_id not in", values, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdBetween(Long value1, Long value2) {
            addCriterion("exception_id between", value1, value2, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotBetween(Long value1, Long value2) {
            addCriterion("exception_id not between", value1, value2, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdIsNull() {
            addCriterion("exception_user_id is null");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdIsNotNull() {
            addCriterion("exception_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdEqualTo(Long value) {
            addCriterion("exception_user_id =", value, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdNotEqualTo(Long value) {
            addCriterion("exception_user_id <>", value, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdGreaterThan(Long value) {
            addCriterion("exception_user_id >", value, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exception_user_id >=", value, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdLessThan(Long value) {
            addCriterion("exception_user_id <", value, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdLessThanOrEqualTo(Long value) {
            addCriterion("exception_user_id <=", value, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdIn(List<Long> values) {
            addCriterion("exception_user_id in", values, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdNotIn(List<Long> values) {
            addCriterion("exception_user_id not in", values, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdBetween(Long value1, Long value2) {
            addCriterion("exception_user_id between", value1, value2, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionUserIdNotBetween(Long value1, Long value2) {
            addCriterion("exception_user_id not between", value1, value2, "exceptionUserId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdIsNull() {
            addCriterion("exception_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdIsNotNull() {
            addCriterion("exception_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdEqualTo(Long value) {
            addCriterion("exception_admin_id =", value, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdNotEqualTo(Long value) {
            addCriterion("exception_admin_id <>", value, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdGreaterThan(Long value) {
            addCriterion("exception_admin_id >", value, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exception_admin_id >=", value, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdLessThan(Long value) {
            addCriterion("exception_admin_id <", value, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("exception_admin_id <=", value, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdIn(List<Long> values) {
            addCriterion("exception_admin_id in", values, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdNotIn(List<Long> values) {
            addCriterion("exception_admin_id not in", values, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdBetween(Long value1, Long value2) {
            addCriterion("exception_admin_id between", value1, value2, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("exception_admin_id not between", value1, value2, "exceptionAdminId");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeIsNull() {
            addCriterion("exception_type is null");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeIsNotNull() {
            addCriterion("exception_type is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeEqualTo(Integer value) {
            addCriterion("exception_type =", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeNotEqualTo(Integer value) {
            addCriterion("exception_type <>", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeGreaterThan(Integer value) {
            addCriterion("exception_type >", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_type >=", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeLessThan(Integer value) {
            addCriterion("exception_type <", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("exception_type <=", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeIn(List<Integer> values) {
            addCriterion("exception_type in", values, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeNotIn(List<Integer> values) {
            addCriterion("exception_type not in", values, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeBetween(Integer value1, Integer value2) {
            addCriterion("exception_type between", value1, value2, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_type not between", value1, value2, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionResultIsNull() {
            addCriterion("exception_result is null");
            return (Criteria) this;
        }

        public Criteria andExceptionResultIsNotNull() {
            addCriterion("exception_result is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionResultEqualTo(Integer value) {
            addCriterion("exception_result =", value, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultNotEqualTo(Integer value) {
            addCriterion("exception_result <>", value, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultGreaterThan(Integer value) {
            addCriterion("exception_result >", value, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_result >=", value, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultLessThan(Integer value) {
            addCriterion("exception_result <", value, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultLessThanOrEqualTo(Integer value) {
            addCriterion("exception_result <=", value, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultIn(List<Integer> values) {
            addCriterion("exception_result in", values, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultNotIn(List<Integer> values) {
            addCriterion("exception_result not in", values, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultBetween(Integer value1, Integer value2) {
            addCriterion("exception_result between", value1, value2, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionResultNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_result not between", value1, value2, "exceptionResult");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkIsNull() {
            addCriterion("exception_remark is null");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkIsNotNull() {
            addCriterion("exception_remark is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkEqualTo(String value) {
            addCriterion("exception_remark =", value, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkNotEqualTo(String value) {
            addCriterion("exception_remark <>", value, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkGreaterThan(String value) {
            addCriterion("exception_remark >", value, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("exception_remark >=", value, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkLessThan(String value) {
            addCriterion("exception_remark <", value, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkLessThanOrEqualTo(String value) {
            addCriterion("exception_remark <=", value, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkLike(String value) {
            addCriterion("exception_remark like", value, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkNotLike(String value) {
            addCriterion("exception_remark not like", value, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkIn(List<String> values) {
            addCriterion("exception_remark in", values, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkNotIn(List<String> values) {
            addCriterion("exception_remark not in", values, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkBetween(String value1, String value2) {
            addCriterion("exception_remark between", value1, value2, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionRemarkNotBetween(String value1, String value2) {
            addCriterion("exception_remark not between", value1, value2, "exceptionRemark");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateIsNull() {
            addCriterion("exception_user_bikestate is null");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateIsNotNull() {
            addCriterion("exception_user_bikestate is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateEqualTo(Integer value) {
            addCriterion("exception_user_bikestate =", value, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateNotEqualTo(Integer value) {
            addCriterion("exception_user_bikestate <>", value, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateGreaterThan(Integer value) {
            addCriterion("exception_user_bikestate >", value, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_user_bikestate >=", value, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateLessThan(Integer value) {
            addCriterion("exception_user_bikestate <", value, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateLessThanOrEqualTo(Integer value) {
            addCriterion("exception_user_bikestate <=", value, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateIn(List<Integer> values) {
            addCriterion("exception_user_bikestate in", values, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateNotIn(List<Integer> values) {
            addCriterion("exception_user_bikestate not in", values, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateBetween(Integer value1, Integer value2) {
            addCriterion("exception_user_bikestate between", value1, value2, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionUserBikestateNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_user_bikestate not between", value1, value2, "exceptionUserBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateIsNull() {
            addCriterion("exception_bikestate is null");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateIsNotNull() {
            addCriterion("exception_bikestate is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateEqualTo(Integer value) {
            addCriterion("exception_bikestate =", value, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateNotEqualTo(Integer value) {
            addCriterion("exception_bikestate <>", value, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateGreaterThan(Integer value) {
            addCriterion("exception_bikestate >", value, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_bikestate >=", value, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateLessThan(Integer value) {
            addCriterion("exception_bikestate <", value, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateLessThanOrEqualTo(Integer value) {
            addCriterion("exception_bikestate <=", value, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateIn(List<Integer> values) {
            addCriterion("exception_bikestate in", values, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateNotIn(List<Integer> values) {
            addCriterion("exception_bikestate not in", values, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateBetween(Integer value1, Integer value2) {
            addCriterion("exception_bikestate between", value1, value2, "exceptionBikestate");
            return (Criteria) this;
        }

        public Criteria andExceptionBikestateNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_bikestate not between", value1, value2, "exceptionBikestate");
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