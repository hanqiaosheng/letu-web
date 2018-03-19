package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;






public class ModelsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ModelsExample() {
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
        //
        public Criteria andChannelNameLike(String value) {
            addCriterion("c.channel_name like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andModelsIdIsNull() {
            addCriterion("models_id is null");
            return (Criteria) this;
        }

        public Criteria andModelsIdIsNotNull() {
            addCriterion("models_id is not null");
            return (Criteria) this;
        }

        public Criteria andModelsIdEqualTo(Long value) {
            addCriterion("models_id =", value, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdNotEqualTo(Long value) {
            addCriterion("models_id <>", value, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdGreaterThan(Long value) {
            addCriterion("models_id >", value, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("models_id >=", value, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdLessThan(Long value) {
            addCriterion("models_id <", value, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdLessThanOrEqualTo(Long value) {
            addCriterion("models_id <=", value, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdIn(List<Long> values) {
            addCriterion("models_id in", values, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdNotIn(List<Long> values) {
            addCriterion("models_id not in", values, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdBetween(Long value1, Long value2) {
            addCriterion("models_id between", value1, value2, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsIdNotBetween(Long value1, Long value2) {
            addCriterion("models_id not between", value1, value2, "modelsId");
            return (Criteria) this;
        }

        public Criteria andModelsCodeIsNull() {
            addCriterion("models_code is null");
            return (Criteria) this;
        }

        public Criteria andModelsCodeIsNotNull() {
            addCriterion("models_code is not null");
            return (Criteria) this;
        }

        public Criteria andModelsCodeEqualTo(String value) {
            addCriterion("models_code =", value, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeNotEqualTo(String value) {
            addCriterion("models_code <>", value, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeGreaterThan(String value) {
            addCriterion("models_code >", value, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("models_code >=", value, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeLessThan(String value) {
            addCriterion("models_code <", value, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeLessThanOrEqualTo(String value) {
            addCriterion("models_code <=", value, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeLike(String value) {
            addCriterion("models_code like", value, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeNotLike(String value) {
            addCriterion("models_code not like", value, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeIn(List<String> values) {
            addCriterion("models_code in", values, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeNotIn(List<String> values) {
            addCriterion("models_code not in", values, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeBetween(String value1, String value2) {
            addCriterion("models_code between", value1, value2, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsCodeNotBetween(String value1, String value2) {
            addCriterion("models_code not between", value1, value2, "modelsCode");
            return (Criteria) this;
        }

        public Criteria andModelsNameIsNull() {
            addCriterion("models_name is null");
            return (Criteria) this;
        }

        public Criteria andModelsNameIsNotNull() {
            addCriterion("models_name is not null");
            return (Criteria) this;
        }

        public Criteria andModelsNameEqualTo(String value) {
            addCriterion("models_name =", value, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameNotEqualTo(String value) {
            addCriterion("models_name <>", value, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameGreaterThan(String value) {
            addCriterion("models_name >", value, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameGreaterThanOrEqualTo(String value) {
            addCriterion("models_name >=", value, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameLessThan(String value) {
            addCriterion("models_name <", value, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameLessThanOrEqualTo(String value) {
            addCriterion("models_name <=", value, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameLike(String value) {
            addCriterion("models_name like", value, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameNotLike(String value) {
            addCriterion("models_name not like", value, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameIn(List<String> values) {
            addCriterion("models_name in", values, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameNotIn(List<String> values) {
            addCriterion("models_name not in", values, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameBetween(String value1, String value2) {
            addCriterion("models_name between", value1, value2, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsNameNotBetween(String value1, String value2) {
            addCriterion("models_name not between", value1, value2, "modelsName");
            return (Criteria) this;
        }

        public Criteria andModelsDepositIsNull() {
            addCriterion("models_deposit is null");
            return (Criteria) this;
        }

        public Criteria andModelsDepositIsNotNull() {
            addCriterion("models_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andModelsDepositEqualTo(BigDecimal value) {
            addCriterion("models_deposit =", value, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositNotEqualTo(BigDecimal value) {
            addCriterion("models_deposit <>", value, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositGreaterThan(BigDecimal value) {
            addCriterion("models_deposit >", value, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("models_deposit >=", value, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositLessThan(BigDecimal value) {
            addCriterion("models_deposit <", value, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("models_deposit <=", value, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositIn(List<BigDecimal> values) {
            addCriterion("models_deposit in", values, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositNotIn(List<BigDecimal> values) {
            addCriterion("models_deposit not in", values, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("models_deposit between", value1, value2, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("models_deposit not between", value1, value2, "modelsDeposit");
            return (Criteria) this;
        }

        public Criteria andModelsContentIsNull() {
            addCriterion("models_content is null");
            return (Criteria) this;
        }

        public Criteria andModelsContentIsNotNull() {
            addCriterion("models_content is not null");
            return (Criteria) this;
        }

        public Criteria andModelsContentEqualTo(String value) {
            addCriterion("models_content =", value, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentNotEqualTo(String value) {
            addCriterion("models_content <>", value, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentGreaterThan(String value) {
            addCriterion("models_content >", value, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentGreaterThanOrEqualTo(String value) {
            addCriterion("models_content >=", value, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentLessThan(String value) {
            addCriterion("models_content <", value, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentLessThanOrEqualTo(String value) {
            addCriterion("models_content <=", value, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentLike(String value) {
            addCriterion("models_content like", value, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentNotLike(String value) {
            addCriterion("models_content not like", value, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentIn(List<String> values) {
            addCriterion("models_content in", values, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentNotIn(List<String> values) {
            addCriterion("models_content not in", values, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentBetween(String value1, String value2) {
            addCriterion("models_content between", value1, value2, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsContentNotBetween(String value1, String value2) {
            addCriterion("models_content not between", value1, value2, "modelsContent");
            return (Criteria) this;
        }

        public Criteria andModelsStateIsNull() {
            addCriterion("models_state is null");
            return (Criteria) this;
        }

        public Criteria andModelsStateIsNotNull() {
            addCriterion("models_state is not null");
            return (Criteria) this;
        }

        public Criteria andModelsStateEqualTo(Integer value) {
            addCriterion("models_state =", value, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateNotEqualTo(Integer value) {
            addCriterion("models_state <>", value, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateGreaterThan(Integer value) {
            addCriterion("models_state >", value, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("models_state >=", value, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateLessThan(Integer value) {
            addCriterion("models_state <", value, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateLessThanOrEqualTo(Integer value) {
            addCriterion("models_state <=", value, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateIn(List<Integer> values) {
            addCriterion("models_state in", values, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateNotIn(List<Integer> values) {
            addCriterion("models_state not in", values, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateBetween(Integer value1, Integer value2) {
            addCriterion("models_state between", value1, value2, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsStateNotBetween(Integer value1, Integer value2) {
            addCriterion("models_state not between", value1, value2, "modelsState");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdIsNull() {
            addCriterion("models_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdIsNotNull() {
            addCriterion("models_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdEqualTo(Long value) {
            addCriterion("models_channel_id =", value, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdNotEqualTo(Long value) {
            addCriterion("models_channel_id <>", value, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdGreaterThan(Long value) {
            addCriterion("models_channel_id >", value, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("models_channel_id >=", value, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdLessThan(Long value) {
            addCriterion("models_channel_id <", value, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("models_channel_id <=", value, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdIn(List<Long> values) {
            addCriterion("models_channel_id in", values, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdNotIn(List<Long> values) {
            addCriterion("models_channel_id not in", values, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdBetween(Long value1, Long value2) {
            addCriterion("models_channel_id between", value1, value2, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andModelsChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("models_channel_id not between", value1, value2, "modelsChannelId");
            return (Criteria) this;
        }


        public Criteria andModelsIsfixedPointIsNull() {
            addCriterion("models_isfixed_point is null");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointIsNotNull() {
            addCriterion("models_isfixed_point is not null");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointEqualTo(Integer value) {
            addCriterion("models_isfixed_point =", value, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointNotEqualTo(Integer value) {
            addCriterion("models_isfixed_point <>", value, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointGreaterThan(Integer value) {
            addCriterion("models_isfixed_point >", value, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("models_isfixed_point >=", value, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointLessThan(Integer value) {
            addCriterion("models_isfixed_point <", value, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointLessThanOrEqualTo(Integer value) {
            addCriterion("models_isfixed_point <=", value, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointIn(List<Integer> values) {
            addCriterion("models_isfixed_point in", values, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointNotIn(List<Integer> values) {
            addCriterion("models_isfixed_point not in", values, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointBetween(Integer value1, Integer value2) {
            addCriterion("models_isfixed_point between", value1, value2, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsIsfixedPointNotBetween(Integer value1, Integer value2) {
            addCriterion("models_isfixed_point not between", value1, value2, "modelsIsfixedPoint");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnIsNull() {
            addCriterion("models_fixed_return is null");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnIsNotNull() {
            addCriterion("models_fixed_return is not null");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnEqualTo(String value) {
            addCriterion("models_fixed_return =", value, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnNotEqualTo(String value) {
            addCriterion("models_fixed_return <>", value, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnGreaterThan(String value) {
            addCriterion("models_fixed_return >", value, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnGreaterThanOrEqualTo(String value) {
            addCriterion("models_fixed_return >=", value, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnLessThan(String value) {
            addCriterion("models_fixed_return <", value, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnLessThanOrEqualTo(String value) {
            addCriterion("models_fixed_return <=", value, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnLike(String value) {
            addCriterion("models_fixed_return like", value, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnNotLike(String value) {
            addCriterion("models_fixed_return not like", value, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnIn(List<String> values) {
            addCriterion("models_fixed_return in", values, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnNotIn(List<String> values) {
            addCriterion("models_fixed_return not in", values, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnBetween(String value1, String value2) {
            addCriterion("models_fixed_return between", value1, value2, "modelsFixedReturn");
            return (Criteria) this;
        }

        public Criteria andModelsFixedReturnNotBetween(String value1, String value2) {
            addCriterion("models_fixed_return not between", value1, value2, "modelsFixedReturn");
            return (Criteria) this;
        }
        
        public Criteria andModelsRentLimitIsNull() {
            addCriterion("models_rent_limit is null");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitIsNotNull() {
            addCriterion("models_rent_limit is not null");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitEqualTo(Integer value) {
            addCriterion("models_rent_limit =", value, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitNotEqualTo(Integer value) {
            addCriterion("models_rent_limit <>", value, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitGreaterThan(Integer value) {
            addCriterion("models_rent_limit >", value, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("models_rent_limit >=", value, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitLessThan(Integer value) {
            addCriterion("models_rent_limit <", value, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitLessThanOrEqualTo(Integer value) {
            addCriterion("models_rent_limit <=", value, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitIn(List<Integer> values) {
            addCriterion("models_rent_limit in", values, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitNotIn(List<Integer> values) {
            addCriterion("models_rent_limit not in", values, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitBetween(Integer value1, Integer value2) {
            addCriterion("models_rent_limit between", value1, value2, "modelsRentLimit");
            return (Criteria) this;
        }

        public Criteria andModelsRentLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("models_rent_limit not between", value1, value2, "modelsRentLimit");
            return (Criteria) this;
        }
        
        public Criteria andModelsFreeTimeIsNull() {
            addCriterion("models_free_time is null");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeIsNotNull() {
            addCriterion("models_free_time is not null");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeEqualTo(Integer value) {
            addCriterion("models_free_time =", value, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeNotEqualTo(Integer value) {
            addCriterion("models_free_time <>", value, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeGreaterThan(Integer value) {
            addCriterion("models_free_time >", value, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("models_free_time >=", value, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeLessThan(Integer value) {
            addCriterion("models_free_time <", value, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeLessThanOrEqualTo(Integer value) {
            addCriterion("models_free_time <=", value, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeIn(List<Integer> values) {
            addCriterion("models_free_time in", values, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeNotIn(List<Integer> values) {
            addCriterion("models_free_time not in", values, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeBetween(Integer value1, Integer value2) {
            addCriterion("models_free_time between", value1, value2, "modelsFreeTime");
            return (Criteria) this;
        }

        public Criteria andModelsFreeTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("models_free_time not between", value1, value2, "modelsFreeTime");
            return (Criteria) this;
        }
        
        public Criteria andModelsInpriceIdIsNull() {
            addCriterion("models_inPrice_id is null");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdIsNotNull() {
            addCriterion("models_inPrice_id is not null");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdEqualTo(Long value) {
            addCriterion("models_inPrice_id =", value, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdNotEqualTo(Long value) {
            addCriterion("models_inPrice_id <>", value, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdGreaterThan(Long value) {
            addCriterion("models_inPrice_id >", value, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("models_inPrice_id >=", value, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdLessThan(Long value) {
            addCriterion("models_inPrice_id <", value, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdLessThanOrEqualTo(Long value) {
            addCriterion("models_inPrice_id <=", value, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdIn(List<Long> values) {
            addCriterion("models_inPrice_id in", values, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdNotIn(List<Long> values) {
            addCriterion("models_inPrice_id not in", values, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdBetween(Long value1, Long value2) {
            addCriterion("models_inPrice_id between", value1, value2, "modelsInpriceId");
            return (Criteria) this;
        }

        public Criteria andModelsInpriceIdNotBetween(Long value1, Long value2) {
            addCriterion("models_inPrice_id not between", value1, value2, "modelsInpriceId");
            return (Criteria) this;
        }
        
        public Criteria andModelsRentTypeIsNull() {
            addCriterion("models_rent_type is null");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeIsNotNull() {
            addCriterion("models_rent_type is not null");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeEqualTo(Integer value) {
            addCriterion("models_rent_type =", value, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeNotEqualTo(Integer value) {
            addCriterion("models_rent_type <>", value, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeGreaterThan(Integer value) {
            addCriterion("models_rent_type >", value, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("models_rent_type >=", value, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeLessThan(Integer value) {
            addCriterion("models_rent_type <", value, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("models_rent_type <=", value, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeIn(List<Integer> values) {
            addCriterion("models_rent_type in", values, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeNotIn(List<Integer> values) {
            addCriterion("models_rent_type not in", values, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeBetween(Integer value1, Integer value2) {
            addCriterion("models_rent_type between", value1, value2, "modelsRentType");
            return (Criteria) this;
        }

        public Criteria andModelsRentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("models_rent_type not between", value1, value2, "modelsRentType");
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