package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TradeorderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TradeorderExample() {
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

        public Criteria andTrOrderIdIsNull() {
            addCriterion("tr_order_id is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdIsNotNull() {
            addCriterion("tr_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdEqualTo(Long value) {
            addCriterion("tr_order_id =", value, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdNotEqualTo(Long value) {
            addCriterion("tr_order_id <>", value, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdGreaterThan(Long value) {
            addCriterion("tr_order_id >", value, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tr_order_id >=", value, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdLessThan(Long value) {
            addCriterion("tr_order_id <", value, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("tr_order_id <=", value, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdIn(List<Long> values) {
            addCriterion("tr_order_id in", values, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdNotIn(List<Long> values) {
            addCriterion("tr_order_id not in", values, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdBetween(Long value1, Long value2) {
            addCriterion("tr_order_id between", value1, value2, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("tr_order_id not between", value1, value2, "trOrderId");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberIsNull() {
            addCriterion("tr_order_transaction_number is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberIsNotNull() {
            addCriterion("tr_order_transaction_number is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberEqualTo(String value) {
            addCriterion("tr_order_transaction_number =", value, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberNotEqualTo(String value) {
            addCriterion("tr_order_transaction_number <>", value, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberGreaterThan(String value) {
            addCriterion("tr_order_transaction_number >", value, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("tr_order_transaction_number >=", value, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberLessThan(String value) {
            addCriterion("tr_order_transaction_number <", value, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberLessThanOrEqualTo(String value) {
            addCriterion("tr_order_transaction_number <=", value, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberLike(String value) {
            addCriterion("tr_order_transaction_number like", value, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberNotLike(String value) {
            addCriterion("tr_order_transaction_number not like", value, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberIn(List<String> values) {
            addCriterion("tr_order_transaction_number in", values, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberNotIn(List<String> values) {
            addCriterion("tr_order_transaction_number not in", values, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberBetween(String value1, String value2) {
            addCriterion("tr_order_transaction_number between", value1, value2, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderTransactionNumberNotBetween(String value1, String value2) {
            addCriterion("tr_order_transaction_number not between", value1, value2, "trOrderTransactionNumber");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridIsNull() {
            addCriterion("tr_order_userId is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridIsNotNull() {
            addCriterion("tr_order_userId is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridEqualTo(Long value) {
            addCriterion("tr_order_userId =", value, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridNotEqualTo(Long value) {
            addCriterion("tr_order_userId <>", value, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridGreaterThan(Long value) {
            addCriterion("tr_order_userId >", value, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("tr_order_userId >=", value, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridLessThan(Long value) {
            addCriterion("tr_order_userId <", value, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridLessThanOrEqualTo(Long value) {
            addCriterion("tr_order_userId <=", value, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridIn(List<Long> values) {
            addCriterion("tr_order_userId in", values, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridNotIn(List<Long> values) {
            addCriterion("tr_order_userId not in", values, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridBetween(Long value1, Long value2) {
            addCriterion("tr_order_userId between", value1, value2, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderUseridNotBetween(Long value1, Long value2) {
            addCriterion("tr_order_userId not between", value1, value2, "trOrderUserid");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeIsNull() {
            addCriterion("tr_order_createtime is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeIsNotNull() {
            addCriterion("tr_order_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeEqualTo(Date value) {
            addCriterion("tr_order_createtime =", value, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeNotEqualTo(Date value) {
            addCriterion("tr_order_createtime <>", value, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeGreaterThan(Date value) {
            addCriterion("tr_order_createtime >", value, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tr_order_createtime >=", value, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeLessThan(Date value) {
            addCriterion("tr_order_createtime <", value, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("tr_order_createtime <=", value, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeIn(List<Date> values) {
            addCriterion("tr_order_createtime in", values, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeNotIn(List<Date> values) {
            addCriterion("tr_order_createtime not in", values, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeBetween(Date value1, Date value2) {
            addCriterion("tr_order_createtime between", value1, value2, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("tr_order_createtime not between", value1, value2, "trOrderCreatetime");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyIsNull() {
            addCriterion("tr_order_money is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyIsNotNull() {
            addCriterion("tr_order_money is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyEqualTo(BigDecimal value) {
            addCriterion("tr_order_money =", value, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyNotEqualTo(BigDecimal value) {
            addCriterion("tr_order_money <>", value, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyGreaterThan(BigDecimal value) {
            addCriterion("tr_order_money >", value, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tr_order_money >=", value, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyLessThan(BigDecimal value) {
            addCriterion("tr_order_money <", value, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tr_order_money <=", value, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyIn(List<BigDecimal> values) {
            addCriterion("tr_order_money in", values, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyNotIn(List<BigDecimal> values) {
            addCriterion("tr_order_money not in", values, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tr_order_money between", value1, value2, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tr_order_money not between", value1, value2, "trOrderMoney");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeIsNull() {
            addCriterion("tr_order_paytime is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeIsNotNull() {
            addCriterion("tr_order_paytime is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeEqualTo(Date value) {
            addCriterion("tr_order_paytime =", value, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeNotEqualTo(Date value) {
            addCriterion("tr_order_paytime <>", value, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeGreaterThan(Date value) {
            addCriterion("tr_order_paytime >", value, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tr_order_paytime >=", value, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeLessThan(Date value) {
            addCriterion("tr_order_paytime <", value, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeLessThanOrEqualTo(Date value) {
            addCriterion("tr_order_paytime <=", value, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeIn(List<Date> values) {
            addCriterion("tr_order_paytime in", values, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeNotIn(List<Date> values) {
            addCriterion("tr_order_paytime not in", values, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeBetween(Date value1, Date value2) {
            addCriterion("tr_order_paytime between", value1, value2, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderPaytimeNotBetween(Date value1, Date value2) {
            addCriterion("tr_order_paytime not between", value1, value2, "trOrderPaytime");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateIsNull() {
            addCriterion("tr_order_state is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateIsNotNull() {
            addCriterion("tr_order_state is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateEqualTo(Short value) {
            addCriterion("tr_order_state =", value, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateNotEqualTo(Short value) {
            addCriterion("tr_order_state <>", value, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateGreaterThan(Short value) {
            addCriterion("tr_order_state >", value, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateGreaterThanOrEqualTo(Short value) {
            addCriterion("tr_order_state >=", value, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateLessThan(Short value) {
            addCriterion("tr_order_state <", value, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateLessThanOrEqualTo(Short value) {
            addCriterion("tr_order_state <=", value, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateIn(List<Short> values) {
            addCriterion("tr_order_state in", values, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateNotIn(List<Short> values) {
            addCriterion("tr_order_state not in", values, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateBetween(Short value1, Short value2) {
            addCriterion("tr_order_state between", value1, value2, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderStateNotBetween(Short value1, Short value2) {
            addCriterion("tr_order_state not between", value1, value2, "trOrderState");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionIsNull() {
            addCriterion("tr_order_description is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionIsNotNull() {
            addCriterion("tr_order_description is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionEqualTo(String value) {
            addCriterion("tr_order_description =", value, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionNotEqualTo(String value) {
            addCriterion("tr_order_description <>", value, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionGreaterThan(String value) {
            addCriterion("tr_order_description >", value, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("tr_order_description >=", value, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionLessThan(String value) {
            addCriterion("tr_order_description <", value, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionLessThanOrEqualTo(String value) {
            addCriterion("tr_order_description <=", value, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionLike(String value) {
            addCriterion("tr_order_description like", value, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionNotLike(String value) {
            addCriterion("tr_order_description not like", value, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionIn(List<String> values) {
            addCriterion("tr_order_description in", values, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionNotIn(List<String> values) {
            addCriterion("tr_order_description not in", values, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionBetween(String value1, String value2) {
            addCriterion("tr_order_description between", value1, value2, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderDescriptionNotBetween(String value1, String value2) {
            addCriterion("tr_order_description not between", value1, value2, "trOrderDescription");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeIsNull() {
            addCriterion("tr_order_type is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeIsNotNull() {
            addCriterion("tr_order_type is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeEqualTo(Integer value) {
            addCriterion("tr_order_type =", value, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeNotEqualTo(Integer value) {
            addCriterion("tr_order_type <>", value, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeGreaterThan(Integer value) {
            addCriterion("tr_order_type >", value, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tr_order_type >=", value, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeLessThan(Integer value) {
            addCriterion("tr_order_type <", value, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("tr_order_type <=", value, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeIn(List<Integer> values) {
            addCriterion("tr_order_type in", values, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeNotIn(List<Integer> values) {
            addCriterion("tr_order_type not in", values, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("tr_order_type between", value1, value2, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tr_order_type not between", value1, value2, "trOrderType");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdIsNull() {
            addCriterion("tr_order_buyer_logon_id is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdIsNotNull() {
            addCriterion("tr_order_buyer_logon_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdEqualTo(String value) {
            addCriterion("tr_order_buyer_logon_id =", value, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdNotEqualTo(String value) {
            addCriterion("tr_order_buyer_logon_id <>", value, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdGreaterThan(String value) {
            addCriterion("tr_order_buyer_logon_id >", value, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdGreaterThanOrEqualTo(String value) {
            addCriterion("tr_order_buyer_logon_id >=", value, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdLessThan(String value) {
            addCriterion("tr_order_buyer_logon_id <", value, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdLessThanOrEqualTo(String value) {
            addCriterion("tr_order_buyer_logon_id <=", value, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdLike(String value) {
            addCriterion("tr_order_buyer_logon_id like", value, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdNotLike(String value) {
            addCriterion("tr_order_buyer_logon_id not like", value, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdIn(List<String> values) {
            addCriterion("tr_order_buyer_logon_id in", values, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdNotIn(List<String> values) {
            addCriterion("tr_order_buyer_logon_id not in", values, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdBetween(String value1, String value2) {
            addCriterion("tr_order_buyer_logon_id between", value1, value2, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderBuyerLogonIdNotBetween(String value1, String value2) {
            addCriterion("tr_order_buyer_logon_id not between", value1, value2, "trOrderBuyerLogonId");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionIsNull() {
            addCriterion("tr_order_option is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionIsNotNull() {
            addCriterion("tr_order_option is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionEqualTo(Integer value) {
            addCriterion("tr_order_option =", value, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionNotEqualTo(Integer value) {
            addCriterion("tr_order_option <>", value, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionGreaterThan(Integer value) {
            addCriterion("tr_order_option >", value, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("tr_order_option >=", value, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionLessThan(Integer value) {
            addCriterion("tr_order_option <", value, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionLessThanOrEqualTo(Integer value) {
            addCriterion("tr_order_option <=", value, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionIn(List<Integer> values) {
            addCriterion("tr_order_option in", values, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionNotIn(List<Integer> values) {
            addCriterion("tr_order_option not in", values, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionBetween(Integer value1, Integer value2) {
            addCriterion("tr_order_option between", value1, value2, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderOptionNotBetween(Integer value1, Integer value2) {
            addCriterion("tr_order_option not between", value1, value2, "trOrderOption");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdIsNull() {
            addCriterion("tr_order_rent_id is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdIsNotNull() {
            addCriterion("tr_order_rent_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdEqualTo(Long value) {
            addCriterion("tr_order_rent_id =", value, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdNotEqualTo(Long value) {
            addCriterion("tr_order_rent_id <>", value, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdGreaterThan(Long value) {
            addCriterion("tr_order_rent_id >", value, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tr_order_rent_id >=", value, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdLessThan(Long value) {
            addCriterion("tr_order_rent_id <", value, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdLessThanOrEqualTo(Long value) {
            addCriterion("tr_order_rent_id <=", value, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdIn(List<Long> values) {
            addCriterion("tr_order_rent_id in", values, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdNotIn(List<Long> values) {
            addCriterion("tr_order_rent_id not in", values, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdBetween(Long value1, Long value2) {
            addCriterion("tr_order_rent_id between", value1, value2, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentIdNotBetween(Long value1, Long value2) {
            addCriterion("tr_order_rent_id not between", value1, value2, "trOrderRentId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdIsNull() {
            addCriterion("tr_order_channel_Id is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdIsNotNull() {
            addCriterion("tr_order_channel_Id is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdEqualTo(Long value) {
            addCriterion("tr_order_channel_Id =", value, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdNotEqualTo(Long value) {
            addCriterion("tr_order_channel_Id <>", value, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdGreaterThan(Long value) {
            addCriterion("tr_order_channel_Id >", value, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tr_order_channel_Id >=", value, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdLessThan(Long value) {
            addCriterion("tr_order_channel_Id <", value, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("tr_order_channel_Id <=", value, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdIn(List<Long> values) {
            addCriterion("tr_order_channel_Id in", values, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdNotIn(List<Long> values) {
            addCriterion("tr_order_channel_Id not in", values, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdBetween(Long value1, Long value2) {
            addCriterion("tr_order_channel_Id between", value1, value2, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("tr_order_channel_Id not between", value1, value2, "trOrderChannelId");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidIsNull() {
            addCriterion("tr_order_invoiceId is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidIsNotNull() {
            addCriterion("tr_order_invoiceId is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidEqualTo(Long value) {
            addCriterion("tr_order_invoiceId =", value, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidNotEqualTo(Long value) {
            addCriterion("tr_order_invoiceId <>", value, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidGreaterThan(Long value) {
            addCriterion("tr_order_invoiceId >", value, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidGreaterThanOrEqualTo(Long value) {
            addCriterion("tr_order_invoiceId >=", value, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidLessThan(Long value) {
            addCriterion("tr_order_invoiceId <", value, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidLessThanOrEqualTo(Long value) {
            addCriterion("tr_order_invoiceId <=", value, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidIn(List<Long> values) {
            addCriterion("tr_order_invoiceId in", values, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidNotIn(List<Long> values) {
            addCriterion("tr_order_invoiceId not in", values, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidBetween(Long value1, Long value2) {
            addCriterion("tr_order_invoiceId between", value1, value2, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderInvoiceidNotBetween(Long value1, Long value2) {
            addCriterion("tr_order_invoiceId not between", value1, value2, "trOrderInvoiceid");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrIsNull() {
            addCriterion("tr_order_rentIdStr is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrIsNotNull() {
            addCriterion("tr_order_rentIdStr is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrEqualTo(String value) {
            addCriterion("tr_order_rentIdStr =", value, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrNotEqualTo(String value) {
            addCriterion("tr_order_rentIdStr <>", value, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrGreaterThan(String value) {
            addCriterion("tr_order_rentIdStr >", value, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrGreaterThanOrEqualTo(String value) {
            addCriterion("tr_order_rentIdStr >=", value, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrLessThan(String value) {
            addCriterion("tr_order_rentIdStr <", value, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrLessThanOrEqualTo(String value) {
            addCriterion("tr_order_rentIdStr <=", value, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrLike(String value) {
            addCriterion("tr_order_rentIdStr like", value, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrNotLike(String value) {
            addCriterion("tr_order_rentIdStr not like", value, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrIn(List<String> values) {
            addCriterion("tr_order_rentIdStr in", values, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrNotIn(List<String> values) {
            addCriterion("tr_order_rentIdStr not in", values, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrBetween(String value1, String value2) {
            addCriterion("tr_order_rentIdStr between", value1, value2, "trOrderRentidstr");
            return (Criteria) this;
        }

        public Criteria andTrOrderRentidstrNotBetween(String value1, String value2) {
            addCriterion("tr_order_rentIdStr not between", value1, value2, "trOrderRentidstr");
            return (Criteria) this;
        }
        public Criteria andTrOrderUserCouponIdIsNull() {
            addCriterion("tr_order_user_coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdIsNotNull() {
            addCriterion("tr_order_user_coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdEqualTo(Long value) {
            addCriterion("tr_order_user_coupon_id =", value, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdNotEqualTo(Long value) {
            addCriterion("tr_order_user_coupon_id <>", value, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdGreaterThan(Long value) {
            addCriterion("tr_order_user_coupon_id >", value, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tr_order_user_coupon_id >=", value, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdLessThan(Long value) {
            addCriterion("tr_order_user_coupon_id <", value, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdLessThanOrEqualTo(Long value) {
            addCriterion("tr_order_user_coupon_id <=", value, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdIn(List<Long> values) {
            addCriterion("tr_order_user_coupon_id in", values, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdNotIn(List<Long> values) {
            addCriterion("tr_order_user_coupon_id not in", values, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdBetween(Long value1, Long value2) {
            addCriterion("tr_order_user_coupon_id between", value1, value2, "trOrderUserCouponId");
            return (Criteria) this;
        }

        public Criteria andTrOrderUserCouponIdNotBetween(Long value1, Long value2) {
            addCriterion("tr_order_user_coupon_id not between", value1, value2, "trOrderUserCouponId");
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