package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class InvoiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public InvoiceExample() {
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

        public Criteria andInvoiceIdIsNull() {
            addCriterion("invoice_id is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIsNotNull() {
            addCriterion("invoice_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdEqualTo(Long value) {
            addCriterion("invoice_id =", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotEqualTo(Long value) {
            addCriterion("invoice_id <>", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdGreaterThan(Long value) {
            addCriterion("invoice_id >", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("invoice_id >=", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdLessThan(Long value) {
            addCriterion("invoice_id <", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdLessThanOrEqualTo(Long value) {
            addCriterion("invoice_id <=", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIn(List<Long> values) {
            addCriterion("invoice_id in", values, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotIn(List<Long> values) {
            addCriterion("invoice_id not in", values, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdBetween(Long value1, Long value2) {
            addCriterion("invoice_id between", value1, value2, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotBetween(Long value1, Long value2) {
            addCriterion("invoice_id not between", value1, value2, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIsNull() {
            addCriterion("invoice_name is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIsNotNull() {
            addCriterion("invoice_name is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameEqualTo(String value) {
            addCriterion("invoice_name =", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotEqualTo(String value) {
            addCriterion("invoice_name <>", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameGreaterThan(String value) {
            addCriterion("invoice_name >", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_name >=", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLessThan(String value) {
            addCriterion("invoice_name <", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLessThanOrEqualTo(String value) {
            addCriterion("invoice_name <=", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLike(String value) {
            addCriterion("invoice_name like", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotLike(String value) {
            addCriterion("invoice_name not like", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIn(List<String> values) {
            addCriterion("invoice_name in", values, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotIn(List<String> values) {
            addCriterion("invoice_name not in", values, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameBetween(String value1, String value2) {
            addCriterion("invoice_name between", value1, value2, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotBetween(String value1, String value2) {
            addCriterion("invoice_name not between", value1, value2, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdIsNull() {
            addCriterion("invoice_user_id is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdIsNotNull() {
            addCriterion("invoice_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdEqualTo(Long value) {
            addCriterion("invoice_user_id =", value, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdNotEqualTo(Long value) {
            addCriterion("invoice_user_id <>", value, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdGreaterThan(Long value) {
            addCriterion("invoice_user_id >", value, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("invoice_user_id >=", value, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdLessThan(Long value) {
            addCriterion("invoice_user_id <", value, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdLessThanOrEqualTo(Long value) {
            addCriterion("invoice_user_id <=", value, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdIn(List<Long> values) {
            addCriterion("invoice_user_id in", values, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdNotIn(List<Long> values) {
            addCriterion("invoice_user_id not in", values, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdBetween(Long value1, Long value2) {
            addCriterion("invoice_user_id between", value1, value2, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceUserIdNotBetween(Long value1, Long value2) {
            addCriterion("invoice_user_id not between", value1, value2, "invoiceUserId");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentIsNull() {
            addCriterion("invoice_content is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentIsNotNull() {
            addCriterion("invoice_content is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentEqualTo(String value) {
            addCriterion("invoice_content =", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentNotEqualTo(String value) {
            addCriterion("invoice_content <>", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentGreaterThan(String value) {
            addCriterion("invoice_content >", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_content >=", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentLessThan(String value) {
            addCriterion("invoice_content <", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentLessThanOrEqualTo(String value) {
            addCriterion("invoice_content <=", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentLike(String value) {
            addCriterion("invoice_content like", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentNotLike(String value) {
            addCriterion("invoice_content not like", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentIn(List<String> values) {
            addCriterion("invoice_content in", values, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentNotIn(List<String> values) {
            addCriterion("invoice_content not in", values, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentBetween(String value1, String value2) {
            addCriterion("invoice_content between", value1, value2, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentNotBetween(String value1, String value2) {
            addCriterion("invoice_content not between", value1, value2, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyIsNull() {
            addCriterion("invoice_money is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyIsNotNull() {
            addCriterion("invoice_money is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyEqualTo(BigDecimal value) {
            addCriterion("invoice_money =", value, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyNotEqualTo(BigDecimal value) {
            addCriterion("invoice_money <>", value, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyGreaterThan(BigDecimal value) {
            addCriterion("invoice_money >", value, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invoice_money >=", value, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyLessThan(BigDecimal value) {
            addCriterion("invoice_money <", value, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invoice_money <=", value, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyIn(List<BigDecimal> values) {
            addCriterion("invoice_money in", values, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyNotIn(List<BigDecimal> values) {
            addCriterion("invoice_money not in", values, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoice_money between", value1, value2, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoice_money not between", value1, value2, "invoiceMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkIsNull() {
            addCriterion("invoice_mark is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkIsNotNull() {
            addCriterion("invoice_mark is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkEqualTo(String value) {
            addCriterion("invoice_mark =", value, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkNotEqualTo(String value) {
            addCriterion("invoice_mark <>", value, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkGreaterThan(String value) {
            addCriterion("invoice_mark >", value, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_mark >=", value, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkLessThan(String value) {
            addCriterion("invoice_mark <", value, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkLessThanOrEqualTo(String value) {
            addCriterion("invoice_mark <=", value, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkLike(String value) {
            addCriterion("invoice_mark like", value, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkNotLike(String value) {
            addCriterion("invoice_mark not like", value, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkIn(List<String> values) {
            addCriterion("invoice_mark in", values, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkNotIn(List<String> values) {
            addCriterion("invoice_mark not in", values, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkBetween(String value1, String value2) {
            addCriterion("invoice_mark between", value1, value2, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceMarkNotBetween(String value1, String value2) {
            addCriterion("invoice_mark not between", value1, value2, "invoiceMark");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberIsNull() {
            addCriterion("invoice_taxpayer_number is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberIsNotNull() {
            addCriterion("invoice_taxpayer_number is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberEqualTo(String value) {
            addCriterion("invoice_taxpayer_number =", value, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberNotEqualTo(String value) {
            addCriterion("invoice_taxpayer_number <>", value, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberGreaterThan(String value) {
            addCriterion("invoice_taxpayer_number >", value, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_taxpayer_number >=", value, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberLessThan(String value) {
            addCriterion("invoice_taxpayer_number <", value, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberLessThanOrEqualTo(String value) {
            addCriterion("invoice_taxpayer_number <=", value, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberLike(String value) {
            addCriterion("invoice_taxpayer_number like", value, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberNotLike(String value) {
            addCriterion("invoice_taxpayer_number not like", value, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberIn(List<String> values) {
            addCriterion("invoice_taxpayer_number in", values, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberNotIn(List<String> values) {
            addCriterion("invoice_taxpayer_number not in", values, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberBetween(String value1, String value2) {
            addCriterion("invoice_taxpayer_number between", value1, value2, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTaxpayerNumberNotBetween(String value1, String value2) {
            addCriterion("invoice_taxpayer_number not between", value1, value2, "invoiceTaxpayerNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneIsNull() {
            addCriterion("invoice_addre_phone is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneIsNotNull() {
            addCriterion("invoice_addre_phone is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneEqualTo(String value) {
            addCriterion("invoice_addre_phone =", value, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneNotEqualTo(String value) {
            addCriterion("invoice_addre_phone <>", value, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneGreaterThan(String value) {
            addCriterion("invoice_addre_phone >", value, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_addre_phone >=", value, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneLessThan(String value) {
            addCriterion("invoice_addre_phone <", value, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneLessThanOrEqualTo(String value) {
            addCriterion("invoice_addre_phone <=", value, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneLike(String value) {
            addCriterion("invoice_addre_phone like", value, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneNotLike(String value) {
            addCriterion("invoice_addre_phone not like", value, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneIn(List<String> values) {
            addCriterion("invoice_addre_phone in", values, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneNotIn(List<String> values) {
            addCriterion("invoice_addre_phone not in", values, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneBetween(String value1, String value2) {
            addCriterion("invoice_addre_phone between", value1, value2, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddrePhoneNotBetween(String value1, String value2) {
            addCriterion("invoice_addre_phone not between", value1, value2, "invoiceAddrePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountIsNull() {
            addCriterion("invoice_bank_account is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountIsNotNull() {
            addCriterion("invoice_bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountEqualTo(String value) {
            addCriterion("invoice_bank_account =", value, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountNotEqualTo(String value) {
            addCriterion("invoice_bank_account <>", value, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountGreaterThan(String value) {
            addCriterion("invoice_bank_account >", value, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_bank_account >=", value, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountLessThan(String value) {
            addCriterion("invoice_bank_account <", value, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountLessThanOrEqualTo(String value) {
            addCriterion("invoice_bank_account <=", value, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountLike(String value) {
            addCriterion("invoice_bank_account like", value, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountNotLike(String value) {
            addCriterion("invoice_bank_account not like", value, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountIn(List<String> values) {
            addCriterion("invoice_bank_account in", values, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountNotIn(List<String> values) {
            addCriterion("invoice_bank_account not in", values, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountBetween(String value1, String value2) {
            addCriterion("invoice_bank_account between", value1, value2, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBankAccountNotBetween(String value1, String value2) {
            addCriterion("invoice_bank_account not between", value1, value2, "invoiceBankAccount");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailIsNull() {
            addCriterion("invoice_email is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailIsNotNull() {
            addCriterion("invoice_email is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailEqualTo(String value) {
            addCriterion("invoice_email =", value, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailNotEqualTo(String value) {
            addCriterion("invoice_email <>", value, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailGreaterThan(String value) {
            addCriterion("invoice_email >", value, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_email >=", value, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailLessThan(String value) {
            addCriterion("invoice_email <", value, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailLessThanOrEqualTo(String value) {
            addCriterion("invoice_email <=", value, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailLike(String value) {
            addCriterion("invoice_email like", value, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailNotLike(String value) {
            addCriterion("invoice_email not like", value, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailIn(List<String> values) {
            addCriterion("invoice_email in", values, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailNotIn(List<String> values) {
            addCriterion("invoice_email not in", values, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailBetween(String value1, String value2) {
            addCriterion("invoice_email between", value1, value2, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceEmailNotBetween(String value1, String value2) {
            addCriterion("invoice_email not between", value1, value2, "invoiceEmail");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameIsNull() {
            addCriterion("invoice_receive_name is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameIsNotNull() {
            addCriterion("invoice_receive_name is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameEqualTo(String value) {
            addCriterion("invoice_receive_name =", value, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameNotEqualTo(String value) {
            addCriterion("invoice_receive_name <>", value, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameGreaterThan(String value) {
            addCriterion("invoice_receive_name >", value, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_receive_name >=", value, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameLessThan(String value) {
            addCriterion("invoice_receive_name <", value, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameLessThanOrEqualTo(String value) {
            addCriterion("invoice_receive_name <=", value, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameLike(String value) {
            addCriterion("invoice_receive_name like", value, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameNotLike(String value) {
            addCriterion("invoice_receive_name not like", value, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameIn(List<String> values) {
            addCriterion("invoice_receive_name in", values, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameNotIn(List<String> values) {
            addCriterion("invoice_receive_name not in", values, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameBetween(String value1, String value2) {
            addCriterion("invoice_receive_name between", value1, value2, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveNameNotBetween(String value1, String value2) {
            addCriterion("invoice_receive_name not between", value1, value2, "invoiceReceiveName");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelIsNull() {
            addCriterion("invoice_receive_tel is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelIsNotNull() {
            addCriterion("invoice_receive_tel is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelEqualTo(String value) {
            addCriterion("invoice_receive_tel =", value, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelNotEqualTo(String value) {
            addCriterion("invoice_receive_tel <>", value, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelGreaterThan(String value) {
            addCriterion("invoice_receive_tel >", value, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_receive_tel >=", value, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelLessThan(String value) {
            addCriterion("invoice_receive_tel <", value, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelLessThanOrEqualTo(String value) {
            addCriterion("invoice_receive_tel <=", value, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelLike(String value) {
            addCriterion("invoice_receive_tel like", value, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelNotLike(String value) {
            addCriterion("invoice_receive_tel not like", value, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelIn(List<String> values) {
            addCriterion("invoice_receive_tel in", values, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelNotIn(List<String> values) {
            addCriterion("invoice_receive_tel not in", values, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelBetween(String value1, String value2) {
            addCriterion("invoice_receive_tel between", value1, value2, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveTelNotBetween(String value1, String value2) {
            addCriterion("invoice_receive_tel not between", value1, value2, "invoiceReceiveTel");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityIsNull() {
            addCriterion("invoice_receive_city is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityIsNotNull() {
            addCriterion("invoice_receive_city is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityEqualTo(String value) {
            addCriterion("invoice_receive_city =", value, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityNotEqualTo(String value) {
            addCriterion("invoice_receive_city <>", value, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityGreaterThan(String value) {
            addCriterion("invoice_receive_city >", value, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_receive_city >=", value, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityLessThan(String value) {
            addCriterion("invoice_receive_city <", value, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityLessThanOrEqualTo(String value) {
            addCriterion("invoice_receive_city <=", value, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityLike(String value) {
            addCriterion("invoice_receive_city like", value, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityNotLike(String value) {
            addCriterion("invoice_receive_city not like", value, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityIn(List<String> values) {
            addCriterion("invoice_receive_city in", values, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityNotIn(List<String> values) {
            addCriterion("invoice_receive_city not in", values, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityBetween(String value1, String value2) {
            addCriterion("invoice_receive_city between", value1, value2, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveCityNotBetween(String value1, String value2) {
            addCriterion("invoice_receive_city not between", value1, value2, "invoiceReceiveCity");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressIsNull() {
            addCriterion("invoice_receive_address is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressIsNotNull() {
            addCriterion("invoice_receive_address is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressEqualTo(String value) {
            addCriterion("invoice_receive_address =", value, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressNotEqualTo(String value) {
            addCriterion("invoice_receive_address <>", value, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressGreaterThan(String value) {
            addCriterion("invoice_receive_address >", value, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_receive_address >=", value, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressLessThan(String value) {
            addCriterion("invoice_receive_address <", value, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressLessThanOrEqualTo(String value) {
            addCriterion("invoice_receive_address <=", value, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressLike(String value) {
            addCriterion("invoice_receive_address like", value, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressNotLike(String value) {
            addCriterion("invoice_receive_address not like", value, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressIn(List<String> values) {
            addCriterion("invoice_receive_address in", values, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressNotIn(List<String> values) {
            addCriterion("invoice_receive_address not in", values, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressBetween(String value1, String value2) {
            addCriterion("invoice_receive_address between", value1, value2, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceReceiveAddressNotBetween(String value1, String value2) {
            addCriterion("invoice_receive_address not between", value1, value2, "invoiceReceiveAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeIsNull() {
            addCriterion("invoice_application_time is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeIsNotNull() {
            addCriterion("invoice_application_time is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeEqualTo(Date value) {
            addCriterion("invoice_application_time =", value, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeNotEqualTo(Date value) {
            addCriterion("invoice_application_time <>", value, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeGreaterThan(Date value) {
            addCriterion("invoice_application_time >", value, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("invoice_application_time >=", value, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeLessThan(Date value) {
            addCriterion("invoice_application_time <", value, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeLessThanOrEqualTo(Date value) {
            addCriterion("invoice_application_time <=", value, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeIn(List<Date> values) {
            addCriterion("invoice_application_time in", values, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeNotIn(List<Date> values) {
            addCriterion("invoice_application_time not in", values, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeBetween(Date value1, Date value2) {
            addCriterion("invoice_application_time between", value1, value2, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceApplicationTimeNotBetween(Date value1, Date value2) {
            addCriterion("invoice_application_time not between", value1, value2, "invoiceApplicationTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("invoice_type is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("invoice_type is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(Integer value) {
            addCriterion("invoice_type =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(Integer value) {
            addCriterion("invoice_type <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(Integer value) {
            addCriterion("invoice_type >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_type >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(Integer value) {
            addCriterion("invoice_type <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_type <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<Integer> values) {
            addCriterion("invoice_type in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<Integer> values) {
            addCriterion("invoice_type not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(Integer value1, Integer value2) {
            addCriterion("invoice_type between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_type not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateIsNull() {
            addCriterion("invoice_state is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateIsNotNull() {
            addCriterion("invoice_state is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateEqualTo(Integer value) {
            addCriterion("invoice_state =", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateNotEqualTo(Integer value) {
            addCriterion("invoice_state <>", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateGreaterThan(Integer value) {
            addCriterion("invoice_state >", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_state >=", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateLessThan(Integer value) {
            addCriterion("invoice_state <", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_state <=", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateIn(List<Integer> values) {
            addCriterion("invoice_state in", values, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateNotIn(List<Integer> values) {
            addCriterion("invoice_state not in", values, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateBetween(Integer value1, Integer value2) {
            addCriterion("invoice_state between", value1, value2, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_state not between", value1, value2, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdIsNull() {
            addCriterion("invoice_deliver_type_id is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdIsNotNull() {
            addCriterion("invoice_deliver_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdEqualTo(Long value) {
            addCriterion("invoice_deliver_type_id =", value, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdNotEqualTo(Long value) {
            addCriterion("invoice_deliver_type_id <>", value, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdGreaterThan(Long value) {
            addCriterion("invoice_deliver_type_id >", value, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("invoice_deliver_type_id >=", value, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdLessThan(Long value) {
            addCriterion("invoice_deliver_type_id <", value, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("invoice_deliver_type_id <=", value, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdIn(List<Long> values) {
            addCriterion("invoice_deliver_type_id in", values, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdNotIn(List<Long> values) {
            addCriterion("invoice_deliver_type_id not in", values, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdBetween(Long value1, Long value2) {
            addCriterion("invoice_deliver_type_id between", value1, value2, "invoiceDeliverTypeId");
            return (Criteria) this;
        }

        public Criteria andInvoiceDeliverTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("invoice_deliver_type_id not between", value1, value2, "invoiceDeliverTypeId");
            return (Criteria) this;
        }
        
        public Criteria andInvoiceUrlIsNull() {
            addCriterion("invoice_url is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlIsNotNull() {
            addCriterion("invoice_url is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlEqualTo(String value) {
            addCriterion("invoice_url =", value, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlNotEqualTo(String value) {
            addCriterion("invoice_url <>", value, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlGreaterThan(String value) {
            addCriterion("invoice_url >", value, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_url >=", value, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlLessThan(String value) {
            addCriterion("invoice_url <", value, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlLessThanOrEqualTo(String value) {
            addCriterion("invoice_url <=", value, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlLike(String value) {
            addCriterion("invoice_url like", value, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlNotLike(String value) {
            addCriterion("invoice_url not like", value, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlIn(List<String> values) {
            addCriterion("invoice_url in", values, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlNotIn(List<String> values) {
            addCriterion("invoice_url not in", values, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlBetween(String value1, String value2) {
            addCriterion("invoice_url between", value1, value2, "invoiceUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceUrlNotBetween(String value1, String value2) {
            addCriterion("invoice_url not between", value1, value2, "invoiceUrl");
            return (Criteria) this;
        }
        
        public Criteria andInvoiceTravelUrlIsNull() {
            addCriterion("invoice_travel_url is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlIsNotNull() {
            addCriterion("invoice_travel_url is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlEqualTo(String value) {
            addCriterion("invoice_travel_url =", value, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlNotEqualTo(String value) {
            addCriterion("invoice_travel_url <>", value, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlGreaterThan(String value) {
            addCriterion("invoice_travel_url >", value, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_travel_url >=", value, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlLessThan(String value) {
            addCriterion("invoice_travel_url <", value, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlLessThanOrEqualTo(String value) {
            addCriterion("invoice_travel_url <=", value, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlLike(String value) {
            addCriterion("invoice_travel_url like", value, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlNotLike(String value) {
            addCriterion("invoice_travel_url not like", value, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlIn(List<String> values) {
            addCriterion("invoice_travel_url in", values, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlNotIn(List<String> values) {
            addCriterion("invoice_travel_url not in", values, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlBetween(String value1, String value2) {
            addCriterion("invoice_travel_url between", value1, value2, "invoiceTravelUrl");
            return (Criteria) this;
        }

        public Criteria andInvoiceTravelUrlNotBetween(String value1, String value2) {
            addCriterion("invoice_travel_url not between", value1, value2, "invoiceTravelUrl");
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