package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class BikeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public BikeExample() {
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
        
        /**
         * 
         * @param value
         * @return
         */
        public Criteria andChannelIdEqualTo(Long value) {
            addCriterion("models_channel_id =", value, "modelsChannelId");
            return (Criteria) this;
        }
        //modelsName
        public Criteria andmodelsNameLike(String value) {
            addCriterion("models_name like", value, "modelsName");
            return (Criteria) this;
        }
        
        public Criteria andChannelIdIn(List<Long> values) {
            addCriterion("models_channel_id in", values, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andBikeIdIsNull() {
            addCriterion("bike_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeIdIsNotNull() {
            addCriterion("bike_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeIdEqualTo(Long value) {
            addCriterion("bike_id =", value, "bikeId");
            return (Criteria) this;
        }

        public Criteria andBikeIdNotEqualTo(Long value) {
            addCriterion("bike_id <>", value, "bikeId");
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

        public Criteria andBikeManagerIdIsNull() {
            addCriterion("bike_manager_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdIsNotNull() {
            addCriterion("bike_manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdEqualTo(Long value) {
            addCriterion("bike_manager_id =", value, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdNotEqualTo(Long value) {
            addCriterion("bike_manager_id <>", value, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdGreaterThan(Long value) {
            addCriterion("bike_manager_id >", value, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_manager_id >=", value, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdLessThan(Long value) {
            addCriterion("bike_manager_id <", value, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_manager_id <=", value, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdIn(List<Long> values) {
            addCriterion("bike_manager_id in", values, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdNotIn(List<Long> values) {
            addCriterion("bike_manager_id not in", values, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdBetween(Long value1, Long value2) {
            addCriterion("bike_manager_id between", value1, value2, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeManagerIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_manager_id not between", value1, value2, "bikeManagerId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdIsNull() {
            addCriterion("bike_admin_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdIsNotNull() {
            addCriterion("bike_admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdEqualTo(Long value) {
            addCriterion("bike_admin_id =", value, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdNotEqualTo(Long value) {
            addCriterion("bike_admin_id <>", value, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdGreaterThan(Long value) {
            addCriterion("bike_admin_id >", value, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_admin_id >=", value, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdLessThan(Long value) {
            addCriterion("bike_admin_id <", value, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_admin_id <=", value, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdIn(List<Long> values) {
            addCriterion("bike_admin_id in", values, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdNotIn(List<Long> values) {
            addCriterion("bike_admin_id not in", values, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdBetween(Long value1, Long value2) {
            addCriterion("bike_admin_id between", value1, value2, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_admin_id not between", value1, value2, "bikeAdminId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdIsNull() {
            addCriterion("bike_block_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdIsNotNull() {
            addCriterion("bike_block_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdEqualTo(Long value) {
            addCriterion("bike_block_id =", value, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdNotEqualTo(Long value) {
            addCriterion("bike_block_id <>", value, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdGreaterThan(Long value) {
            addCriterion("bike_block_id >", value, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_block_id >=", value, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdLessThan(Long value) {
            addCriterion("bike_block_id <", value, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_block_id <=", value, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdIn(List<Long> values) {
            addCriterion("bike_block_id in", values, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdNotIn(List<Long> values) {
            addCriterion("bike_block_id not in", values, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdBetween(Long value1, Long value2) {
            addCriterion("bike_block_id between", value1, value2, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_block_id not between", value1, value2, "bikeBlockId");
            return (Criteria) this;
        }

        public Criteria andBikeCodeIsNull() {
            addCriterion("bike_code is null");
            return (Criteria) this;
        }

        public Criteria andBikeCodeIsNotNull() {
            addCriterion("bike_code is not null");
            return (Criteria) this;
        }

        public Criteria andBikeCodeEqualTo(String value) {
            addCriterion("bike_code =", value, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeNotEqualTo(String value) {
            addCriterion("bike_code <>", value, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeGreaterThan(String value) {
            addCriterion("bike_code >", value, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bike_code >=", value, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeLessThan(String value) {
            addCriterion("bike_code <", value, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeLessThanOrEqualTo(String value) {
            addCriterion("bike_code <=", value, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeLike(String value) {
            addCriterion("bike_code like", value, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeNotLike(String value) {
            addCriterion("bike_code not like", value, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeIn(List<String> values) {
            addCriterion("bike_code in", values, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeNotIn(List<String> values) {
            addCriterion("bike_code not in", values, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeBetween(String value1, String value2) {
            addCriterion("bike_code between", value1, value2, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeCodeNotBetween(String value1, String value2) {
            addCriterion("bike_code not between", value1, value2, "bikeCode");
            return (Criteria) this;
        }

        public Criteria andBikeStateIsNull() {
            addCriterion("bike_state is null");
            return (Criteria) this;
        }

        public Criteria andBikeStateIsNotNull() {
            addCriterion("bike_state is not null");
            return (Criteria) this;
        }

        public Criteria andBikeStateEqualTo(Integer value) {
            addCriterion("bike_state =", value, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateNotEqualTo(Integer value) {
            addCriterion("bike_state <>", value, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateGreaterThan(Integer value) {
            addCriterion("bike_state >", value, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_state >=", value, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateLessThan(Integer value) {
            addCriterion("bike_state <", value, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateLessThanOrEqualTo(Integer value) {
            addCriterion("bike_state <=", value, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateIn(List<Integer> values) {
            addCriterion("bike_state in", values, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateNotIn(List<Integer> values) {
            addCriterion("bike_state not in", values, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateBetween(Integer value1, Integer value2) {
            addCriterion("bike_state between", value1, value2, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeStateNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_state not between", value1, value2, "bikeState");
            return (Criteria) this;
        }

        public Criteria andBikeDelIsNull() {
            addCriterion("bike_del is null");
            return (Criteria) this;
        }

        public Criteria andBikeDelIsNotNull() {
            addCriterion("bike_del is not null");
            return (Criteria) this;
        }

        public Criteria andBikeDelEqualTo(Integer value) {
            addCriterion("bike_del =", value, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelNotEqualTo(Integer value) {
            addCriterion("bike_del <>", value, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelGreaterThan(Integer value) {
            addCriterion("bike_del >", value, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_del >=", value, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelLessThan(Integer value) {
            addCriterion("bike_del <", value, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelLessThanOrEqualTo(Integer value) {
            addCriterion("bike_del <=", value, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelIn(List<Integer> values) {
            addCriterion("bike_del in", values, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelNotIn(List<Integer> values) {
            addCriterion("bike_del not in", values, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelBetween(Integer value1, Integer value2) {
            addCriterion("bike_del between", value1, value2, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeDelNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_del not between", value1, value2, "bikeDel");
            return (Criteria) this;
        }

        public Criteria andBikeAddressIsNull() {
            addCriterion("bike_address is null");
            return (Criteria) this;
        }

        public Criteria andBikeAddressIsNotNull() {
            addCriterion("bike_address is not null");
            return (Criteria) this;
        }

        public Criteria andBikeAddressEqualTo(String value) {
            addCriterion("bike_address =", value, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressNotEqualTo(String value) {
            addCriterion("bike_address <>", value, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressGreaterThan(String value) {
            addCriterion("bike_address >", value, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("bike_address >=", value, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressLessThan(String value) {
            addCriterion("bike_address <", value, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressLessThanOrEqualTo(String value) {
            addCriterion("bike_address <=", value, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressLike(String value) {
            addCriterion("bike_address like", value, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressNotLike(String value) {
            addCriterion("bike_address not like", value, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressIn(List<String> values) {
            addCriterion("bike_address in", values, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressNotIn(List<String> values) {
            addCriterion("bike_address not in", values, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressBetween(String value1, String value2) {
            addCriterion("bike_address between", value1, value2, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAddressNotBetween(String value1, String value2) {
            addCriterion("bike_address not between", value1, value2, "bikeAddress");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeIsNull() {
            addCriterion("bike_atitude is null");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeIsNotNull() {
            addCriterion("bike_atitude is not null");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeEqualTo(Double value) {
            addCriterion("bike_atitude =", value, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeNotEqualTo(Double value) {
            addCriterion("bike_atitude <>", value, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeGreaterThan(Double value) {
            addCriterion("bike_atitude >", value, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("bike_atitude >=", value, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeLessThan(Double value) {
            addCriterion("bike_atitude <", value, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeLessThanOrEqualTo(Double value) {
            addCriterion("bike_atitude <=", value, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeIn(List<Double> values) {
            addCriterion("bike_atitude in", values, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeNotIn(List<Double> values) {
            addCriterion("bike_atitude not in", values, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeBetween(Double value1, Double value2) {
            addCriterion("bike_atitude between", value1, value2, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeAtitudeNotBetween(Double value1, Double value2) {
            addCriterion("bike_atitude not between", value1, value2, "bikeAtitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeIsNull() {
            addCriterion("bike_longitude is null");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeIsNotNull() {
            addCriterion("bike_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeEqualTo(Double value) {
            addCriterion("bike_longitude =", value, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeNotEqualTo(Double value) {
            addCriterion("bike_longitude <>", value, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeGreaterThan(Double value) {
            addCriterion("bike_longitude >", value, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("bike_longitude >=", value, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeLessThan(Double value) {
            addCriterion("bike_longitude <", value, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("bike_longitude <=", value, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeIn(List<Double> values) {
            addCriterion("bike_longitude in", values, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeNotIn(List<Double> values) {
            addCriterion("bike_longitude not in", values, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeBetween(Double value1, Double value2) {
            addCriterion("bike_longitude between", value1, value2, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("bike_longitude not between", value1, value2, "bikeLongitude");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumIsNull() {
            addCriterion("bike_rent_num is null");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumIsNotNull() {
            addCriterion("bike_rent_num is not null");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumEqualTo(Integer value) {
            addCriterion("bike_rent_num =", value, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumNotEqualTo(Integer value) {
            addCriterion("bike_rent_num <>", value, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumGreaterThan(Integer value) {
            addCriterion("bike_rent_num >", value, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_rent_num >=", value, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumLessThan(Integer value) {
            addCriterion("bike_rent_num <", value, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumLessThanOrEqualTo(Integer value) {
            addCriterion("bike_rent_num <=", value, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumIn(List<Integer> values) {
            addCriterion("bike_rent_num in", values, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumNotIn(List<Integer> values) {
            addCriterion("bike_rent_num not in", values, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumBetween(Integer value1, Integer value2) {
            addCriterion("bike_rent_num between", value1, value2, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeRentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_rent_num not between", value1, value2, "bikeRentNum");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeIsNull() {
            addCriterion("bike_createtime is null");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeIsNotNull() {
            addCriterion("bike_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeEqualTo(Date value) {
            addCriterion("bike_createtime =", value, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeNotEqualTo(Date value) {
            addCriterion("bike_createtime <>", value, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeGreaterThan(Date value) {
            addCriterion("bike_createtime >", value, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bike_createtime >=", value, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeLessThan(Date value) {
            addCriterion("bike_createtime <", value, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("bike_createtime <=", value, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeIn(List<Date> values) {
            addCriterion("bike_createtime in", values, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeNotIn(List<Date> values) {
            addCriterion("bike_createtime not in", values, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeBetween(Date value1, Date value2) {
            addCriterion("bike_createtime between", value1, value2, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("bike_createtime not between", value1, value2, "bikeCreatetime");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberIsNull() {
            addCriterion("bike_batch_number is null");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberIsNotNull() {
            addCriterion("bike_batch_number is not null");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberEqualTo(String value) {
            addCriterion("bike_batch_number =", value, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberNotEqualTo(String value) {
            addCriterion("bike_batch_number <>", value, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberGreaterThan(String value) {
            addCriterion("bike_batch_number >", value, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberGreaterThanOrEqualTo(String value) {
            addCriterion("bike_batch_number >=", value, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberLessThan(String value) {
            addCriterion("bike_batch_number <", value, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberLessThanOrEqualTo(String value) {
            addCriterion("bike_batch_number <=", value, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberLike(String value) {
            addCriterion("bike_batch_number like", value, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberNotLike(String value) {
            addCriterion("bike_batch_number not like", value, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberIn(List<String> values) {
            addCriterion("bike_batch_number in", values, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberNotIn(List<String> values) {
            addCriterion("bike_batch_number not in", values, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberBetween(String value1, String value2) {
            addCriterion("bike_batch_number between", value1, value2, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeBatchNumberNotBetween(String value1, String value2) {
            addCriterion("bike_batch_number not between", value1, value2, "bikeBatchNumber");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdIsNull() {
            addCriterion("bike_lock_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdIsNotNull() {
            addCriterion("bike_lock_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdEqualTo(Long value) {
            addCriterion("bike_lock_id =", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdNotEqualTo(Long value) {
            addCriterion("bike_lock_id <>", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdGreaterThan(Long value) {
            addCriterion("bike_lock_id >", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_lock_id >=", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdLessThan(Long value) {
            addCriterion("bike_lock_id <", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_lock_id <=", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdIn(List<Long> values) {
            addCriterion("bike_lock_id in", values, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdNotIn(List<Long> values) {
            addCriterion("bike_lock_id not in", values, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdBetween(Long value1, Long value2) {
            addCriterion("bike_lock_id between", value1, value2, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_lock_id not between", value1, value2, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeIsNull() {
            addCriterion("bike_chip_code is null");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeIsNotNull() {
            addCriterion("bike_chip_code is not null");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeEqualTo(String value) {
            addCriterion("bike_chip_code =", value, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeNotEqualTo(String value) {
            addCriterion("bike_chip_code <>", value, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeGreaterThan(String value) {
            addCriterion("bike_chip_code >", value, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bike_chip_code >=", value, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeLessThan(String value) {
            addCriterion("bike_chip_code <", value, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeLessThanOrEqualTo(String value) {
            addCriterion("bike_chip_code <=", value, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeLike(String value) {
            addCriterion("bike_chip_code like", value, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeNotLike(String value) {
            addCriterion("bike_chip_code not like", value, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeIn(List<String> values) {
            addCriterion("bike_chip_code in", values, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeNotIn(List<String> values) {
            addCriterion("bike_chip_code not in", values, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeBetween(String value1, String value2) {
            addCriterion("bike_chip_code between", value1, value2, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeChipCodeNotBetween(String value1, String value2) {
            addCriterion("bike_chip_code not between", value1, value2, "bikeChipCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeIsNull() {
            addCriterion("bike_dynamotor_code is null");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeIsNotNull() {
            addCriterion("bike_dynamotor_code is not null");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeEqualTo(String value) {
            addCriterion("bike_dynamotor_code =", value, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeNotEqualTo(String value) {
            addCriterion("bike_dynamotor_code <>", value, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeGreaterThan(String value) {
            addCriterion("bike_dynamotor_code >", value, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bike_dynamotor_code >=", value, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeLessThan(String value) {
            addCriterion("bike_dynamotor_code <", value, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeLessThanOrEqualTo(String value) {
            addCriterion("bike_dynamotor_code <=", value, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeLike(String value) {
            addCriterion("bike_dynamotor_code like", value, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeNotLike(String value) {
            addCriterion("bike_dynamotor_code not like", value, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeIn(List<String> values) {
            addCriterion("bike_dynamotor_code in", values, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeNotIn(List<String> values) {
            addCriterion("bike_dynamotor_code not in", values, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeBetween(String value1, String value2) {
            addCriterion("bike_dynamotor_code between", value1, value2, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikeDynamotorCodeNotBetween(String value1, String value2) {
            addCriterion("bike_dynamotor_code not between", value1, value2, "bikeDynamotorCode");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeIsNull() {
            addCriterion("bike_put_time is null");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeIsNotNull() {
            addCriterion("bike_put_time is not null");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeEqualTo(Date value) {
            addCriterion("bike_put_time =", value, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeNotEqualTo(Date value) {
            addCriterion("bike_put_time <>", value, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeGreaterThan(Date value) {
            addCriterion("bike_put_time >", value, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bike_put_time >=", value, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeLessThan(Date value) {
            addCriterion("bike_put_time <", value, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeLessThanOrEqualTo(Date value) {
            addCriterion("bike_put_time <=", value, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeIn(List<Date> values) {
            addCriterion("bike_put_time in", values, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeNotIn(List<Date> values) {
            addCriterion("bike_put_time not in", values, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeBetween(Date value1, Date value2) {
            addCriterion("bike_put_time between", value1, value2, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikePutTimeNotBetween(Date value1, Date value2) {
            addCriterion("bike_put_time not between", value1, value2, "bikePutTime");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdIsNull() {
            addCriterion("bike_models_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdIsNotNull() {
            addCriterion("bike_models_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdEqualTo(Long value) {
            addCriterion("bike_models_id =", value, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdNotEqualTo(Long value) {
            addCriterion("bike_models_id <>", value, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdGreaterThan(Long value) {
            addCriterion("bike_models_id >", value, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_models_id >=", value, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdLessThan(Long value) {
            addCriterion("bike_models_id <", value, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_models_id <=", value, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdIn(List<Long> values) {
            addCriterion("bike_models_id in", values, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdNotIn(List<Long> values) {
            addCriterion("bike_models_id not in", values, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdBetween(Long value1, Long value2) {
            addCriterion("bike_models_id between", value1, value2, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeModelsIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_models_id not between", value1, value2, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeIsNull() {
            addCriterion("bike_last_rent_time is null");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeIsNotNull() {
            addCriterion("bike_last_rent_time is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeEqualTo(Date value) {
            addCriterion("bike_last_rent_time =", value, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeNotEqualTo(Date value) {
            addCriterion("bike_last_rent_time <>", value, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeGreaterThan(Date value) {
            addCriterion("bike_last_rent_time >", value, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bike_last_rent_time >=", value, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeLessThan(Date value) {
            addCriterion("bike_last_rent_time <", value, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeLessThanOrEqualTo(Date value) {
            addCriterion("bike_last_rent_time <=", value, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeIn(List<Date> values) {
            addCriterion("bike_last_rent_time in", values, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeNotIn(List<Date> values) {
            addCriterion("bike_last_rent_time not in", values, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeBetween(Date value1, Date value2) {
            addCriterion("bike_last_rent_time between", value1, value2, "bikeLastRentTime");
            return (Criteria) this;
        }

        public Criteria andBikeLastRentTimeNotBetween(Date value1, Date value2) {
            addCriterion("bike_last_rent_time not between", value1, value2, "bikeLastRentTime");
            return (Criteria) this;
        }

		public Criteria andManagerNameLike(String value) {
            addCriterion("manager_name like", value, "managerName");
            return (Criteria) this;
        }
        
        public Criteria andBikeLockCodeLike(String value) {
            addCriterion("bike_lock_code like", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameIsNull() {
            addCriterion("bike_manager_name is null");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameIsNotNull() {
            addCriterion("bike_manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameEqualTo(String value) {
            addCriterion("bike_manager_name =", value, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameNotEqualTo(String value) {
            addCriterion("bike_manager_name <>", value, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameGreaterThan(String value) {
            addCriterion("bike_manager_name >", value, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("bike_manager_name >=", value, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameLessThan(String value) {
            addCriterion("bike_manager_name <", value, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameLessThanOrEqualTo(String value) {
            addCriterion("bike_manager_name <=", value, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameLike(String value) {
            addCriterion("bike_manager_name like", value, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameNotLike(String value) {
            addCriterion("bike_manager_name not like", value, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameIn(List<String> values) {
            addCriterion("bike_manager_name in", values, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameNotIn(List<String> values) {
            addCriterion("bike_manager_name not in", values, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameBetween(String value1, String value2) {
            addCriterion("bike_manager_name between", value1, value2, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerNameNotBetween(String value1, String value2) {
            addCriterion("bike_manager_name not between", value1, value2, "bikeManagerName");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelIsNull() {
            addCriterion("bike_manager_tel is null");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelIsNotNull() {
            addCriterion("bike_manager_tel is not null");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelEqualTo(String value) {
            addCriterion("bike_manager_tel =", value, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelNotEqualTo(String value) {
            addCriterion("bike_manager_tel <>", value, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelGreaterThan(String value) {
            addCriterion("bike_manager_tel >", value, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelGreaterThanOrEqualTo(String value) {
            addCriterion("bike_manager_tel >=", value, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelLessThan(String value) {
            addCriterion("bike_manager_tel <", value, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelLessThanOrEqualTo(String value) {
            addCriterion("bike_manager_tel <=", value, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelLike(String value) {
            addCriterion("bike_manager_tel like", value, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelNotLike(String value) {
            addCriterion("bike_manager_tel not like", value, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelIn(List<String> values) {
            addCriterion("bike_manager_tel in", values, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelNotIn(List<String> values) {
            addCriterion("bike_manager_tel not in", values, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelBetween(String value1, String value2) {
            addCriterion("bike_manager_tel between", value1, value2, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeManagerTelNotBetween(String value1, String value2) {
            addCriterion("bike_manager_tel not between", value1, value2, "bikeManagerTel");
            return (Criteria) this;
        }

        public Criteria andBikeAlertIsNull() {
            addCriterion("bike_alert is null");
            return (Criteria) this;
        }

        public Criteria andBikeAlertIsNotNull() {
            addCriterion("bike_alert is not null");
            return (Criteria) this;
        }

        public Criteria andBikeAlertEqualTo(Integer value) {
            addCriterion("bike_alert =", value, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertNotEqualTo(Integer value) {
            addCriterion("bike_alert <>", value, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertGreaterThan(Integer value) {
            addCriterion("bike_alert >", value, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_alert >=", value, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertLessThan(Integer value) {
            addCriterion("bike_alert <", value, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertLessThanOrEqualTo(Integer value) {
            addCriterion("bike_alert <=", value, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertIn(List<Integer> values) {
            addCriterion("bike_alert in", values, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertNotIn(List<Integer> values) {
            addCriterion("bike_alert not in", values, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertBetween(Integer value1, Integer value2) {
            addCriterion("bike_alert between", value1, value2, "bikeAlert");
            return (Criteria) this;
        }

        public Criteria andBikeAlertNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_alert not between", value1, value2, "bikeAlert");
            return (Criteria) this;
        }
        
        public Criteria andBikeFixedReturnIdIsNull() {
            addCriterion("bike_fixed_return_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdIsNotNull() {
            addCriterion("bike_fixed_return_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdEqualTo(Long value) {
            addCriterion("bike_fixed_return_id =", value, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdNotEqualTo(Long value) {
            addCriterion("bike_fixed_return_id <>", value, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdGreaterThan(Long value) {
            addCriterion("bike_fixed_return_id >", value, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_fixed_return_id >=", value, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdLessThan(Long value) {
            addCriterion("bike_fixed_return_id <", value, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_fixed_return_id <=", value, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdIn(List<Long> values) {
            addCriterion("bike_fixed_return_id in", values, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdNotIn(List<Long> values) {
            addCriterion("bike_fixed_return_id not in", values, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdBetween(Long value1, Long value2) {
            addCriterion("bike_fixed_return_id between", value1, value2, "bikeFixedReturnId");
            return (Criteria) this;
        }

        public Criteria andBikeFixedReturnIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_fixed_return_id not between", value1, value2, "bikeFixedReturnId");
            return (Criteria) this;
        }
        
        public Criteria andBikeTemporarylocktimeIsNull() {
            addCriterion("bike_temporarylocktime is null");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeIsNotNull() {
            addCriterion("bike_temporarylocktime is not null");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeEqualTo(Date value) {
            addCriterion("bike_temporarylocktime =", value, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeNotEqualTo(Date value) {
            addCriterion("bike_temporarylocktime <>", value, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeGreaterThan(Date value) {
            addCriterion("bike_temporarylocktime >", value, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bike_temporarylocktime >=", value, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeLessThan(Date value) {
            addCriterion("bike_temporarylocktime <", value, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeLessThanOrEqualTo(Date value) {
            addCriterion("bike_temporarylocktime <=", value, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeIn(List<Date> values) {
            addCriterion("bike_temporarylocktime in", values, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeNotIn(List<Date> values) {
            addCriterion("bike_temporarylocktime not in", values, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeBetween(Date value1, Date value2) {
            addCriterion("bike_temporarylocktime between", value1, value2, "bikeTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andBikeTemporarylocktimeNotBetween(Date value1, Date value2) {
            addCriterion("bike_temporarylocktime not between", value1, value2, "bikeTemporarylocktime");
            return (Criteria) this;
        }
        
        public Criteria andBikeBlockIsNull() {
            addCriterion("bike_block is null");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIsNotNull() {
            addCriterion("bike_block is not null");
            return (Criteria) this;
        }

        public Criteria andBikeBlockEqualTo(String value) {
            addCriterion("bike_block =", value, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockNotEqualTo(String value) {
            addCriterion("bike_block <>", value, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockGreaterThan(String value) {
            addCriterion("bike_block >", value, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockGreaterThanOrEqualTo(String value) {
            addCriterion("bike_block >=", value, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockLessThan(String value) {
            addCriterion("bike_block <", value, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockLessThanOrEqualTo(String value) {
            addCriterion("bike_block <=", value, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockLike(String value) {
            addCriterion("bike_block like", value, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockNotLike(String value) {
            addCriterion("bike_block not like", value, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockIn(List<String> values) {
            addCriterion("bike_block in", values, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockNotIn(List<String> values) {
            addCriterion("bike_block not in", values, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockBetween(String value1, String value2) {
            addCriterion("bike_block between", value1, value2, "bikeBlock");
            return (Criteria) this;
        }

        public Criteria andBikeBlockNotBetween(String value1, String value2) {
            addCriterion("bike_block not between", value1, value2, "bikeBlock");
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