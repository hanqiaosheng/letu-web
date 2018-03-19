package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FixedReturnExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public FixedReturnExample() {
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
        public Criteria andChannelNameLike(String value) {
            addCriterion("channel_name like", value, "channelName");
            return (Criteria) this;
        }
        
        public Criteria andAdminNameLike(String value) {
            addCriterion("admin_realname like", value, "adminRealname");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdIsNull() {
            addCriterion("fixed_return_id is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdIsNotNull() {
            addCriterion("fixed_return_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdEqualTo(Long value) {
            addCriterion("fixed_return_id =", value, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdNotEqualTo(Long value) {
            addCriterion("fixed_return_id <>", value, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdGreaterThan(Long value) {
            addCriterion("fixed_return_id >", value, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fixed_return_id >=", value, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdLessThan(Long value) {
            addCriterion("fixed_return_id <", value, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdLessThanOrEqualTo(Long value) {
            addCriterion("fixed_return_id <=", value, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdIn(List<Long> values) {
            addCriterion("fixed_return_id in", values, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdNotIn(List<Long> values) {
            addCriterion("fixed_return_id not in", values, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdBetween(Long value1, Long value2) {
            addCriterion("fixed_return_id between", value1, value2, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnIdNotBetween(Long value1, Long value2) {
            addCriterion("fixed_return_id not between", value1, value2, "fixedReturnId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatIsNull() {
            addCriterion("fixed_return_lat is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatIsNotNull() {
            addCriterion("fixed_return_lat is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatEqualTo(Double value) {
            addCriterion("fixed_return_lat =", value, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatNotEqualTo(Double value) {
            addCriterion("fixed_return_lat <>", value, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatGreaterThan(Double value) {
            addCriterion("fixed_return_lat >", value, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatGreaterThanOrEqualTo(Double value) {
            addCriterion("fixed_return_lat >=", value, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatLessThan(Double value) {
            addCriterion("fixed_return_lat <", value, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatLessThanOrEqualTo(Double value) {
            addCriterion("fixed_return_lat <=", value, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatIn(List<Double> values) {
            addCriterion("fixed_return_lat in", values, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatNotIn(List<Double> values) {
            addCriterion("fixed_return_lat not in", values, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatBetween(Double value1, Double value2) {
            addCriterion("fixed_return_lat between", value1, value2, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLatNotBetween(Double value1, Double value2) {
            addCriterion("fixed_return_lat not between", value1, value2, "fixedReturnLat");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngIsNull() {
            addCriterion("fixed_return_lng is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngIsNotNull() {
            addCriterion("fixed_return_lng is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngEqualTo(Double value) {
            addCriterion("fixed_return_lng =", value, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngNotEqualTo(Double value) {
            addCriterion("fixed_return_lng <>", value, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngGreaterThan(Double value) {
            addCriterion("fixed_return_lng >", value, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngGreaterThanOrEqualTo(Double value) {
            addCriterion("fixed_return_lng >=", value, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngLessThan(Double value) {
            addCriterion("fixed_return_lng <", value, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngLessThanOrEqualTo(Double value) {
            addCriterion("fixed_return_lng <=", value, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngIn(List<Double> values) {
            addCriterion("fixed_return_lng in", values, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngNotIn(List<Double> values) {
            addCriterion("fixed_return_lng not in", values, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngBetween(Double value1, Double value2) {
            addCriterion("fixed_return_lng between", value1, value2, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnLngNotBetween(Double value1, Double value2) {
            addCriterion("fixed_return_lng not between", value1, value2, "fixedReturnLng");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceIsNull() {
            addCriterion("fixed_return_distance is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceIsNotNull() {
            addCriterion("fixed_return_distance is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceEqualTo(Double value) {
            addCriterion("fixed_return_distance =", value, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceNotEqualTo(Double value) {
            addCriterion("fixed_return_distance <>", value, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceGreaterThan(Double value) {
            addCriterion("fixed_return_distance >", value, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceGreaterThanOrEqualTo(Double value) {
            addCriterion("fixed_return_distance >=", value, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceLessThan(Double value) {
            addCriterion("fixed_return_distance <", value, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceLessThanOrEqualTo(Double value) {
            addCriterion("fixed_return_distance <=", value, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceIn(List<Double> values) {
            addCriterion("fixed_return_distance in", values, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceNotIn(List<Double> values) {
            addCriterion("fixed_return_distance not in", values, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceBetween(Double value1, Double value2) {
            addCriterion("fixed_return_distance between", value1, value2, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnDistanceNotBetween(Double value1, Double value2) {
            addCriterion("fixed_return_distance not between", value1, value2, "fixedReturnDistance");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdIsNull() {
            addCriterion("fixed_return_models_id is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdIsNotNull() {
            addCriterion("fixed_return_models_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdEqualTo(String value) {
            addCriterion("fixed_return_models_id =", value, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdNotEqualTo(String value) {
            addCriterion("fixed_return_models_id <>", value, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdGreaterThan(String value) {
            addCriterion("fixed_return_models_id >", value, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdGreaterThanOrEqualTo(String value) {
            addCriterion("fixed_return_models_id >=", value, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdLessThan(String value) {
            addCriterion("fixed_return_models_id <", value, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdLessThanOrEqualTo(String value) {
            addCriterion("fixed_return_models_id <=", value, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdLike(String value) {
            addCriterion("fixed_return_models_id like", value, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdNotLike(String value) {
            addCriterion("fixed_return_models_id not like", value, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdIn(List<String> values) {
            addCriterion("fixed_return_models_id in", values, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdNotIn(List<String> values) {
            addCriterion("fixed_return_models_id not in", values, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdBetween(String value1, String value2) {
            addCriterion("fixed_return_models_id between", value1, value2, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnModelsIdNotBetween(String value1, String value2) {
            addCriterion("fixed_return_models_id not between", value1, value2, "fixedReturnModelsId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameIsNull() {
            addCriterion("fixed_return_name is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameIsNotNull() {
            addCriterion("fixed_return_name is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameEqualTo(String value) {
            addCriterion("fixed_return_name =", value, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameNotEqualTo(String value) {
            addCriterion("fixed_return_name <>", value, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameGreaterThan(String value) {
            addCriterion("fixed_return_name >", value, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameGreaterThanOrEqualTo(String value) {
            addCriterion("fixed_return_name >=", value, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameLessThan(String value) {
            addCriterion("fixed_return_name <", value, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameLessThanOrEqualTo(String value) {
            addCriterion("fixed_return_name <=", value, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameLike(String value) {
            addCriterion("fixed_return_name like", value, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameNotLike(String value) {
            addCriterion("fixed_return_name not like", value, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameIn(List<String> values) {
            addCriterion("fixed_return_name in", values, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameNotIn(List<String> values) {
            addCriterion("fixed_return_name not in", values, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameBetween(String value1, String value2) {
            addCriterion("fixed_return_name between", value1, value2, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnNameNotBetween(String value1, String value2) {
            addCriterion("fixed_return_name not between", value1, value2, "fixedReturnName");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeIsNull() {
            addCriterion("fixed_return_createtime is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeIsNotNull() {
            addCriterion("fixed_return_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeEqualTo(Date value) {
            addCriterion("fixed_return_createtime =", value, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeNotEqualTo(Date value) {
            addCriterion("fixed_return_createtime <>", value, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeGreaterThan(Date value) {
            addCriterion("fixed_return_createtime >", value, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fixed_return_createtime >=", value, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeLessThan(Date value) {
            addCriterion("fixed_return_createtime <", value, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("fixed_return_createtime <=", value, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeIn(List<Date> values) {
            addCriterion("fixed_return_createtime in", values, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeNotIn(List<Date> values) {
            addCriterion("fixed_return_createtime not in", values, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeBetween(Date value1, Date value2) {
            addCriterion("fixed_return_createtime between", value1, value2, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("fixed_return_createtime not between", value1, value2, "fixedReturnCreatetime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdIsNull() {
            addCriterion("fixed_return_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdIsNotNull() {
            addCriterion("fixed_return_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdEqualTo(Long value) {
            addCriterion("fixed_return_admin_id =", value, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdNotEqualTo(Long value) {
            addCriterion("fixed_return_admin_id <>", value, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdGreaterThan(Long value) {
            addCriterion("fixed_return_admin_id >", value, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fixed_return_admin_id >=", value, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdLessThan(Long value) {
            addCriterion("fixed_return_admin_id <", value, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("fixed_return_admin_id <=", value, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdIn(List<Long> values) {
            addCriterion("fixed_return_admin_id in", values, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdNotIn(List<Long> values) {
            addCriterion("fixed_return_admin_id not in", values, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdBetween(Long value1, Long value2) {
            addCriterion("fixed_return_admin_id between", value1, value2, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("fixed_return_admin_id not between", value1, value2, "fixedReturnAdminId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateIsNull() {
            addCriterion("fixed_return_state is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateIsNotNull() {
            addCriterion("fixed_return_state is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateEqualTo(Integer value) {
            addCriterion("fixed_return_state =", value, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateNotEqualTo(Integer value) {
            addCriterion("fixed_return_state <>", value, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateGreaterThan(Integer value) {
            addCriterion("fixed_return_state >", value, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("fixed_return_state >=", value, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateLessThan(Integer value) {
            addCriterion("fixed_return_state <", value, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateLessThanOrEqualTo(Integer value) {
            addCriterion("fixed_return_state <=", value, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateIn(List<Integer> values) {
            addCriterion("fixed_return_state in", values, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateNotIn(List<Integer> values) {
            addCriterion("fixed_return_state not in", values, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateBetween(Integer value1, Integer value2) {
            addCriterion("fixed_return_state between", value1, value2, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnStateNotBetween(Integer value1, Integer value2) {
            addCriterion("fixed_return_state not between", value1, value2, "fixedReturnState");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefIsNull() {
            addCriterion("fixed_return_brief is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefIsNotNull() {
            addCriterion("fixed_return_brief is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefEqualTo(String value) {
            addCriterion("fixed_return_brief =", value, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefNotEqualTo(String value) {
            addCriterion("fixed_return_brief <>", value, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefGreaterThan(String value) {
            addCriterion("fixed_return_brief >", value, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefGreaterThanOrEqualTo(String value) {
            addCriterion("fixed_return_brief >=", value, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefLessThan(String value) {
            addCriterion("fixed_return_brief <", value, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefLessThanOrEqualTo(String value) {
            addCriterion("fixed_return_brief <=", value, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefLike(String value) {
            addCriterion("fixed_return_brief like", value, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefNotLike(String value) {
            addCriterion("fixed_return_brief not like", value, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefIn(List<String> values) {
            addCriterion("fixed_return_brief in", values, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefNotIn(List<String> values) {
            addCriterion("fixed_return_brief not in", values, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefBetween(String value1, String value2) {
            addCriterion("fixed_return_brief between", value1, value2, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBriefNotBetween(String value1, String value2) {
            addCriterion("fixed_return_brief not between", value1, value2, "fixedReturnBrief");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelIsNull() {
            addCriterion("fixed_return_tel is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelIsNotNull() {
            addCriterion("fixed_return_tel is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelEqualTo(String value) {
            addCriterion("fixed_return_tel =", value, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelNotEqualTo(String value) {
            addCriterion("fixed_return_tel <>", value, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelGreaterThan(String value) {
            addCriterion("fixed_return_tel >", value, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelGreaterThanOrEqualTo(String value) {
            addCriterion("fixed_return_tel >=", value, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelLessThan(String value) {
            addCriterion("fixed_return_tel <", value, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelLessThanOrEqualTo(String value) {
            addCriterion("fixed_return_tel <=", value, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelLike(String value) {
            addCriterion("fixed_return_tel like", value, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelNotLike(String value) {
            addCriterion("fixed_return_tel not like", value, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelIn(List<String> values) {
            addCriterion("fixed_return_tel in", values, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelNotIn(List<String> values) {
            addCriterion("fixed_return_tel not in", values, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelBetween(String value1, String value2) {
            addCriterion("fixed_return_tel between", value1, value2, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnTelNotBetween(String value1, String value2) {
            addCriterion("fixed_return_tel not between", value1, value2, "fixedReturnTel");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageIsNull() {
            addCriterion("fixed_return_image is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageIsNotNull() {
            addCriterion("fixed_return_image is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageEqualTo(String value) {
            addCriterion("fixed_return_image =", value, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageNotEqualTo(String value) {
            addCriterion("fixed_return_image <>", value, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageGreaterThan(String value) {
            addCriterion("fixed_return_image >", value, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageGreaterThanOrEqualTo(String value) {
            addCriterion("fixed_return_image >=", value, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageLessThan(String value) {
            addCriterion("fixed_return_image <", value, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageLessThanOrEqualTo(String value) {
            addCriterion("fixed_return_image <=", value, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageLike(String value) {
            addCriterion("fixed_return_image like", value, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageNotLike(String value) {
            addCriterion("fixed_return_image not like", value, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageIn(List<String> values) {
            addCriterion("fixed_return_image in", values, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageNotIn(List<String> values) {
            addCriterion("fixed_return_image not in", values, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageBetween(String value1, String value2) {
            addCriterion("fixed_return_image between", value1, value2, "fixedReturnImage");
            return (Criteria) this;
        }

        public Criteria andFixedReturnImageNotBetween(String value1, String value2) {
            addCriterion("fixed_return_image not between", value1, value2, "fixedReturnImage");
            return (Criteria) this;
        }
        
        public Criteria andFixedReturnBlockIdIsNull() {
            addCriterion("fixed_return_block_id is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdIsNotNull() {
            addCriterion("fixed_return_block_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdEqualTo(Long value) {
            addCriterion("fixed_return_block_id =", value, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdNotEqualTo(Long value) {
            addCriterion("fixed_return_block_id <>", value, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdGreaterThan(Long value) {
            addCriterion("fixed_return_block_id >", value, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fixed_return_block_id >=", value, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdLessThan(Long value) {
            addCriterion("fixed_return_block_id <", value, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdLessThanOrEqualTo(Long value) {
            addCriterion("fixed_return_block_id <=", value, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdIn(List<Long> values) {
            addCriterion("fixed_return_block_id in", values, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdNotIn(List<Long> values) {
            addCriterion("fixed_return_block_id not in", values, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdBetween(Long value1, Long value2) {
            addCriterion("fixed_return_block_id between", value1, value2, "fixedReturnBlockId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIdNotBetween(Long value1, Long value2) {
            addCriterion("fixed_return_block_id not between", value1, value2, "fixedReturnBlockId");
            return (Criteria) this;
        }
        
        public Criteria andFixedReturnChannelIdIsNull() {
            addCriterion("fixed_return_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdIsNotNull() {
            addCriterion("fixed_return_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdEqualTo(Long value) {
            addCriterion("fixed_return_channel_id =", value, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdNotEqualTo(Long value) {
            addCriterion("fixed_return_channel_id <>", value, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdGreaterThan(Long value) {
            addCriterion("fixed_return_channel_id >", value, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fixed_return_channel_id >=", value, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdLessThan(Long value) {
            addCriterion("fixed_return_channel_id <", value, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("fixed_return_channel_id <=", value, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdIn(List<Long> values) {
            addCriterion("fixed_return_channel_id in", values, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdNotIn(List<Long> values) {
            addCriterion("fixed_return_channel_id not in", values, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdBetween(Long value1, Long value2) {
            addCriterion("fixed_return_channel_id between", value1, value2, "fixedReturnChannelId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("fixed_return_channel_id not between", value1, value2, "fixedReturnChannelId");
            return (Criteria) this;
        }
        public Criteria andFixedReturnBikeNumIsNull() {
            addCriterion("fixed_return_bike_num is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumIsNotNull() {
            addCriterion("fixed_return_bike_num is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumEqualTo(Integer value) {
            addCriterion("fixed_return_bike_num =", value, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumNotEqualTo(Integer value) {
            addCriterion("fixed_return_bike_num <>", value, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumGreaterThan(Integer value) {
            addCriterion("fixed_return_bike_num >", value, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("fixed_return_bike_num >=", value, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumLessThan(Integer value) {
            addCriterion("fixed_return_bike_num <", value, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumLessThanOrEqualTo(Integer value) {
            addCriterion("fixed_return_bike_num <=", value, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumIn(List<Integer> values) {
            addCriterion("fixed_return_bike_num in", values, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumNotIn(List<Integer> values) {
            addCriterion("fixed_return_bike_num not in", values, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumBetween(Integer value1, Integer value2) {
            addCriterion("fixed_return_bike_num between", value1, value2, "fixedReturnBikeNum");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBikeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("fixed_return_bike_num not between", value1, value2, "fixedReturnBikeNum");
            return (Criteria) this;
        }
        
        public Criteria andFixedReturnOriginatorIdIsNull() {
            addCriterion("fixed_return_originator_id is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdIsNotNull() {
            addCriterion("fixed_return_originator_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdEqualTo(Long value) {
            addCriterion("fixed_return_originator_id =", value, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdNotEqualTo(Long value) {
            addCriterion("fixed_return_originator_id <>", value, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdGreaterThan(Long value) {
            addCriterion("fixed_return_originator_id >", value, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fixed_return_originator_id >=", value, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdLessThan(Long value) {
            addCriterion("fixed_return_originator_id <", value, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdLessThanOrEqualTo(Long value) {
            addCriterion("fixed_return_originator_id <=", value, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdIn(List<Long> values) {
            addCriterion("fixed_return_originator_id in", values, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdNotIn(List<Long> values) {
            addCriterion("fixed_return_originator_id not in", values, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdBetween(Long value1, Long value2) {
            addCriterion("fixed_return_originator_id between", value1, value2, "fixedReturnOriginatorId");
            return (Criteria) this;
        }

        public Criteria andFixedReturnOriginatorIdNotBetween(Long value1, Long value2) {
            addCriterion("fixed_return_originator_id not between", value1, value2, "fixedReturnOriginatorId");
            return (Criteria) this;
        }
        public Criteria andFixedReturnFixedTimeIsNull() {
            addCriterion("fixed_return_fixed_time is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeIsNotNull() {
            addCriterion("fixed_return_fixed_time is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeEqualTo(Date value) {
            addCriterion("fixed_return_fixed_time =", value, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeNotEqualTo(Date value) {
            addCriterion("fixed_return_fixed_time <>", value, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeGreaterThan(Date value) {
            addCriterion("fixed_return_fixed_time >", value, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fixed_return_fixed_time >=", value, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeLessThan(Date value) {
            addCriterion("fixed_return_fixed_time <", value, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeLessThanOrEqualTo(Date value) {
            addCriterion("fixed_return_fixed_time <=", value, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeIn(List<Date> values) {
            addCriterion("fixed_return_fixed_time in", values, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeNotIn(List<Date> values) {
            addCriterion("fixed_return_fixed_time not in", values, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeBetween(Date value1, Date value2) {
            addCriterion("fixed_return_fixed_time between", value1, value2, "fixedReturnFixedTime");
            return (Criteria) this;
        }

        public Criteria andFixedReturnFixedTimeNotBetween(Date value1, Date value2) {
            addCriterion("fixed_return_fixed_time not between", value1, value2, "fixedReturnFixedTime");
            return (Criteria) this;
        }
        
        public Criteria andFixedReturnBlockIsNull() {
            addCriterion("fixed_return_block is null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIsNotNull() {
            addCriterion("fixed_return_block is not null");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockEqualTo(String value) {
            addCriterion("fixed_return_block =", value, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockNotEqualTo(String value) {
            addCriterion("fixed_return_block <>", value, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockGreaterThan(String value) {
            addCriterion("fixed_return_block >", value, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockGreaterThanOrEqualTo(String value) {
            addCriterion("fixed_return_block >=", value, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockLessThan(String value) {
            addCriterion("fixed_return_block <", value, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockLessThanOrEqualTo(String value) {
            addCriterion("fixed_return_block <=", value, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockLike(String value) {
            addCriterion("fixed_return_block like", value, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockNotLike(String value) {
            addCriterion("fixed_return_block not like", value, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockIn(List<String> values) {
            addCriterion("fixed_return_block in", values, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockNotIn(List<String> values) {
            addCriterion("fixed_return_block not in", values, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockBetween(String value1, String value2) {
            addCriterion("fixed_return_block between", value1, value2, "fixedReturnBlock");
            return (Criteria) this;
        }

        public Criteria andFixedReturnBlockNotBetween(String value1, String value2) {
            addCriterion("fixed_return_block not between", value1, value2, "fixedReturnBlock");
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