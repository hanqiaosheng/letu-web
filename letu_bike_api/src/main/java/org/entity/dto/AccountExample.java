package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.entity.dto.FeedbackExample.Criteria;



public class AccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public AccountExample() {
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

        //用户姓名
        public Criteria andUserNameLike(String value) {
            addCriterion("user_realname like", value, "userRealname");
            return (Criteria) this;
        }
        
        //用户手机号
        public Criteria andUserTelLike(String value) {
            addCriterion("user_tel like", value, "userTel");
            return (Criteria) this;
        }
        
        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Long value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Long value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Long value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Long value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Long> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Long> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Long value1, Long value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdIsNull() {
            addCriterion("account_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdIsNotNull() {
            addCriterion("account_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdEqualTo(Long value) {
            addCriterion("account_user_id =", value, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdNotEqualTo(Long value) {
            addCriterion("account_user_id <>", value, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdGreaterThan(Long value) {
            addCriterion("account_user_id >", value, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_user_id >=", value, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdLessThan(Long value) {
            addCriterion("account_user_id <", value, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdLessThanOrEqualTo(Long value) {
            addCriterion("account_user_id <=", value, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdIn(List<Long> values) {
            addCriterion("account_user_id in", values, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdNotIn(List<Long> values) {
            addCriterion("account_user_id not in", values, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdBetween(Long value1, Long value2) {
            addCriterion("account_user_id between", value1, value2, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountUserIdNotBetween(Long value1, Long value2) {
            addCriterion("account_user_id not between", value1, value2, "accountUserId");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyIsNull() {
            addCriterion("account_totalmoney is null");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyIsNotNull() {
            addCriterion("account_totalmoney is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyEqualTo(BigDecimal value) {
            addCriterion("account_totalmoney =", value, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyNotEqualTo(BigDecimal value) {
            addCriterion("account_totalmoney <>", value, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyGreaterThan(BigDecimal value) {
            addCriterion("account_totalmoney >", value, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_totalmoney >=", value, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyLessThan(BigDecimal value) {
            addCriterion("account_totalmoney <", value, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_totalmoney <=", value, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyIn(List<BigDecimal> values) {
            addCriterion("account_totalmoney in", values, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyNotIn(List<BigDecimal> values) {
            addCriterion("account_totalmoney not in", values, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_totalmoney between", value1, value2, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountTotalmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_totalmoney not between", value1, value2, "accountTotalmoney");
            return (Criteria) this;
        }

        public Criteria andAccountDepositIsNull() {
            addCriterion("account_deposit is null");
            return (Criteria) this;
        }

        public Criteria andAccountDepositIsNotNull() {
            addCriterion("account_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andAccountDepositEqualTo(BigDecimal value) {
            addCriterion("account_deposit =", value, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositNotEqualTo(BigDecimal value) {
            addCriterion("account_deposit <>", value, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositGreaterThan(BigDecimal value) {
            addCriterion("account_deposit >", value, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_deposit >=", value, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositLessThan(BigDecimal value) {
            addCriterion("account_deposit <", value, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_deposit <=", value, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositIn(List<BigDecimal> values) {
            addCriterion("account_deposit in", values, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositNotIn(List<BigDecimal> values) {
            addCriterion("account_deposit not in", values, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_deposit between", value1, value2, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_deposit not between", value1, value2, "accountDeposit");
            return (Criteria) this;
        }

        public Criteria andAccountBbinIsNull() {
            addCriterion("account_bbin is null");
            return (Criteria) this;
        }

        public Criteria andAccountBbinIsNotNull() {
            addCriterion("account_bbin is not null");
            return (Criteria) this;
        }

        public Criteria andAccountBbinEqualTo(BigDecimal value) {
            addCriterion("account_bbin =", value, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinNotEqualTo(BigDecimal value) {
            addCriterion("account_bbin <>", value, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinGreaterThan(BigDecimal value) {
            addCriterion("account_bbin >", value, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_bbin >=", value, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinLessThan(BigDecimal value) {
            addCriterion("account_bbin <", value, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_bbin <=", value, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinIn(List<BigDecimal> values) {
            addCriterion("account_bbin in", values, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinNotIn(List<BigDecimal> values) {
            addCriterion("account_bbin not in", values, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_bbin between", value1, value2, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountBbinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_bbin not between", value1, value2, "accountBbin");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyIsNull() {
            addCriterion("account_freezemoney is null");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyIsNotNull() {
            addCriterion("account_freezemoney is not null");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyEqualTo(BigDecimal value) {
            addCriterion("account_freezemoney =", value, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyNotEqualTo(BigDecimal value) {
            addCriterion("account_freezemoney <>", value, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyGreaterThan(BigDecimal value) {
            addCriterion("account_freezemoney >", value, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_freezemoney >=", value, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyLessThan(BigDecimal value) {
            addCriterion("account_freezemoney <", value, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_freezemoney <=", value, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyIn(List<BigDecimal> values) {
            addCriterion("account_freezemoney in", values, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyNotIn(List<BigDecimal> values) {
            addCriterion("account_freezemoney not in", values, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_freezemoney between", value1, value2, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountFreezemoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_freezemoney not between", value1, value2, "accountFreezemoney");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceIsNull() {
            addCriterion("account_available_balance is null");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceIsNotNull() {
            addCriterion("account_available_balance is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceEqualTo(BigDecimal value) {
            addCriterion("account_available_balance =", value, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceNotEqualTo(BigDecimal value) {
            addCriterion("account_available_balance <>", value, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceGreaterThan(BigDecimal value) {
            addCriterion("account_available_balance >", value, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_available_balance >=", value, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceLessThan(BigDecimal value) {
            addCriterion("account_available_balance <", value, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_available_balance <=", value, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceIn(List<BigDecimal> values) {
            addCriterion("account_available_balance in", values, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceNotIn(List<BigDecimal> values) {
            addCriterion("account_available_balance not in", values, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_available_balance between", value1, value2, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountAvailableBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_available_balance not between", value1, value2, "accountAvailableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeIsNull() {
            addCriterion("account_createtime is null");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeIsNotNull() {
            addCriterion("account_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeEqualTo(Date value) {
            addCriterion("account_createtime =", value, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeNotEqualTo(Date value) {
            addCriterion("account_createtime <>", value, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeGreaterThan(Date value) {
            addCriterion("account_createtime >", value, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("account_createtime >=", value, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeLessThan(Date value) {
            addCriterion("account_createtime <", value, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("account_createtime <=", value, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeIn(List<Date> values) {
            addCriterion("account_createtime in", values, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeNotIn(List<Date> values) {
            addCriterion("account_createtime not in", values, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeBetween(Date value1, Date value2) {
            addCriterion("account_createtime between", value1, value2, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("account_createtime not between", value1, value2, "accountCreatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeIsNull() {
            addCriterion("account_updatetime is null");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeIsNotNull() {
            addCriterion("account_updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeEqualTo(Date value) {
            addCriterion("account_updatetime =", value, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeNotEqualTo(Date value) {
            addCriterion("account_updatetime <>", value, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeGreaterThan(Date value) {
            addCriterion("account_updatetime >", value, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("account_updatetime >=", value, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeLessThan(Date value) {
            addCriterion("account_updatetime <", value, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("account_updatetime <=", value, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeIn(List<Date> values) {
            addCriterion("account_updatetime in", values, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeNotIn(List<Date> values) {
            addCriterion("account_updatetime not in", values, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("account_updatetime between", value1, value2, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("account_updatetime not between", value1, value2, "accountUpdatetime");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeIsNull() {
            addCriterion("account_is_freeze is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeIsNotNull() {
            addCriterion("account_is_freeze is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeEqualTo(Integer value) {
            addCriterion("account_is_freeze =", value, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeNotEqualTo(Integer value) {
            addCriterion("account_is_freeze <>", value, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeGreaterThan(Integer value) {
            addCriterion("account_is_freeze >", value, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_is_freeze >=", value, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeLessThan(Integer value) {
            addCriterion("account_is_freeze <", value, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeLessThanOrEqualTo(Integer value) {
            addCriterion("account_is_freeze <=", value, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeIn(List<Integer> values) {
            addCriterion("account_is_freeze in", values, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeNotIn(List<Integer> values) {
            addCriterion("account_is_freeze not in", values, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeBetween(Integer value1, Integer value2) {
            addCriterion("account_is_freeze between", value1, value2, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountIsFreezeNotBetween(Integer value1, Integer value2) {
            addCriterion("account_is_freeze not between", value1, value2, "accountIsFreeze");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeIsNull() {
            addCriterion("account_final_recharge_time is null");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeIsNotNull() {
            addCriterion("account_final_recharge_time is not null");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeEqualTo(Date value) {
            addCriterion("account_final_recharge_time =", value, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeNotEqualTo(Date value) {
            addCriterion("account_final_recharge_time <>", value, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeGreaterThan(Date value) {
            addCriterion("account_final_recharge_time >", value, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("account_final_recharge_time >=", value, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeLessThan(Date value) {
            addCriterion("account_final_recharge_time <", value, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeLessThanOrEqualTo(Date value) {
            addCriterion("account_final_recharge_time <=", value, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeIn(List<Date> values) {
            addCriterion("account_final_recharge_time in", values, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeNotIn(List<Date> values) {
            addCriterion("account_final_recharge_time not in", values, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeBetween(Date value1, Date value2) {
            addCriterion("account_final_recharge_time between", value1, value2, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRechargeTimeNotBetween(Date value1, Date value2) {
            addCriterion("account_final_recharge_time not between", value1, value2, "accountFinalRechargeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeIsNull() {
            addCriterion("account_final_consume_time is null");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeIsNotNull() {
            addCriterion("account_final_consume_time is not null");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeEqualTo(Date value) {
            addCriterion("account_final_consume_time =", value, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeNotEqualTo(Date value) {
            addCriterion("account_final_consume_time <>", value, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeGreaterThan(Date value) {
            addCriterion("account_final_consume_time >", value, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("account_final_consume_time >=", value, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeLessThan(Date value) {
            addCriterion("account_final_consume_time <", value, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeLessThanOrEqualTo(Date value) {
            addCriterion("account_final_consume_time <=", value, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeIn(List<Date> values) {
            addCriterion("account_final_consume_time in", values, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeNotIn(List<Date> values) {
            addCriterion("account_final_consume_time not in", values, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeBetween(Date value1, Date value2) {
            addCriterion("account_final_consume_time between", value1, value2, "accountFinalConsumeTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalConsumeTimeNotBetween(Date value1, Date value2) {
            addCriterion("account_final_consume_time not between", value1, value2, "accountFinalConsumeTime");
            return (Criteria) this;
        }
        public Criteria andAccountFinalRefundTimeIsNull() {
            addCriterion("account_final_refund_time is null");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeIsNotNull() {
            addCriterion("account_final_refund_time is not null");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeEqualTo(Date value) {
            addCriterion("account_final_refund_time =", value, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeNotEqualTo(Date value) {
            addCriterion("account_final_refund_time <>", value, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeGreaterThan(Date value) {
            addCriterion("account_final_refund_time >", value, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("account_final_refund_time >=", value, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeLessThan(Date value) {
            addCriterion("account_final_refund_time <", value, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeLessThanOrEqualTo(Date value) {
            addCriterion("account_final_refund_time <=", value, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeIn(List<Date> values) {
            addCriterion("account_final_refund_time in", values, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeNotIn(List<Date> values) {
            addCriterion("account_final_refund_time not in", values, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeBetween(Date value1, Date value2) {
            addCriterion("account_final_refund_time between", value1, value2, "accountFinalRefundTime");
            return (Criteria) this;
        }

        public Criteria andAccountFinalRefundTimeNotBetween(Date value1, Date value2) {
            addCriterion("account_final_refund_time not between", value1, value2, "accountFinalRefundTime");
            return (Criteria) this;
        }
        
        public Criteria andAccountGiveMoneyIsNull() {
            addCriterion("account_give_money is null");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyIsNotNull() {
            addCriterion("account_give_money is not null");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyEqualTo(BigDecimal value) {
            addCriterion("account_give_money =", value, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyNotEqualTo(BigDecimal value) {
            addCriterion("account_give_money <>", value, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyGreaterThan(BigDecimal value) {
            addCriterion("account_give_money >", value, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_give_money >=", value, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyLessThan(BigDecimal value) {
            addCriterion("account_give_money <", value, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_give_money <=", value, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyIn(List<BigDecimal> values) {
            addCriterion("account_give_money in", values, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyNotIn(List<BigDecimal> values) {
            addCriterion("account_give_money not in", values, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_give_money between", value1, value2, "accountGiveMoney");
            return (Criteria) this;
        }

        public Criteria andAccountGiveMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_give_money not between", value1, value2, "accountGiveMoney");
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