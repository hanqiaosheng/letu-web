package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BikeRepairExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public BikeRepairExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(Long value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(Long value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(Long value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(Long value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(Long value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<Long> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<Long> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(Long value1, Long value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(Long value1, Long value2) {
            addCriterion("manager_id not between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNull() {
            addCriterion("manager_name is null");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNotNull() {
            addCriterion("manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNameEqualTo(String value) {
            addCriterion("manager_name =", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotEqualTo(String value) {
            addCriterion("manager_name <>", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThan(String value) {
            addCriterion("manager_name >", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("manager_name >=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThan(String value) {
            addCriterion("manager_name <", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThanOrEqualTo(String value) {
            addCriterion("manager_name <=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLike(String value) {
            addCriterion("manager_name like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotLike(String value) {
            addCriterion("manager_name not like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameIn(List<String> values) {
            addCriterion("manager_name in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotIn(List<String> values) {
            addCriterion("manager_name not in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameBetween(String value1, String value2) {
            addCriterion("manager_name between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotBetween(String value1, String value2) {
            addCriterion("manager_name not between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerTelIsNull() {
            addCriterion("manager_tel is null");
            return (Criteria) this;
        }

        public Criteria andManagerTelIsNotNull() {
            addCriterion("manager_tel is not null");
            return (Criteria) this;
        }

        public Criteria andManagerTelEqualTo(String value) {
            addCriterion("manager_tel =", value, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelNotEqualTo(String value) {
            addCriterion("manager_tel <>", value, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelGreaterThan(String value) {
            addCriterion("manager_tel >", value, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelGreaterThanOrEqualTo(String value) {
            addCriterion("manager_tel >=", value, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelLessThan(String value) {
            addCriterion("manager_tel <", value, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelLessThanOrEqualTo(String value) {
            addCriterion("manager_tel <=", value, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelLike(String value) {
            addCriterion("manager_tel like", value, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelNotLike(String value) {
            addCriterion("manager_tel not like", value, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelIn(List<String> values) {
            addCriterion("manager_tel in", values, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelNotIn(List<String> values) {
            addCriterion("manager_tel not in", values, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelBetween(String value1, String value2) {
            addCriterion("manager_tel between", value1, value2, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerTelNotBetween(String value1, String value2) {
            addCriterion("manager_tel not between", value1, value2, "managerTel");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeIsNull() {
            addCriterion("manager_createtime is null");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeIsNotNull() {
            addCriterion("manager_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("manager_createtime =", value, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("manager_createtime <>", value, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("manager_createtime >", value, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("manager_createtime >=", value, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("manager_createtime <", value, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("manager_createtime <=", value, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("manager_createtime in", values, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("manager_createtime not in", values, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("manager_createtime between", value1, value2, "managerCreatetime");
            return (Criteria) this;
        }

        public Criteria andManagerCreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("manager_createtime not between", value1, value2, "managerCreatetime");
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