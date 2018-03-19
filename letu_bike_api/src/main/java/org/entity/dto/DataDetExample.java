package org.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class DataDetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public DataDetExample() {
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

        public Criteria andDataIdIsNull() {
            addCriterion("data_id is null");
            return (Criteria) this;
        }

        public Criteria andDataIdIsNotNull() {
            addCriterion("data_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataIdEqualTo(Long value) {
            addCriterion("data_id =", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotEqualTo(Long value) {
            addCriterion("data_id <>", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThan(Long value) {
            addCriterion("data_id >", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdGreaterThanOrEqualTo(Long value) {
            addCriterion("data_id >=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThan(Long value) {
            addCriterion("data_id <", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdLessThanOrEqualTo(Long value) {
            addCriterion("data_id <=", value, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdIn(List<Long> values) {
            addCriterion("data_id in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotIn(List<Long> values) {
            addCriterion("data_id not in", values, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdBetween(Long value1, Long value2) {
            addCriterion("data_id between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataIdNotBetween(Long value1, Long value2) {
            addCriterion("data_id not between", value1, value2, "dataId");
            return (Criteria) this;
        }

        public Criteria andDataDetNameIsNull() {
            addCriterion("data_det_name is null");
            return (Criteria) this;
        }

        public Criteria andDataDetNameIsNotNull() {
            addCriterion("data_det_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataDetNameEqualTo(String value) {
            addCriterion("data_det_name =", value, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameNotEqualTo(String value) {
            addCriterion("data_det_name <>", value, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameGreaterThan(String value) {
            addCriterion("data_det_name >", value, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_det_name >=", value, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameLessThan(String value) {
            addCriterion("data_det_name <", value, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameLessThanOrEqualTo(String value) {
            addCriterion("data_det_name <=", value, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameLike(String value) {
            addCriterion("data_det_name like", value, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameNotLike(String value) {
            addCriterion("data_det_name not like", value, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameIn(List<String> values) {
            addCriterion("data_det_name in", values, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameNotIn(List<String> values) {
            addCriterion("data_det_name not in", values, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameBetween(String value1, String value2) {
            addCriterion("data_det_name between", value1, value2, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetNameNotBetween(String value1, String value2) {
            addCriterion("data_det_name not between", value1, value2, "dataDetName");
            return (Criteria) this;
        }

        public Criteria andDataDetValIsNull() {
            addCriterion("data_det_val is null");
            return (Criteria) this;
        }

        public Criteria andDataDetValIsNotNull() {
            addCriterion("data_det_val is not null");
            return (Criteria) this;
        }

        public Criteria andDataDetValEqualTo(String value) {
            addCriterion("data_det_val =", value, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValNotEqualTo(String value) {
            addCriterion("data_det_val <>", value, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValGreaterThan(String value) {
            addCriterion("data_det_val >", value, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValGreaterThanOrEqualTo(String value) {
            addCriterion("data_det_val >=", value, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValLessThan(String value) {
            addCriterion("data_det_val <", value, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValLessThanOrEqualTo(String value) {
            addCriterion("data_det_val <=", value, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValLike(String value) {
            addCriterion("data_det_val like", value, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValNotLike(String value) {
            addCriterion("data_det_val not like", value, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValIn(List<String> values) {
            addCriterion("data_det_val in", values, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValNotIn(List<String> values) {
            addCriterion("data_det_val not in", values, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValBetween(String value1, String value2) {
            addCriterion("data_det_val between", value1, value2, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetValNotBetween(String value1, String value2) {
            addCriterion("data_det_val not between", value1, value2, "dataDetVal");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionIsNull() {
            addCriterion("data_det_description is null");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionIsNotNull() {
            addCriterion("data_det_description is not null");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionEqualTo(String value) {
            addCriterion("data_det_description =", value, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionNotEqualTo(String value) {
            addCriterion("data_det_description <>", value, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionGreaterThan(String value) {
            addCriterion("data_det_description >", value, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("data_det_description >=", value, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionLessThan(String value) {
            addCriterion("data_det_description <", value, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionLessThanOrEqualTo(String value) {
            addCriterion("data_det_description <=", value, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionLike(String value) {
            addCriterion("data_det_description like", value, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionNotLike(String value) {
            addCriterion("data_det_description not like", value, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionIn(List<String> values) {
            addCriterion("data_det_description in", values, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionNotIn(List<String> values) {
            addCriterion("data_det_description not in", values, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionBetween(String value1, String value2) {
            addCriterion("data_det_description between", value1, value2, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDescriptionNotBetween(String value1, String value2) {
            addCriterion("data_det_description not between", value1, value2, "dataDetDescription");
            return (Criteria) this;
        }

        public Criteria andDataDetDrIsNull() {
            addCriterion("data_det_dr is null");
            return (Criteria) this;
        }

        public Criteria andDataDetDrIsNotNull() {
            addCriterion("data_det_dr is not null");
            return (Criteria) this;
        }

        public Criteria andDataDetDrEqualTo(Integer value) {
            addCriterion("data_det_dr =", value, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrNotEqualTo(Integer value) {
            addCriterion("data_det_dr <>", value, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrGreaterThan(Integer value) {
            addCriterion("data_det_dr >", value, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_det_dr >=", value, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrLessThan(Integer value) {
            addCriterion("data_det_dr <", value, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrLessThanOrEqualTo(Integer value) {
            addCriterion("data_det_dr <=", value, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrIn(List<Integer> values) {
            addCriterion("data_det_dr in", values, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrNotIn(List<Integer> values) {
            addCriterion("data_det_dr not in", values, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrBetween(Integer value1, Integer value2) {
            addCriterion("data_det_dr between", value1, value2, "dataDetDr");
            return (Criteria) this;
        }

        public Criteria andDataDetDrNotBetween(Integer value1, Integer value2) {
            addCriterion("data_det_dr not between", value1, value2, "dataDetDr");
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