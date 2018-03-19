package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TicketExample() {
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

        public Criteria andTicketIdIsNull() {
            addCriterion("ticket_id is null");
            return (Criteria) this;
        }

        public Criteria andTicketIdIsNotNull() {
            addCriterion("ticket_id is not null");
            return (Criteria) this;
        }

        public Criteria andTicketIdEqualTo(Long value) {
            addCriterion("ticket_id =", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotEqualTo(Long value) {
            addCriterion("ticket_id <>", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThan(Long value) {
            addCriterion("ticket_id >", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ticket_id >=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThan(Long value) {
            addCriterion("ticket_id <", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThanOrEqualTo(Long value) {
            addCriterion("ticket_id <=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdIn(List<Long> values) {
            addCriterion("ticket_id in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotIn(List<Long> values) {
            addCriterion("ticket_id not in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdBetween(Long value1, Long value2) {
            addCriterion("ticket_id between", value1, value2, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotBetween(Long value1, Long value2) {
            addCriterion("ticket_id not between", value1, value2, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketNameIsNull() {
            addCriterion("ticket_name is null");
            return (Criteria) this;
        }

        public Criteria andTicketNameIsNotNull() {
            addCriterion("ticket_name is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNameEqualTo(String value) {
            addCriterion("ticket_name =", value, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameNotEqualTo(String value) {
            addCriterion("ticket_name <>", value, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameGreaterThan(String value) {
            addCriterion("ticket_name >", value, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameGreaterThanOrEqualTo(String value) {
            addCriterion("ticket_name >=", value, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameLessThan(String value) {
            addCriterion("ticket_name <", value, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameLessThanOrEqualTo(String value) {
            addCriterion("ticket_name <=", value, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameLike(String value) {
            addCriterion("ticket_name like", value, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameNotLike(String value) {
            addCriterion("ticket_name not like", value, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameIn(List<String> values) {
            addCriterion("ticket_name in", values, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameNotIn(List<String> values) {
            addCriterion("ticket_name not in", values, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameBetween(String value1, String value2) {
            addCriterion("ticket_name between", value1, value2, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketNameNotBetween(String value1, String value2) {
            addCriterion("ticket_name not between", value1, value2, "ticketName");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeIsNull() {
            addCriterion("ticket_validity_start_time is null");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeIsNotNull() {
            addCriterion("ticket_validity_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeEqualTo(Date value) {
            addCriterion("ticket_validity_start_time =", value, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeNotEqualTo(Date value) {
            addCriterion("ticket_validity_start_time <>", value, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeGreaterThan(Date value) {
            addCriterion("ticket_validity_start_time >", value, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ticket_validity_start_time >=", value, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeLessThan(Date value) {
            addCriterion("ticket_validity_start_time <", value, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("ticket_validity_start_time <=", value, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeIn(List<Date> values) {
            addCriterion("ticket_validity_start_time in", values, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeNotIn(List<Date> values) {
            addCriterion("ticket_validity_start_time not in", values, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeBetween(Date value1, Date value2) {
            addCriterion("ticket_validity_start_time between", value1, value2, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("ticket_validity_start_time not between", value1, value2, "ticketValidityStartTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeIsNull() {
            addCriterion("ticket_validity_end_time is null");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeIsNotNull() {
            addCriterion("ticket_validity_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeEqualTo(Date value) {
            addCriterion("ticket_validity_end_time =", value, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeNotEqualTo(Date value) {
            addCriterion("ticket_validity_end_time <>", value, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeGreaterThan(Date value) {
            addCriterion("ticket_validity_end_time >", value, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ticket_validity_end_time >=", value, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeLessThan(Date value) {
            addCriterion("ticket_validity_end_time <", value, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("ticket_validity_end_time <=", value, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeIn(List<Date> values) {
            addCriterion("ticket_validity_end_time in", values, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeNotIn(List<Date> values) {
            addCriterion("ticket_validity_end_time not in", values, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeBetween(Date value1, Date value2) {
            addCriterion("ticket_validity_end_time between", value1, value2, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketValidityEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("ticket_validity_end_time not between", value1, value2, "ticketValidityEndTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeIsNull() {
            addCriterion("ticket_create_time is null");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeIsNotNull() {
            addCriterion("ticket_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeEqualTo(Date value) {
            addCriterion("ticket_create_time =", value, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeNotEqualTo(Date value) {
            addCriterion("ticket_create_time <>", value, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeGreaterThan(Date value) {
            addCriterion("ticket_create_time >", value, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ticket_create_time >=", value, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeLessThan(Date value) {
            addCriterion("ticket_create_time <", value, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("ticket_create_time <=", value, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeIn(List<Date> values) {
            addCriterion("ticket_create_time in", values, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeNotIn(List<Date> values) {
            addCriterion("ticket_create_time not in", values, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeBetween(Date value1, Date value2) {
            addCriterion("ticket_create_time between", value1, value2, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("ticket_create_time not between", value1, value2, "ticketCreateTime");
            return (Criteria) this;
        }

        public Criteria andTicketStateIsNull() {
            addCriterion("ticket_state is null");
            return (Criteria) this;
        }

        public Criteria andTicketStateIsNotNull() {
            addCriterion("ticket_state is not null");
            return (Criteria) this;
        }

        public Criteria andTicketStateEqualTo(Integer value) {
            addCriterion("ticket_state =", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateNotEqualTo(Integer value) {
            addCriterion("ticket_state <>", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateGreaterThan(Integer value) {
            addCriterion("ticket_state >", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("ticket_state >=", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateLessThan(Integer value) {
            addCriterion("ticket_state <", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateLessThanOrEqualTo(Integer value) {
            addCriterion("ticket_state <=", value, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateIn(List<Integer> values) {
            addCriterion("ticket_state in", values, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateNotIn(List<Integer> values) {
            addCriterion("ticket_state not in", values, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateBetween(Integer value1, Integer value2) {
            addCriterion("ticket_state between", value1, value2, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketStateNotBetween(Integer value1, Integer value2) {
            addCriterion("ticket_state not between", value1, value2, "ticketState");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeIsNull() {
            addCriterion("ticket_redeem_code is null");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeIsNotNull() {
            addCriterion("ticket_redeem_code is not null");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeEqualTo(String value) {
            addCriterion("ticket_redeem_code =", value, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeNotEqualTo(String value) {
            addCriterion("ticket_redeem_code <>", value, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeGreaterThan(String value) {
            addCriterion("ticket_redeem_code >", value, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ticket_redeem_code >=", value, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeLessThan(String value) {
            addCriterion("ticket_redeem_code <", value, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeLessThanOrEqualTo(String value) {
            addCriterion("ticket_redeem_code <=", value, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeLike(String value) {
            addCriterion("ticket_redeem_code like", value, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeNotLike(String value) {
            addCriterion("ticket_redeem_code not like", value, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeIn(List<String> values) {
            addCriterion("ticket_redeem_code in", values, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeNotIn(List<String> values) {
            addCriterion("ticket_redeem_code not in", values, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeBetween(String value1, String value2) {
            addCriterion("ticket_redeem_code between", value1, value2, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemCodeNotBetween(String value1, String value2) {
            addCriterion("ticket_redeem_code not between", value1, value2, "ticketRedeemCode");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageIsNull() {
            addCriterion("ticket_redeem_image is null");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageIsNotNull() {
            addCriterion("ticket_redeem_image is not null");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageEqualTo(String value) {
            addCriterion("ticket_redeem_image =", value, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageNotEqualTo(String value) {
            addCriterion("ticket_redeem_image <>", value, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageGreaterThan(String value) {
            addCriterion("ticket_redeem_image >", value, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageGreaterThanOrEqualTo(String value) {
            addCriterion("ticket_redeem_image >=", value, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageLessThan(String value) {
            addCriterion("ticket_redeem_image <", value, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageLessThanOrEqualTo(String value) {
            addCriterion("ticket_redeem_image <=", value, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageLike(String value) {
            addCriterion("ticket_redeem_image like", value, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageNotLike(String value) {
            addCriterion("ticket_redeem_image not like", value, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageIn(List<String> values) {
            addCriterion("ticket_redeem_image in", values, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageNotIn(List<String> values) {
            addCriterion("ticket_redeem_image not in", values, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageBetween(String value1, String value2) {
            addCriterion("ticket_redeem_image between", value1, value2, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketRedeemImageNotBetween(String value1, String value2) {
            addCriterion("ticket_redeem_image not between", value1, value2, "ticketRedeemImage");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdIsNull() {
            addCriterion("ticket_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdIsNotNull() {
            addCriterion("ticket_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdEqualTo(Long value) {
            addCriterion("ticket_channel_id =", value, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdNotEqualTo(Long value) {
            addCriterion("ticket_channel_id <>", value, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdGreaterThan(Long value) {
            addCriterion("ticket_channel_id >", value, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ticket_channel_id >=", value, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdLessThan(Long value) {
            addCriterion("ticket_channel_id <", value, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("ticket_channel_id <=", value, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdIn(List<Long> values) {
            addCriterion("ticket_channel_id in", values, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdNotIn(List<Long> values) {
            addCriterion("ticket_channel_id not in", values, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdBetween(Long value1, Long value2) {
            addCriterion("ticket_channel_id between", value1, value2, "ticketChannelId");
            return (Criteria) this;
        }

        public Criteria andTicketChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("ticket_channel_id not between", value1, value2, "ticketChannelId");
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