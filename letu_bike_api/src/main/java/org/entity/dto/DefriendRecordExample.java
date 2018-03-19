package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DefriendRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public DefriendRecordExample() {
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

        public Criteria andDefriendIdIsNull() {
            addCriterion("defriend_id is null");
            return (Criteria) this;
        }

        public Criteria andDefriendIdIsNotNull() {
            addCriterion("defriend_id is not null");
            return (Criteria) this;
        }

        public Criteria andDefriendIdEqualTo(Long value) {
            addCriterion("defriend_id =", value, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdNotEqualTo(Long value) {
            addCriterion("defriend_id <>", value, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdGreaterThan(Long value) {
            addCriterion("defriend_id >", value, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdGreaterThanOrEqualTo(Long value) {
            addCriterion("defriend_id >=", value, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdLessThan(Long value) {
            addCriterion("defriend_id <", value, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdLessThanOrEqualTo(Long value) {
            addCriterion("defriend_id <=", value, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdIn(List<Long> values) {
            addCriterion("defriend_id in", values, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdNotIn(List<Long> values) {
            addCriterion("defriend_id not in", values, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdBetween(Long value1, Long value2) {
            addCriterion("defriend_id between", value1, value2, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendIdNotBetween(Long value1, Long value2) {
            addCriterion("defriend_id not between", value1, value2, "defriendId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdIsNull() {
            addCriterion("defriend_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdIsNotNull() {
            addCriterion("defriend_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdEqualTo(Long value) {
            addCriterion("defriend_admin_id =", value, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdNotEqualTo(Long value) {
            addCriterion("defriend_admin_id <>", value, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdGreaterThan(Long value) {
            addCriterion("defriend_admin_id >", value, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("defriend_admin_id >=", value, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdLessThan(Long value) {
            addCriterion("defriend_admin_id <", value, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("defriend_admin_id <=", value, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdIn(List<Long> values) {
            addCriterion("defriend_admin_id in", values, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdNotIn(List<Long> values) {
            addCriterion("defriend_admin_id not in", values, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdBetween(Long value1, Long value2) {
            addCriterion("defriend_admin_id between", value1, value2, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("defriend_admin_id not between", value1, value2, "defriendAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeIsNull() {
            addCriterion("defriend_time is null");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeIsNotNull() {
            addCriterion("defriend_time is not null");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeEqualTo(Date value) {
            addCriterion("defriend_time =", value, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeNotEqualTo(Date value) {
            addCriterion("defriend_time <>", value, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeGreaterThan(Date value) {
            addCriterion("defriend_time >", value, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("defriend_time >=", value, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeLessThan(Date value) {
            addCriterion("defriend_time <", value, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeLessThanOrEqualTo(Date value) {
            addCriterion("defriend_time <=", value, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeIn(List<Date> values) {
            addCriterion("defriend_time in", values, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeNotIn(List<Date> values) {
            addCriterion("defriend_time not in", values, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeBetween(Date value1, Date value2) {
            addCriterion("defriend_time between", value1, value2, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendTimeNotBetween(Date value1, Date value2) {
            addCriterion("defriend_time not between", value1, value2, "defriendTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdIsNull() {
            addCriterion("defriend_regain_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdIsNotNull() {
            addCriterion("defriend_regain_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdEqualTo(Long value) {
            addCriterion("defriend_regain_admin_id =", value, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdNotEqualTo(Long value) {
            addCriterion("defriend_regain_admin_id <>", value, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdGreaterThan(Long value) {
            addCriterion("defriend_regain_admin_id >", value, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("defriend_regain_admin_id >=", value, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdLessThan(Long value) {
            addCriterion("defriend_regain_admin_id <", value, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("defriend_regain_admin_id <=", value, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdIn(List<Long> values) {
            addCriterion("defriend_regain_admin_id in", values, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdNotIn(List<Long> values) {
            addCriterion("defriend_regain_admin_id not in", values, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdBetween(Long value1, Long value2) {
            addCriterion("defriend_regain_admin_id between", value1, value2, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("defriend_regain_admin_id not between", value1, value2, "defriendRegainAdminId");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeIsNull() {
            addCriterion("defriend_regain_time is null");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeIsNotNull() {
            addCriterion("defriend_regain_time is not null");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeEqualTo(Date value) {
            addCriterion("defriend_regain_time =", value, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeNotEqualTo(Date value) {
            addCriterion("defriend_regain_time <>", value, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeGreaterThan(Date value) {
            addCriterion("defriend_regain_time >", value, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("defriend_regain_time >=", value, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeLessThan(Date value) {
            addCriterion("defriend_regain_time <", value, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeLessThanOrEqualTo(Date value) {
            addCriterion("defriend_regain_time <=", value, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeIn(List<Date> values) {
            addCriterion("defriend_regain_time in", values, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeNotIn(List<Date> values) {
            addCriterion("defriend_regain_time not in", values, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeBetween(Date value1, Date value2) {
            addCriterion("defriend_regain_time between", value1, value2, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendRegainTimeNotBetween(Date value1, Date value2) {
            addCriterion("defriend_regain_time not between", value1, value2, "defriendRegainTime");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdIsNull() {
            addCriterion("defriend_user_id is null");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdIsNotNull() {
            addCriterion("defriend_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdEqualTo(Long value) {
            addCriterion("defriend_user_id =", value, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdNotEqualTo(Long value) {
            addCriterion("defriend_user_id <>", value, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdGreaterThan(Long value) {
            addCriterion("defriend_user_id >", value, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("defriend_user_id >=", value, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdLessThan(Long value) {
            addCriterion("defriend_user_id <", value, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdLessThanOrEqualTo(Long value) {
            addCriterion("defriend_user_id <=", value, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdIn(List<Long> values) {
            addCriterion("defriend_user_id in", values, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdNotIn(List<Long> values) {
            addCriterion("defriend_user_id not in", values, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdBetween(Long value1, Long value2) {
            addCriterion("defriend_user_id between", value1, value2, "defriendUserId");
            return (Criteria) this;
        }

        public Criteria andDefriendUserIdNotBetween(Long value1, Long value2) {
            addCriterion("defriend_user_id not between", value1, value2, "defriendUserId");
            return (Criteria) this;
        }
        
        public Criteria andDefriendReasonIsNull() {
            addCriterion("defriend_reason is null");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonIsNotNull() {
            addCriterion("defriend_reason is not null");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonEqualTo(String value) {
            addCriterion("defriend_reason =", value, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonNotEqualTo(String value) {
            addCriterion("defriend_reason <>", value, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonGreaterThan(String value) {
            addCriterion("defriend_reason >", value, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonGreaterThanOrEqualTo(String value) {
            addCriterion("defriend_reason >=", value, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonLessThan(String value) {
            addCriterion("defriend_reason <", value, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonLessThanOrEqualTo(String value) {
            addCriterion("defriend_reason <=", value, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonLike(String value) {
            addCriterion("defriend_reason like", value, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonNotLike(String value) {
            addCriterion("defriend_reason not like", value, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonIn(List<String> values) {
            addCriterion("defriend_reason in", values, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonNotIn(List<String> values) {
            addCriterion("defriend_reason not in", values, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonBetween(String value1, String value2) {
            addCriterion("defriend_reason between", value1, value2, "defriendReason");
            return (Criteria) this;
        }

        public Criteria andDefriendReasonNotBetween(String value1, String value2) {
            addCriterion("defriend_reason not between", value1, value2, "defriendReason");
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