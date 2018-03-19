package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public InsuranceExample() {
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

        public Criteria andInsuranceIdIsNull() {
            addCriterion("insurance_id is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdIsNotNull() {
            addCriterion("insurance_id is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdEqualTo(Long value) {
            addCriterion("insurance_id =", value, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdNotEqualTo(Long value) {
            addCriterion("insurance_id <>", value, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdGreaterThan(Long value) {
            addCriterion("insurance_id >", value, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("insurance_id >=", value, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdLessThan(Long value) {
            addCriterion("insurance_id <", value, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdLessThanOrEqualTo(Long value) {
            addCriterion("insurance_id <=", value, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdIn(List<Long> values) {
            addCriterion("insurance_id in", values, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdNotIn(List<Long> values) {
            addCriterion("insurance_id not in", values, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdBetween(Long value1, Long value2) {
            addCriterion("insurance_id between", value1, value2, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdNotBetween(Long value1, Long value2) {
            addCriterion("insurance_id not between", value1, value2, "insuranceId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumIsNull() {
            addCriterion("insurance_odd_num is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumIsNotNull() {
            addCriterion("insurance_odd_num is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumEqualTo(String value) {
            addCriterion("insurance_odd_num =", value, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumNotEqualTo(String value) {
            addCriterion("insurance_odd_num <>", value, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumGreaterThan(String value) {
            addCriterion("insurance_odd_num >", value, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_odd_num >=", value, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumLessThan(String value) {
            addCriterion("insurance_odd_num <", value, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumLessThanOrEqualTo(String value) {
            addCriterion("insurance_odd_num <=", value, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumLike(String value) {
            addCriterion("insurance_odd_num like", value, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumNotLike(String value) {
            addCriterion("insurance_odd_num not like", value, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumIn(List<String> values) {
            addCriterion("insurance_odd_num in", values, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumNotIn(List<String> values) {
            addCriterion("insurance_odd_num not in", values, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumBetween(String value1, String value2) {
            addCriterion("insurance_odd_num between", value1, value2, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceOddNumNotBetween(String value1, String value2) {
            addCriterion("insurance_odd_num not between", value1, value2, "insuranceOddNum");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdIsNull() {
            addCriterion("insurance_user_id is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdIsNotNull() {
            addCriterion("insurance_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdEqualTo(Long value) {
            addCriterion("insurance_user_id =", value, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdNotEqualTo(Long value) {
            addCriterion("insurance_user_id <>", value, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdGreaterThan(Long value) {
            addCriterion("insurance_user_id >", value, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("insurance_user_id >=", value, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdLessThan(Long value) {
            addCriterion("insurance_user_id <", value, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdLessThanOrEqualTo(Long value) {
            addCriterion("insurance_user_id <=", value, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdIn(List<Long> values) {
            addCriterion("insurance_user_id in", values, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdNotIn(List<Long> values) {
            addCriterion("insurance_user_id not in", values, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdBetween(Long value1, Long value2) {
            addCriterion("insurance_user_id between", value1, value2, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceUserIdNotBetween(Long value1, Long value2) {
            addCriterion("insurance_user_id not between", value1, value2, "insuranceUserId");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentIsNull() {
            addCriterion("insurance_content is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentIsNotNull() {
            addCriterion("insurance_content is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentEqualTo(String value) {
            addCriterion("insurance_content =", value, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentNotEqualTo(String value) {
            addCriterion("insurance_content <>", value, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentGreaterThan(String value) {
            addCriterion("insurance_content >", value, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_content >=", value, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentLessThan(String value) {
            addCriterion("insurance_content <", value, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentLessThanOrEqualTo(String value) {
            addCriterion("insurance_content <=", value, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentLike(String value) {
            addCriterion("insurance_content like", value, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentNotLike(String value) {
            addCriterion("insurance_content not like", value, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentIn(List<String> values) {
            addCriterion("insurance_content in", values, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentNotIn(List<String> values) {
            addCriterion("insurance_content not in", values, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentBetween(String value1, String value2) {
            addCriterion("insurance_content between", value1, value2, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceContentNotBetween(String value1, String value2) {
            addCriterion("insurance_content not between", value1, value2, "insuranceContent");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyIsNull() {
            addCriterion("insurance_money is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyIsNotNull() {
            addCriterion("insurance_money is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyEqualTo(BigDecimal value) {
            addCriterion("insurance_money =", value, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyNotEqualTo(BigDecimal value) {
            addCriterion("insurance_money <>", value, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyGreaterThan(BigDecimal value) {
            addCriterion("insurance_money >", value, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("insurance_money >=", value, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyLessThan(BigDecimal value) {
            addCriterion("insurance_money <", value, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("insurance_money <=", value, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyIn(List<BigDecimal> values) {
            addCriterion("insurance_money in", values, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyNotIn(List<BigDecimal> values) {
            addCriterion("insurance_money not in", values, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("insurance_money between", value1, value2, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("insurance_money not between", value1, value2, "insuranceMoney");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateIsNull() {
            addCriterion("insurance_state is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateIsNotNull() {
            addCriterion("insurance_state is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateEqualTo(Integer value) {
            addCriterion("insurance_state =", value, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateNotEqualTo(Integer value) {
            addCriterion("insurance_state <>", value, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateGreaterThan(Integer value) {
            addCriterion("insurance_state >", value, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("insurance_state >=", value, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateLessThan(Integer value) {
            addCriterion("insurance_state <", value, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateLessThanOrEqualTo(Integer value) {
            addCriterion("insurance_state <=", value, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateIn(List<Integer> values) {
            addCriterion("insurance_state in", values, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateNotIn(List<Integer> values) {
            addCriterion("insurance_state not in", values, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateBetween(Integer value1, Integer value2) {
            addCriterion("insurance_state between", value1, value2, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceStateNotBetween(Integer value1, Integer value2) {
            addCriterion("insurance_state not between", value1, value2, "insuranceState");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdIsNull() {
            addCriterion("insurance_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdIsNotNull() {
            addCriterion("insurance_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdEqualTo(Long value) {
            addCriterion("insurance_admin_id =", value, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdNotEqualTo(Long value) {
            addCriterion("insurance_admin_id <>", value, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdGreaterThan(Long value) {
            addCriterion("insurance_admin_id >", value, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("insurance_admin_id >=", value, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdLessThan(Long value) {
            addCriterion("insurance_admin_id <", value, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("insurance_admin_id <=", value, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdIn(List<Long> values) {
            addCriterion("insurance_admin_id in", values, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdNotIn(List<Long> values) {
            addCriterion("insurance_admin_id not in", values, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdBetween(Long value1, Long value2) {
            addCriterion("insurance_admin_id between", value1, value2, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("insurance_admin_id not between", value1, value2, "insuranceAdminId");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeIsNull() {
            addCriterion("insurance_deal_time is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeIsNotNull() {
            addCriterion("insurance_deal_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeEqualTo(Date value) {
            addCriterion("insurance_deal_time =", value, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeNotEqualTo(Date value) {
            addCriterion("insurance_deal_time <>", value, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeGreaterThan(Date value) {
            addCriterion("insurance_deal_time >", value, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insurance_deal_time >=", value, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeLessThan(Date value) {
            addCriterion("insurance_deal_time <", value, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeLessThanOrEqualTo(Date value) {
            addCriterion("insurance_deal_time <=", value, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeIn(List<Date> values) {
            addCriterion("insurance_deal_time in", values, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeNotIn(List<Date> values) {
            addCriterion("insurance_deal_time not in", values, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeBetween(Date value1, Date value2) {
            addCriterion("insurance_deal_time between", value1, value2, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceDealTimeNotBetween(Date value1, Date value2) {
            addCriterion("insurance_deal_time not between", value1, value2, "insuranceDealTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeIsNull() {
            addCriterion("insurance_apply_time is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeIsNotNull() {
            addCriterion("insurance_apply_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeEqualTo(Date value) {
            addCriterion("insurance_apply_time =", value, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeNotEqualTo(Date value) {
            addCriterion("insurance_apply_time <>", value, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeGreaterThan(Date value) {
            addCriterion("insurance_apply_time >", value, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insurance_apply_time >=", value, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeLessThan(Date value) {
            addCriterion("insurance_apply_time <", value, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("insurance_apply_time <=", value, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeIn(List<Date> values) {
            addCriterion("insurance_apply_time in", values, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeNotIn(List<Date> values) {
            addCriterion("insurance_apply_time not in", values, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeBetween(Date value1, Date value2) {
            addCriterion("insurance_apply_time between", value1, value2, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceApplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("insurance_apply_time not between", value1, value2, "insuranceApplyTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultIsNull() {
            addCriterion("insurance_result is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultIsNotNull() {
            addCriterion("insurance_result is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultEqualTo(String value) {
            addCriterion("insurance_result =", value, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultNotEqualTo(String value) {
            addCriterion("insurance_result <>", value, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultGreaterThan(String value) {
            addCriterion("insurance_result >", value, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_result >=", value, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultLessThan(String value) {
            addCriterion("insurance_result <", value, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultLessThanOrEqualTo(String value) {
            addCriterion("insurance_result <=", value, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultLike(String value) {
            addCriterion("insurance_result like", value, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultNotLike(String value) {
            addCriterion("insurance_result not like", value, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultIn(List<String> values) {
            addCriterion("insurance_result in", values, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultNotIn(List<String> values) {
            addCriterion("insurance_result not in", values, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultBetween(String value1, String value2) {
            addCriterion("insurance_result between", value1, value2, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultNotBetween(String value1, String value2) {
            addCriterion("insurance_result not between", value1, value2, "insuranceResult");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsIsNull() {
            addCriterion("insurance_accident_imgs is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsIsNotNull() {
            addCriterion("insurance_accident_imgs is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsEqualTo(String value) {
            addCriterion("insurance_accident_imgs =", value, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsNotEqualTo(String value) {
            addCriterion("insurance_accident_imgs <>", value, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsGreaterThan(String value) {
            addCriterion("insurance_accident_imgs >", value, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_accident_imgs >=", value, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsLessThan(String value) {
            addCriterion("insurance_accident_imgs <", value, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsLessThanOrEqualTo(String value) {
            addCriterion("insurance_accident_imgs <=", value, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsLike(String value) {
            addCriterion("insurance_accident_imgs like", value, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsNotLike(String value) {
            addCriterion("insurance_accident_imgs not like", value, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsIn(List<String> values) {
            addCriterion("insurance_accident_imgs in", values, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsNotIn(List<String> values) {
            addCriterion("insurance_accident_imgs not in", values, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsBetween(String value1, String value2) {
            addCriterion("insurance_accident_imgs between", value1, value2, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceAccidentImgsNotBetween(String value1, String value2) {
            addCriterion("insurance_accident_imgs not between", value1, value2, "insuranceAccidentImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsIsNull() {
            addCriterion("insurance_detail_imgs is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsIsNotNull() {
            addCriterion("insurance_detail_imgs is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsEqualTo(String value) {
            addCriterion("insurance_detail_imgs =", value, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsNotEqualTo(String value) {
            addCriterion("insurance_detail_imgs <>", value, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsGreaterThan(String value) {
            addCriterion("insurance_detail_imgs >", value, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_detail_imgs >=", value, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsLessThan(String value) {
            addCriterion("insurance_detail_imgs <", value, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsLessThanOrEqualTo(String value) {
            addCriterion("insurance_detail_imgs <=", value, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsLike(String value) {
            addCriterion("insurance_detail_imgs like", value, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsNotLike(String value) {
            addCriterion("insurance_detail_imgs not like", value, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsIn(List<String> values) {
            addCriterion("insurance_detail_imgs in", values, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsNotIn(List<String> values) {
            addCriterion("insurance_detail_imgs not in", values, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsBetween(String value1, String value2) {
            addCriterion("insurance_detail_imgs between", value1, value2, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceDetailImgsNotBetween(String value1, String value2) {
            addCriterion("insurance_detail_imgs not between", value1, value2, "insuranceDetailImgs");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateIsNull() {
            addCriterion("insurance_compensate is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateIsNotNull() {
            addCriterion("insurance_compensate is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateEqualTo(String value) {
            addCriterion("insurance_compensate =", value, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateNotEqualTo(String value) {
            addCriterion("insurance_compensate <>", value, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateGreaterThan(String value) {
            addCriterion("insurance_compensate >", value, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_compensate >=", value, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateLessThan(String value) {
            addCriterion("insurance_compensate <", value, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateLessThanOrEqualTo(String value) {
            addCriterion("insurance_compensate <=", value, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateLike(String value) {
            addCriterion("insurance_compensate like", value, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateNotLike(String value) {
            addCriterion("insurance_compensate not like", value, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateIn(List<String> values) {
            addCriterion("insurance_compensate in", values, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateNotIn(List<String> values) {
            addCriterion("insurance_compensate not in", values, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateBetween(String value1, String value2) {
            addCriterion("insurance_compensate between", value1, value2, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompensateNotBetween(String value1, String value2) {
            addCriterion("insurance_compensate not between", value1, value2, "insuranceCompensate");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactIsNull() {
            addCriterion("insurance_identity_contact is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactIsNotNull() {
            addCriterion("insurance_identity_contact is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactEqualTo(String value) {
            addCriterion("insurance_identity_contact =", value, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactNotEqualTo(String value) {
            addCriterion("insurance_identity_contact <>", value, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactGreaterThan(String value) {
            addCriterion("insurance_identity_contact >", value, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_identity_contact >=", value, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactLessThan(String value) {
            addCriterion("insurance_identity_contact <", value, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactLessThanOrEqualTo(String value) {
            addCriterion("insurance_identity_contact <=", value, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactLike(String value) {
            addCriterion("insurance_identity_contact like", value, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactNotLike(String value) {
            addCriterion("insurance_identity_contact not like", value, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactIn(List<String> values) {
            addCriterion("insurance_identity_contact in", values, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactNotIn(List<String> values) {
            addCriterion("insurance_identity_contact not in", values, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactBetween(String value1, String value2) {
            addCriterion("insurance_identity_contact between", value1, value2, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceIdentityContactNotBetween(String value1, String value2) {
            addCriterion("insurance_identity_contact not between", value1, value2, "insuranceIdentityContact");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveIsNull() {
            addCriterion("insurance_result_prove is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveIsNotNull() {
            addCriterion("insurance_result_prove is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveEqualTo(String value) {
            addCriterion("insurance_result_prove =", value, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveNotEqualTo(String value) {
            addCriterion("insurance_result_prove <>", value, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveGreaterThan(String value) {
            addCriterion("insurance_result_prove >", value, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_result_prove >=", value, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveLessThan(String value) {
            addCriterion("insurance_result_prove <", value, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveLessThanOrEqualTo(String value) {
            addCriterion("insurance_result_prove <=", value, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveLike(String value) {
            addCriterion("insurance_result_prove like", value, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveNotLike(String value) {
            addCriterion("insurance_result_prove not like", value, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveIn(List<String> values) {
            addCriterion("insurance_result_prove in", values, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveNotIn(List<String> values) {
            addCriterion("insurance_result_prove not in", values, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveBetween(String value1, String value2) {
            addCriterion("insurance_result_prove between", value1, value2, "insuranceResultProve");
            return (Criteria) this;
        }

        public Criteria andInsuranceResultProveNotBetween(String value1, String value2) {
            addCriterion("insurance_result_prove not between", value1, value2, "insuranceResultProve");
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