package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RechargeRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public RechargeRecordExample() {
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

        public Criteria andRechargeIdIsNull() {
            addCriterion("recharge_id is null");
            return (Criteria) this;
        }

        public Criteria andRechargeIdIsNotNull() {
            addCriterion("recharge_id is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeIdEqualTo(Long value) {
            addCriterion("recharge_id =", value, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdNotEqualTo(Long value) {
            addCriterion("recharge_id <>", value, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdGreaterThan(Long value) {
            addCriterion("recharge_id >", value, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("recharge_id >=", value, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdLessThan(Long value) {
            addCriterion("recharge_id <", value, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdLessThanOrEqualTo(Long value) {
            addCriterion("recharge_id <=", value, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdIn(List<Long> values) {
            addCriterion("recharge_id in", values, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdNotIn(List<Long> values) {
            addCriterion("recharge_id not in", values, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdBetween(Long value1, Long value2) {
            addCriterion("recharge_id between", value1, value2, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeIdNotBetween(Long value1, Long value2) {
            addCriterion("recharge_id not between", value1, value2, "rechargeId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdIsNull() {
            addCriterion("recharge_account_id is null");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdIsNotNull() {
            addCriterion("recharge_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdEqualTo(Long value) {
            addCriterion("recharge_account_id =", value, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdNotEqualTo(Long value) {
            addCriterion("recharge_account_id <>", value, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdGreaterThan(Long value) {
            addCriterion("recharge_account_id >", value, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("recharge_account_id >=", value, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdLessThan(Long value) {
            addCriterion("recharge_account_id <", value, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("recharge_account_id <=", value, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdIn(List<Long> values) {
            addCriterion("recharge_account_id in", values, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdNotIn(List<Long> values) {
            addCriterion("recharge_account_id not in", values, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdBetween(Long value1, Long value2) {
            addCriterion("recharge_account_id between", value1, value2, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("recharge_account_id not between", value1, value2, "rechargeAccountId");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyIsNull() {
            addCriterion("recharge_money is null");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyIsNotNull() {
            addCriterion("recharge_money is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyEqualTo(BigDecimal value) {
            addCriterion("recharge_money =", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyNotEqualTo(BigDecimal value) {
            addCriterion("recharge_money <>", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyGreaterThan(BigDecimal value) {
            addCriterion("recharge_money >", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("recharge_money >=", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyLessThan(BigDecimal value) {
            addCriterion("recharge_money <", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("recharge_money <=", value, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyIn(List<BigDecimal> values) {
            addCriterion("recharge_money in", values, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyNotIn(List<BigDecimal> values) {
            addCriterion("recharge_money not in", values, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recharge_money between", value1, value2, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargeMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recharge_money not between", value1, value2, "rechargeMoney");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeIsNull() {
            addCriterion("recharge_pay_type is null");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeIsNotNull() {
            addCriterion("recharge_pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeEqualTo(Integer value) {
            addCriterion("recharge_pay_type =", value, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeNotEqualTo(Integer value) {
            addCriterion("recharge_pay_type <>", value, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeGreaterThan(Integer value) {
            addCriterion("recharge_pay_type >", value, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("recharge_pay_type >=", value, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeLessThan(Integer value) {
            addCriterion("recharge_pay_type <", value, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("recharge_pay_type <=", value, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeIn(List<Integer> values) {
            addCriterion("recharge_pay_type in", values, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeNotIn(List<Integer> values) {
            addCriterion("recharge_pay_type not in", values, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeBetween(Integer value1, Integer value2) {
            addCriterion("recharge_pay_type between", value1, value2, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargePayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("recharge_pay_type not between", value1, value2, "rechargePayType");
            return (Criteria) this;
        }

        public Criteria andRechargeStateIsNull() {
            addCriterion("recharge_state is null");
            return (Criteria) this;
        }

        public Criteria andRechargeStateIsNotNull() {
            addCriterion("recharge_state is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeStateEqualTo(Integer value) {
            addCriterion("recharge_state =", value, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateNotEqualTo(Integer value) {
            addCriterion("recharge_state <>", value, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateGreaterThan(Integer value) {
            addCriterion("recharge_state >", value, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("recharge_state >=", value, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateLessThan(Integer value) {
            addCriterion("recharge_state <", value, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateLessThanOrEqualTo(Integer value) {
            addCriterion("recharge_state <=", value, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateIn(List<Integer> values) {
            addCriterion("recharge_state in", values, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateNotIn(List<Integer> values) {
            addCriterion("recharge_state not in", values, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateBetween(Integer value1, Integer value2) {
            addCriterion("recharge_state between", value1, value2, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeStateNotBetween(Integer value1, Integer value2) {
            addCriterion("recharge_state not between", value1, value2, "rechargeState");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIsNull() {
            addCriterion("recharge_time is null");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIsNotNull() {
            addCriterion("recharge_time is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeEqualTo(Date value) {
            addCriterion("recharge_time =", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotEqualTo(Date value) {
            addCriterion("recharge_time <>", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeGreaterThan(Date value) {
            addCriterion("recharge_time >", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recharge_time >=", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeLessThan(Date value) {
            addCriterion("recharge_time <", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeLessThanOrEqualTo(Date value) {
            addCriterion("recharge_time <=", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIn(List<Date> values) {
            addCriterion("recharge_time in", values, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotIn(List<Date> values) {
            addCriterion("recharge_time not in", values, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeBetween(Date value1, Date value2) {
            addCriterion("recharge_time between", value1, value2, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotBetween(Date value1, Date value2) {
            addCriterion("recharge_time not between", value1, value2, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdIsNull() {
            addCriterion("recharge_order_id is null");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdIsNotNull() {
            addCriterion("recharge_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdEqualTo(String value) {
            addCriterion("recharge_order_id =", value, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdNotEqualTo(String value) {
            addCriterion("recharge_order_id <>", value, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdGreaterThan(String value) {
            addCriterion("recharge_order_id >", value, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("recharge_order_id >=", value, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdLessThan(String value) {
            addCriterion("recharge_order_id <", value, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdLessThanOrEqualTo(String value) {
            addCriterion("recharge_order_id <=", value, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdLike(String value) {
            addCriterion("recharge_order_id like", value, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdNotLike(String value) {
            addCriterion("recharge_order_id not like", value, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdIn(List<String> values) {
            addCriterion("recharge_order_id in", values, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdNotIn(List<String> values) {
            addCriterion("recharge_order_id not in", values, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdBetween(String value1, String value2) {
            addCriterion("recharge_order_id between", value1, value2, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeOrderIdNotBetween(String value1, String value2) {
            addCriterion("recharge_order_id not between", value1, value2, "rechargeOrderId");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceIsNull() {
            addCriterion("recharge_available_balance is null");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceIsNotNull() {
            addCriterion("recharge_available_balance is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceEqualTo(BigDecimal value) {
            addCriterion("recharge_available_balance =", value, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceNotEqualTo(BigDecimal value) {
            addCriterion("recharge_available_balance <>", value, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceGreaterThan(BigDecimal value) {
            addCriterion("recharge_available_balance >", value, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("recharge_available_balance >=", value, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceLessThan(BigDecimal value) {
            addCriterion("recharge_available_balance <", value, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("recharge_available_balance <=", value, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceIn(List<BigDecimal> values) {
            addCriterion("recharge_available_balance in", values, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceNotIn(List<BigDecimal> values) {
            addCriterion("recharge_available_balance not in", values, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recharge_available_balance between", value1, value2, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeAvailableBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recharge_available_balance not between", value1, value2, "rechargeAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeIsNull() {
            addCriterion("recharge_type is null");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeIsNotNull() {
            addCriterion("recharge_type is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeEqualTo(Integer value) {
            addCriterion("recharge_type =", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeNotEqualTo(Integer value) {
            addCriterion("recharge_type <>", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeGreaterThan(Integer value) {
            addCriterion("recharge_type >", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("recharge_type >=", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeLessThan(Integer value) {
            addCriterion("recharge_type <", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("recharge_type <=", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeIn(List<Integer> values) {
            addCriterion("recharge_type in", values, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeNotIn(List<Integer> values) {
            addCriterion("recharge_type not in", values, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeBetween(Integer value1, Integer value2) {
            addCriterion("recharge_type between", value1, value2, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("recharge_type not between", value1, value2, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoIsNull() {
            addCriterion("recharge_out_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoIsNotNull() {
            addCriterion("recharge_out_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoEqualTo(String value) {
            addCriterion("recharge_out_trade_no =", value, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoNotEqualTo(String value) {
            addCriterion("recharge_out_trade_no <>", value, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoGreaterThan(String value) {
            addCriterion("recharge_out_trade_no >", value, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("recharge_out_trade_no >=", value, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoLessThan(String value) {
            addCriterion("recharge_out_trade_no <", value, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoLessThanOrEqualTo(String value) {
            addCriterion("recharge_out_trade_no <=", value, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoLike(String value) {
            addCriterion("recharge_out_trade_no like", value, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoNotLike(String value) {
            addCriterion("recharge_out_trade_no not like", value, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoIn(List<String> values) {
            addCriterion("recharge_out_trade_no in", values, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoNotIn(List<String> values) {
            addCriterion("recharge_out_trade_no not in", values, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoBetween(String value1, String value2) {
            addCriterion("recharge_out_trade_no between", value1, value2, "rechargeOutTradeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeOutTradeNoNotBetween(String value1, String value2) {
            addCriterion("recharge_out_trade_no not between", value1, value2, "rechargeOutTradeNo");
            return (Criteria) this;
        }
        
        public Criteria andRechargeChannelIdIsNull() {
            addCriterion("recharge_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdIsNotNull() {
            addCriterion("recharge_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdEqualTo(Long value) {
            addCriterion("recharge_channel_id =", value, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdNotEqualTo(Long value) {
            addCriterion("recharge_channel_id <>", value, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdGreaterThan(Long value) {
            addCriterion("recharge_channel_id >", value, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("recharge_channel_id >=", value, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdLessThan(Long value) {
            addCriterion("recharge_channel_id <", value, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("recharge_channel_id <=", value, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdIn(List<Long> values) {
            addCriterion("recharge_channel_id in", values, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdNotIn(List<Long> values) {
            addCriterion("recharge_channel_id not in", values, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdBetween(Long value1, Long value2) {
            addCriterion("recharge_channel_id between", value1, value2, "rechargeChannelId");
            return (Criteria) this;
        }

        public Criteria andRechargeChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("recharge_channel_id not between", value1, value2, "rechargeChannelId");
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