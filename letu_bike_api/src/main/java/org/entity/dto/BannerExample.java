package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BannerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public BannerExample() {
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
        
        //城市编号
        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andBannerIdIsNull() {
            addCriterion("banner_id is null");
            return (Criteria) this;
        }

        public Criteria andBannerIdIsNotNull() {
            addCriterion("banner_id is not null");
            return (Criteria) this;
        }

        public Criteria andBannerIdEqualTo(Long value) {
            addCriterion("banner_id =", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotEqualTo(Long value) {
            addCriterion("banner_id <>", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdGreaterThan(Long value) {
            addCriterion("banner_id >", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("banner_id >=", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdLessThan(Long value) {
            addCriterion("banner_id <", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdLessThanOrEqualTo(Long value) {
            addCriterion("banner_id <=", value, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdIn(List<Long> values) {
            addCriterion("banner_id in", values, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotIn(List<Long> values) {
            addCriterion("banner_id not in", values, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdBetween(Long value1, Long value2) {
            addCriterion("banner_id between", value1, value2, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerIdNotBetween(Long value1, Long value2) {
            addCriterion("banner_id not between", value1, value2, "bannerId");
            return (Criteria) this;
        }

        public Criteria andBannerNameIsNull() {
            addCriterion("banner_name is null");
            return (Criteria) this;
        }

        public Criteria andBannerNameIsNotNull() {
            addCriterion("banner_name is not null");
            return (Criteria) this;
        }

        public Criteria andBannerNameEqualTo(String value) {
            addCriterion("banner_name =", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameNotEqualTo(String value) {
            addCriterion("banner_name <>", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameGreaterThan(String value) {
            addCriterion("banner_name >", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameGreaterThanOrEqualTo(String value) {
            addCriterion("banner_name >=", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameLessThan(String value) {
            addCriterion("banner_name <", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameLessThanOrEqualTo(String value) {
            addCriterion("banner_name <=", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameLike(String value) {
            addCriterion("banner_name like", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameNotLike(String value) {
            addCriterion("banner_name not like", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameIn(List<String> values) {
            addCriterion("banner_name in", values, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameNotIn(List<String> values) {
            addCriterion("banner_name not in", values, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameBetween(String value1, String value2) {
            addCriterion("banner_name between", value1, value2, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameNotBetween(String value1, String value2) {
            addCriterion("banner_name not between", value1, value2, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlIsNull() {
            addCriterion("banner_image_url is null");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlIsNotNull() {
            addCriterion("banner_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlEqualTo(String value) {
            addCriterion("banner_image_url =", value, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlNotEqualTo(String value) {
            addCriterion("banner_image_url <>", value, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlGreaterThan(String value) {
            addCriterion("banner_image_url >", value, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("banner_image_url >=", value, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlLessThan(String value) {
            addCriterion("banner_image_url <", value, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlLessThanOrEqualTo(String value) {
            addCriterion("banner_image_url <=", value, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlLike(String value) {
            addCriterion("banner_image_url like", value, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlNotLike(String value) {
            addCriterion("banner_image_url not like", value, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlIn(List<String> values) {
            addCriterion("banner_image_url in", values, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlNotIn(List<String> values) {
            addCriterion("banner_image_url not in", values, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlBetween(String value1, String value2) {
            addCriterion("banner_image_url between", value1, value2, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerImageUrlNotBetween(String value1, String value2) {
            addCriterion("banner_image_url not between", value1, value2, "bannerImageUrl");
            return (Criteria) this;
        }

        public Criteria andBannerStateIsNull() {
            addCriterion("banner_state is null");
            return (Criteria) this;
        }

        public Criteria andBannerStateIsNotNull() {
            addCriterion("banner_state is not null");
            return (Criteria) this;
        }

        public Criteria andBannerStateEqualTo(Integer value) {
            addCriterion("banner_state =", value, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateNotEqualTo(Integer value) {
            addCriterion("banner_state <>", value, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateGreaterThan(Integer value) {
            addCriterion("banner_state >", value, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("banner_state >=", value, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateLessThan(Integer value) {
            addCriterion("banner_state <", value, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateLessThanOrEqualTo(Integer value) {
            addCriterion("banner_state <=", value, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateIn(List<Integer> values) {
            addCriterion("banner_state in", values, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateNotIn(List<Integer> values) {
            addCriterion("banner_state not in", values, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateBetween(Integer value1, Integer value2) {
            addCriterion("banner_state between", value1, value2, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerStateNotBetween(Integer value1, Integer value2) {
            addCriterion("banner_state not between", value1, value2, "bannerState");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumIsNull() {
            addCriterion("banner_top_num is null");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumIsNotNull() {
            addCriterion("banner_top_num is not null");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumEqualTo(Integer value) {
            addCriterion("banner_top_num =", value, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumNotEqualTo(Integer value) {
            addCriterion("banner_top_num <>", value, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumGreaterThan(Integer value) {
            addCriterion("banner_top_num >", value, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("banner_top_num >=", value, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumLessThan(Integer value) {
            addCriterion("banner_top_num <", value, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumLessThanOrEqualTo(Integer value) {
            addCriterion("banner_top_num <=", value, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumIn(List<Integer> values) {
            addCriterion("banner_top_num in", values, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumNotIn(List<Integer> values) {
            addCriterion("banner_top_num not in", values, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumBetween(Integer value1, Integer value2) {
            addCriterion("banner_top_num between", value1, value2, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerTopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("banner_top_num not between", value1, value2, "bannerTopNum");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeIsNull() {
            addCriterion("banner_create_time is null");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeIsNotNull() {
            addCriterion("banner_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeEqualTo(Date value) {
            addCriterion("banner_create_time =", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeNotEqualTo(Date value) {
            addCriterion("banner_create_time <>", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeGreaterThan(Date value) {
            addCriterion("banner_create_time >", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("banner_create_time >=", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeLessThan(Date value) {
            addCriterion("banner_create_time <", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("banner_create_time <=", value, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeIn(List<Date> values) {
            addCriterion("banner_create_time in", values, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeNotIn(List<Date> values) {
            addCriterion("banner_create_time not in", values, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeBetween(Date value1, Date value2) {
            addCriterion("banner_create_time between", value1, value2, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("banner_create_time not between", value1, value2, "bannerCreateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeIsNull() {
            addCriterion("banner_update_time is null");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeIsNotNull() {
            addCriterion("banner_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeEqualTo(Date value) {
            addCriterion("banner_update_time =", value, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeNotEqualTo(Date value) {
            addCriterion("banner_update_time <>", value, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeGreaterThan(Date value) {
            addCriterion("banner_update_time >", value, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("banner_update_time >=", value, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeLessThan(Date value) {
            addCriterion("banner_update_time <", value, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("banner_update_time <=", value, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeIn(List<Date> values) {
            addCriterion("banner_update_time in", values, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeNotIn(List<Date> values) {
            addCriterion("banner_update_time not in", values, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("banner_update_time between", value1, value2, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("banner_update_time not between", value1, value2, "bannerUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdIsNull() {
            addCriterion("banner_city_id is null");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdIsNotNull() {
            addCriterion("banner_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdEqualTo(Long value) {
            addCriterion("banner_city_id =", value, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdNotEqualTo(Long value) {
            addCriterion("banner_city_id <>", value, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdGreaterThan(Long value) {
            addCriterion("banner_city_id >", value, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("banner_city_id >=", value, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdLessThan(Long value) {
            addCriterion("banner_city_id <", value, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdLessThanOrEqualTo(Long value) {
            addCriterion("banner_city_id <=", value, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdIn(List<Long> values) {
            addCriterion("banner_city_id in", values, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdNotIn(List<Long> values) {
            addCriterion("banner_city_id not in", values, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdBetween(Long value1, Long value2) {
            addCriterion("banner_city_id between", value1, value2, "bannerCityId");
            return (Criteria) this;
        }

        public Criteria andBannerCityIdNotBetween(Long value1, Long value2) {
            addCriterion("banner_city_id not between", value1, value2, "bannerCityId");
            return (Criteria) this;
        }
        
        public Criteria andBannerIsAllIsNull() {
            addCriterion("banner_is_all is null");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllIsNotNull() {
            addCriterion("banner_is_all is not null");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllEqualTo(Integer value) {
            addCriterion("banner_is_all =", value, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllNotEqualTo(Integer value) {
            addCriterion("banner_is_all <>", value, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllGreaterThan(Integer value) {
            addCriterion("banner_is_all >", value, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllGreaterThanOrEqualTo(Integer value) {
            addCriterion("banner_is_all >=", value, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllLessThan(Integer value) {
            addCriterion("banner_is_all <", value, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllLessThanOrEqualTo(Integer value) {
            addCriterion("banner_is_all <=", value, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllIn(List<Integer> values) {
            addCriterion("banner_is_all in", values, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllNotIn(List<Integer> values) {
            addCriterion("banner_is_all not in", values, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllBetween(Integer value1, Integer value2) {
            addCriterion("banner_is_all between", value1, value2, "bannerIsAll");
            return (Criteria) this;
        }

        public Criteria andBannerIsAllNotBetween(Integer value1, Integer value2) {
            addCriterion("banner_is_all not between", value1, value2, "bannerIsAll");
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