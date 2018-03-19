package org.entity.dto;

import java.util.ArrayList;
import java.util.List;


public class SysParamentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public SysParamentExample() {
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

        public Criteria andSysParamentIdIsNull() {
            addCriterion("sys_parament_id is null");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdIsNotNull() {
            addCriterion("sys_parament_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdEqualTo(Long value) {
            addCriterion("sys_parament_id =", value, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdNotEqualTo(Long value) {
            addCriterion("sys_parament_id <>", value, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdGreaterThan(Long value) {
            addCriterion("sys_parament_id >", value, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sys_parament_id >=", value, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdLessThan(Long value) {
            addCriterion("sys_parament_id <", value, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdLessThanOrEqualTo(Long value) {
            addCriterion("sys_parament_id <=", value, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdIn(List<Long> values) {
            addCriterion("sys_parament_id in", values, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdNotIn(List<Long> values) {
            addCriterion("sys_parament_id not in", values, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdBetween(Long value1, Long value2) {
            addCriterion("sys_parament_id between", value1, value2, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentIdNotBetween(Long value1, Long value2) {
            addCriterion("sys_parament_id not between", value1, value2, "sysParamentId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdIsNull() {
            addCriterion("sys_parament_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdIsNotNull() {
            addCriterion("sys_parament_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdEqualTo(Long value) {
            addCriterion("sys_parament_admin_id =", value, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdNotEqualTo(Long value) {
            addCriterion("sys_parament_admin_id <>", value, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdGreaterThan(Long value) {
            addCriterion("sys_parament_admin_id >", value, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sys_parament_admin_id >=", value, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdLessThan(Long value) {
            addCriterion("sys_parament_admin_id <", value, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("sys_parament_admin_id <=", value, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdIn(List<Long> values) {
            addCriterion("sys_parament_admin_id in", values, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdNotIn(List<Long> values) {
            addCriterion("sys_parament_admin_id not in", values, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdBetween(Long value1, Long value2) {
            addCriterion("sys_parament_admin_id between", value1, value2, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("sys_parament_admin_id not between", value1, value2, "sysParamentAdminId");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameIsNull() {
            addCriterion("sys_parament_name is null");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameIsNotNull() {
            addCriterion("sys_parament_name is not null");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameEqualTo(String value) {
            addCriterion("sys_parament_name =", value, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameNotEqualTo(String value) {
            addCriterion("sys_parament_name <>", value, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameGreaterThan(String value) {
            addCriterion("sys_parament_name >", value, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_parament_name >=", value, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameLessThan(String value) {
            addCriterion("sys_parament_name <", value, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameLessThanOrEqualTo(String value) {
            addCriterion("sys_parament_name <=", value, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameLike(String value) {
            addCriterion("sys_parament_name like", value, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameNotLike(String value) {
            addCriterion("sys_parament_name not like", value, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameIn(List<String> values) {
            addCriterion("sys_parament_name in", values, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameNotIn(List<String> values) {
            addCriterion("sys_parament_name not in", values, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameBetween(String value1, String value2) {
            addCriterion("sys_parament_name between", value1, value2, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentNameNotBetween(String value1, String value2) {
            addCriterion("sys_parament_name not between", value1, value2, "sysParamentName");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueIsNull() {
            addCriterion("sys_parament_value is null");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueIsNotNull() {
            addCriterion("sys_parament_value is not null");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueEqualTo(String value) {
            addCriterion("sys_parament_value =", value, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueNotEqualTo(String value) {
            addCriterion("sys_parament_value <>", value, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueGreaterThan(String value) {
            addCriterion("sys_parament_value >", value, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueGreaterThanOrEqualTo(String value) {
            addCriterion("sys_parament_value >=", value, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueLessThan(String value) {
            addCriterion("sys_parament_value <", value, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueLessThanOrEqualTo(String value) {
            addCriterion("sys_parament_value <=", value, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueLike(String value) {
            addCriterion("sys_parament_value like", value, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueNotLike(String value) {
            addCriterion("sys_parament_value not like", value, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueIn(List<String> values) {
            addCriterion("sys_parament_value in", values, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueNotIn(List<String> values) {
            addCriterion("sys_parament_value not in", values, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueBetween(String value1, String value2) {
            addCriterion("sys_parament_value between", value1, value2, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentValueNotBetween(String value1, String value2) {
            addCriterion("sys_parament_value not between", value1, value2, "sysParamentValue");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionIsNull() {
            addCriterion("sys_parament_description is null");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionIsNotNull() {
            addCriterion("sys_parament_description is not null");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionEqualTo(String value) {
            addCriterion("sys_parament_description =", value, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionNotEqualTo(String value) {
            addCriterion("sys_parament_description <>", value, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionGreaterThan(String value) {
            addCriterion("sys_parament_description >", value, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("sys_parament_description >=", value, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionLessThan(String value) {
            addCriterion("sys_parament_description <", value, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionLessThanOrEqualTo(String value) {
            addCriterion("sys_parament_description <=", value, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionLike(String value) {
            addCriterion("sys_parament_description like", value, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionNotLike(String value) {
            addCriterion("sys_parament_description not like", value, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionIn(List<String> values) {
            addCriterion("sys_parament_description in", values, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionNotIn(List<String> values) {
            addCriterion("sys_parament_description not in", values, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionBetween(String value1, String value2) {
            addCriterion("sys_parament_description between", value1, value2, "sysParamentDescription");
            return (Criteria) this;
        }

        public Criteria andSysParamentDescriptionNotBetween(String value1, String value2) {
            addCriterion("sys_parament_description not between", value1, value2, "sysParamentDescription");
            return (Criteria) this;
        }
        
        public Criteria andSysParamentIsUpdateIsNull() {
            addCriterion("sys_parament_is_update is null");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateIsNotNull() {
            addCriterion("sys_parament_is_update is not null");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateEqualTo(Integer value) {
            addCriterion("sys_parament_is_update =", value, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateNotEqualTo(Integer value) {
            addCriterion("sys_parament_is_update <>", value, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateGreaterThan(Integer value) {
            addCriterion("sys_parament_is_update >", value, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateGreaterThanOrEqualTo(Integer value) {
            addCriterion("sys_parament_is_update >=", value, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateLessThan(Integer value) {
            addCriterion("sys_parament_is_update <", value, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateLessThanOrEqualTo(Integer value) {
            addCriterion("sys_parament_is_update <=", value, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateIn(List<Integer> values) {
            addCriterion("sys_parament_is_update in", values, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateNotIn(List<Integer> values) {
            addCriterion("sys_parament_is_update not in", values, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateBetween(Integer value1, Integer value2) {
            addCriterion("sys_parament_is_update between", value1, value2, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentIsUpdateNotBetween(Integer value1, Integer value2) {
            addCriterion("sys_parament_is_update not between", value1, value2, "sysParamentIsUpdate");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentIsNull() {
            addCriterion("sys_parament_update_content is null");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentIsNotNull() {
            addCriterion("sys_parament_update_content is not null");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentEqualTo(String value) {
            addCriterion("sys_parament_update_content =", value, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentNotEqualTo(String value) {
            addCriterion("sys_parament_update_content <>", value, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentGreaterThan(String value) {
            addCriterion("sys_parament_update_content >", value, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentGreaterThanOrEqualTo(String value) {
            addCriterion("sys_parament_update_content >=", value, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentLessThan(String value) {
            addCriterion("sys_parament_update_content <", value, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentLessThanOrEqualTo(String value) {
            addCriterion("sys_parament_update_content <=", value, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentLike(String value) {
            addCriterion("sys_parament_update_content like", value, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentNotLike(String value) {
            addCriterion("sys_parament_update_content not like", value, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentIn(List<String> values) {
            addCriterion("sys_parament_update_content in", values, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentNotIn(List<String> values) {
            addCriterion("sys_parament_update_content not in", values, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentBetween(String value1, String value2) {
            addCriterion("sys_parament_update_content between", value1, value2, "sysParamentUpdateContent");
            return (Criteria) this;
        }

        public Criteria andSysParamentUpdateContentNotBetween(String value1, String value2) {
            addCriterion("sys_parament_update_content not between", value1, value2, "sysParamentUpdateContent");
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