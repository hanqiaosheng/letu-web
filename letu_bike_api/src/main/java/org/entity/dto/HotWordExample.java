package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotWordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HotWordExample() {
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

        public Criteria andHotWordIdIsNull() {
            addCriterion("hot_word_id is null");
            return (Criteria) this;
        }

        public Criteria andHotWordIdIsNotNull() {
            addCriterion("hot_word_id is not null");
            return (Criteria) this;
        }

        public Criteria andHotWordIdEqualTo(Long value) {
            addCriterion("hot_word_id =", value, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdNotEqualTo(Long value) {
            addCriterion("hot_word_id <>", value, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdGreaterThan(Long value) {
            addCriterion("hot_word_id >", value, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hot_word_id >=", value, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdLessThan(Long value) {
            addCriterion("hot_word_id <", value, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdLessThanOrEqualTo(Long value) {
            addCriterion("hot_word_id <=", value, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdIn(List<Long> values) {
            addCriterion("hot_word_id in", values, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdNotIn(List<Long> values) {
            addCriterion("hot_word_id not in", values, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdBetween(Long value1, Long value2) {
            addCriterion("hot_word_id between", value1, value2, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordIdNotBetween(Long value1, Long value2) {
            addCriterion("hot_word_id not between", value1, value2, "hotWordId");
            return (Criteria) this;
        }

        public Criteria andHotWordNameIsNull() {
            addCriterion("hot_word_name is null");
            return (Criteria) this;
        }

        public Criteria andHotWordNameIsNotNull() {
            addCriterion("hot_word_name is not null");
            return (Criteria) this;
        }

        public Criteria andHotWordNameEqualTo(String value) {
            addCriterion("hot_word_name =", value, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameNotEqualTo(String value) {
            addCriterion("hot_word_name <>", value, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameGreaterThan(String value) {
            addCriterion("hot_word_name >", value, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameGreaterThanOrEqualTo(String value) {
            addCriterion("hot_word_name >=", value, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameLessThan(String value) {
            addCriterion("hot_word_name <", value, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameLessThanOrEqualTo(String value) {
            addCriterion("hot_word_name <=", value, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameLike(String value) {
            addCriterion("hot_word_name like", value, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameNotLike(String value) {
            addCriterion("hot_word_name not like", value, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameIn(List<String> values) {
            addCriterion("hot_word_name in", values, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameNotIn(List<String> values) {
            addCriterion("hot_word_name not in", values, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameBetween(String value1, String value2) {
            addCriterion("hot_word_name between", value1, value2, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordNameNotBetween(String value1, String value2) {
            addCriterion("hot_word_name not between", value1, value2, "hotWordName");
            return (Criteria) this;
        }

        public Criteria andHotWordStateIsNull() {
            addCriterion("hot_word_state is null");
            return (Criteria) this;
        }

        public Criteria andHotWordStateIsNotNull() {
            addCriterion("hot_word_state is not null");
            return (Criteria) this;
        }

        public Criteria andHotWordStateEqualTo(Integer value) {
            addCriterion("hot_word_state =", value, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateNotEqualTo(Integer value) {
            addCriterion("hot_word_state <>", value, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateGreaterThan(Integer value) {
            addCriterion("hot_word_state >", value, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("hot_word_state >=", value, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateLessThan(Integer value) {
            addCriterion("hot_word_state <", value, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateLessThanOrEqualTo(Integer value) {
            addCriterion("hot_word_state <=", value, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateIn(List<Integer> values) {
            addCriterion("hot_word_state in", values, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateNotIn(List<Integer> values) {
            addCriterion("hot_word_state not in", values, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateBetween(Integer value1, Integer value2) {
            addCriterion("hot_word_state between", value1, value2, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordStateNotBetween(Integer value1, Integer value2) {
            addCriterion("hot_word_state not between", value1, value2, "hotWordState");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumIsNull() {
            addCriterion("hot_word_top_num is null");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumIsNotNull() {
            addCriterion("hot_word_top_num is not null");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumEqualTo(Integer value) {
            addCriterion("hot_word_top_num =", value, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumNotEqualTo(Integer value) {
            addCriterion("hot_word_top_num <>", value, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumGreaterThan(Integer value) {
            addCriterion("hot_word_top_num >", value, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("hot_word_top_num >=", value, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumLessThan(Integer value) {
            addCriterion("hot_word_top_num <", value, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumLessThanOrEqualTo(Integer value) {
            addCriterion("hot_word_top_num <=", value, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumIn(List<Integer> values) {
            addCriterion("hot_word_top_num in", values, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumNotIn(List<Integer> values) {
            addCriterion("hot_word_top_num not in", values, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumBetween(Integer value1, Integer value2) {
            addCriterion("hot_word_top_num between", value1, value2, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordTopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("hot_word_top_num not between", value1, value2, "hotWordTopNum");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefIsNull() {
            addCriterion("hot_word_brief is null");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefIsNotNull() {
            addCriterion("hot_word_brief is not null");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefEqualTo(String value) {
            addCriterion("hot_word_brief =", value, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefNotEqualTo(String value) {
            addCriterion("hot_word_brief <>", value, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefGreaterThan(String value) {
            addCriterion("hot_word_brief >", value, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefGreaterThanOrEqualTo(String value) {
            addCriterion("hot_word_brief >=", value, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefLessThan(String value) {
            addCriterion("hot_word_brief <", value, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefLessThanOrEqualTo(String value) {
            addCriterion("hot_word_brief <=", value, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefLike(String value) {
            addCriterion("hot_word_brief like", value, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefNotLike(String value) {
            addCriterion("hot_word_brief not like", value, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefIn(List<String> values) {
            addCriterion("hot_word_brief in", values, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefNotIn(List<String> values) {
            addCriterion("hot_word_brief not in", values, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefBetween(String value1, String value2) {
            addCriterion("hot_word_brief between", value1, value2, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordBriefNotBetween(String value1, String value2) {
            addCriterion("hot_word_brief not between", value1, value2, "hotWordBrief");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeIsNull() {
            addCriterion("hot_word_create_time is null");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeIsNotNull() {
            addCriterion("hot_word_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeEqualTo(Date value) {
            addCriterion("hot_word_create_time =", value, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeNotEqualTo(Date value) {
            addCriterion("hot_word_create_time <>", value, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeGreaterThan(Date value) {
            addCriterion("hot_word_create_time >", value, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("hot_word_create_time >=", value, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeLessThan(Date value) {
            addCriterion("hot_word_create_time <", value, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("hot_word_create_time <=", value, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeIn(List<Date> values) {
            addCriterion("hot_word_create_time in", values, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeNotIn(List<Date> values) {
            addCriterion("hot_word_create_time not in", values, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeBetween(Date value1, Date value2) {
            addCriterion("hot_word_create_time between", value1, value2, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("hot_word_create_time not between", value1, value2, "hotWordCreateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeIsNull() {
            addCriterion("hot_word_update_time is null");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeIsNotNull() {
            addCriterion("hot_word_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeEqualTo(Date value) {
            addCriterion("hot_word_update_time =", value, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeNotEqualTo(Date value) {
            addCriterion("hot_word_update_time <>", value, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeGreaterThan(Date value) {
            addCriterion("hot_word_update_time >", value, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("hot_word_update_time >=", value, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeLessThan(Date value) {
            addCriterion("hot_word_update_time <", value, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("hot_word_update_time <=", value, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeIn(List<Date> values) {
            addCriterion("hot_word_update_time in", values, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeNotIn(List<Date> values) {
            addCriterion("hot_word_update_time not in", values, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("hot_word_update_time between", value1, value2, "hotWordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHotWordUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("hot_word_update_time not between", value1, value2, "hotWordUpdateTime");
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