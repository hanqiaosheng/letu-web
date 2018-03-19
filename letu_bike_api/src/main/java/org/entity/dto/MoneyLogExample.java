package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





public class MoneyLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public MoneyLogExample() {
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
        public Criteria andUserTelLike(String value) {
            addCriterion("user_tel like", value, "userTel");
            return (Criteria) this;
        }
        
        public Criteria andUserNameLike(String value) {
            addCriterion("user_realname like", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdIsNull() {
            addCriterion("money_log_id is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdIsNotNull() {
            addCriterion("money_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdEqualTo(Long value) {
            addCriterion("money_log_id =", value, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdNotEqualTo(Long value) {
            addCriterion("money_log_id <>", value, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdGreaterThan(Long value) {
            addCriterion("money_log_id >", value, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("money_log_id >=", value, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdLessThan(Long value) {
            addCriterion("money_log_id <", value, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdLessThanOrEqualTo(Long value) {
            addCriterion("money_log_id <=", value, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdIn(List<Long> values) {
            addCriterion("money_log_id in", values, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdNotIn(List<Long> values) {
            addCriterion("money_log_id not in", values, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdBetween(Long value1, Long value2) {
            addCriterion("money_log_id between", value1, value2, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIdNotBetween(Long value1, Long value2) {
            addCriterion("money_log_id not between", value1, value2, "moneyLogId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdIsNull() {
            addCriterion("money_log_account_id is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdIsNotNull() {
            addCriterion("money_log_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdEqualTo(Long value) {
            addCriterion("money_log_account_id =", value, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdNotEqualTo(Long value) {
            addCriterion("money_log_account_id <>", value, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdGreaterThan(Long value) {
            addCriterion("money_log_account_id >", value, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("money_log_account_id >=", value, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdLessThan(Long value) {
            addCriterion("money_log_account_id <", value, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("money_log_account_id <=", value, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdIn(List<Long> values) {
            addCriterion("money_log_account_id in", values, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdNotIn(List<Long> values) {
            addCriterion("money_log_account_id not in", values, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdBetween(Long value1, Long value2) {
            addCriterion("money_log_account_id between", value1, value2, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("money_log_account_id not between", value1, value2, "moneyLogAccountId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeIsNull() {
            addCriterion("money_log_stream_type is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeIsNotNull() {
            addCriterion("money_log_stream_type is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeEqualTo(Integer value) {
            addCriterion("money_log_stream_type =", value, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeNotEqualTo(Integer value) {
            addCriterion("money_log_stream_type <>", value, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeGreaterThan(Integer value) {
            addCriterion("money_log_stream_type >", value, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_log_stream_type >=", value, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeLessThan(Integer value) {
            addCriterion("money_log_stream_type <", value, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeLessThanOrEqualTo(Integer value) {
            addCriterion("money_log_stream_type <=", value, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeIn(List<Integer> values) {
            addCriterion("money_log_stream_type in", values, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeNotIn(List<Integer> values) {
            addCriterion("money_log_stream_type not in", values, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeBetween(Integer value1, Integer value2) {
            addCriterion("money_log_stream_type between", value1, value2, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStreamTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("money_log_stream_type not between", value1, value2, "moneyLogStreamType");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyIsNull() {
            addCriterion("money_log_opreate_money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyIsNotNull() {
            addCriterion("money_log_opreate_money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyEqualTo(BigDecimal value) {
            addCriterion("money_log_opreate_money =", value, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money_log_opreate_money <>", value, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyGreaterThan(BigDecimal value) {
            addCriterion("money_log_opreate_money >", value, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money_log_opreate_money >=", value, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyLessThan(BigDecimal value) {
            addCriterion("money_log_opreate_money <", value, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money_log_opreate_money <=", value, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyIn(List<BigDecimal> values) {
            addCriterion("money_log_opreate_money in", values, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money_log_opreate_money not in", values, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money_log_opreate_money between", value1, value2, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money_log_opreate_money not between", value1, value2, "moneyLogOpreateMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositIsNull() {
            addCriterion("money_log_deposit is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositIsNotNull() {
            addCriterion("money_log_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositEqualTo(BigDecimal value) {
            addCriterion("money_log_deposit =", value, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositNotEqualTo(BigDecimal value) {
            addCriterion("money_log_deposit <>", value, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositGreaterThan(BigDecimal value) {
            addCriterion("money_log_deposit >", value, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money_log_deposit >=", value, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositLessThan(BigDecimal value) {
            addCriterion("money_log_deposit <", value, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money_log_deposit <=", value, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositIn(List<BigDecimal> values) {
            addCriterion("money_log_deposit in", values, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositNotIn(List<BigDecimal> values) {
            addCriterion("money_log_deposit not in", values, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money_log_deposit between", value1, value2, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money_log_deposit not between", value1, value2, "moneyLogDeposit");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeIsNull() {
            addCriterion("money_log_create_time is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeIsNotNull() {
            addCriterion("money_log_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeEqualTo(Date value) {
            addCriterion("money_log_create_time =", value, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeNotEqualTo(Date value) {
            addCriterion("money_log_create_time <>", value, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeGreaterThan(Date value) {
            addCriterion("money_log_create_time >", value, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("money_log_create_time >=", value, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeLessThan(Date value) {
            addCriterion("money_log_create_time <", value, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("money_log_create_time <=", value, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeIn(List<Date> values) {
            addCriterion("money_log_create_time in", values, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeNotIn(List<Date> values) {
            addCriterion("money_log_create_time not in", values, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeBetween(Date value1, Date value2) {
            addCriterion("money_log_create_time between", value1, value2, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("money_log_create_time not between", value1, value2, "moneyLogCreateTime");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkIsNull() {
            addCriterion("money_log_remark is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkIsNotNull() {
            addCriterion("money_log_remark is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkEqualTo(String value) {
            addCriterion("money_log_remark =", value, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkNotEqualTo(String value) {
            addCriterion("money_log_remark <>", value, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkGreaterThan(String value) {
            addCriterion("money_log_remark >", value, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("money_log_remark >=", value, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkLessThan(String value) {
            addCriterion("money_log_remark <", value, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkLessThanOrEqualTo(String value) {
            addCriterion("money_log_remark <=", value, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkLike(String value) {
            addCriterion("money_log_remark like", value, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkNotLike(String value) {
            addCriterion("money_log_remark not like", value, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkIn(List<String> values) {
            addCriterion("money_log_remark in", values, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkNotIn(List<String> values) {
            addCriterion("money_log_remark not in", values, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkBetween(String value1, String value2) {
            addCriterion("money_log_remark between", value1, value2, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRemarkNotBetween(String value1, String value2) {
            addCriterion("money_log_remark not between", value1, value2, "moneyLogRemark");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpIsNull() {
            addCriterion("money_log_IP is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpIsNotNull() {
            addCriterion("money_log_IP is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpEqualTo(String value) {
            addCriterion("money_log_IP =", value, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpNotEqualTo(String value) {
            addCriterion("money_log_IP <>", value, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpGreaterThan(String value) {
            addCriterion("money_log_IP >", value, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpGreaterThanOrEqualTo(String value) {
            addCriterion("money_log_IP >=", value, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpLessThan(String value) {
            addCriterion("money_log_IP <", value, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpLessThanOrEqualTo(String value) {
            addCriterion("money_log_IP <=", value, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpLike(String value) {
            addCriterion("money_log_IP like", value, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpNotLike(String value) {
            addCriterion("money_log_IP not like", value, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpIn(List<String> values) {
            addCriterion("money_log_IP in", values, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpNotIn(List<String> values) {
            addCriterion("money_log_IP not in", values, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpBetween(String value1, String value2) {
            addCriterion("money_log_IP between", value1, value2, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIpNotBetween(String value1, String value2) {
            addCriterion("money_log_IP not between", value1, value2, "moneyLogIp");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdIsNull() {
            addCriterion("money_log_opreate_id is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdIsNotNull() {
            addCriterion("money_log_opreate_id is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdEqualTo(Long value) {
            addCriterion("money_log_opreate_id =", value, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdNotEqualTo(Long value) {
            addCriterion("money_log_opreate_id <>", value, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdGreaterThan(Long value) {
            addCriterion("money_log_opreate_id >", value, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("money_log_opreate_id >=", value, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdLessThan(Long value) {
            addCriterion("money_log_opreate_id <", value, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdLessThanOrEqualTo(Long value) {
            addCriterion("money_log_opreate_id <=", value, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdIn(List<Long> values) {
            addCriterion("money_log_opreate_id in", values, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdNotIn(List<Long> values) {
            addCriterion("money_log_opreate_id not in", values, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdBetween(Long value1, Long value2) {
            addCriterion("money_log_opreate_id between", value1, value2, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOpreateIdNotBetween(Long value1, Long value2) {
            addCriterion("money_log_opreate_id not between", value1, value2, "moneyLogOpreateId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateIsNull() {
            addCriterion("money_log_state is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateIsNotNull() {
            addCriterion("money_log_state is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateEqualTo(Integer value) {
            addCriterion("money_log_state =", value, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateNotEqualTo(Integer value) {
            addCriterion("money_log_state <>", value, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateGreaterThan(Integer value) {
            addCriterion("money_log_state >", value, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_log_state >=", value, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateLessThan(Integer value) {
            addCriterion("money_log_state <", value, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateLessThanOrEqualTo(Integer value) {
            addCriterion("money_log_state <=", value, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateIn(List<Integer> values) {
            addCriterion("money_log_state in", values, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateNotIn(List<Integer> values) {
            addCriterion("money_log_state not in", values, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateBetween(Integer value1, Integer value2) {
            addCriterion("money_log_state between", value1, value2, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogStateNotBetween(Integer value1, Integer value2) {
            addCriterion("money_log_state not between", value1, value2, "moneyLogState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderIsNull() {
            addCriterion("money_log_order is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderIsNotNull() {
            addCriterion("money_log_order is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderEqualTo(String value) {
            addCriterion("money_log_order =", value, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderNotEqualTo(String value) {
            addCriterion("money_log_order <>", value, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderGreaterThan(String value) {
            addCriterion("money_log_order >", value, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderGreaterThanOrEqualTo(String value) {
            addCriterion("money_log_order >=", value, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderLessThan(String value) {
            addCriterion("money_log_order <", value, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderLessThanOrEqualTo(String value) {
            addCriterion("money_log_order <=", value, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderLike(String value) {
            addCriterion("money_log_order like", value, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderNotLike(String value) {
            addCriterion("money_log_order not like", value, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderIn(List<String> values) {
            addCriterion("money_log_order in", values, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderNotIn(List<String> values) {
            addCriterion("money_log_order not in", values, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderBetween(String value1, String value2) {
            addCriterion("money_log_order between", value1, value2, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOrderNotBetween(String value1, String value2) {
            addCriterion("money_log_order not between", value1, value2, "moneyLogOrder");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeIsNull() {
            addCriterion("money_log_out_trade is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeIsNotNull() {
            addCriterion("money_log_out_trade is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeEqualTo(String value) {
            addCriterion("money_log_out_trade =", value, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeNotEqualTo(String value) {
            addCriterion("money_log_out_trade <>", value, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeGreaterThan(String value) {
            addCriterion("money_log_out_trade >", value, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeGreaterThanOrEqualTo(String value) {
            addCriterion("money_log_out_trade >=", value, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeLessThan(String value) {
            addCriterion("money_log_out_trade <", value, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeLessThanOrEqualTo(String value) {
            addCriterion("money_log_out_trade <=", value, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeLike(String value) {
            addCriterion("money_log_out_trade like", value, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeNotLike(String value) {
            addCriterion("money_log_out_trade not like", value, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeIn(List<String> values) {
            addCriterion("money_log_out_trade in", values, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeNotIn(List<String> values) {
            addCriterion("money_log_out_trade not in", values, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeBetween(String value1, String value2) {
            addCriterion("money_log_out_trade between", value1, value2, "moneyLogOutTrade");
            return (Criteria) this;
        }

        public Criteria andMoneyLogOutTradeNotBetween(String value1, String value2) {
            addCriterion("money_log_out_trade not between", value1, value2, "moneyLogOutTrade");
            return (Criteria) this;
        }
        
        public Criteria andMoneyLogRefundOpreateIsNull() {
            addCriterion("money_log_refund_opreate is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateIsNotNull() {
            addCriterion("money_log_refund_opreate is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateEqualTo(Long value) {
            addCriterion("money_log_refund_opreate =", value, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateNotEqualTo(Long value) {
            addCriterion("money_log_refund_opreate <>", value, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateGreaterThan(Long value) {
            addCriterion("money_log_refund_opreate >", value, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateGreaterThanOrEqualTo(Long value) {
            addCriterion("money_log_refund_opreate >=", value, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateLessThan(Long value) {
            addCriterion("money_log_refund_opreate <", value, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateLessThanOrEqualTo(Long value) {
            addCriterion("money_log_refund_opreate <=", value, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateIn(List<Long> values) {
            addCriterion("money_log_refund_opreate in", values, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateNotIn(List<Long> values) {
            addCriterion("money_log_refund_opreate not in", values, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateBetween(Long value1, Long value2) {
            addCriterion("money_log_refund_opreate between", value1, value2, "moneyLogRefundOpreate");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundOpreateNotBetween(Long value1, Long value2) {
            addCriterion("money_log_refund_opreate not between", value1, value2, "moneyLogRefundOpreate");
            return (Criteria) this;
        }
        public Criteria andMoneyLogIsvillagerIsNull() {
            addCriterion("money_log_isvillager is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerIsNotNull() {
            addCriterion("money_log_isvillager is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerEqualTo(Integer value) {
            addCriterion("money_log_isvillager =", value, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerNotEqualTo(Integer value) {
            addCriterion("money_log_isvillager <>", value, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerGreaterThan(Integer value) {
            addCriterion("money_log_isvillager >", value, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_log_isvillager >=", value, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerLessThan(Integer value) {
            addCriterion("money_log_isvillager <", value, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerLessThanOrEqualTo(Integer value) {
            addCriterion("money_log_isvillager <=", value, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerIn(List<Integer> values) {
            addCriterion("money_log_isvillager in", values, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerNotIn(List<Integer> values) {
            addCriterion("money_log_isvillager not in", values, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerBetween(Integer value1, Integer value2) {
            addCriterion("money_log_isvillager between", value1, value2, "moneyLogIsvillager");
            return (Criteria) this;
        }

        public Criteria andMoneyLogIsvillagerNotBetween(Integer value1, Integer value2) {
            addCriterion("money_log_isvillager not between", value1, value2, "moneyLogIsvillager");
            return (Criteria) this;
        }
        public Criteria andMoneyLogRefundStateIsNull() {
            addCriterion("money_log_refund_state is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateIsNotNull() {
            addCriterion("money_log_refund_state is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateEqualTo(Integer value) {
            addCriterion("money_log_refund_state =", value, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateNotEqualTo(Integer value) {
            addCriterion("money_log_refund_state <>", value, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateGreaterThan(Integer value) {
            addCriterion("money_log_refund_state >", value, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_log_refund_state >=", value, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateLessThan(Integer value) {
            addCriterion("money_log_refund_state <", value, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateLessThanOrEqualTo(Integer value) {
            addCriterion("money_log_refund_state <=", value, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateIn(List<Integer> values) {
            addCriterion("money_log_refund_state in", values, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateNotIn(List<Integer> values) {
            addCriterion("money_log_refund_state not in", values, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateBetween(Integer value1, Integer value2) {
            addCriterion("money_log_refund_state between", value1, value2, "moneyLogRefundState");
            return (Criteria) this;
        }

        public Criteria andMoneyLogRefundStateNotBetween(Integer value1, Integer value2) {
            addCriterion("money_log_refund_state not between", value1, value2, "moneyLogRefundState");
            return (Criteria) this;
        }
        public Criteria andMoneyLogChannelIdIsNull() {
            addCriterion("money_log_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdIsNotNull() {
            addCriterion("money_log_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdEqualTo(Long value) {
            addCriterion("money_log_channel_id =", value, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdNotEqualTo(Long value) {
            addCriterion("money_log_channel_id <>", value, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdGreaterThan(Long value) {
            addCriterion("money_log_channel_id >", value, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("money_log_channel_id >=", value, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdLessThan(Long value) {
            addCriterion("money_log_channel_id <", value, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("money_log_channel_id <=", value, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdIn(List<Long> values) {
            addCriterion("money_log_channel_id in", values, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdNotIn(List<Long> values) {
            addCriterion("money_log_channel_id not in", values, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdBetween(Long value1, Long value2) {
            addCriterion("money_log_channel_id between", value1, value2, "moneyLogChannelId");
            return (Criteria) this;
        }

        public Criteria andMoneyLogChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("money_log_channel_id not between", value1, value2, "moneyLogChannelId");
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