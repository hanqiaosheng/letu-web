package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BikeFixInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public BikeFixInfoExample() {
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
         * 车辆设备号
         * @return
         */
        public Criteria andBikeCodeLike(String value) {
            addCriterion("bike_code like", value, "bikeCode");
            return (Criteria) this;
        }
        
        /**
         * 渠道号
         * @param value
         * @return
         */
        public Criteria andChannelIdEqualTo(Long value) {
            addCriterion("models_channel_id =", value, "modelsChannelId");
            return (Criteria) this;
        }
        
        public Criteria andChannelIdIn(List<Long> values) {
            addCriterion("models_channel_id in", values, "modelsChannelId");
            return (Criteria) this;
        }

        public Criteria andFixIdIsNull() {
            addCriterion("fix_id is null");
            return (Criteria) this;
        }

        public Criteria andFixIdIsNotNull() {
            addCriterion("fix_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixIdEqualTo(Long value) {
            addCriterion("fix_id =", value, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdNotEqualTo(Long value) {
            addCriterion("fix_id <>", value, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdGreaterThan(Long value) {
            addCriterion("fix_id >", value, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fix_id >=", value, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdLessThan(Long value) {
            addCriterion("fix_id <", value, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdLessThanOrEqualTo(Long value) {
            addCriterion("fix_id <=", value, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdIn(List<Long> values) {
            addCriterion("fix_id in", values, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdNotIn(List<Long> values) {
            addCriterion("fix_id not in", values, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdBetween(Long value1, Long value2) {
            addCriterion("fix_id between", value1, value2, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixIdNotBetween(Long value1, Long value2) {
            addCriterion("fix_id not between", value1, value2, "fixId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdIsNull() {
            addCriterion("fix_bike_id is null");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdIsNotNull() {
            addCriterion("fix_bike_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdEqualTo(Long value) {
            addCriterion("fix_bike_id =", value, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdNotEqualTo(Long value) {
            addCriterion("fix_bike_id <>", value, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdGreaterThan(Long value) {
            addCriterion("fix_bike_id >", value, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fix_bike_id >=", value, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdLessThan(Long value) {
            addCriterion("fix_bike_id <", value, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdLessThanOrEqualTo(Long value) {
            addCriterion("fix_bike_id <=", value, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdIn(List<Long> values) {
            addCriterion("fix_bike_id in", values, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdNotIn(List<Long> values) {
            addCriterion("fix_bike_id not in", values, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdBetween(Long value1, Long value2) {
            addCriterion("fix_bike_id between", value1, value2, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixBikeIdNotBetween(Long value1, Long value2) {
            addCriterion("fix_bike_id not between", value1, value2, "fixBikeId");
            return (Criteria) this;
        }

        public Criteria andFixManIsNull() {
            addCriterion("fix_man is null");
            return (Criteria) this;
        }

        public Criteria andFixManIsNotNull() {
            addCriterion("fix_man is not null");
            return (Criteria) this;
        }

        public Criteria andFixManEqualTo(String value) {
            addCriterion("fix_man =", value, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManNotEqualTo(String value) {
            addCriterion("fix_man <>", value, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManGreaterThan(String value) {
            addCriterion("fix_man >", value, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManGreaterThanOrEqualTo(String value) {
            addCriterion("fix_man >=", value, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManLessThan(String value) {
            addCriterion("fix_man <", value, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManLessThanOrEqualTo(String value) {
            addCriterion("fix_man <=", value, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManLike(String value) {
            addCriterion("fix_man like", value, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManNotLike(String value) {
            addCriterion("fix_man not like", value, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManIn(List<String> values) {
            addCriterion("fix_man in", values, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManNotIn(List<String> values) {
            addCriterion("fix_man not in", values, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManBetween(String value1, String value2) {
            addCriterion("fix_man between", value1, value2, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixManNotBetween(String value1, String value2) {
            addCriterion("fix_man not between", value1, value2, "fixMan");
            return (Criteria) this;
        }

        public Criteria andFixTelIsNull() {
            addCriterion("fix_tel is null");
            return (Criteria) this;
        }

        public Criteria andFixTelIsNotNull() {
            addCriterion("fix_tel is not null");
            return (Criteria) this;
        }

        public Criteria andFixTelEqualTo(String value) {
            addCriterion("fix_tel =", value, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelNotEqualTo(String value) {
            addCriterion("fix_tel <>", value, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelGreaterThan(String value) {
            addCriterion("fix_tel >", value, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelGreaterThanOrEqualTo(String value) {
            addCriterion("fix_tel >=", value, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelLessThan(String value) {
            addCriterion("fix_tel <", value, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelLessThanOrEqualTo(String value) {
            addCriterion("fix_tel <=", value, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelLike(String value) {
            addCriterion("fix_tel like", value, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelNotLike(String value) {
            addCriterion("fix_tel not like", value, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelIn(List<String> values) {
            addCriterion("fix_tel in", values, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelNotIn(List<String> values) {
            addCriterion("fix_tel not in", values, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelBetween(String value1, String value2) {
            addCriterion("fix_tel between", value1, value2, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixTelNotBetween(String value1, String value2) {
            addCriterion("fix_tel not between", value1, value2, "fixTel");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeIsNull() {
            addCriterion("fix_starttime is null");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeIsNotNull() {
            addCriterion("fix_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeEqualTo(Date value) {
            addCriterion("fix_starttime =", value, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeNotEqualTo(Date value) {
            addCriterion("fix_starttime <>", value, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeGreaterThan(Date value) {
            addCriterion("fix_starttime >", value, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fix_starttime >=", value, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeLessThan(Date value) {
            addCriterion("fix_starttime <", value, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("fix_starttime <=", value, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeIn(List<Date> values) {
            addCriterion("fix_starttime in", values, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeNotIn(List<Date> values) {
            addCriterion("fix_starttime not in", values, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeBetween(Date value1, Date value2) {
            addCriterion("fix_starttime between", value1, value2, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("fix_starttime not between", value1, value2, "fixStarttime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeIsNull() {
            addCriterion("fix_endtime is null");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeIsNotNull() {
            addCriterion("fix_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeEqualTo(Date value) {
            addCriterion("fix_endtime =", value, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeNotEqualTo(Date value) {
            addCriterion("fix_endtime <>", value, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeGreaterThan(Date value) {
            addCriterion("fix_endtime >", value, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fix_endtime >=", value, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeLessThan(Date value) {
            addCriterion("fix_endtime <", value, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("fix_endtime <=", value, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeIn(List<Date> values) {
            addCriterion("fix_endtime in", values, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeNotIn(List<Date> values) {
            addCriterion("fix_endtime not in", values, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeBetween(Date value1, Date value2) {
            addCriterion("fix_endtime between", value1, value2, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("fix_endtime not between", value1, value2, "fixEndtime");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeIsNull() {
            addCriterion("fix_longitude is null");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeIsNotNull() {
            addCriterion("fix_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeEqualTo(Double value) {
            addCriterion("fix_longitude =", value, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeNotEqualTo(Double value) {
            addCriterion("fix_longitude <>", value, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeGreaterThan(Double value) {
            addCriterion("fix_longitude >", value, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("fix_longitude >=", value, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeLessThan(Double value) {
            addCriterion("fix_longitude <", value, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("fix_longitude <=", value, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeIn(List<Double> values) {
            addCriterion("fix_longitude in", values, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeNotIn(List<Double> values) {
            addCriterion("fix_longitude not in", values, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeBetween(Double value1, Double value2) {
            addCriterion("fix_longitude between", value1, value2, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("fix_longitude not between", value1, value2, "fixLongitude");
            return (Criteria) this;
        }

        public Criteria andFixRemarkIsNull() {
            addCriterion("fix_remark is null");
            return (Criteria) this;
        }

        public Criteria andFixRemarkIsNotNull() {
            addCriterion("fix_remark is not null");
            return (Criteria) this;
        }

        public Criteria andFixRemarkEqualTo(String value) {
            addCriterion("fix_remark =", value, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkNotEqualTo(String value) {
            addCriterion("fix_remark <>", value, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkGreaterThan(String value) {
            addCriterion("fix_remark >", value, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("fix_remark >=", value, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkLessThan(String value) {
            addCriterion("fix_remark <", value, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkLessThanOrEqualTo(String value) {
            addCriterion("fix_remark <=", value, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkLike(String value) {
            addCriterion("fix_remark like", value, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkNotLike(String value) {
            addCriterion("fix_remark not like", value, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkIn(List<String> values) {
            addCriterion("fix_remark in", values, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkNotIn(List<String> values) {
            addCriterion("fix_remark not in", values, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkBetween(String value1, String value2) {
            addCriterion("fix_remark between", value1, value2, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixRemarkNotBetween(String value1, String value2) {
            addCriterion("fix_remark not between", value1, value2, "fixRemark");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberIsNull() {
            addCriterion("fix_batch_number is null");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberIsNotNull() {
            addCriterion("fix_batch_number is not null");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberEqualTo(String value) {
            addCriterion("fix_batch_number =", value, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberNotEqualTo(String value) {
            addCriterion("fix_batch_number <>", value, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberGreaterThan(String value) {
            addCriterion("fix_batch_number >", value, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberGreaterThanOrEqualTo(String value) {
            addCriterion("fix_batch_number >=", value, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberLessThan(String value) {
            addCriterion("fix_batch_number <", value, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberLessThanOrEqualTo(String value) {
            addCriterion("fix_batch_number <=", value, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberLike(String value) {
            addCriterion("fix_batch_number like", value, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberNotLike(String value) {
            addCriterion("fix_batch_number not like", value, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberIn(List<String> values) {
            addCriterion("fix_batch_number in", values, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberNotIn(List<String> values) {
            addCriterion("fix_batch_number not in", values, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberBetween(String value1, String value2) {
            addCriterion("fix_batch_number between", value1, value2, "fixBatchNumber");
            return (Criteria) this;
        }

        public Criteria andFixBatchNumberNotBetween(String value1, String value2) {
            addCriterion("fix_batch_number not between", value1, value2, "fixBatchNumber");
            return (Criteria) this;
        }
        
        public Criteria andFixAtitudeIsNull() {
            addCriterion("fix_atitude is null");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeIsNotNull() {
            addCriterion("fix_atitude is not null");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeEqualTo(Double value) {
            addCriterion("fix_atitude =", value, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeNotEqualTo(Double value) {
            addCriterion("fix_atitude <>", value, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeGreaterThan(Double value) {
            addCriterion("fix_atitude >", value, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("fix_atitude >=", value, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeLessThan(Double value) {
            addCriterion("fix_atitude <", value, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeLessThanOrEqualTo(Double value) {
            addCriterion("fix_atitude <=", value, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeIn(List<Double> values) {
            addCriterion("fix_atitude in", values, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeNotIn(List<Double> values) {
            addCriterion("fix_atitude not in", values, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeBetween(Double value1, Double value2) {
            addCriterion("fix_atitude between", value1, value2, "fixAtitude");
            return (Criteria) this;
        }

        public Criteria andFixAtitudeNotBetween(Double value1, Double value2) {
            addCriterion("fix_atitude not between", value1, value2, "fixAtitude");
            return (Criteria) this;
        }
        public Criteria andFixDelIsNull() {
            addCriterion("fix_del is null");
            return (Criteria) this;
        }

        public Criteria andFixDelIsNotNull() {
            addCriterion("fix_del is not null");
            return (Criteria) this;
        }

        public Criteria andFixDelEqualTo(Integer value) {
            addCriterion("fix_del =", value, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelNotEqualTo(Integer value) {
            addCriterion("fix_del <>", value, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelGreaterThan(Integer value) {
            addCriterion("fix_del >", value, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("fix_del >=", value, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelLessThan(Integer value) {
            addCriterion("fix_del <", value, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelLessThanOrEqualTo(Integer value) {
            addCriterion("fix_del <=", value, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelIn(List<Integer> values) {
            addCriterion("fix_del in", values, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelNotIn(List<Integer> values) {
            addCriterion("fix_del not in", values, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelBetween(Integer value1, Integer value2) {
            addCriterion("fix_del between", value1, value2, "fixDel");
            return (Criteria) this;
        }

        public Criteria andFixDelNotBetween(Integer value1, Integer value2) {
            addCriterion("fix_del not between", value1, value2, "fixDel");
            return (Criteria) this;
        }
        public Criteria andFixBlockIdIsNull() {
            addCriterion("fix_block_id is null");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdIsNotNull() {
            addCriterion("fix_block_id is not null");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdEqualTo(Long value) {
            addCriterion("fix_block_id =", value, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdNotEqualTo(Long value) {
            addCriterion("fix_block_id <>", value, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdGreaterThan(Long value) {
            addCriterion("fix_block_id >", value, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fix_block_id >=", value, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdLessThan(Long value) {
            addCriterion("fix_block_id <", value, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdLessThanOrEqualTo(Long value) {
            addCriterion("fix_block_id <=", value, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdIn(List<Long> values) {
            addCriterion("fix_block_id in", values, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdNotIn(List<Long> values) {
            addCriterion("fix_block_id not in", values, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdBetween(Long value1, Long value2) {
            addCriterion("fix_block_id between", value1, value2, "fixBlockId");
            return (Criteria) this;
        }

        public Criteria andFixBlockIdNotBetween(Long value1, Long value2) {
            addCriterion("fix_block_id not between", value1, value2, "fixBlockId");
            return (Criteria) this;
        }
        
        public Criteria andFixBlockIsNull() {
            addCriterion("fix_block is null");
            return (Criteria) this;
        }

        public Criteria andFixBlockIsNotNull() {
            addCriterion("fix_block is not null");
            return (Criteria) this;
        }

        public Criteria andFixBlockEqualTo(String value) {
            addCriterion("fix_block =", value, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockNotEqualTo(String value) {
            addCriterion("fix_block <>", value, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockGreaterThan(String value) {
            addCriterion("fix_block >", value, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockGreaterThanOrEqualTo(String value) {
            addCriterion("fix_block >=", value, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockLessThan(String value) {
            addCriterion("fix_block <", value, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockLessThanOrEqualTo(String value) {
            addCriterion("fix_block <=", value, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockLike(String value) {
            addCriterion("fix_block like", value, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockNotLike(String value) {
            addCriterion("fix_block not like", value, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockIn(List<String> values) {
            addCriterion("fix_block in", values, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockNotIn(List<String> values) {
            addCriterion("fix_block not in", values, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockBetween(String value1, String value2) {
            addCriterion("fix_block between", value1, value2, "fixBlock");
            return (Criteria) this;
        }

        public Criteria andFixBlockNotBetween(String value1, String value2) {
            addCriterion("fix_block not between", value1, value2, "fixBlock");
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