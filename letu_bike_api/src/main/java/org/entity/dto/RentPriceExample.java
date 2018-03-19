package org.entity.dto;

import java.util.ArrayList;
import java.util.List;


public class RentPriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public RentPriceExample() {
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

        public Criteria andRentPriceIdIsNull() {
            addCriterion("rent_price_id is null");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdIsNotNull() {
            addCriterion("rent_price_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdEqualTo(Long value) {
            addCriterion("rent_price_id =", value, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdNotEqualTo(Long value) {
            addCriterion("rent_price_id <>", value, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdGreaterThan(Long value) {
            addCriterion("rent_price_id >", value, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_price_id >=", value, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdLessThan(Long value) {
            addCriterion("rent_price_id <", value, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_price_id <=", value, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdIn(List<Long> values) {
            addCriterion("rent_price_id in", values, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdNotIn(List<Long> values) {
            addCriterion("rent_price_id not in", values, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdBetween(Long value1, Long value2) {
            addCriterion("rent_price_id between", value1, value2, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_price_id not between", value1, value2, "rentPriceId");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeIsNull() {
            addCriterion("rent_price_type is null");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeIsNotNull() {
            addCriterion("rent_price_type is not null");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeEqualTo(Integer value) {
            addCriterion("rent_price_type =", value, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeNotEqualTo(Integer value) {
            addCriterion("rent_price_type <>", value, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeGreaterThan(Integer value) {
            addCriterion("rent_price_type >", value, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_price_type >=", value, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeLessThan(Integer value) {
            addCriterion("rent_price_type <", value, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("rent_price_type <=", value, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeIn(List<Integer> values) {
            addCriterion("rent_price_type in", values, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeNotIn(List<Integer> values) {
            addCriterion("rent_price_type not in", values, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeBetween(Integer value1, Integer value2) {
            addCriterion("rent_price_type between", value1, value2, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentPriceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_price_type not between", value1, value2, "rentPriceType");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeIsNull() {
            addCriterion("rent_free_time is null");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeIsNotNull() {
            addCriterion("rent_free_time is not null");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeEqualTo(Integer value) {
            addCriterion("rent_free_time =", value, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeNotEqualTo(Integer value) {
            addCriterion("rent_free_time <>", value, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeGreaterThan(Integer value) {
            addCriterion("rent_free_time >", value, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_free_time >=", value, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeLessThan(Integer value) {
            addCriterion("rent_free_time <", value, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeLessThanOrEqualTo(Integer value) {
            addCriterion("rent_free_time <=", value, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeIn(List<Integer> values) {
            addCriterion("rent_free_time in", values, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeNotIn(List<Integer> values) {
            addCriterion("rent_free_time not in", values, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeBetween(Integer value1, Integer value2) {
            addCriterion("rent_free_time between", value1, value2, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentFreeTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_free_time not between", value1, value2, "rentFreeTime");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionIsNull() {
            addCriterion("rent_price_option is null");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionIsNotNull() {
            addCriterion("rent_price_option is not null");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionEqualTo(Integer value) {
            addCriterion("rent_price_option =", value, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionNotEqualTo(Integer value) {
            addCriterion("rent_price_option <>", value, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionGreaterThan(Integer value) {
            addCriterion("rent_price_option >", value, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_price_option >=", value, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionLessThan(Integer value) {
            addCriterion("rent_price_option <", value, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionLessThanOrEqualTo(Integer value) {
            addCriterion("rent_price_option <=", value, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionIn(List<Integer> values) {
            addCriterion("rent_price_option in", values, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionNotIn(List<Integer> values) {
            addCriterion("rent_price_option not in", values, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionBetween(Integer value1, Integer value2) {
            addCriterion("rent_price_option between", value1, value2, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceOptionNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_price_option not between", value1, value2, "rentPriceOption");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdIsNull() {
            addCriterion("rent_price_models_id is null");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdIsNotNull() {
            addCriterion("rent_price_models_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdEqualTo(Long value) {
            addCriterion("rent_price_models_id =", value, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdNotEqualTo(Long value) {
            addCriterion("rent_price_models_id <>", value, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdGreaterThan(Long value) {
            addCriterion("rent_price_models_id >", value, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_price_models_id >=", value, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdLessThan(Long value) {
            addCriterion("rent_price_models_id <", value, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_price_models_id <=", value, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdIn(List<Long> values) {
            addCriterion("rent_price_models_id in", values, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdNotIn(List<Long> values) {
            addCriterion("rent_price_models_id not in", values, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdBetween(Long value1, Long value2) {
            addCriterion("rent_price_models_id between", value1, value2, "rentPriceModelsId");
            return (Criteria) this;
        }

        public Criteria andRentPriceModelsIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_price_models_id not between", value1, value2, "rentPriceModelsId");
            return (Criteria) this;
        }
        
        public Criteria andRentPriceMaxIsNull() {
            addCriterion("rent_price_max is null");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxIsNotNull() {
            addCriterion("rent_price_max is not null");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxEqualTo(Double value) {
            addCriterion("rent_price_max =", value, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxNotEqualTo(Double value) {
            addCriterion("rent_price_max <>", value, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxGreaterThan(Double value) {
            addCriterion("rent_price_max >", value, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxGreaterThanOrEqualTo(Double value) {
            addCriterion("rent_price_max >=", value, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxLessThan(Double value) {
            addCriterion("rent_price_max <", value, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxLessThanOrEqualTo(Double value) {
            addCriterion("rent_price_max <=", value, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxIn(List<Double> values) {
            addCriterion("rent_price_max in", values, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxNotIn(List<Double> values) {
            addCriterion("rent_price_max not in", values, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxBetween(Double value1, Double value2) {
            addCriterion("rent_price_max between", value1, value2, "rentPriceMax");
            return (Criteria) this;
        }

        public Criteria andRentPriceMaxNotBetween(Double value1, Double value2) {
            addCriterion("rent_price_max not between", value1, value2, "rentPriceMax");
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