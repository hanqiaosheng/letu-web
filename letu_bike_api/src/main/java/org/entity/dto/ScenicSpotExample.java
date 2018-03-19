package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ScenicSpotExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ScenicSpotExample() {
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

        
      //城市名
        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }
        
      //城市名
        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }
        
      //城市编码
        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }
        
        public Criteria andScenicSpotIdIsNull() {
            addCriterion("scenic_spot_id is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdIsNotNull() {
            addCriterion("scenic_spot_id is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdEqualTo(Long value) {
            addCriterion("scenic_spot_id =", value, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdNotEqualTo(Long value) {
            addCriterion("scenic_spot_id <>", value, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdGreaterThan(Long value) {
            addCriterion("scenic_spot_id >", value, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdGreaterThanOrEqualTo(Long value) {
            addCriterion("scenic_spot_id >=", value, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdLessThan(Long value) {
            addCriterion("scenic_spot_id <", value, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdLessThanOrEqualTo(Long value) {
            addCriterion("scenic_spot_id <=", value, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdIn(List<Long> values) {
            addCriterion("scenic_spot_id in", values, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdNotIn(List<Long> values) {
            addCriterion("scenic_spot_id not in", values, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdBetween(Long value1, Long value2) {
            addCriterion("scenic_spot_id between", value1, value2, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIdNotBetween(Long value1, Long value2) {
            addCriterion("scenic_spot_id not between", value1, value2, "scenicSpotId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameIsNull() {
            addCriterion("scenic_spot_name is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameIsNotNull() {
            addCriterion("scenic_spot_name is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameEqualTo(String value) {
            addCriterion("scenic_spot_name =", value, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameNotEqualTo(String value) {
            addCriterion("scenic_spot_name <>", value, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameGreaterThan(String value) {
            addCriterion("scenic_spot_name >", value, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_spot_name >=", value, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameLessThan(String value) {
            addCriterion("scenic_spot_name <", value, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameLessThanOrEqualTo(String value) {
            addCriterion("scenic_spot_name <=", value, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameLike(String value) {
            addCriterion("scenic_spot_name like", value, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameNotLike(String value) {
            addCriterion("scenic_spot_name not like", value, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameIn(List<String> values) {
            addCriterion("scenic_spot_name in", values, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameNotIn(List<String> values) {
            addCriterion("scenic_spot_name not in", values, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameBetween(String value1, String value2) {
            addCriterion("scenic_spot_name between", value1, value2, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotNameNotBetween(String value1, String value2) {
            addCriterion("scenic_spot_name not between", value1, value2, "scenicSpotName");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageIsNull() {
            addCriterion("scenic_spot_image is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageIsNotNull() {
            addCriterion("scenic_spot_image is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageEqualTo(String value) {
            addCriterion("scenic_spot_image =", value, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageNotEqualTo(String value) {
            addCriterion("scenic_spot_image <>", value, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageGreaterThan(String value) {
            addCriterion("scenic_spot_image >", value, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_spot_image >=", value, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageLessThan(String value) {
            addCriterion("scenic_spot_image <", value, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageLessThanOrEqualTo(String value) {
            addCriterion("scenic_spot_image <=", value, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageLike(String value) {
            addCriterion("scenic_spot_image like", value, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageNotLike(String value) {
            addCriterion("scenic_spot_image not like", value, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageIn(List<String> values) {
            addCriterion("scenic_spot_image in", values, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageNotIn(List<String> values) {
            addCriterion("scenic_spot_image not in", values, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageBetween(String value1, String value2) {
            addCriterion("scenic_spot_image between", value1, value2, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotImageNotBetween(String value1, String value2) {
            addCriterion("scenic_spot_image not between", value1, value2, "scenicSpotImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdIsNull() {
            addCriterion("scenic_spot_city_id is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdIsNotNull() {
            addCriterion("scenic_spot_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdEqualTo(Long value) {
            addCriterion("scenic_spot_city_id =", value, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdNotEqualTo(Long value) {
            addCriterion("scenic_spot_city_id <>", value, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdGreaterThan(Long value) {
            addCriterion("scenic_spot_city_id >", value, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("scenic_spot_city_id >=", value, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdLessThan(Long value) {
            addCriterion("scenic_spot_city_id <", value, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdLessThanOrEqualTo(Long value) {
            addCriterion("scenic_spot_city_id <=", value, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdIn(List<Long> values) {
            addCriterion("scenic_spot_city_id in", values, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdNotIn(List<Long> values) {
            addCriterion("scenic_spot_city_id not in", values, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdBetween(Long value1, Long value2) {
            addCriterion("scenic_spot_city_id between", value1, value2, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCityIdNotBetween(Long value1, Long value2) {
            addCriterion("scenic_spot_city_id not between", value1, value2, "scenicSpotCityId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagIsNull() {
            addCriterion("scenic_spot_tag is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagIsNotNull() {
            addCriterion("scenic_spot_tag is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagEqualTo(String value) {
            addCriterion("scenic_spot_tag =", value, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagNotEqualTo(String value) {
            addCriterion("scenic_spot_tag <>", value, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagGreaterThan(String value) {
            addCriterion("scenic_spot_tag >", value, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_spot_tag >=", value, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagLessThan(String value) {
            addCriterion("scenic_spot_tag <", value, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagLessThanOrEqualTo(String value) {
            addCriterion("scenic_spot_tag <=", value, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagLike(String value) {
            addCriterion("scenic_spot_tag like", value, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagNotLike(String value) {
            addCriterion("scenic_spot_tag not like", value, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagIn(List<String> values) {
            addCriterion("scenic_spot_tag in", values, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagNotIn(List<String> values) {
            addCriterion("scenic_spot_tag not in", values, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagBetween(String value1, String value2) {
            addCriterion("scenic_spot_tag between", value1, value2, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTagNotBetween(String value1, String value2) {
            addCriterion("scenic_spot_tag not between", value1, value2, "scenicSpotTag");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceIsNull() {
            addCriterion("scenic_spot_distance is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceIsNotNull() {
            addCriterion("scenic_spot_distance is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceEqualTo(Double value) {
            addCriterion("scenic_spot_distance =", value, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceNotEqualTo(Double value) {
            addCriterion("scenic_spot_distance <>", value, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceGreaterThan(Double value) {
            addCriterion("scenic_spot_distance >", value, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceGreaterThanOrEqualTo(Double value) {
            addCriterion("scenic_spot_distance >=", value, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceLessThan(Double value) {
            addCriterion("scenic_spot_distance <", value, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceLessThanOrEqualTo(Double value) {
            addCriterion("scenic_spot_distance <=", value, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceIn(List<Double> values) {
            addCriterion("scenic_spot_distance in", values, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceNotIn(List<Double> values) {
            addCriterion("scenic_spot_distance not in", values, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceBetween(Double value1, Double value2) {
            addCriterion("scenic_spot_distance between", value1, value2, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDistanceNotBetween(Double value1, Double value2) {
            addCriterion("scenic_spot_distance not between", value1, value2, "scenicSpotDistance");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateIsNull() {
            addCriterion("scenic_spot_state is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateIsNotNull() {
            addCriterion("scenic_spot_state is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateEqualTo(Integer value) {
            addCriterion("scenic_spot_state =", value, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateNotEqualTo(Integer value) {
            addCriterion("scenic_spot_state <>", value, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateGreaterThan(Integer value) {
            addCriterion("scenic_spot_state >", value, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("scenic_spot_state >=", value, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateLessThan(Integer value) {
            addCriterion("scenic_spot_state <", value, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateLessThanOrEqualTo(Integer value) {
            addCriterion("scenic_spot_state <=", value, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateIn(List<Integer> values) {
            addCriterion("scenic_spot_state in", values, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateNotIn(List<Integer> values) {
            addCriterion("scenic_spot_state not in", values, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateBetween(Integer value1, Integer value2) {
            addCriterion("scenic_spot_state between", value1, value2, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotStateNotBetween(Integer value1, Integer value2) {
            addCriterion("scenic_spot_state not between", value1, value2, "scenicSpotState");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeIsNull() {
            addCriterion("scenic_spot_create_time is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeIsNotNull() {
            addCriterion("scenic_spot_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeEqualTo(Date value) {
            addCriterion("scenic_spot_create_time =", value, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeNotEqualTo(Date value) {
            addCriterion("scenic_spot_create_time <>", value, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeGreaterThan(Date value) {
            addCriterion("scenic_spot_create_time >", value, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("scenic_spot_create_time >=", value, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeLessThan(Date value) {
            addCriterion("scenic_spot_create_time <", value, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("scenic_spot_create_time <=", value, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeIn(List<Date> values) {
            addCriterion("scenic_spot_create_time in", values, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeNotIn(List<Date> values) {
            addCriterion("scenic_spot_create_time not in", values, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeBetween(Date value1, Date value2) {
            addCriterion("scenic_spot_create_time between", value1, value2, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("scenic_spot_create_time not between", value1, value2, "scenicSpotCreateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumIsNull() {
            addCriterion("scenic_spot_top_num is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumIsNotNull() {
            addCriterion("scenic_spot_top_num is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumEqualTo(Integer value) {
            addCriterion("scenic_spot_top_num =", value, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumNotEqualTo(Integer value) {
            addCriterion("scenic_spot_top_num <>", value, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumGreaterThan(Integer value) {
            addCriterion("scenic_spot_top_num >", value, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("scenic_spot_top_num >=", value, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumLessThan(Integer value) {
            addCriterion("scenic_spot_top_num <", value, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumLessThanOrEqualTo(Integer value) {
            addCriterion("scenic_spot_top_num <=", value, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumIn(List<Integer> values) {
            addCriterion("scenic_spot_top_num in", values, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumNotIn(List<Integer> values) {
            addCriterion("scenic_spot_top_num not in", values, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumBetween(Integer value1, Integer value2) {
            addCriterion("scenic_spot_top_num between", value1, value2, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("scenic_spot_top_num not between", value1, value2, "scenicSpotTopNum");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeIsNull() {
            addCriterion("scenic_spot_type is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeIsNotNull() {
            addCriterion("scenic_spot_type is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeEqualTo(Integer value) {
            addCriterion("scenic_spot_type =", value, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeNotEqualTo(Integer value) {
            addCriterion("scenic_spot_type <>", value, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeGreaterThan(Integer value) {
            addCriterion("scenic_spot_type >", value, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("scenic_spot_type >=", value, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeLessThan(Integer value) {
            addCriterion("scenic_spot_type <", value, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeLessThanOrEqualTo(Integer value) {
            addCriterion("scenic_spot_type <=", value, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeIn(List<Integer> values) {
            addCriterion("scenic_spot_type in", values, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeNotIn(List<Integer> values) {
            addCriterion("scenic_spot_type not in", values, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeBetween(Integer value1, Integer value2) {
            addCriterion("scenic_spot_type between", value1, value2, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("scenic_spot_type not between", value1, value2, "scenicSpotType");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngIsNull() {
            addCriterion("scenic_spot_lng is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngIsNotNull() {
            addCriterion("scenic_spot_lng is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngEqualTo(Double value) {
            addCriterion("scenic_spot_lng =", value, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngNotEqualTo(Double value) {
            addCriterion("scenic_spot_lng <>", value, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngGreaterThan(Double value) {
            addCriterion("scenic_spot_lng >", value, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngGreaterThanOrEqualTo(Double value) {
            addCriterion("scenic_spot_lng >=", value, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngLessThan(Double value) {
            addCriterion("scenic_spot_lng <", value, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngLessThanOrEqualTo(Double value) {
            addCriterion("scenic_spot_lng <=", value, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngIn(List<Double> values) {
            addCriterion("scenic_spot_lng in", values, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngNotIn(List<Double> values) {
            addCriterion("scenic_spot_lng not in", values, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngBetween(Double value1, Double value2) {
            addCriterion("scenic_spot_lng between", value1, value2, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLngNotBetween(Double value1, Double value2) {
            addCriterion("scenic_spot_lng not between", value1, value2, "scenicSpotLng");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatIsNull() {
            addCriterion("scenic_spot_lat is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatIsNotNull() {
            addCriterion("scenic_spot_lat is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatEqualTo(Double value) {
            addCriterion("scenic_spot_lat =", value, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatNotEqualTo(Double value) {
            addCriterion("scenic_spot_lat <>", value, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatGreaterThan(Double value) {
            addCriterion("scenic_spot_lat >", value, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatGreaterThanOrEqualTo(Double value) {
            addCriterion("scenic_spot_lat >=", value, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatLessThan(Double value) {
            addCriterion("scenic_spot_lat <", value, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatLessThanOrEqualTo(Double value) {
            addCriterion("scenic_spot_lat <=", value, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatIn(List<Double> values) {
            addCriterion("scenic_spot_lat in", values, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatNotIn(List<Double> values) {
            addCriterion("scenic_spot_lat not in", values, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatBetween(Double value1, Double value2) {
            addCriterion("scenic_spot_lat between", value1, value2, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotLatNotBetween(Double value1, Double value2) {
            addCriterion("scenic_spot_lat not between", value1, value2, "scenicSpotLat");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdIsNull() {
            addCriterion("scenic_spot_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdIsNotNull() {
            addCriterion("scenic_spot_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdEqualTo(Long value) {
            addCriterion("scenic_spot_channel_id =", value, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdNotEqualTo(Long value) {
            addCriterion("scenic_spot_channel_id <>", value, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdGreaterThan(Long value) {
            addCriterion("scenic_spot_channel_id >", value, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("scenic_spot_channel_id >=", value, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdLessThan(Long value) {
            addCriterion("scenic_spot_channel_id <", value, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("scenic_spot_channel_id <=", value, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdIn(List<Long> values) {
            addCriterion("scenic_spot_channel_id in", values, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdNotIn(List<Long> values) {
            addCriterion("scenic_spot_channel_id not in", values, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdBetween(Long value1, Long value2) {
            addCriterion("scenic_spot_channel_id between", value1, value2, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("scenic_spot_channel_id not between", value1, value2, "scenicSpotChannelId");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeIsNull() {
            addCriterion("scenic_spot_update_time is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeIsNotNull() {
            addCriterion("scenic_spot_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeEqualTo(Date value) {
            addCriterion("scenic_spot_update_time =", value, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeNotEqualTo(Date value) {
            addCriterion("scenic_spot_update_time <>", value, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeGreaterThan(Date value) {
            addCriterion("scenic_spot_update_time >", value, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("scenic_spot_update_time >=", value, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeLessThan(Date value) {
            addCriterion("scenic_spot_update_time <", value, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("scenic_spot_update_time <=", value, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeIn(List<Date> values) {
            addCriterion("scenic_spot_update_time in", values, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeNotIn(List<Date> values) {
            addCriterion("scenic_spot_update_time not in", values, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("scenic_spot_update_time between", value1, value2, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("scenic_spot_update_time not between", value1, value2, "scenicSpotUpdateTime");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageIsNull() {
            addCriterion("scenic_spot_detail_image is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageIsNotNull() {
            addCriterion("scenic_spot_detail_image is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageEqualTo(String value) {
            addCriterion("scenic_spot_detail_image =", value, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageNotEqualTo(String value) {
            addCriterion("scenic_spot_detail_image <>", value, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageGreaterThan(String value) {
            addCriterion("scenic_spot_detail_image >", value, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_spot_detail_image >=", value, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageLessThan(String value) {
            addCriterion("scenic_spot_detail_image <", value, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageLessThanOrEqualTo(String value) {
            addCriterion("scenic_spot_detail_image <=", value, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageLike(String value) {
            addCriterion("scenic_spot_detail_image like", value, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageNotLike(String value) {
            addCriterion("scenic_spot_detail_image not like", value, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageIn(List<String> values) {
            addCriterion("scenic_spot_detail_image in", values, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageNotIn(List<String> values) {
            addCriterion("scenic_spot_detail_image not in", values, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageBetween(String value1, String value2) {
            addCriterion("scenic_spot_detail_image between", value1, value2, "scenicSpotDetailImage");
            return (Criteria) this;
        }

        public Criteria andScenicSpotDetailImageNotBetween(String value1, String value2) {
            addCriterion("scenic_spot_detail_image not between", value1, value2, "scenicSpotDetailImage");
            return (Criteria) this;
        }
        
        public Criteria andScenicSpotIsAllIsNull() {
            addCriterion("scenic_spot_is_all is null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllIsNotNull() {
            addCriterion("scenic_spot_is_all is not null");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllEqualTo(Integer value) {
            addCriterion("scenic_spot_is_all =", value, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllNotEqualTo(Integer value) {
            addCriterion("scenic_spot_is_all <>", value, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllGreaterThan(Integer value) {
            addCriterion("scenic_spot_is_all >", value, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllGreaterThanOrEqualTo(Integer value) {
            addCriterion("scenic_spot_is_all >=", value, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllLessThan(Integer value) {
            addCriterion("scenic_spot_is_all <", value, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllLessThanOrEqualTo(Integer value) {
            addCriterion("scenic_spot_is_all <=", value, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllIn(List<Integer> values) {
            addCriterion("scenic_spot_is_all in", values, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllNotIn(List<Integer> values) {
            addCriterion("scenic_spot_is_all not in", values, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllBetween(Integer value1, Integer value2) {
            addCriterion("scenic_spot_is_all between", value1, value2, "scenicSpotIsAll");
            return (Criteria) this;
        }

        public Criteria andScenicSpotIsAllNotBetween(Integer value1, Integer value2) {
            addCriterion("scenic_spot_is_all not between", value1, value2, "scenicSpotIsAll");
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