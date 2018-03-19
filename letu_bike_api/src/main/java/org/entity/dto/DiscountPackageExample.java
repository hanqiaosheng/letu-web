package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscountPackageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public DiscountPackageExample() {
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

        public Criteria andDiscountPackageIdIsNull() {
            addCriterion("discount_package_id is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdIsNotNull() {
            addCriterion("discount_package_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdEqualTo(Long value) {
            addCriterion("discount_package_id =", value, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdNotEqualTo(Long value) {
            addCriterion("discount_package_id <>", value, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdGreaterThan(Long value) {
            addCriterion("discount_package_id >", value, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("discount_package_id >=", value, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdLessThan(Long value) {
            addCriterion("discount_package_id <", value, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdLessThanOrEqualTo(Long value) {
            addCriterion("discount_package_id <=", value, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdIn(List<Long> values) {
            addCriterion("discount_package_id in", values, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdNotIn(List<Long> values) {
            addCriterion("discount_package_id not in", values, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdBetween(Long value1, Long value2) {
            addCriterion("discount_package_id between", value1, value2, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageIdNotBetween(Long value1, Long value2) {
            addCriterion("discount_package_id not between", value1, value2, "discountPackageId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameIsNull() {
            addCriterion("discount_package_name is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameIsNotNull() {
            addCriterion("discount_package_name is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameEqualTo(String value) {
            addCriterion("discount_package_name =", value, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameNotEqualTo(String value) {
            addCriterion("discount_package_name <>", value, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameGreaterThan(String value) {
            addCriterion("discount_package_name >", value, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameGreaterThanOrEqualTo(String value) {
            addCriterion("discount_package_name >=", value, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameLessThan(String value) {
            addCriterion("discount_package_name <", value, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameLessThanOrEqualTo(String value) {
            addCriterion("discount_package_name <=", value, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameLike(String value) {
            addCriterion("discount_package_name like", value, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameNotLike(String value) {
            addCriterion("discount_package_name not like", value, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameIn(List<String> values) {
            addCriterion("discount_package_name in", values, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameNotIn(List<String> values) {
            addCriterion("discount_package_name not in", values, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameBetween(String value1, String value2) {
            addCriterion("discount_package_name between", value1, value2, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageNameNotBetween(String value1, String value2) {
            addCriterion("discount_package_name not between", value1, value2, "discountPackageName");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceIsNull() {
            addCriterion("discount_package_price is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceIsNotNull() {
            addCriterion("discount_package_price is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceEqualTo(BigDecimal value) {
            addCriterion("discount_package_price =", value, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceNotEqualTo(BigDecimal value) {
            addCriterion("discount_package_price <>", value, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceGreaterThan(BigDecimal value) {
            addCriterion("discount_package_price >", value, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_package_price >=", value, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceLessThan(BigDecimal value) {
            addCriterion("discount_package_price <", value, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_package_price <=", value, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceIn(List<BigDecimal> values) {
            addCriterion("discount_package_price in", values, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceNotIn(List<BigDecimal> values) {
            addCriterion("discount_package_price not in", values, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_package_price between", value1, value2, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_package_price not between", value1, value2, "discountPackagePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceIsNull() {
            addCriterion("discount_package_preferential_price is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceIsNotNull() {
            addCriterion("discount_package_preferential_price is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceEqualTo(BigDecimal value) {
            addCriterion("discount_package_preferential_price =", value, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceNotEqualTo(BigDecimal value) {
            addCriterion("discount_package_preferential_price <>", value, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceGreaterThan(BigDecimal value) {
            addCriterion("discount_package_preferential_price >", value, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_package_preferential_price >=", value, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceLessThan(BigDecimal value) {
            addCriterion("discount_package_preferential_price <", value, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_package_preferential_price <=", value, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceIn(List<BigDecimal> values) {
            addCriterion("discount_package_preferential_price in", values, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceNotIn(List<BigDecimal> values) {
            addCriterion("discount_package_preferential_price not in", values, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_package_preferential_price between", value1, value2, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackagePreferentialPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_package_preferential_price not between", value1, value2, "discountPackagePreferentialPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagIsNull() {
            addCriterion("discount_package_tag is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagIsNotNull() {
            addCriterion("discount_package_tag is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagEqualTo(String value) {
            addCriterion("discount_package_tag =", value, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagNotEqualTo(String value) {
            addCriterion("discount_package_tag <>", value, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagGreaterThan(String value) {
            addCriterion("discount_package_tag >", value, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagGreaterThanOrEqualTo(String value) {
            addCriterion("discount_package_tag >=", value, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagLessThan(String value) {
            addCriterion("discount_package_tag <", value, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagLessThanOrEqualTo(String value) {
            addCriterion("discount_package_tag <=", value, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagLike(String value) {
            addCriterion("discount_package_tag like", value, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagNotLike(String value) {
            addCriterion("discount_package_tag not like", value, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagIn(List<String> values) {
            addCriterion("discount_package_tag in", values, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagNotIn(List<String> values) {
            addCriterion("discount_package_tag not in", values, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagBetween(String value1, String value2) {
            addCriterion("discount_package_tag between", value1, value2, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTagNotBetween(String value1, String value2) {
            addCriterion("discount_package_tag not between", value1, value2, "discountPackageTag");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateIsNull() {
            addCriterion("discount_package_state is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateIsNotNull() {
            addCriterion("discount_package_state is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateEqualTo(Integer value) {
            addCriterion("discount_package_state =", value, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateNotEqualTo(Integer value) {
            addCriterion("discount_package_state <>", value, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateGreaterThan(Integer value) {
            addCriterion("discount_package_state >", value, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount_package_state >=", value, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateLessThan(Integer value) {
            addCriterion("discount_package_state <", value, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateLessThanOrEqualTo(Integer value) {
            addCriterion("discount_package_state <=", value, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateIn(List<Integer> values) {
            addCriterion("discount_package_state in", values, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateNotIn(List<Integer> values) {
            addCriterion("discount_package_state not in", values, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateBetween(Integer value1, Integer value2) {
            addCriterion("discount_package_state between", value1, value2, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageStateNotBetween(Integer value1, Integer value2) {
            addCriterion("discount_package_state not between", value1, value2, "discountPackageState");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeIsNull() {
            addCriterion("discount_package_create_time is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeIsNotNull() {
            addCriterion("discount_package_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeEqualTo(Date value) {
            addCriterion("discount_package_create_time =", value, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeNotEqualTo(Date value) {
            addCriterion("discount_package_create_time <>", value, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeGreaterThan(Date value) {
            addCriterion("discount_package_create_time >", value, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("discount_package_create_time >=", value, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeLessThan(Date value) {
            addCriterion("discount_package_create_time <", value, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("discount_package_create_time <=", value, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeIn(List<Date> values) {
            addCriterion("discount_package_create_time in", values, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeNotIn(List<Date> values) {
            addCriterion("discount_package_create_time not in", values, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeBetween(Date value1, Date value2) {
            addCriterion("discount_package_create_time between", value1, value2, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("discount_package_create_time not between", value1, value2, "discountPackageCreateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitIsNull() {
            addCriterion("discount_package_buy_limit is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitIsNotNull() {
            addCriterion("discount_package_buy_limit is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitEqualTo(Integer value) {
            addCriterion("discount_package_buy_limit =", value, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitNotEqualTo(Integer value) {
            addCriterion("discount_package_buy_limit <>", value, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitGreaterThan(Integer value) {
            addCriterion("discount_package_buy_limit >", value, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount_package_buy_limit >=", value, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitLessThan(Integer value) {
            addCriterion("discount_package_buy_limit <", value, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitLessThanOrEqualTo(Integer value) {
            addCriterion("discount_package_buy_limit <=", value, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitIn(List<Integer> values) {
            addCriterion("discount_package_buy_limit in", values, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitNotIn(List<Integer> values) {
            addCriterion("discount_package_buy_limit not in", values, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitBetween(Integer value1, Integer value2) {
            addCriterion("discount_package_buy_limit between", value1, value2, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageBuyLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("discount_package_buy_limit not between", value1, value2, "discountPackageBuyLimit");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdIsNull() {
            addCriterion("discount_package_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdIsNotNull() {
            addCriterion("discount_package_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdEqualTo(Long value) {
            addCriterion("discount_package_channel_id =", value, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdNotEqualTo(Long value) {
            addCriterion("discount_package_channel_id <>", value, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdGreaterThan(Long value) {
            addCriterion("discount_package_channel_id >", value, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("discount_package_channel_id >=", value, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdLessThan(Long value) {
            addCriterion("discount_package_channel_id <", value, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("discount_package_channel_id <=", value, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdIn(List<Long> values) {
            addCriterion("discount_package_channel_id in", values, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdNotIn(List<Long> values) {
            addCriterion("discount_package_channel_id not in", values, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdBetween(Long value1, Long value2) {
            addCriterion("discount_package_channel_id between", value1, value2, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("discount_package_channel_id not between", value1, value2, "discountPackageChannelId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdIsNull() {
            addCriterion("discount_package_scenic_spot_id is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdIsNotNull() {
            addCriterion("discount_package_scenic_spot_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdEqualTo(Long value) {
            addCriterion("discount_package_scenic_spot_id =", value, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdNotEqualTo(Long value) {
            addCriterion("discount_package_scenic_spot_id <>", value, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdGreaterThan(Long value) {
            addCriterion("discount_package_scenic_spot_id >", value, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdGreaterThanOrEqualTo(Long value) {
            addCriterion("discount_package_scenic_spot_id >=", value, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdLessThan(Long value) {
            addCriterion("discount_package_scenic_spot_id <", value, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdLessThanOrEqualTo(Long value) {
            addCriterion("discount_package_scenic_spot_id <=", value, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdIn(List<Long> values) {
            addCriterion("discount_package_scenic_spot_id in", values, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdNotIn(List<Long> values) {
            addCriterion("discount_package_scenic_spot_id not in", values, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdBetween(Long value1, Long value2) {
            addCriterion("discount_package_scenic_spot_id between", value1, value2, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageScenicSpotIdNotBetween(Long value1, Long value2) {
            addCriterion("discount_package_scenic_spot_id not between", value1, value2, "discountPackageScenicSpotId");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeIsNull() {
            addCriterion("discount_package_update_time is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeIsNotNull() {
            addCriterion("discount_package_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeEqualTo(Date value) {
            addCriterion("discount_package_update_time =", value, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeNotEqualTo(Date value) {
            addCriterion("discount_package_update_time <>", value, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeGreaterThan(Date value) {
            addCriterion("discount_package_update_time >", value, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("discount_package_update_time >=", value, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeLessThan(Date value) {
            addCriterion("discount_package_update_time <", value, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("discount_package_update_time <=", value, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeIn(List<Date> values) {
            addCriterion("discount_package_update_time in", values, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeNotIn(List<Date> values) {
            addCriterion("discount_package_update_time not in", values, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("discount_package_update_time between", value1, value2, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("discount_package_update_time not between", value1, value2, "discountPackageUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumIsNull() {
            addCriterion("discount_package_top_num is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumIsNotNull() {
            addCriterion("discount_package_top_num is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumEqualTo(Integer value) {
            addCriterion("discount_package_top_num =", value, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumNotEqualTo(Integer value) {
            addCriterion("discount_package_top_num <>", value, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumGreaterThan(Integer value) {
            addCriterion("discount_package_top_num >", value, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount_package_top_num >=", value, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumLessThan(Integer value) {
            addCriterion("discount_package_top_num <", value, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumLessThanOrEqualTo(Integer value) {
            addCriterion("discount_package_top_num <=", value, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumIn(List<Integer> values) {
            addCriterion("discount_package_top_num in", values, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumNotIn(List<Integer> values) {
            addCriterion("discount_package_top_num not in", values, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumBetween(Integer value1, Integer value2) {
            addCriterion("discount_package_top_num between", value1, value2, "discountPackageTopNum");
            return (Criteria) this;
        }

        public Criteria andDiscountPackageTopNumNotBetween(Integer value1, Integer value2) {
            addCriterion("discount_package_top_num not between", value1, value2, "discountPackageTopNum");
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