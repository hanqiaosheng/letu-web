package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysMsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public SysMsgExample() {
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

        public Criteria andSysMsgIdIsNull() {
            addCriterion("sys_msg_id is null");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdIsNotNull() {
            addCriterion("sys_msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdEqualTo(Long value) {
            addCriterion("sys_msg_id =", value, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdNotEqualTo(Long value) {
            addCriterion("sys_msg_id <>", value, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdGreaterThan(Long value) {
            addCriterion("sys_msg_id >", value, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sys_msg_id >=", value, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdLessThan(Long value) {
            addCriterion("sys_msg_id <", value, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdLessThanOrEqualTo(Long value) {
            addCriterion("sys_msg_id <=", value, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdIn(List<Long> values) {
            addCriterion("sys_msg_id in", values, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdNotIn(List<Long> values) {
            addCriterion("sys_msg_id not in", values, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdBetween(Long value1, Long value2) {
            addCriterion("sys_msg_id between", value1, value2, "sysMsgId");
            return (Criteria) this;
        }

        public Criteria andSysMsgIdNotBetween(Long value1, Long value2) {
            addCriterion("sys_msg_id not between", value1, value2, "sysMsgId");
            return (Criteria) this;
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

        public Criteria andSysMsgContentIsNull() {
            addCriterion("sys_msg_content is null");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentIsNotNull() {
            addCriterion("sys_msg_content is not null");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentEqualTo(String value) {
            addCriterion("sys_msg_content =", value, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentNotEqualTo(String value) {
            addCriterion("sys_msg_content <>", value, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentGreaterThan(String value) {
            addCriterion("sys_msg_content >", value, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentGreaterThanOrEqualTo(String value) {
            addCriterion("sys_msg_content >=", value, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentLessThan(String value) {
            addCriterion("sys_msg_content <", value, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentLessThanOrEqualTo(String value) {
            addCriterion("sys_msg_content <=", value, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentLike(String value) {
            addCriterion("sys_msg_content like", value, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentNotLike(String value) {
            addCriterion("sys_msg_content not like", value, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentIn(List<String> values) {
            addCriterion("sys_msg_content in", values, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentNotIn(List<String> values) {
            addCriterion("sys_msg_content not in", values, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentBetween(String value1, String value2) {
            addCriterion("sys_msg_content between", value1, value2, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgContentNotBetween(String value1, String value2) {
            addCriterion("sys_msg_content not between", value1, value2, "sysMsgContent");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeIsNull() {
            addCriterion("sys_msg_createtime is null");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeIsNotNull() {
            addCriterion("sys_msg_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeEqualTo(Date value) {
            addCriterion("sys_msg_createtime =", value, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeNotEqualTo(Date value) {
            addCriterion("sys_msg_createtime <>", value, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeGreaterThan(Date value) {
            addCriterion("sys_msg_createtime >", value, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sys_msg_createtime >=", value, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeLessThan(Date value) {
            addCriterion("sys_msg_createtime <", value, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("sys_msg_createtime <=", value, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeIn(List<Date> values) {
            addCriterion("sys_msg_createtime in", values, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeNotIn(List<Date> values) {
            addCriterion("sys_msg_createtime not in", values, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeBetween(Date value1, Date value2) {
            addCriterion("sys_msg_createtime between", value1, value2, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("sys_msg_createtime not between", value1, value2, "sysMsgCreatetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineIsNull() {
            addCriterion("sys_msg_isonline is null");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineIsNotNull() {
            addCriterion("sys_msg_isonline is not null");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineEqualTo(Short value) {
            addCriterion("sys_msg_isonline =", value, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineNotEqualTo(Short value) {
            addCriterion("sys_msg_isonline <>", value, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineGreaterThan(Short value) {
            addCriterion("sys_msg_isonline >", value, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineGreaterThanOrEqualTo(Short value) {
            addCriterion("sys_msg_isonline >=", value, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineLessThan(Short value) {
            addCriterion("sys_msg_isonline <", value, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineLessThanOrEqualTo(Short value) {
            addCriterion("sys_msg_isonline <=", value, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineIn(List<Short> values) {
            addCriterion("sys_msg_isonline in", values, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineNotIn(List<Short> values) {
            addCriterion("sys_msg_isonline not in", values, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineBetween(Short value1, Short value2) {
            addCriterion("sys_msg_isonline between", value1, value2, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgIsonlineNotBetween(Short value1, Short value2) {
            addCriterion("sys_msg_isonline not between", value1, value2, "sysMsgIsonline");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeIsNull() {
            addCriterion("sys_msg_onlinetime is null");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeIsNotNull() {
            addCriterion("sys_msg_onlinetime is not null");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeEqualTo(Date value) {
            addCriterion("sys_msg_onlinetime =", value, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeNotEqualTo(Date value) {
            addCriterion("sys_msg_onlinetime <>", value, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeGreaterThan(Date value) {
            addCriterion("sys_msg_onlinetime >", value, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sys_msg_onlinetime >=", value, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeLessThan(Date value) {
            addCriterion("sys_msg_onlinetime <", value, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeLessThanOrEqualTo(Date value) {
            addCriterion("sys_msg_onlinetime <=", value, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeIn(List<Date> values) {
            addCriterion("sys_msg_onlinetime in", values, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeNotIn(List<Date> values) {
            addCriterion("sys_msg_onlinetime not in", values, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeBetween(Date value1, Date value2) {
            addCriterion("sys_msg_onlinetime between", value1, value2, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOnlinetimeNotBetween(Date value1, Date value2) {
            addCriterion("sys_msg_onlinetime not between", value1, value2, "sysMsgOnlinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeIsNull() {
            addCriterion("sys_msg_offlinetime is null");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeIsNotNull() {
            addCriterion("sys_msg_offlinetime is not null");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeEqualTo(Date value) {
            addCriterion("sys_msg_offlinetime =", value, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeNotEqualTo(Date value) {
            addCriterion("sys_msg_offlinetime <>", value, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeGreaterThan(Date value) {
            addCriterion("sys_msg_offlinetime >", value, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sys_msg_offlinetime >=", value, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeLessThan(Date value) {
            addCriterion("sys_msg_offlinetime <", value, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeLessThanOrEqualTo(Date value) {
            addCriterion("sys_msg_offlinetime <=", value, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeIn(List<Date> values) {
            addCriterion("sys_msg_offlinetime in", values, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeNotIn(List<Date> values) {
            addCriterion("sys_msg_offlinetime not in", values, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeBetween(Date value1, Date value2) {
            addCriterion("sys_msg_offlinetime between", value1, value2, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgOfflinetimeNotBetween(Date value1, Date value2) {
            addCriterion("sys_msg_offlinetime not between", value1, value2, "sysMsgOfflinetime");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleIsNull() {
            addCriterion("sys_msg_title is null");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleIsNotNull() {
            addCriterion("sys_msg_title is not null");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleEqualTo(String value) {
            addCriterion("sys_msg_title =", value, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleNotEqualTo(String value) {
            addCriterion("sys_msg_title <>", value, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleGreaterThan(String value) {
            addCriterion("sys_msg_title >", value, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleGreaterThanOrEqualTo(String value) {
            addCriterion("sys_msg_title >=", value, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleLessThan(String value) {
            addCriterion("sys_msg_title <", value, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleLessThanOrEqualTo(String value) {
            addCriterion("sys_msg_title <=", value, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleLike(String value) {
            addCriterion("sys_msg_title like", value, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleNotLike(String value) {
            addCriterion("sys_msg_title not like", value, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleIn(List<String> values) {
            addCriterion("sys_msg_title in", values, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleNotIn(List<String> values) {
            addCriterion("sys_msg_title not in", values, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleBetween(String value1, String value2) {
            addCriterion("sys_msg_title between", value1, value2, "sysMsgTitle");
            return (Criteria) this;
        }

        public Criteria andSysMsgTitleNotBetween(String value1, String value2) {
            addCriterion("sys_msg_title not between", value1, value2, "sysMsgTitle");
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