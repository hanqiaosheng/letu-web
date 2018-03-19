package org.entity.dto;

import java.util.ArrayList;
import java.util.List;



public class BikeLockInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public BikeLockInfoExample() {
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
        

        //车辆编号
        public Criteria andBikeCodeLike(String value) {
            addCriterion("bike_code like", value, "bikeCode");
            return (Criteria) this;
        }
        
        //车型
        public Criteria andModelsIdEqualTo(Long value) {
            addCriterion("bike_models_id =", value, "bikeModelsId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdIsNull() {
            addCriterion("bike_lock_Id is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdIsNotNull() {
            addCriterion("bike_lock_Id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdEqualTo(Long value) {
            addCriterion("bike_lock_Id =", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdNotEqualTo(Long value) {
            addCriterion("bike_lock_Id <>", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdGreaterThan(Long value) {
            addCriterion("bike_lock_Id >", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_lock_Id >=", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdLessThan(Long value) {
            addCriterion("bike_lock_Id <", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_lock_Id <=", value, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdIn(List<Long> values) {
            addCriterion("bike_lock_Id in", values, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdNotIn(List<Long> values) {
            addCriterion("bike_lock_Id not in", values, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdBetween(Long value1, Long value2) {
            addCriterion("bike_lock_Id between", value1, value2, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_lock_Id not between", value1, value2, "bikeLockId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdIsNull() {
            addCriterion("bike_lock_bike_id is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdIsNotNull() {
            addCriterion("bike_lock_bike_id is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdEqualTo(Long value) {
            addCriterion("bike_lock_bike_id =", value, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdNotEqualTo(Long value) {
            addCriterion("bike_lock_bike_id <>", value, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdGreaterThan(Long value) {
            addCriterion("bike_lock_bike_id >", value, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_lock_bike_id >=", value, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdLessThan(Long value) {
            addCriterion("bike_lock_bike_id <", value, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdLessThanOrEqualTo(Long value) {
            addCriterion("bike_lock_bike_id <=", value, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdIn(List<Long> values) {
            addCriterion("bike_lock_bike_id in", values, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdNotIn(List<Long> values) {
            addCriterion("bike_lock_bike_id not in", values, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdBetween(Long value1, Long value2) {
            addCriterion("bike_lock_bike_id between", value1, value2, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockBikeIdNotBetween(Long value1, Long value2) {
            addCriterion("bike_lock_bike_id not between", value1, value2, "bikeLockBikeId");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeIsNull() {
            addCriterion("bike_lock_code is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeIsNotNull() {
            addCriterion("bike_lock_code is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeEqualTo(String value) {
            addCriterion("bike_lock_code =", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeNotEqualTo(String value) {
            addCriterion("bike_lock_code <>", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeGreaterThan(String value) {
            addCriterion("bike_lock_code >", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bike_lock_code >=", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeLessThan(String value) {
            addCriterion("bike_lock_code <", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeLessThanOrEqualTo(String value) {
            addCriterion("bike_lock_code <=", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeLike(String value) {
            addCriterion("bike_lock_code like", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeNotLike(String value) {
            addCriterion("bike_lock_code not like", value, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeIn(List<String> values) {
            addCriterion("bike_lock_code in", values, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeNotIn(List<String> values) {
            addCriterion("bike_lock_code not in", values, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeBetween(String value1, String value2) {
            addCriterion("bike_lock_code between", value1, value2, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockCodeNotBetween(String value1, String value2) {
            addCriterion("bike_lock_code not between", value1, value2, "bikeLockCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateIsNull() {
            addCriterion("bike_lock_equipment_state is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateIsNotNull() {
            addCriterion("bike_lock_equipment_state is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateEqualTo(Integer value) {
            addCriterion("bike_lock_equipment_state =", value, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateNotEqualTo(Integer value) {
            addCriterion("bike_lock_equipment_state <>", value, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateGreaterThan(Integer value) {
            addCriterion("bike_lock_equipment_state >", value, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_equipment_state >=", value, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateLessThan(Integer value) {
            addCriterion("bike_lock_equipment_state <", value, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_equipment_state <=", value, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateIn(List<Integer> values) {
            addCriterion("bike_lock_equipment_state in", values, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateNotIn(List<Integer> values) {
            addCriterion("bike_lock_equipment_state not in", values, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_equipment_state between", value1, value2, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockEquipmentStateNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_equipment_state not between", value1, value2, "bikeLockEquipmentState");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageIsNull() {
            addCriterion("bike_lock_voltage is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageIsNotNull() {
            addCriterion("bike_lock_voltage is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageEqualTo(Double value) {
            addCriterion("bike_lock_voltage =", value, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageNotEqualTo(Double value) {
            addCriterion("bike_lock_voltage <>", value, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageGreaterThan(Double value) {
            addCriterion("bike_lock_voltage >", value, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageGreaterThanOrEqualTo(Double value) {
            addCriterion("bike_lock_voltage >=", value, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageLessThan(Double value) {
            addCriterion("bike_lock_voltage <", value, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageLessThanOrEqualTo(Double value) {
            addCriterion("bike_lock_voltage <=", value, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageIn(List<Double> values) {
            addCriterion("bike_lock_voltage in", values, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageNotIn(List<Double> values) {
            addCriterion("bike_lock_voltage not in", values, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageBetween(Double value1, Double value2) {
            addCriterion("bike_lock_voltage between", value1, value2, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockVoltageNotBetween(Double value1, Double value2) {
            addCriterion("bike_lock_voltage not between", value1, value2, "bikeLockVoltage");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusIsNull() {
            addCriterion("bike_lock_status is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusIsNotNull() {
            addCriterion("bike_lock_status is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusEqualTo(Integer value) {
            addCriterion("bike_lock_status =", value, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusNotEqualTo(Integer value) {
            addCriterion("bike_lock_status <>", value, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusGreaterThan(Integer value) {
            addCriterion("bike_lock_status >", value, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_status >=", value, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusLessThan(Integer value) {
            addCriterion("bike_lock_status <", value, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_status <=", value, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusIn(List<Integer> values) {
            addCriterion("bike_lock_status in", values, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusNotIn(List<Integer> values) {
            addCriterion("bike_lock_status not in", values, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_status between", value1, value2, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_status not between", value1, value2, "bikeLockStatus");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatIsNull() {
            addCriterion("bike_lock_lat is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatIsNotNull() {
            addCriterion("bike_lock_lat is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatEqualTo(Double value) {
            addCriterion("bike_lock_lat =", value, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatNotEqualTo(Double value) {
            addCriterion("bike_lock_lat <>", value, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatGreaterThan(Double value) {
            addCriterion("bike_lock_lat >", value, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatGreaterThanOrEqualTo(Double value) {
            addCriterion("bike_lock_lat >=", value, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatLessThan(Double value) {
            addCriterion("bike_lock_lat <", value, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatLessThanOrEqualTo(Double value) {
            addCriterion("bike_lock_lat <=", value, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatIn(List<Double> values) {
            addCriterion("bike_lock_lat in", values, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatNotIn(List<Double> values) {
            addCriterion("bike_lock_lat not in", values, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatBetween(Double value1, Double value2) {
            addCriterion("bike_lock_lat between", value1, value2, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLatNotBetween(Double value1, Double value2) {
            addCriterion("bike_lock_lat not between", value1, value2, "bikeLockLat");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngIsNull() {
            addCriterion("bike_lock_lng is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngIsNotNull() {
            addCriterion("bike_lock_lng is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngEqualTo(Double value) {
            addCriterion("bike_lock_lng =", value, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngNotEqualTo(Double value) {
            addCriterion("bike_lock_lng <>", value, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngGreaterThan(Double value) {
            addCriterion("bike_lock_lng >", value, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngGreaterThanOrEqualTo(Double value) {
            addCriterion("bike_lock_lng >=", value, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngLessThan(Double value) {
            addCriterion("bike_lock_lng <", value, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngLessThanOrEqualTo(Double value) {
            addCriterion("bike_lock_lng <=", value, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngIn(List<Double> values) {
            addCriterion("bike_lock_lng in", values, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngNotIn(List<Double> values) {
            addCriterion("bike_lock_lng not in", values, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngBetween(Double value1, Double value2) {
            addCriterion("bike_lock_lng between", value1, value2, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockLngNotBetween(Double value1, Double value2) {
            addCriterion("bike_lock_lng not between", value1, value2, "bikeLockLng");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightIsNull() {
            addCriterion("bike_lock_height is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightIsNotNull() {
            addCriterion("bike_lock_height is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightEqualTo(Double value) {
            addCriterion("bike_lock_height =", value, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightNotEqualTo(Double value) {
            addCriterion("bike_lock_height <>", value, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightGreaterThan(Double value) {
            addCriterion("bike_lock_height >", value, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightGreaterThanOrEqualTo(Double value) {
            addCriterion("bike_lock_height >=", value, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightLessThan(Double value) {
            addCriterion("bike_lock_height <", value, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightLessThanOrEqualTo(Double value) {
            addCriterion("bike_lock_height <=", value, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightIn(List<Double> values) {
            addCriterion("bike_lock_height in", values, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightNotIn(List<Double> values) {
            addCriterion("bike_lock_height not in", values, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightBetween(Double value1, Double value2) {
            addCriterion("bike_lock_height between", value1, value2, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockHeightNotBetween(Double value1, Double value2) {
            addCriterion("bike_lock_height not between", value1, value2, "bikeLockHeight");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelIsNull() {
            addCriterion("bike_lock_del is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelIsNotNull() {
            addCriterion("bike_lock_del is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelEqualTo(Integer value) {
            addCriterion("bike_lock_del =", value, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelNotEqualTo(Integer value) {
            addCriterion("bike_lock_del <>", value, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelGreaterThan(Integer value) {
            addCriterion("bike_lock_del >", value, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_del >=", value, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelLessThan(Integer value) {
            addCriterion("bike_lock_del <", value, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_del <=", value, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelIn(List<Integer> values) {
            addCriterion("bike_lock_del in", values, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelNotIn(List<Integer> values) {
            addCriterion("bike_lock_del not in", values, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_del between", value1, value2, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockDelNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_del not between", value1, value2, "bikeLockDel");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidIsNull() {
            addCriterion("bike_lock_blockid is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidIsNotNull() {
            addCriterion("bike_lock_blockid is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidEqualTo(Long value) {
            addCriterion("bike_lock_blockid =", value, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidNotEqualTo(Long value) {
            addCriterion("bike_lock_blockid <>", value, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidGreaterThan(Long value) {
            addCriterion("bike_lock_blockid >", value, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidGreaterThanOrEqualTo(Long value) {
            addCriterion("bike_lock_blockid >=", value, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidLessThan(Long value) {
            addCriterion("bike_lock_blockid <", value, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidLessThanOrEqualTo(Long value) {
            addCriterion("bike_lock_blockid <=", value, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidIn(List<Long> values) {
            addCriterion("bike_lock_blockid in", values, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidNotIn(List<Long> values) {
            addCriterion("bike_lock_blockid not in", values, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidBetween(Long value1, Long value2) {
            addCriterion("bike_lock_blockid between", value1, value2, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockBlockidNotBetween(Long value1, Long value2) {
            addCriterion("bike_lock_blockid not between", value1, value2, "bikeLockBlockid");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerIsNull() {
            addCriterion("bike_lock_power is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerIsNotNull() {
            addCriterion("bike_lock_power is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerEqualTo(Integer value) {
            addCriterion("bike_lock_power =", value, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerNotEqualTo(Integer value) {
            addCriterion("bike_lock_power <>", value, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerGreaterThan(Integer value) {
            addCriterion("bike_lock_power >", value, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_power >=", value, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerLessThan(Integer value) {
            addCriterion("bike_lock_power <", value, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_power <=", value, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerIn(List<Integer> values) {
            addCriterion("bike_lock_power in", values, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerNotIn(List<Integer> values) {
            addCriterion("bike_lock_power not in", values, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_power between", value1, value2, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockPowerNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_power not between", value1, value2, "bikeLockPower");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdIsNull() {
            addCriterion("bike_lock_domain_pwd is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdIsNotNull() {
            addCriterion("bike_lock_domain_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdEqualTo(String value) {
            addCriterion("bike_lock_domain_pwd =", value, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdNotEqualTo(String value) {
            addCriterion("bike_lock_domain_pwd <>", value, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdGreaterThan(String value) {
            addCriterion("bike_lock_domain_pwd >", value, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdGreaterThanOrEqualTo(String value) {
            addCriterion("bike_lock_domain_pwd >=", value, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdLessThan(String value) {
            addCriterion("bike_lock_domain_pwd <", value, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdLessThanOrEqualTo(String value) {
            addCriterion("bike_lock_domain_pwd <=", value, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdLike(String value) {
            addCriterion("bike_lock_domain_pwd like", value, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdNotLike(String value) {
            addCriterion("bike_lock_domain_pwd not like", value, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdIn(List<String> values) {
            addCriterion("bike_lock_domain_pwd in", values, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdNotIn(List<String> values) {
            addCriterion("bike_lock_domain_pwd not in", values, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdBetween(String value1, String value2) {
            addCriterion("bike_lock_domain_pwd between", value1, value2, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdNotBetween(String value1, String value2) {
            addCriterion("bike_lock_domain_pwd not between", value1, value2, "bikeLockDomainPwd");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainIsNull() {
            addCriterion("bike_lock_domain is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainIsNotNull() {
            addCriterion("bike_lock_domain is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainEqualTo(String value) {
            addCriterion("bike_lock_domain =", value, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainNotEqualTo(String value) {
            addCriterion("bike_lock_domain <>", value, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainGreaterThan(String value) {
            addCriterion("bike_lock_domain >", value, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainGreaterThanOrEqualTo(String value) {
            addCriterion("bike_lock_domain >=", value, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainLessThan(String value) {
            addCriterion("bike_lock_domain <", value, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainLessThanOrEqualTo(String value) {
            addCriterion("bike_lock_domain <=", value, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainLike(String value) {
            addCriterion("bike_lock_domain like", value, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainNotLike(String value) {
            addCriterion("bike_lock_domain not like", value, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainIn(List<String> values) {
            addCriterion("bike_lock_domain in", values, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainNotIn(List<String> values) {
            addCriterion("bike_lock_domain not in", values, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainBetween(String value1, String value2) {
            addCriterion("bike_lock_domain between", value1, value2, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainNotBetween(String value1, String value2) {
            addCriterion("bike_lock_domain not between", value1, value2, "bikeLockDomain");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortIsNull() {
            addCriterion("bike_lock_port is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortIsNotNull() {
            addCriterion("bike_lock_port is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortEqualTo(String value) {
            addCriterion("bike_lock_port =", value, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortNotEqualTo(String value) {
            addCriterion("bike_lock_port <>", value, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortGreaterThan(String value) {
            addCriterion("bike_lock_port >", value, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortGreaterThanOrEqualTo(String value) {
            addCriterion("bike_lock_port >=", value, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortLessThan(String value) {
            addCriterion("bike_lock_port <", value, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortLessThanOrEqualTo(String value) {
            addCriterion("bike_lock_port <=", value, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortLike(String value) {
            addCriterion("bike_lock_port like", value, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortNotLike(String value) {
            addCriterion("bike_lock_port not like", value, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortIn(List<String> values) {
            addCriterion("bike_lock_port in", values, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortNotIn(List<String> values) {
            addCriterion("bike_lock_port not in", values, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortBetween(String value1, String value2) {
            addCriterion("bike_lock_port between", value1, value2, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockPortNotBetween(String value1, String value2) {
            addCriterion("bike_lock_port not between", value1, value2, "bikeLockPort");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateIsNull() {
            addCriterion("bike_lock_domain_pwd_state is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateIsNotNull() {
            addCriterion("bike_lock_domain_pwd_state is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateEqualTo(Integer value) {
            addCriterion("bike_lock_domain_pwd_state =", value, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateNotEqualTo(Integer value) {
            addCriterion("bike_lock_domain_pwd_state <>", value, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateGreaterThan(Integer value) {
            addCriterion("bike_lock_domain_pwd_state >", value, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_domain_pwd_state >=", value, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateLessThan(Integer value) {
            addCriterion("bike_lock_domain_pwd_state <", value, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_domain_pwd_state <=", value, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateIn(List<Integer> values) {
            addCriterion("bike_lock_domain_pwd_state in", values, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateNotIn(List<Integer> values) {
            addCriterion("bike_lock_domain_pwd_state not in", values, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_domain_pwd_state between", value1, value2, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDomainPwdStateNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_domain_pwd_state not between", value1, value2, "bikeLockDomainPwdState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateIsNull() {
            addCriterion("bike_lock_domin_state is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateIsNotNull() {
            addCriterion("bike_lock_domin_state is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateEqualTo(Integer value) {
            addCriterion("bike_lock_domin_state =", value, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateNotEqualTo(Integer value) {
            addCriterion("bike_lock_domin_state <>", value, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateGreaterThan(Integer value) {
            addCriterion("bike_lock_domin_state >", value, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_domin_state >=", value, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateLessThan(Integer value) {
            addCriterion("bike_lock_domin_state <", value, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_domin_state <=", value, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateIn(List<Integer> values) {
            addCriterion("bike_lock_domin_state in", values, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateNotIn(List<Integer> values) {
            addCriterion("bike_lock_domin_state not in", values, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_domin_state between", value1, value2, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockDominStateNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_domin_state not between", value1, value2, "bikeLockDominState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateIsNull() {
            addCriterion("bike_lock_state is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateIsNotNull() {
            addCriterion("bike_lock_state is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateEqualTo(Integer value) {
            addCriterion("bike_lock_state =", value, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateNotEqualTo(Integer value) {
            addCriterion("bike_lock_state <>", value, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateGreaterThan(Integer value) {
            addCriterion("bike_lock_state >", value, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_state >=", value, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateLessThan(Integer value) {
            addCriterion("bike_lock_state <", value, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_state <=", value, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateIn(List<Integer> values) {
            addCriterion("bike_lock_state in", values, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateNotIn(List<Integer> values) {
            addCriterion("bike_lock_state not in", values, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_state between", value1, value2, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockStateNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_state not between", value1, value2, "bikeLockState");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeIsNull() {
            addCriterion("bike_lock_type is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeIsNotNull() {
            addCriterion("bike_lock_type is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeEqualTo(Integer value) {
            addCriterion("bike_lock_type =", value, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeNotEqualTo(Integer value) {
            addCriterion("bike_lock_type <>", value, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeGreaterThan(Integer value) {
            addCriterion("bike_lock_type >", value, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_type >=", value, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeLessThan(Integer value) {
            addCriterion("bike_lock_type <", value, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_type <=", value, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeIn(List<Integer> values) {
            addCriterion("bike_lock_type in", values, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeNotIn(List<Integer> values) {
            addCriterion("bike_lock_type not in", values, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_type between", value1, value2, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_type not between", value1, value2, "bikeLockType");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionIsNull() {
            addCriterion("bike_lock_version is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionIsNotNull() {
            addCriterion("bike_lock_version is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionEqualTo(String value) {
            addCriterion("bike_lock_version =", value, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionNotEqualTo(String value) {
            addCriterion("bike_lock_version <>", value, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionGreaterThan(String value) {
            addCriterion("bike_lock_version >", value, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionGreaterThanOrEqualTo(String value) {
            addCriterion("bike_lock_version >=", value, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionLessThan(String value) {
            addCriterion("bike_lock_version <", value, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionLessThanOrEqualTo(String value) {
            addCriterion("bike_lock_version <=", value, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionLike(String value) {
            addCriterion("bike_lock_version like", value, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionNotLike(String value) {
            addCriterion("bike_lock_version not like", value, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionIn(List<String> values) {
            addCriterion("bike_lock_version in", values, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionNotIn(List<String> values) {
            addCriterion("bike_lock_version not in", values, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionBetween(String value1, String value2) {
            addCriterion("bike_lock_version between", value1, value2, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockVersionNotBetween(String value1, String value2) {
            addCriterion("bike_lock_version not between", value1, value2, "bikeLockVersion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateIsNull() {
            addCriterion("bike_lock_upstate is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateIsNotNull() {
            addCriterion("bike_lock_upstate is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateEqualTo(Integer value) {
            addCriterion("bike_lock_upstate =", value, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateNotEqualTo(Integer value) {
            addCriterion("bike_lock_upstate <>", value, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateGreaterThan(Integer value) {
            addCriterion("bike_lock_upstate >", value, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_upstate >=", value, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateLessThan(Integer value) {
            addCriterion("bike_lock_upstate <", value, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_upstate <=", value, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateIn(List<Integer> values) {
            addCriterion("bike_lock_upstate in", values, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateNotIn(List<Integer> values) {
            addCriterion("bike_lock_upstate not in", values, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_upstate between", value1, value2, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpstateNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_upstate not between", value1, value2, "bikeLockUpstate");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileIsNull() {
            addCriterion("bike_lock_upfile is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileIsNotNull() {
            addCriterion("bike_lock_upfile is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileEqualTo(String value) {
            addCriterion("bike_lock_upfile =", value, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileNotEqualTo(String value) {
            addCriterion("bike_lock_upfile <>", value, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileGreaterThan(String value) {
            addCriterion("bike_lock_upfile >", value, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileGreaterThanOrEqualTo(String value) {
            addCriterion("bike_lock_upfile >=", value, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileLessThan(String value) {
            addCriterion("bike_lock_upfile <", value, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileLessThanOrEqualTo(String value) {
            addCriterion("bike_lock_upfile <=", value, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileLike(String value) {
            addCriterion("bike_lock_upfile like", value, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileNotLike(String value) {
            addCriterion("bike_lock_upfile not like", value, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileIn(List<String> values) {
            addCriterion("bike_lock_upfile in", values, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileNotIn(List<String> values) {
            addCriterion("bike_lock_upfile not in", values, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileBetween(String value1, String value2) {
            addCriterion("bike_lock_upfile between", value1, value2, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpfileNotBetween(String value1, String value2) {
            addCriterion("bike_lock_upfile not between", value1, value2, "bikeLockUpfile");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionIsNull() {
            addCriterion("bike_lock_upversion is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionIsNotNull() {
            addCriterion("bike_lock_upversion is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionEqualTo(String value) {
            addCriterion("bike_lock_upversion =", value, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionNotEqualTo(String value) {
            addCriterion("bike_lock_upversion <>", value, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionGreaterThan(String value) {
            addCriterion("bike_lock_upversion >", value, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionGreaterThanOrEqualTo(String value) {
            addCriterion("bike_lock_upversion >=", value, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionLessThan(String value) {
            addCriterion("bike_lock_upversion <", value, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionLessThanOrEqualTo(String value) {
            addCriterion("bike_lock_upversion <=", value, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionLike(String value) {
            addCriterion("bike_lock_upversion like", value, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionNotLike(String value) {
            addCriterion("bike_lock_upversion not like", value, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionIn(List<String> values) {
            addCriterion("bike_lock_upversion in", values, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionNotIn(List<String> values) {
            addCriterion("bike_lock_upversion not in", values, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionBetween(String value1, String value2) {
            addCriterion("bike_lock_upversion between", value1, value2, "bikeLockUpversion");
            return (Criteria) this;
        }

        public Criteria andBikeLockUpversionNotBetween(String value1, String value2) {
            addCriterion("bike_lock_upversion not between", value1, value2, "bikeLockUpversion");
            return (Criteria) this;
        }
        
        public Criteria andBikeLockIsFenceIsNull() {
            addCriterion("bike_lock_is_fence is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceIsNotNull() {
            addCriterion("bike_lock_is_fence is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceEqualTo(Integer value) {
            addCriterion("bike_lock_is_fence =", value, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceNotEqualTo(Integer value) {
            addCriterion("bike_lock_is_fence <>", value, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceGreaterThan(Integer value) {
            addCriterion("bike_lock_is_fence >", value, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_is_fence >=", value, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceLessThan(Integer value) {
            addCriterion("bike_lock_is_fence <", value, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceLessThanOrEqualTo(Integer value) {
            addCriterion("bike_lock_is_fence <=", value, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceIn(List<Integer> values) {
            addCriterion("bike_lock_is_fence in", values, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceNotIn(List<Integer> values) {
            addCriterion("bike_lock_is_fence not in", values, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_is_fence between", value1, value2, "bikeLockIsFence");
            return (Criteria) this;
        }

        public Criteria andBikeLockIsFenceNotBetween(Integer value1, Integer value2) {
            addCriterion("bike_lock_is_fence not between", value1, value2, "bikeLockIsFence");
            return (Criteria) this;
        }
        
        public Criteria andBikeLockSimCodeIsNull() {
            addCriterion("bike_lock_sim_code is null");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeIsNotNull() {
            addCriterion("bike_lock_sim_code is not null");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeEqualTo(String value) {
            addCriterion("bike_lock_sim_code =", value, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeNotEqualTo(String value) {
            addCriterion("bike_lock_sim_code <>", value, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeGreaterThan(String value) {
            addCriterion("bike_lock_sim_code >", value, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bike_lock_sim_code >=", value, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeLessThan(String value) {
            addCriterion("bike_lock_sim_code <", value, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeLessThanOrEqualTo(String value) {
            addCriterion("bike_lock_sim_code <=", value, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeLike(String value) {
            addCriterion("bike_lock_sim_code like", value, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeNotLike(String value) {
            addCriterion("bike_lock_sim_code not like", value, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeIn(List<String> values) {
            addCriterion("bike_lock_sim_code in", values, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeNotIn(List<String> values) {
            addCriterion("bike_lock_sim_code not in", values, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeBetween(String value1, String value2) {
            addCriterion("bike_lock_sim_code between", value1, value2, "bikeLockSimCode");
            return (Criteria) this;
        }

        public Criteria andBikeLockSimCodeNotBetween(String value1, String value2) {
            addCriterion("bike_lock_sim_code not between", value1, value2, "bikeLockSimCode");
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