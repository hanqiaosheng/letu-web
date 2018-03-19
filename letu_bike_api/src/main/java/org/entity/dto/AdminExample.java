package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdminExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public AdminExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(Long value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(Long value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(Long value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(Long value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<Long> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<Long> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(Long value1, Long value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdIsNull() {
            addCriterion("admin_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdIsNotNull() {
            addCriterion("admin_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdEqualTo(Long value) {
            addCriterion("admin_channel_id =", value, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdNotEqualTo(Long value) {
            addCriterion("admin_channel_id <>", value, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdGreaterThan(Long value) {
            addCriterion("admin_channel_id >", value, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("admin_channel_id >=", value, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdLessThan(Long value) {
            addCriterion("admin_channel_id <", value, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("admin_channel_id <=", value, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdIn(List<Long> values) {
            addCriterion("admin_channel_id in", values, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdNotIn(List<Long> values) {
            addCriterion("admin_channel_id not in", values, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdBetween(Long value1, Long value2) {
            addCriterion("admin_channel_id between", value1, value2, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("admin_channel_id not between", value1, value2, "adminChannelId");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIsNull() {
            addCriterion("admin_username is null");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIsNotNull() {
            addCriterion("admin_username is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameEqualTo(String value) {
            addCriterion("admin_username =", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotEqualTo(String value) {
            addCriterion("admin_username <>", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameGreaterThan(String value) {
            addCriterion("admin_username >", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("admin_username >=", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLessThan(String value) {
            addCriterion("admin_username <", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLessThanOrEqualTo(String value) {
            addCriterion("admin_username <=", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLike(String value) {
            addCriterion("admin_username like", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotLike(String value) {
            addCriterion("admin_username not like", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIn(List<String> values) {
            addCriterion("admin_username in", values, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotIn(List<String> values) {
            addCriterion("admin_username not in", values, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameBetween(String value1, String value2) {
            addCriterion("admin_username between", value1, value2, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotBetween(String value1, String value2) {
            addCriterion("admin_username not between", value1, value2, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminPwdIsNull() {
            addCriterion("admin_pwd is null");
            return (Criteria) this;
        }

        public Criteria andAdminPwdIsNotNull() {
            addCriterion("admin_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andAdminPwdEqualTo(String value) {
            addCriterion("admin_pwd =", value, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdNotEqualTo(String value) {
            addCriterion("admin_pwd <>", value, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdGreaterThan(String value) {
            addCriterion("admin_pwd >", value, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdGreaterThanOrEqualTo(String value) {
            addCriterion("admin_pwd >=", value, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdLessThan(String value) {
            addCriterion("admin_pwd <", value, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdLessThanOrEqualTo(String value) {
            addCriterion("admin_pwd <=", value, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdLike(String value) {
            addCriterion("admin_pwd like", value, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdNotLike(String value) {
            addCriterion("admin_pwd not like", value, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdIn(List<String> values) {
            addCriterion("admin_pwd in", values, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdNotIn(List<String> values) {
            addCriterion("admin_pwd not in", values, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdBetween(String value1, String value2) {
            addCriterion("admin_pwd between", value1, value2, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminPwdNotBetween(String value1, String value2) {
            addCriterion("admin_pwd not between", value1, value2, "adminPwd");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameIsNull() {
            addCriterion("admin_realname is null");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameIsNotNull() {
            addCriterion("admin_realname is not null");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameEqualTo(String value) {
            addCriterion("admin_realname =", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameNotEqualTo(String value) {
            addCriterion("admin_realname <>", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameGreaterThan(String value) {
            addCriterion("admin_realname >", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("admin_realname >=", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameLessThan(String value) {
            addCriterion("admin_realname <", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameLessThanOrEqualTo(String value) {
            addCriterion("admin_realname <=", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameLike(String value) {
            addCriterion("admin_realname like", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameNotLike(String value) {
            addCriterion("admin_realname not like", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameIn(List<String> values) {
            addCriterion("admin_realname in", values, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameNotIn(List<String> values) {
            addCriterion("admin_realname not in", values, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameBetween(String value1, String value2) {
            addCriterion("admin_realname between", value1, value2, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminRealnameNotBetween(String value1, String value2) {
            addCriterion("admin_realname not between", value1, value2, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andAdminTelIsNull() {
            addCriterion("admin_tel is null");
            return (Criteria) this;
        }

        public Criteria andAdminTelIsNotNull() {
            addCriterion("admin_tel is not null");
            return (Criteria) this;
        }

        public Criteria andAdminTelEqualTo(String value) {
            addCriterion("admin_tel =", value, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelNotEqualTo(String value) {
            addCriterion("admin_tel <>", value, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelGreaterThan(String value) {
            addCriterion("admin_tel >", value, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelGreaterThanOrEqualTo(String value) {
            addCriterion("admin_tel >=", value, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelLessThan(String value) {
            addCriterion("admin_tel <", value, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelLessThanOrEqualTo(String value) {
            addCriterion("admin_tel <=", value, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelLike(String value) {
            addCriterion("admin_tel like", value, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelNotLike(String value) {
            addCriterion("admin_tel not like", value, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelIn(List<String> values) {
            addCriterion("admin_tel in", values, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelNotIn(List<String> values) {
            addCriterion("admin_tel not in", values, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelBetween(String value1, String value2) {
            addCriterion("admin_tel between", value1, value2, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminTelNotBetween(String value1, String value2) {
            addCriterion("admin_tel not between", value1, value2, "adminTel");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeIsNull() {
            addCriterion("admin_reg_time is null");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeIsNotNull() {
            addCriterion("admin_reg_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeEqualTo(Date value) {
            addCriterionForJDBCDate("admin_reg_time =", value, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("admin_reg_time <>", value, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("admin_reg_time >", value, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("admin_reg_time >=", value, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeLessThan(Date value) {
            addCriterionForJDBCDate("admin_reg_time <", value, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("admin_reg_time <=", value, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeIn(List<Date> values) {
            addCriterionForJDBCDate("admin_reg_time in", values, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("admin_reg_time not in", values, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("admin_reg_time between", value1, value2, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminRegTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("admin_reg_time not between", value1, value2, "adminRegTime");
            return (Criteria) this;
        }

        public Criteria andAdminStateIsNull() {
            addCriterion("admin_state is null");
            return (Criteria) this;
        }

        public Criteria andAdminStateIsNotNull() {
            addCriterion("admin_state is not null");
            return (Criteria) this;
        }

        public Criteria andAdminStateEqualTo(Integer value) {
            addCriterion("admin_state =", value, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateNotEqualTo(Integer value) {
            addCriterion("admin_state <>", value, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateGreaterThan(Integer value) {
            addCriterion("admin_state >", value, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_state >=", value, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateLessThan(Integer value) {
            addCriterion("admin_state <", value, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateLessThanOrEqualTo(Integer value) {
            addCriterion("admin_state <=", value, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateIn(List<Integer> values) {
            addCriterion("admin_state in", values, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateNotIn(List<Integer> values) {
            addCriterion("admin_state not in", values, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateBetween(Integer value1, Integer value2) {
            addCriterion("admin_state between", value1, value2, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminStateNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_state not between", value1, value2, "adminState");
            return (Criteria) this;
        }

        public Criteria andAdminSaltIsNull() {
            addCriterion("admin_salt is null");
            return (Criteria) this;
        }

        public Criteria andAdminSaltIsNotNull() {
            addCriterion("admin_salt is not null");
            return (Criteria) this;
        }

        public Criteria andAdminSaltEqualTo(String value) {
            addCriterion("admin_salt =", value, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltNotEqualTo(String value) {
            addCriterion("admin_salt <>", value, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltGreaterThan(String value) {
            addCriterion("admin_salt >", value, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltGreaterThanOrEqualTo(String value) {
            addCriterion("admin_salt >=", value, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltLessThan(String value) {
            addCriterion("admin_salt <", value, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltLessThanOrEqualTo(String value) {
            addCriterion("admin_salt <=", value, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltLike(String value) {
            addCriterion("admin_salt like", value, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltNotLike(String value) {
            addCriterion("admin_salt not like", value, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltIn(List<String> values) {
            addCriterion("admin_salt in", values, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltNotIn(List<String> values) {
            addCriterion("admin_salt not in", values, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltBetween(String value1, String value2) {
            addCriterion("admin_salt between", value1, value2, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminSaltNotBetween(String value1, String value2) {
            addCriterion("admin_salt not between", value1, value2, "adminSalt");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeIsNull() {
            addCriterion("admin_district_code is null");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeIsNotNull() {
            addCriterion("admin_district_code is not null");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeEqualTo(String value) {
            addCriterion("admin_district_code =", value, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeNotEqualTo(String value) {
            addCriterion("admin_district_code <>", value, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeGreaterThan(String value) {
            addCriterion("admin_district_code >", value, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeGreaterThanOrEqualTo(String value) {
            addCriterion("admin_district_code >=", value, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeLessThan(String value) {
            addCriterion("admin_district_code <", value, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeLessThanOrEqualTo(String value) {
            addCriterion("admin_district_code <=", value, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeLike(String value) {
            addCriterion("admin_district_code like", value, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeNotLike(String value) {
            addCriterion("admin_district_code not like", value, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeIn(List<String> values) {
            addCriterion("admin_district_code in", values, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeNotIn(List<String> values) {
            addCriterion("admin_district_code not in", values, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeBetween(String value1, String value2) {
            addCriterion("admin_district_code between", value1, value2, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminDistrictCodeNotBetween(String value1, String value2) {
            addCriterion("admin_district_code not between", value1, value2, "adminDistrictCode");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidIsNull() {
            addCriterion("admin_create_adminId is null");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidIsNotNull() {
            addCriterion("admin_create_adminId is not null");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidEqualTo(Long value) {
            addCriterion("admin_create_adminId =", value, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidNotEqualTo(Long value) {
            addCriterion("admin_create_adminId <>", value, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidGreaterThan(Long value) {
            addCriterion("admin_create_adminId >", value, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidGreaterThanOrEqualTo(Long value) {
            addCriterion("admin_create_adminId >=", value, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidLessThan(Long value) {
            addCriterion("admin_create_adminId <", value, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidLessThanOrEqualTo(Long value) {
            addCriterion("admin_create_adminId <=", value, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidIn(List<Long> values) {
            addCriterion("admin_create_adminId in", values, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidNotIn(List<Long> values) {
            addCriterion("admin_create_adminId not in", values, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidBetween(Long value1, Long value2) {
            addCriterion("admin_create_adminId between", value1, value2, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateAdminidNotBetween(Long value1, Long value2) {
            addCriterion("admin_create_adminId not between", value1, value2, "adminCreateAdminid");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailIsNull() {
            addCriterion("admin_create_email is null");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailIsNotNull() {
            addCriterion("admin_create_email is not null");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailEqualTo(String value) {
            addCriterion("admin_create_email =", value, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailNotEqualTo(String value) {
            addCriterion("admin_create_email <>", value, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailGreaterThan(String value) {
            addCriterion("admin_create_email >", value, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailGreaterThanOrEqualTo(String value) {
            addCriterion("admin_create_email >=", value, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailLessThan(String value) {
            addCriterion("admin_create_email <", value, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailLessThanOrEqualTo(String value) {
            addCriterion("admin_create_email <=", value, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailLike(String value) {
            addCriterion("admin_create_email like", value, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailNotLike(String value) {
            addCriterion("admin_create_email not like", value, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailIn(List<String> values) {
            addCriterion("admin_create_email in", values, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailNotIn(List<String> values) {
            addCriterion("admin_create_email not in", values, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailBetween(String value1, String value2) {
            addCriterion("admin_create_email between", value1, value2, "adminCreateEmail");
            return (Criteria) this;
        }

        public Criteria andAdminCreateEmailNotBetween(String value1, String value2) {
            addCriterion("admin_create_email not between", value1, value2, "adminCreateEmail");
            return (Criteria) this;
        }
        public Criteria andAdminIdCardIsNull() {
            addCriterion("admin_id_card is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardIsNotNull() {
            addCriterion("admin_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardEqualTo(String value) {
            addCriterion("admin_id_card =", value, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardNotEqualTo(String value) {
            addCriterion("admin_id_card <>", value, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardGreaterThan(String value) {
            addCriterion("admin_id_card >", value, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("admin_id_card >=", value, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardLessThan(String value) {
            addCriterion("admin_id_card <", value, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardLessThanOrEqualTo(String value) {
            addCriterion("admin_id_card <=", value, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardLike(String value) {
            addCriterion("admin_id_card like", value, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardNotLike(String value) {
            addCriterion("admin_id_card not like", value, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardIn(List<String> values) {
            addCriterion("admin_id_card in", values, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardNotIn(List<String> values) {
            addCriterion("admin_id_card not in", values, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardBetween(String value1, String value2) {
            addCriterion("admin_id_card between", value1, value2, "adminIdCard");
            return (Criteria) this;
        }

        public Criteria andAdminIdCardNotBetween(String value1, String value2) {
            addCriterion("admin_id_card not between", value1, value2, "adminIdCard");
            return (Criteria) this;
        }

      //自定义查找 管理员的角色名
//        public Criteria andAdminRoleNameLike(String value) {
//            addCriterion("role_name like", value, "roleName");
//            return (Criteria) this;
//        }
        
      //自定义查找 管理员的角色Id
        public Criteria andAdminRoleIdEqualTo(Long value) {
            addCriterion("r.role_id =", value, "roleId");
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