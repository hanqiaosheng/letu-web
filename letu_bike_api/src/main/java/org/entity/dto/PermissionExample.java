package org.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class PermissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public PermissionExample() {
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

        public Criteria andPermissionIdIsNull() {
            addCriterion("permission_id is null");
            return (Criteria) this;
        }

        public Criteria andPermissionIdIsNotNull() {
            addCriterion("permission_id is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionIdEqualTo(Long value) {
            addCriterion("permission_id =", value, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdNotEqualTo(Long value) {
            addCriterion("permission_id <>", value, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdGreaterThan(Long value) {
            addCriterion("permission_id >", value, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("permission_id >=", value, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdLessThan(Long value) {
            addCriterion("permission_id <", value, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdLessThanOrEqualTo(Long value) {
            addCriterion("permission_id <=", value, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdIn(List<Long> values) {
            addCriterion("permission_id in", values, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdNotIn(List<Long> values) {
            addCriterion("permission_id not in", values, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdBetween(Long value1, Long value2) {
            addCriterion("permission_id between", value1, value2, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionIdNotBetween(Long value1, Long value2) {
            addCriterion("permission_id not between", value1, value2, "permissionId");
            return (Criteria) this;
        }

        public Criteria andPermissionNameIsNull() {
            addCriterion("permission_name is null");
            return (Criteria) this;
        }

        public Criteria andPermissionNameIsNotNull() {
            addCriterion("permission_name is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionNameEqualTo(String value) {
            addCriterion("permission_name =", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameNotEqualTo(String value) {
            addCriterion("permission_name <>", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameGreaterThan(String value) {
            addCriterion("permission_name >", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameGreaterThanOrEqualTo(String value) {
            addCriterion("permission_name >=", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameLessThan(String value) {
            addCriterion("permission_name <", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameLessThanOrEqualTo(String value) {
            addCriterion("permission_name <=", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameLike(String value) {
            addCriterion("permission_name like", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameNotLike(String value) {
            addCriterion("permission_name not like", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameIn(List<String> values) {
            addCriterion("permission_name in", values, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameNotIn(List<String> values) {
            addCriterion("permission_name not in", values, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameBetween(String value1, String value2) {
            addCriterion("permission_name between", value1, value2, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameNotBetween(String value1, String value2) {
            addCriterion("permission_name not between", value1, value2, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionIsNull() {
            addCriterion("permission_description is null");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionIsNotNull() {
            addCriterion("permission_description is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionEqualTo(String value) {
            addCriterion("permission_description =", value, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionNotEqualTo(String value) {
            addCriterion("permission_description <>", value, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionGreaterThan(String value) {
            addCriterion("permission_description >", value, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("permission_description >=", value, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionLessThan(String value) {
            addCriterion("permission_description <", value, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionLessThanOrEqualTo(String value) {
            addCriterion("permission_description <=", value, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionLike(String value) {
            addCriterion("permission_description like", value, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionNotLike(String value) {
            addCriterion("permission_description not like", value, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionIn(List<String> values) {
            addCriterion("permission_description in", values, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionNotIn(List<String> values) {
            addCriterion("permission_description not in", values, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionBetween(String value1, String value2) {
            addCriterion("permission_description between", value1, value2, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionDescriptionNotBetween(String value1, String value2) {
            addCriterion("permission_description not between", value1, value2, "permissionDescription");
            return (Criteria) this;
        }

        public Criteria andPermissionValueIsNull() {
            addCriterion("permission_value is null");
            return (Criteria) this;
        }

        public Criteria andPermissionValueIsNotNull() {
            addCriterion("permission_value is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionValueEqualTo(String value) {
            addCriterion("permission_value =", value, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueNotEqualTo(String value) {
            addCriterion("permission_value <>", value, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueGreaterThan(String value) {
            addCriterion("permission_value >", value, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueGreaterThanOrEqualTo(String value) {
            addCriterion("permission_value >=", value, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueLessThan(String value) {
            addCriterion("permission_value <", value, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueLessThanOrEqualTo(String value) {
            addCriterion("permission_value <=", value, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueLike(String value) {
            addCriterion("permission_value like", value, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueNotLike(String value) {
            addCriterion("permission_value not like", value, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueIn(List<String> values) {
            addCriterion("permission_value in", values, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueNotIn(List<String> values) {
            addCriterion("permission_value not in", values, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueBetween(String value1, String value2) {
            addCriterion("permission_value between", value1, value2, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionValueNotBetween(String value1, String value2) {
            addCriterion("permission_value not between", value1, value2, "permissionValue");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdIsNull() {
            addCriterion("permission_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdIsNotNull() {
            addCriterion("permission_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdEqualTo(Long value) {
            addCriterion("permission_parent_id =", value, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdNotEqualTo(Long value) {
            addCriterion("permission_parent_id <>", value, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdGreaterThan(Long value) {
            addCriterion("permission_parent_id >", value, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("permission_parent_id >=", value, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdLessThan(Long value) {
            addCriterion("permission_parent_id <", value, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdLessThanOrEqualTo(Long value) {
            addCriterion("permission_parent_id <=", value, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdIn(List<Long> values) {
            addCriterion("permission_parent_id in", values, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdNotIn(List<Long> values) {
            addCriterion("permission_parent_id not in", values, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdBetween(Long value1, Long value2) {
            addCriterion("permission_parent_id between", value1, value2, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionParentIdNotBetween(Long value1, Long value2) {
            addCriterion("permission_parent_id not between", value1, value2, "permissionParentId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdIsNull() {
            addCriterion("permission_type_id is null");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdIsNotNull() {
            addCriterion("permission_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdEqualTo(Long value) {
            addCriterion("permission_type_id =", value, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdNotEqualTo(Long value) {
            addCriterion("permission_type_id <>", value, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdGreaterThan(Long value) {
            addCriterion("permission_type_id >", value, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("permission_type_id >=", value, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdLessThan(Long value) {
            addCriterion("permission_type_id <", value, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("permission_type_id <=", value, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdIn(List<Long> values) {
            addCriterion("permission_type_id in", values, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdNotIn(List<Long> values) {
            addCriterion("permission_type_id not in", values, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdBetween(Long value1, Long value2) {
            addCriterion("permission_type_id between", value1, value2, "permissionTypeId");
            return (Criteria) this;
        }

        public Criteria andPermissionTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("permission_type_id not between", value1, value2, "permissionTypeId");
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