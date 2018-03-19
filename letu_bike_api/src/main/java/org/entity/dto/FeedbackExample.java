package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class FeedbackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public FeedbackExample() {
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
        
        //渠道
        public Criteria andModelsChannelIdIn(List<Long> values) {
            addCriterion("models_channel_id in", values, "modelsChannelId");
            return (Criteria) this;
        }
        //用户名
        public Criteria andUserNameLike(String value) {
            addCriterion("user_realname like", value, "userRealname");
            return (Criteria) this;
        }
        
        //用户手机号
        public Criteria andUserTelLike(String value) {
            addCriterion("user_tel like", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdIsNull() {
            addCriterion("feedback_id is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdIsNotNull() {
            addCriterion("feedback_id is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdEqualTo(Long value) {
            addCriterion("feedback_id =", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotEqualTo(Long value) {
            addCriterion("feedback_id <>", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdGreaterThan(Long value) {
            addCriterion("feedback_id >", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("feedback_id >=", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdLessThan(Long value) {
            addCriterion("feedback_id <", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdLessThanOrEqualTo(Long value) {
            addCriterion("feedback_id <=", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdIn(List<Long> values) {
            addCriterion("feedback_id in", values, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotIn(List<Long> values) {
            addCriterion("feedback_id not in", values, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdBetween(Long value1, Long value2) {
            addCriterion("feedback_id between", value1, value2, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotBetween(Long value1, Long value2) {
            addCriterion("feedback_id not between", value1, value2, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andBikeIdIsNull() {
            addCriterion("f.bike_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeIdIsNotNull() {
            addCriterion("f.bike_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeIdEqualTo(Long value) {
            addCriterion("f.bike_id =", value, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdNotEqualTo(Long value) {
            addCriterion("f.bike_id <>", value, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdGreaterThan(Long value) {
            addCriterion("bike_id >", value, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_id >=", value, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdLessThan(Long value) {
            addCriterion("bike_id <", value, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_id <=", value, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdIn(List<Long> values) {
            addCriterion("bike_id in", values, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdNotIn(List<Long> values) {
            addCriterion("bike_id not in", values, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdBetween(Long value1, Long value2) {
            addCriterion("bike_id between", value1, value2, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_id not between", value1, value2, "bikeId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andFeedbackTypeIdIsNull() {
            addCriterion("feedback_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdIsNotNull() {
            addCriterion("feedback_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdEqualTo(Long value) {
            addCriterion("feedback_type_id =", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdNotEqualTo(Long value) {
            addCriterion("feedback_type_id <>", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdGreaterThan(Long value) {
            addCriterion("feedback_type_id >", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("feedback_type_id >=", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdLessThan(Long value) {
            addCriterion("feedback_type_id <", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("feedback_type_id <=", value, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdIn(List<Long> values) {
            addCriterion("feedback_type_id in", values, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdNotIn(List<Long> values) {
            addCriterion("feedback_type_id not in", values, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdBetween(Long value1, Long value2) {
            addCriterion("feedback_type_id between", value1, value2, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("feedback_type_id not between", value1, value2, "feedbackTypeId");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIsNull() {
            addCriterion("feedback_time is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIsNotNull() {
            addCriterion("feedback_time is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeEqualTo(Date value) {
            addCriterion("feedback_time =", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotEqualTo(Date value) {
            addCriterion("feedback_time <>", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeGreaterThan(Date value) {
            addCriterion("feedback_time >", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("feedback_time >=", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeLessThan(Date value) {
            addCriterion("feedback_time <", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeLessThanOrEqualTo(Date value) {
            addCriterion("feedback_time <=", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIn(List<Date> values) {
            addCriterion("feedback_time in", values, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotIn(List<Date> values) {
            addCriterion("feedback_time not in", values, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeBetween(Date value1, Date value2) {
            addCriterion("feedback_time between", value1, value2, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotBetween(Date value1, Date value2) {
            addCriterion("feedback_time not between", value1, value2, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentIsNull() {
            addCriterion("feedback_content is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentIsNotNull() {
            addCriterion("feedback_content is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentEqualTo(String value) {
            addCriterion("feedback_content =", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentNotEqualTo(String value) {
            addCriterion("feedback_content <>", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentGreaterThan(String value) {
            addCriterion("feedback_content >", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_content >=", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentLessThan(String value) {
            addCriterion("feedback_content <", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentLessThanOrEqualTo(String value) {
            addCriterion("feedback_content <=", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentLike(String value) {
            addCriterion("feedback_content like", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentNotLike(String value) {
            addCriterion("feedback_content not like", value, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentIn(List<String> values) {
            addCriterion("feedback_content in", values, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentNotIn(List<String> values) {
            addCriterion("feedback_content not in", values, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentBetween(String value1, String value2) {
            addCriterion("feedback_content between", value1, value2, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackContentNotBetween(String value1, String value2) {
            addCriterion("feedback_content not between", value1, value2, "feedbackContent");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateIsNull() {
            addCriterion("feedback_state is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateIsNotNull() {
            addCriterion("feedback_state is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateEqualTo(Integer value) {
            addCriterion("feedback_state =", value, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateNotEqualTo(Integer value) {
            addCriterion("feedback_state <>", value, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateGreaterThan(Integer value) {
            addCriterion("feedback_state >", value, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("feedback_state >=", value, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateLessThan(Integer value) {
            addCriterion("feedback_state <", value, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateLessThanOrEqualTo(Integer value) {
            addCriterion("feedback_state <=", value, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateIn(List<Integer> values) {
            addCriterion("feedback_state in", values, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateNotIn(List<Integer> values) {
            addCriterion("feedback_state not in", values, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateBetween(Integer value1, Integer value2) {
            addCriterion("feedback_state between", value1, value2, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackStateNotBetween(Integer value1, Integer value2) {
            addCriterion("feedback_state not between", value1, value2, "feedbackState");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeIsNull() {
            addCriterion("feedback_dealtime is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeIsNotNull() {
            addCriterion("feedback_dealtime is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeEqualTo(Date value) {
            addCriterion("feedback_dealtime =", value, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeNotEqualTo(Date value) {
            addCriterion("feedback_dealtime <>", value, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeGreaterThan(Date value) {
            addCriterion("feedback_dealtime >", value, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("feedback_dealtime >=", value, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeLessThan(Date value) {
            addCriterion("feedback_dealtime <", value, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeLessThanOrEqualTo(Date value) {
            addCriterion("feedback_dealtime <=", value, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeIn(List<Date> values) {
            addCriterion("feedback_dealtime in", values, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeNotIn(List<Date> values) {
            addCriterion("feedback_dealtime not in", values, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeBetween(Date value1, Date value2) {
            addCriterion("feedback_dealtime between", value1, value2, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackDealtimeNotBetween(Date value1, Date value2) {
            addCriterion("feedback_dealtime not between", value1, value2, "feedbackDealtime");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressIsNull() {
            addCriterion("feedback_picaddress is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressIsNotNull() {
            addCriterion("feedback_picaddress is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressEqualTo(String value) {
            addCriterion("feedback_picaddress =", value, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressNotEqualTo(String value) {
            addCriterion("feedback_picaddress <>", value, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressGreaterThan(String value) {
            addCriterion("feedback_picaddress >", value, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_picaddress >=", value, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressLessThan(String value) {
            addCriterion("feedback_picaddress <", value, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressLessThanOrEqualTo(String value) {
            addCriterion("feedback_picaddress <=", value, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressLike(String value) {
            addCriterion("feedback_picaddress like", value, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressNotLike(String value) {
            addCriterion("feedback_picaddress not like", value, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressIn(List<String> values) {
            addCriterion("feedback_picaddress in", values, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressNotIn(List<String> values) {
            addCriterion("feedback_picaddress not in", values, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressBetween(String value1, String value2) {
            addCriterion("feedback_picaddress between", value1, value2, "feedbackPicaddress");
            return (Criteria) this;
        }

        public Criteria andFeedbackPicaddressNotBetween(String value1, String value2) {
            addCriterion("feedback_picaddress not between", value1, value2, "feedbackPicaddress");
            return (Criteria) this;
        }
        
        public Criteria andFeedbackRefuseReasonIsNull() {
            addCriterion("feedback_refuse_reason is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonIsNotNull() {
            addCriterion("feedback_refuse_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonEqualTo(String value) {
            addCriterion("feedback_refuse_reason =", value, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonNotEqualTo(String value) {
            addCriterion("feedback_refuse_reason <>", value, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonGreaterThan(String value) {
            addCriterion("feedback_refuse_reason >", value, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_refuse_reason >=", value, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonLessThan(String value) {
            addCriterion("feedback_refuse_reason <", value, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonLessThanOrEqualTo(String value) {
            addCriterion("feedback_refuse_reason <=", value, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonLike(String value) {
            addCriterion("feedback_refuse_reason like", value, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonNotLike(String value) {
            addCriterion("feedback_refuse_reason not like", value, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonIn(List<String> values) {
            addCriterion("feedback_refuse_reason in", values, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonNotIn(List<String> values) {
            addCriterion("feedback_refuse_reason not in", values, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonBetween(String value1, String value2) {
            addCriterion("feedback_refuse_reason between", value1, value2, "feedbackRefuseReason");
            return (Criteria) this;
        }

        public Criteria andFeedbackRefuseReasonNotBetween(String value1, String value2) {
            addCriterion("feedback_refuse_reason not between", value1, value2, "feedbackRefuseReason");
            return (Criteria) this;
        }
        
        public Criteria andFeedbackRemarkIsNull() {
            addCriterion("feedback_remark is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkIsNotNull() {
            addCriterion("feedback_remark is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkEqualTo(String value) {
            addCriterion("feedback_remark =", value, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkNotEqualTo(String value) {
            addCriterion("feedback_remark <>", value, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkGreaterThan(String value) {
            addCriterion("feedback_remark >", value, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_remark >=", value, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkLessThan(String value) {
            addCriterion("feedback_remark <", value, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkLessThanOrEqualTo(String value) {
            addCriterion("feedback_remark <=", value, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkLike(String value) {
            addCriterion("feedback_remark like", value, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkNotLike(String value) {
            addCriterion("feedback_remark not like", value, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkIn(List<String> values) {
            addCriterion("feedback_remark in", values, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkNotIn(List<String> values) {
            addCriterion("feedback_remark not in", values, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkBetween(String value1, String value2) {
            addCriterion("feedback_remark between", value1, value2, "feedbackRemark");
            return (Criteria) this;
        }

        public Criteria andFeedbackRemarkNotBetween(String value1, String value2) {
            addCriterion("feedback_remark not between", value1, value2, "feedbackRemark");
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