package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class RefundExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public RefundExample() {
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
        
        /**
         * 用户手机账号
         * @param value
         * @return
         */
        public Criteria andUserTel(String value) {
            addCriterion("user_tel like", value, "userTel");
            return (Criteria) this;
        }
        
        /**
         * 操作人
         * @param value
         * @return
         */
        public Criteria andAdminName(String value) {
            addCriterion("admin_realname like", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andRefundIdIsNull() {
            addCriterion("refund_id is null");
            return (Criteria) this;
        }

        public Criteria andRefundIdIsNotNull() {
            addCriterion("refund_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefundIdEqualTo(Long value) {
            addCriterion("refund_id =", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdNotEqualTo(Long value) {
            addCriterion("refund_id <>", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdGreaterThan(Long value) {
            addCriterion("refund_id >", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_id >=", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdLessThan(Long value) {
            addCriterion("refund_id <", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdLessThanOrEqualTo(Long value) {
            addCriterion("refund_id <=", value, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdIn(List<Long> values) {
            addCriterion("refund_id in", values, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdNotIn(List<Long> values) {
            addCriterion("refund_id not in", values, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdBetween(Long value1, Long value2) {
            addCriterion("refund_id between", value1, value2, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundIdNotBetween(Long value1, Long value2) {
            addCriterion("refund_id not between", value1, value2, "refundId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdIsNull() {
            addCriterion("refund_account_id is null");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdIsNotNull() {
            addCriterion("refund_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdEqualTo(Long value) {
            addCriterion("refund_account_id =", value, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdNotEqualTo(Long value) {
            addCriterion("refund_account_id <>", value, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdGreaterThan(Long value) {
            addCriterion("refund_account_id >", value, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_account_id >=", value, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdLessThan(Long value) {
            addCriterion("refund_account_id <", value, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("refund_account_id <=", value, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdIn(List<Long> values) {
            addCriterion("refund_account_id in", values, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdNotIn(List<Long> values) {
            addCriterion("refund_account_id not in", values, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdBetween(Long value1, Long value2) {
            addCriterion("refund_account_id between", value1, value2, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("refund_account_id not between", value1, value2, "refundAccountId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdIsNull() {
            addCriterion("refund_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdIsNotNull() {
            addCriterion("refund_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdEqualTo(Long value) {
            addCriterion("refund_admin_id =", value, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdNotEqualTo(Long value) {
            addCriterion("refund_admin_id <>", value, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdGreaterThan(Long value) {
            addCriterion("refund_admin_id >", value, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_admin_id >=", value, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdLessThan(Long value) {
            addCriterion("refund_admin_id <", value, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("refund_admin_id <=", value, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdIn(List<Long> values) {
            addCriterion("refund_admin_id in", values, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdNotIn(List<Long> values) {
            addCriterion("refund_admin_id not in", values, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdBetween(Long value1, Long value2) {
            addCriterion("refund_admin_id between", value1, value2, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("refund_admin_id not between", value1, value2, "refundAdminId");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyIsNull() {
            addCriterion("refund_money is null");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyIsNotNull() {
            addCriterion("refund_money is not null");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyEqualTo(BigDecimal value) {
            addCriterion("refund_money =", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyNotEqualTo(BigDecimal value) {
            addCriterion("refund_money <>", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyGreaterThan(BigDecimal value) {
            addCriterion("refund_money >", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_money >=", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyLessThan(BigDecimal value) {
            addCriterion("refund_money <", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_money <=", value, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyIn(List<BigDecimal> values) {
            addCriterion("refund_money in", values, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyNotIn(List<BigDecimal> values) {
            addCriterion("refund_money not in", values, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_money between", value1, value2, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_money not between", value1, value2, "refundMoney");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeIsNull() {
            addCriterion("refund_operatetime is null");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeIsNotNull() {
            addCriterion("refund_operatetime is not null");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeEqualTo(Date value) {
            addCriterion("refund_operatetime =", value, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeNotEqualTo(Date value) {
            addCriterion("refund_operatetime <>", value, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeGreaterThan(Date value) {
            addCriterion("refund_operatetime >", value, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_operatetime >=", value, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeLessThan(Date value) {
            addCriterion("refund_operatetime <", value, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeLessThanOrEqualTo(Date value) {
            addCriterion("refund_operatetime <=", value, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeIn(List<Date> values) {
            addCriterion("refund_operatetime in", values, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeNotIn(List<Date> values) {
            addCriterion("refund_operatetime not in", values, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeBetween(Date value1, Date value2) {
            addCriterion("refund_operatetime between", value1, value2, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundOperatetimeNotBetween(Date value1, Date value2) {
            addCriterion("refund_operatetime not between", value1, value2, "refundOperatetime");
            return (Criteria) this;
        }

        public Criteria andRefundStateIsNull() {
            addCriterion("refund_state is null");
            return (Criteria) this;
        }

        public Criteria andRefundStateIsNotNull() {
            addCriterion("refund_state is not null");
            return (Criteria) this;
        }

        public Criteria andRefundStateEqualTo(Integer value) {
            addCriterion("refund_state =", value, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateNotEqualTo(Integer value) {
            addCriterion("refund_state <>", value, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateGreaterThan(Integer value) {
            addCriterion("refund_state >", value, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_state >=", value, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateLessThan(Integer value) {
            addCriterion("refund_state <", value, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateLessThanOrEqualTo(Integer value) {
            addCriterion("refund_state <=", value, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateIn(List<Integer> values) {
            addCriterion("refund_state in", values, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateNotIn(List<Integer> values) {
            addCriterion("refund_state not in", values, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateBetween(Integer value1, Integer value2) {
            addCriterion("refund_state between", value1, value2, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundStateNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_state not between", value1, value2, "refundState");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeIsNull() {
            addCriterion("refund_createtime is null");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeIsNotNull() {
            addCriterion("refund_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeEqualTo(Date value) {
            addCriterion("refund_createtime =", value, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeNotEqualTo(Date value) {
            addCriterion("refund_createtime <>", value, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeGreaterThan(Date value) {
            addCriterion("refund_createtime >", value, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_createtime >=", value, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeLessThan(Date value) {
            addCriterion("refund_createtime <", value, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("refund_createtime <=", value, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeIn(List<Date> values) {
            addCriterion("refund_createtime in", values, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeNotIn(List<Date> values) {
            addCriterion("refund_createtime not in", values, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeBetween(Date value1, Date value2) {
            addCriterion("refund_createtime between", value1, value2, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("refund_createtime not between", value1, value2, "refundCreatetime");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNull() {
            addCriterion("refund_type is null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNotNull() {
            addCriterion("refund_type is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeEqualTo(Integer value) {
            addCriterion("refund_type =", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotEqualTo(Integer value) {
            addCriterion("refund_type <>", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThan(Integer value) {
            addCriterion("refund_type >", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_type >=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThan(Integer value) {
            addCriterion("refund_type <", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThanOrEqualTo(Integer value) {
            addCriterion("refund_type <=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIn(List<Integer> values) {
            addCriterion("refund_type in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotIn(List<Integer> values) {
            addCriterion("refund_type not in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeBetween(Integer value1, Integer value2) {
            addCriterion("refund_type between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_type not between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundCodeIsNull() {
            addCriterion("refund_code is null");
            return (Criteria) this;
        }

        public Criteria andRefundCodeIsNotNull() {
            addCriterion("refund_code is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCodeEqualTo(String value) {
            addCriterion("refund_code =", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeNotEqualTo(String value) {
            addCriterion("refund_code <>", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeGreaterThan(String value) {
            addCriterion("refund_code >", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeGreaterThanOrEqualTo(String value) {
            addCriterion("refund_code >=", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeLessThan(String value) {
            addCriterion("refund_code <", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeLessThanOrEqualTo(String value) {
            addCriterion("refund_code <=", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeLike(String value) {
            addCriterion("refund_code like", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeNotLike(String value) {
            addCriterion("refund_code not like", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeIn(List<String> values) {
            addCriterion("refund_code in", values, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeNotIn(List<String> values) {
            addCriterion("refund_code not in", values, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeBetween(String value1, String value2) {
            addCriterion("refund_code between", value1, value2, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeNotBetween(String value1, String value2) {
            addCriterion("refund_code not between", value1, value2, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdIsNull() {
            addCriterion("refund_order_id is null");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdIsNotNull() {
            addCriterion("refund_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdEqualTo(String value) {
            addCriterion("refund_order_id =", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdNotEqualTo(String value) {
            addCriterion("refund_order_id <>", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdGreaterThan(String value) {
            addCriterion("refund_order_id >", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("refund_order_id >=", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdLessThan(String value) {
            addCriterion("refund_order_id <", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdLessThanOrEqualTo(String value) {
            addCriterion("refund_order_id <=", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdLike(String value) {
            addCriterion("refund_order_id like", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdNotLike(String value) {
            addCriterion("refund_order_id not like", value, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdIn(List<String> values) {
            addCriterion("refund_order_id in", values, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdNotIn(List<String> values) {
            addCriterion("refund_order_id not in", values, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdBetween(String value1, String value2) {
            addCriterion("refund_order_id between", value1, value2, "refundOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundOrderIdNotBetween(String value1, String value2) {
            addCriterion("refund_order_id not between", value1, value2, "refundOrderId");
            return (Criteria) this;
        }
        
        public Criteria andRefundRefuseReasonIsNull() {
            addCriterion("refund_refuse_reason is null");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonIsNotNull() {
            addCriterion("refund_refuse_reason is not null");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonEqualTo(String value) {
            addCriterion("refund_refuse_reason =", value, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonNotEqualTo(String value) {
            addCriterion("refund_refuse_reason <>", value, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonGreaterThan(String value) {
            addCriterion("refund_refuse_reason >", value, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("refund_refuse_reason >=", value, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonLessThan(String value) {
            addCriterion("refund_refuse_reason <", value, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonLessThanOrEqualTo(String value) {
            addCriterion("refund_refuse_reason <=", value, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonLike(String value) {
            addCriterion("refund_refuse_reason like", value, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonNotLike(String value) {
            addCriterion("refund_refuse_reason not like", value, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonIn(List<String> values) {
            addCriterion("refund_refuse_reason in", values, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonNotIn(List<String> values) {
            addCriterion("refund_refuse_reason not in", values, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonBetween(String value1, String value2) {
            addCriterion("refund_refuse_reason between", value1, value2, "refundRefuseReason");
            return (Criteria) this;
        }

        public Criteria andRefundRefuseReasonNotBetween(String value1, String value2) {
            addCriterion("refund_refuse_reason not between", value1, value2, "refundRefuseReason");
            return (Criteria) this;
        }
        
        public Criteria andRefundSourceIsNull() {
            addCriterion("refund_source is null");
            return (Criteria) this;
        }

        public Criteria andRefundSourceIsNotNull() {
            addCriterion("refund_source is not null");
            return (Criteria) this;
        }

        public Criteria andRefundSourceEqualTo(Integer value) {
            addCriterion("refund_source =", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceNotEqualTo(Integer value) {
            addCriterion("refund_source <>", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceGreaterThan(Integer value) {
            addCriterion("refund_source >", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_source >=", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceLessThan(Integer value) {
            addCriterion("refund_source <", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceLessThanOrEqualTo(Integer value) {
            addCriterion("refund_source <=", value, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceIn(List<Integer> values) {
            addCriterion("refund_source in", values, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceNotIn(List<Integer> values) {
            addCriterion("refund_source not in", values, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceBetween(Integer value1, Integer value2) {
            addCriterion("refund_source between", value1, value2, "refundSource");
            return (Criteria) this;
        }

        public Criteria andRefundSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_source not between", value1, value2, "refundSource");
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