package org.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class UserGuideExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UserGuideExample() {
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

        public Criteria andUserGuideIdIsNull() {
            addCriterion("user_guide_id is null");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdIsNotNull() {
            addCriterion("user_guide_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdEqualTo(Long value) {
            addCriterion("user_guide_id =", value, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdNotEqualTo(Long value) {
            addCriterion("user_guide_id <>", value, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdGreaterThan(Long value) {
            addCriterion("user_guide_id >", value, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_guide_id >=", value, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdLessThan(Long value) {
            addCriterion("user_guide_id <", value, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdLessThanOrEqualTo(Long value) {
            addCriterion("user_guide_id <=", value, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdIn(List<Long> values) {
            addCriterion("user_guide_id in", values, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdNotIn(List<Long> values) {
            addCriterion("user_guide_id not in", values, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdBetween(Long value1, Long value2) {
            addCriterion("user_guide_id between", value1, value2, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideIdNotBetween(Long value1, Long value2) {
            addCriterion("user_guide_id not between", value1, value2, "userGuideId");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeIsNull() {
            addCriterion("user_guide_type is null");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeIsNotNull() {
            addCriterion("user_guide_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeEqualTo(String value) {
            addCriterion("user_guide_type =", value, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeNotEqualTo(String value) {
            addCriterion("user_guide_type <>", value, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeGreaterThan(String value) {
            addCriterion("user_guide_type >", value, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_guide_type >=", value, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeLessThan(String value) {
            addCriterion("user_guide_type <", value, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeLessThanOrEqualTo(String value) {
            addCriterion("user_guide_type <=", value, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeLike(String value) {
            addCriterion("user_guide_type like", value, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeNotLike(String value) {
            addCriterion("user_guide_type not like", value, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeIn(List<String> values) {
            addCriterion("user_guide_type in", values, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeNotIn(List<String> values) {
            addCriterion("user_guide_type not in", values, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeBetween(String value1, String value2) {
            addCriterion("user_guide_type between", value1, value2, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideTypeNotBetween(String value1, String value2) {
            addCriterion("user_guide_type not between", value1, value2, "userGuideType");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidIsNull() {
            addCriterion("user_guide_adminId is null");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidIsNotNull() {
            addCriterion("user_guide_adminId is not null");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidEqualTo(Long value) {
            addCriterion("user_guide_adminId =", value, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidNotEqualTo(Long value) {
            addCriterion("user_guide_adminId <>", value, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidGreaterThan(Long value) {
            addCriterion("user_guide_adminId >", value, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidGreaterThanOrEqualTo(Long value) {
            addCriterion("user_guide_adminId >=", value, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidLessThan(Long value) {
            addCriterion("user_guide_adminId <", value, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidLessThanOrEqualTo(Long value) {
            addCriterion("user_guide_adminId <=", value, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidIn(List<Long> values) {
            addCriterion("user_guide_adminId in", values, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidNotIn(List<Long> values) {
            addCriterion("user_guide_adminId not in", values, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidBetween(Long value1, Long value2) {
            addCriterion("user_guide_adminId between", value1, value2, "userGuideAdminid");
            return (Criteria) this;
        }

        public Criteria andUserGuideAdminidNotBetween(Long value1, Long value2) {
            addCriterion("user_guide_adminId not between", value1, value2, "userGuideAdminid");
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