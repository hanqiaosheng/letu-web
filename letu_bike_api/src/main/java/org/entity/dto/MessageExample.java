package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public MessageExample() {
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

        public Criteria andMessageIdIsNull() {
            addCriterion("message_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("message_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(Long value) {
            addCriterion("message_id =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(Long value) {
            addCriterion("message_id <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(Long value) {
            addCriterion("message_id >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("message_id >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(Long value) {
            addCriterion("message_id <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(Long value) {
            addCriterion("message_id <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<Long> values) {
            addCriterion("message_id in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<Long> values) {
            addCriterion("message_id not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(Long value1, Long value2) {
            addCriterion("message_id between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(Long value1, Long value2) {
            addCriterion("message_id not between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIsNull() {
            addCriterion("message_user_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIsNotNull() {
            addCriterion("message_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdEqualTo(Long value) {
            addCriterion("message_user_id =", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotEqualTo(Long value) {
            addCriterion("message_user_id <>", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdGreaterThan(Long value) {
            addCriterion("message_user_id >", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("message_user_id >=", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdLessThan(Long value) {
            addCriterion("message_user_id <", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdLessThanOrEqualTo(Long value) {
            addCriterion("message_user_id <=", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIn(List<Long> values) {
            addCriterion("message_user_id in", values, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotIn(List<Long> values) {
            addCriterion("message_user_id not in", values, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdBetween(Long value1, Long value2) {
            addCriterion("message_user_id between", value1, value2, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotBetween(Long value1, Long value2) {
            addCriterion("message_user_id not between", value1, value2, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdIsNull() {
            addCriterion("message_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdIsNotNull() {
            addCriterion("message_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdEqualTo(Long value) {
            addCriterion("message_admin_id =", value, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdNotEqualTo(Long value) {
            addCriterion("message_admin_id <>", value, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdGreaterThan(Long value) {
            addCriterion("message_admin_id >", value, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("message_admin_id >=", value, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdLessThan(Long value) {
            addCriterion("message_admin_id <", value, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("message_admin_id <=", value, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdIn(List<Long> values) {
            addCriterion("message_admin_id in", values, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdNotIn(List<Long> values) {
            addCriterion("message_admin_id not in", values, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdBetween(Long value1, Long value2) {
            addCriterion("message_admin_id between", value1, value2, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andMessageAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("message_admin_id not between", value1, value2, "messageAdminId");
            return (Criteria) this;
        }

        public Criteria andTempletCodeIsNull() {
            addCriterion("templet_code is null");
            return (Criteria) this;
        }

        public Criteria andTempletCodeIsNotNull() {
            addCriterion("templet_code is not null");
            return (Criteria) this;
        }

        public Criteria andTempletCodeEqualTo(String value) {
            addCriterion("templet_code =", value, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeNotEqualTo(String value) {
            addCriterion("templet_code <>", value, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeGreaterThan(String value) {
            addCriterion("templet_code >", value, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeGreaterThanOrEqualTo(String value) {
            addCriterion("templet_code >=", value, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeLessThan(String value) {
            addCriterion("templet_code <", value, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeLessThanOrEqualTo(String value) {
            addCriterion("templet_code <=", value, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeLike(String value) {
            addCriterion("templet_code like", value, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeNotLike(String value) {
            addCriterion("templet_code not like", value, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeIn(List<String> values) {
            addCriterion("templet_code in", values, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeNotIn(List<String> values) {
            addCriterion("templet_code not in", values, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeBetween(String value1, String value2) {
            addCriterion("templet_code between", value1, value2, "templetCode");
            return (Criteria) this;
        }

        public Criteria andTempletCodeNotBetween(String value1, String value2) {
            addCriterion("templet_code not between", value1, value2, "templetCode");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNull() {
            addCriterion("message_content is null");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNotNull() {
            addCriterion("message_content is not null");
            return (Criteria) this;
        }

        public Criteria andMessageContentEqualTo(String value) {
            addCriterion("message_content =", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotEqualTo(String value) {
            addCriterion("message_content <>", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThan(String value) {
            addCriterion("message_content >", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThanOrEqualTo(String value) {
            addCriterion("message_content >=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThan(String value) {
            addCriterion("message_content <", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThanOrEqualTo(String value) {
            addCriterion("message_content <=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLike(String value) {
            addCriterion("message_content like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotLike(String value) {
            addCriterion("message_content not like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentIn(List<String> values) {
            addCriterion("message_content in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotIn(List<String> values) {
            addCriterion("message_content not in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentBetween(String value1, String value2) {
            addCriterion("message_content between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotBetween(String value1, String value2) {
            addCriterion("message_content not between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelIsNull() {
            addCriterion("message_isdel is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelIsNotNull() {
            addCriterion("message_isdel is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelEqualTo(Integer value) {
            addCriterion("message_isdel =", value, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelNotEqualTo(Integer value) {
            addCriterion("message_isdel <>", value, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelGreaterThan(Integer value) {
            addCriterion("message_isdel >", value, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_isdel >=", value, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelLessThan(Integer value) {
            addCriterion("message_isdel <", value, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelLessThanOrEqualTo(Integer value) {
            addCriterion("message_isdel <=", value, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelIn(List<Integer> values) {
            addCriterion("message_isdel in", values, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelNotIn(List<Integer> values) {
            addCriterion("message_isdel not in", values, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelBetween(Integer value1, Integer value2) {
            addCriterion("message_isdel between", value1, value2, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageIsdelNotBetween(Integer value1, Integer value2) {
            addCriterion("message_isdel not between", value1, value2, "messageIsdel");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeIsNull() {
            addCriterion("message_send_time is null");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeIsNotNull() {
            addCriterion("message_send_time is not null");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeEqualTo(Date value) {
            addCriterion("message_send_time =", value, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeNotEqualTo(Date value) {
            addCriterion("message_send_time <>", value, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeGreaterThan(Date value) {
            addCriterion("message_send_time >", value, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("message_send_time >=", value, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeLessThan(Date value) {
            addCriterion("message_send_time <", value, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("message_send_time <=", value, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeIn(List<Date> values) {
            addCriterion("message_send_time in", values, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeNotIn(List<Date> values) {
            addCriterion("message_send_time not in", values, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeBetween(Date value1, Date value2) {
            addCriterion("message_send_time between", value1, value2, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("message_send_time not between", value1, value2, "messageSendTime");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIsNull() {
            addCriterion("message_title is null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIsNotNull() {
            addCriterion("message_title is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleEqualTo(String value) {
            addCriterion("message_title =", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotEqualTo(String value) {
            addCriterion("message_title <>", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThan(String value) {
            addCriterion("message_title >", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThanOrEqualTo(String value) {
            addCriterion("message_title >=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThan(String value) {
            addCriterion("message_title <", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThanOrEqualTo(String value) {
            addCriterion("message_title <=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLike(String value) {
            addCriterion("message_title like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotLike(String value) {
            addCriterion("message_title not like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIn(List<String> values) {
            addCriterion("message_title in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotIn(List<String> values) {
            addCriterion("message_title not in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleBetween(String value1, String value2) {
            addCriterion("message_title between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotBetween(String value1, String value2) {
            addCriterion("message_title not between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceIsNull() {
            addCriterion("message_insurance is null");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceIsNotNull() {
            addCriterion("message_insurance is not null");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceEqualTo(Long value) {
            addCriterion("message_insurance =", value, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceNotEqualTo(Long value) {
            addCriterion("message_insurance <>", value, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceGreaterThan(Long value) {
            addCriterion("message_insurance >", value, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceGreaterThanOrEqualTo(Long value) {
            addCriterion("message_insurance >=", value, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceLessThan(Long value) {
            addCriterion("message_insurance <", value, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceLessThanOrEqualTo(Long value) {
            addCriterion("message_insurance <=", value, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceIn(List<Long> values) {
            addCriterion("message_insurance in", values, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceNotIn(List<Long> values) {
            addCriterion("message_insurance not in", values, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceBetween(Long value1, Long value2) {
            addCriterion("message_insurance between", value1, value2, "messageInsurance");
            return (Criteria) this;
        }

        public Criteria andMessageInsuranceNotBetween(Long value1, Long value2) {
            addCriterion("message_insurance not between", value1, value2, "messageInsurance");
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