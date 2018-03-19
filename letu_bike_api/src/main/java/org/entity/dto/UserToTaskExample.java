package org.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class UserToTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UserToTaskExample() {
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

        public Criteria andUserToTaskIdIsNull() {
            addCriterion("user_to_task_id is null");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdIsNotNull() {
            addCriterion("user_to_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdEqualTo(Long value) {
            addCriterion("user_to_task_id =", value, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdNotEqualTo(Long value) {
            addCriterion("user_to_task_id <>", value, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdGreaterThan(Long value) {
            addCriterion("user_to_task_id >", value, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_to_task_id >=", value, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdLessThan(Long value) {
            addCriterion("user_to_task_id <", value, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdLessThanOrEqualTo(Long value) {
            addCriterion("user_to_task_id <=", value, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdIn(List<Long> values) {
            addCriterion("user_to_task_id in", values, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdNotIn(List<Long> values) {
            addCriterion("user_to_task_id not in", values, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdBetween(Long value1, Long value2) {
            addCriterion("user_to_task_id between", value1, value2, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskIdNotBetween(Long value1, Long value2) {
            addCriterion("user_to_task_id not between", value1, value2, "userToTaskId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdIsNull() {
            addCriterion("data_det_id is null");
            return (Criteria) this;
        }

        public Criteria andDataDetIdIsNotNull() {
            addCriterion("data_det_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataDetIdEqualTo(Long value) {
            addCriterion("data_det_id =", value, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdNotEqualTo(Long value) {
            addCriterion("data_det_id <>", value, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdGreaterThan(Long value) {
            addCriterion("data_det_id >", value, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("data_det_id >=", value, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdLessThan(Long value) {
            addCriterion("data_det_id <", value, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdLessThanOrEqualTo(Long value) {
            addCriterion("data_det_id <=", value, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdIn(List<Long> values) {
            addCriterion("data_det_id in", values, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdNotIn(List<Long> values) {
            addCriterion("data_det_id not in", values, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdBetween(Long value1, Long value2) {
            addCriterion("data_det_id between", value1, value2, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andDataDetIdNotBetween(Long value1, Long value2) {
            addCriterion("data_det_id not between", value1, value2, "dataDetId");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateIsNull() {
            addCriterion("user_to_task_state is null");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateIsNotNull() {
            addCriterion("user_to_task_state is not null");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateEqualTo(Integer value) {
            addCriterion("user_to_task_state =", value, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateNotEqualTo(Integer value) {
            addCriterion("user_to_task_state <>", value, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateGreaterThan(Integer value) {
            addCriterion("user_to_task_state >", value, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_to_task_state >=", value, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateLessThan(Integer value) {
            addCriterion("user_to_task_state <", value, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateLessThanOrEqualTo(Integer value) {
            addCriterion("user_to_task_state <=", value, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateIn(List<Integer> values) {
            addCriterion("user_to_task_state in", values, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateNotIn(List<Integer> values) {
            addCriterion("user_to_task_state not in", values, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateBetween(Integer value1, Integer value2) {
            addCriterion("user_to_task_state between", value1, value2, "userToTaskState");
            return (Criteria) this;
        }

        public Criteria andUserToTaskStateNotBetween(Integer value1, Integer value2) {
            addCriterion("user_to_task_state not between", value1, value2, "userToTaskState");
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