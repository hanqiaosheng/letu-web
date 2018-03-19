package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public RoleExample() {
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

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Long value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Long value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Long value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Long value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Long value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Long> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Long> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Long value1, Long value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Long value1, Long value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleValueIsNull() {
            addCriterion("role_value is null");
            return (Criteria) this;
        }

        public Criteria andRoleValueIsNotNull() {
            addCriterion("role_value is not null");
            return (Criteria) this;
        }

        public Criteria andRoleValueEqualTo(String value) {
            addCriterion("role_value =", value, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueNotEqualTo(String value) {
            addCriterion("role_value <>", value, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueGreaterThan(String value) {
            addCriterion("role_value >", value, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueGreaterThanOrEqualTo(String value) {
            addCriterion("role_value >=", value, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueLessThan(String value) {
            addCriterion("role_value <", value, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueLessThanOrEqualTo(String value) {
            addCriterion("role_value <=", value, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueLike(String value) {
            addCriterion("role_value like", value, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueNotLike(String value) {
            addCriterion("role_value not like", value, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueIn(List<String> values) {
            addCriterion("role_value in", values, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueNotIn(List<String> values) {
            addCriterion("role_value not in", values, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueBetween(String value1, String value2) {
            addCriterion("role_value between", value1, value2, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleValueNotBetween(String value1, String value2) {
            addCriterion("role_value not between", value1, value2, "roleValue");
            return (Criteria) this;
        }

        public Criteria andRoleStateIsNull() {
            addCriterion("role_state is null");
            return (Criteria) this;
        }

        public Criteria andRoleStateIsNotNull() {
            addCriterion("role_state is not null");
            return (Criteria) this;
        }

        public Criteria andRoleStateEqualTo(Integer value) {
            addCriterion("role_state =", value, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateNotEqualTo(Integer value) {
            addCriterion("role_state <>", value, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateGreaterThan(Integer value) {
            addCriterion("role_state >", value, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_state >=", value, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateLessThan(Integer value) {
            addCriterion("role_state <", value, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateLessThanOrEqualTo(Integer value) {
            addCriterion("role_state <=", value, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateIn(List<Integer> values) {
            addCriterion("role_state in", values, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateNotIn(List<Integer> values) {
            addCriterion("role_state not in", values, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateBetween(Integer value1, Integer value2) {
            addCriterion("role_state between", value1, value2, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleStateNotBetween(Integer value1, Integer value2) {
            addCriterion("role_state not between", value1, value2, "roleState");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeIsNull() {
            addCriterion("role_createtime is null");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeIsNotNull() {
            addCriterion("role_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("role_createtime =", value, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("role_createtime <>", value, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("role_createtime >", value, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("role_createtime >=", value, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("role_createtime <", value, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("role_createtime <=", value, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("role_createtime in", values, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("role_createtime not in", values, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("role_createtime between", value1, value2, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("role_createtime not between", value1, value2, "roleCreatetime");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumIsNull() {
            addCriterion("role_hasnum is null");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumIsNotNull() {
            addCriterion("role_hasnum is not null");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumEqualTo(Integer value) {
            addCriterion("role_hasnum =", value, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumNotEqualTo(Integer value) {
            addCriterion("role_hasnum <>", value, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumGreaterThan(Integer value) {
            addCriterion("role_hasnum >", value, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_hasnum >=", value, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumLessThan(Integer value) {
            addCriterion("role_hasnum <", value, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumLessThanOrEqualTo(Integer value) {
            addCriterion("role_hasnum <=", value, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumIn(List<Integer> values) {
            addCriterion("role_hasnum in", values, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumNotIn(List<Integer> values) {
            addCriterion("role_hasnum not in", values, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumBetween(Integer value1, Integer value2) {
            addCriterion("role_hasnum between", value1, value2, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleHasnumNotBetween(Integer value1, Integer value2) {
            addCriterion("role_hasnum not between", value1, value2, "roleHasnum");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeIsNull() {
            addCriterion("role_updatetime is null");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeIsNotNull() {
            addCriterion("role_updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("role_updatetime =", value, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("role_updatetime <>", value, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("role_updatetime >", value, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("role_updatetime >=", value, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeLessThan(Date value) {
            addCriterionForJDBCDate("role_updatetime <", value, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("role_updatetime <=", value, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("role_updatetime in", values, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("role_updatetime not in", values, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("role_updatetime between", value1, value2, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("role_updatetime not between", value1, value2, "roleUpdatetime");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanIsNull() {
            addCriterion("role_createman is null");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanIsNotNull() {
            addCriterion("role_createman is not null");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanEqualTo(Long value) {
            addCriterion("role_createman =", value, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanNotEqualTo(Long value) {
            addCriterion("role_createman <>", value, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanGreaterThan(Long value) {
            addCriterion("role_createman >", value, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanGreaterThanOrEqualTo(Long value) {
            addCriterion("role_createman >=", value, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanLessThan(Long value) {
            addCriterion("role_createman <", value, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanLessThanOrEqualTo(Long value) {
            addCriterion("role_createman <=", value, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanIn(List<Long> values) {
            addCriterion("role_createman in", values, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanNotIn(List<Long> values) {
            addCriterion("role_createman not in", values, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanBetween(Long value1, Long value2) {
            addCriterion("role_createman between", value1, value2, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleCreatemanNotBetween(Long value1, Long value2) {
            addCriterion("role_createman not between", value1, value2, "roleCreateman");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIsNull() {
            addCriterion("role_description is null");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIsNotNull() {
            addCriterion("role_description is not null");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionEqualTo(String value) {
            addCriterion("role_description =", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotEqualTo(String value) {
            addCriterion("role_description <>", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionGreaterThan(String value) {
            addCriterion("role_description >", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("role_description >=", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLessThan(String value) {
            addCriterion("role_description <", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLessThanOrEqualTo(String value) {
            addCriterion("role_description <=", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionLike(String value) {
            addCriterion("role_description like", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotLike(String value) {
            addCriterion("role_description not like", value, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionIn(List<String> values) {
            addCriterion("role_description in", values, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotIn(List<String> values) {
            addCriterion("role_description not in", values, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionBetween(String value1, String value2) {
            addCriterion("role_description between", value1, value2, "roleDescription");
            return (Criteria) this;
        }

        public Criteria andRoleDescriptionNotBetween(String value1, String value2) {
            addCriterion("role_description not between", value1, value2, "roleDescription");
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