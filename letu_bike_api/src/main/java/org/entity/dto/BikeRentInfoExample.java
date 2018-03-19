package org.entity.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class BikeRentInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public BikeRentInfoExample() {
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
         *  telphoe
         * @return
         */
        public Criteria andRentTelLike(String value) {
            addCriterion("user_tel like", value, "userTel");
            return (Criteria) this;
        }
        
        public Criteria andUserNameLike(String value) {
            addCriterion("user_realname like", value, "userRealname");
            return (Criteria) this;
        }
        
        /**
         *  bikeCode
         * @return
         */
        public Criteria andBikeCodeLike(String value) {
            addCriterion("bike_code like", value, "bikeCode");
            return (Criteria) this;
        }
        
        public Criteria andRentInfoIdIsNull() {
            addCriterion("rent_info_id is null");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdIsNotNull() {
            addCriterion("rent_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdEqualTo(Long value) {
            addCriterion("rent_info_id =", value, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdNotEqualTo(Long value) {
            addCriterion("rent_info_id <>", value, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdGreaterThan(Long value) {
            addCriterion("rent_info_id >", value, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_info_id >=", value, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdLessThan(Long value) {
            addCriterion("rent_info_id <", value, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_info_id <=", value, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdIn(List<Long> values) {
            addCriterion("rent_info_id in", values, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdNotIn(List<Long> values) {
            addCriterion("rent_info_id not in", values, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdBetween(Long value1, Long value2) {
            addCriterion("rent_info_id between", value1, value2, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_info_id not between", value1, value2, "rentInfoId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdIsNull() {
            addCriterion("rent_info_bike_id is null");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdIsNotNull() {
            addCriterion("rent_info_bike_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdEqualTo(Long value) {
            addCriterion("rent_info_bike_id =", value, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdNotEqualTo(Long value) {
            addCriterion("rent_info_bike_id <>", value, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdGreaterThan(Long value) {
            addCriterion("rent_info_bike_id >", value, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_info_bike_id >=", value, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdLessThan(Long value) {
            addCriterion("rent_info_bike_id <", value, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_info_bike_id <=", value, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdIn(List<Long> values) {
            addCriterion("rent_info_bike_id in", values, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdNotIn(List<Long> values) {
            addCriterion("rent_info_bike_id not in", values, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdBetween(Long value1, Long value2) {
            addCriterion("rent_info_bike_id between", value1, value2, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoBikeIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_info_bike_id not between", value1, value2, "rentInfoBikeId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdIsNull() {
            addCriterion("rent_info_user_id is null");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdIsNotNull() {
            addCriterion("rent_info_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdEqualTo(Long value) {
            addCriterion("rent_info_user_id =", value, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdNotEqualTo(Long value) {
            addCriterion("rent_info_user_id <>", value, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdGreaterThan(Long value) {
            addCriterion("rent_info_user_id >", value, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_info_user_id >=", value, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdLessThan(Long value) {
            addCriterion("rent_info_user_id <", value, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_info_user_id <=", value, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdIn(List<Long> values) {
            addCriterion("rent_info_user_id in", values, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdNotIn(List<Long> values) {
            addCriterion("rent_info_user_id not in", values, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdBetween(Long value1, Long value2) {
            addCriterion("rent_info_user_id between", value1, value2, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentInfoUserIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_info_user_id not between", value1, value2, "rentInfoUserId");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeIsNull() {
            addCriterion("rent_starttime is null");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeIsNotNull() {
            addCriterion("rent_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeEqualTo(Date value) {
            addCriterion("rent_starttime =", value, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeNotEqualTo(Date value) {
            addCriterion("rent_starttime <>", value, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeGreaterThan(Date value) {
            addCriterion("rent_starttime >", value, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rent_starttime >=", value, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeLessThan(Date value) {
            addCriterion("rent_starttime <", value, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("rent_starttime <=", value, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeIn(List<Date> values) {
            addCriterion("rent_starttime in", values, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeNotIn(List<Date> values) {
            addCriterion("rent_starttime not in", values, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeBetween(Date value1, Date value2) {
            addCriterion("rent_starttime between", value1, value2, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("rent_starttime not between", value1, value2, "rentStarttime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeIsNull() {
            addCriterion("rent_endtime is null");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeIsNotNull() {
            addCriterion("rent_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeEqualTo(Date value) {
            addCriterion("rent_endtime =", value, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeNotEqualTo(Date value) {
            addCriterion("rent_endtime <>", value, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeGreaterThan(Date value) {
            addCriterion("rent_endtime >", value, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rent_endtime >=", value, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeLessThan(Date value) {
            addCriterion("rent_endtime <", value, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("rent_endtime <=", value, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeIn(List<Date> values) {
            addCriterion("rent_endtime in", values, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeNotIn(List<Date> values) {
            addCriterion("rent_endtime not in", values, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeBetween(Date value1, Date value2) {
            addCriterion("rent_endtime between", value1, value2, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("rent_endtime not between", value1, value2, "rentEndtime");
            return (Criteria) this;
        }

        public Criteria andRentStartlatIsNull() {
            addCriterion("rent_startlat is null");
            return (Criteria) this;
        }

        public Criteria andRentStartlatIsNotNull() {
            addCriterion("rent_startlat is not null");
            return (Criteria) this;
        }

        public Criteria andRentStartlatEqualTo(Double value) {
            addCriterion("rent_startlat =", value, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatNotEqualTo(Double value) {
            addCriterion("rent_startlat <>", value, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatGreaterThan(Double value) {
            addCriterion("rent_startlat >", value, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatGreaterThanOrEqualTo(Double value) {
            addCriterion("rent_startlat >=", value, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatLessThan(Double value) {
            addCriterion("rent_startlat <", value, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatLessThanOrEqualTo(Double value) {
            addCriterion("rent_startlat <=", value, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatIn(List<Double> values) {
            addCriterion("rent_startlat in", values, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatNotIn(List<Double> values) {
            addCriterion("rent_startlat not in", values, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatBetween(Double value1, Double value2) {
            addCriterion("rent_startlat between", value1, value2, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlatNotBetween(Double value1, Double value2) {
            addCriterion("rent_startlat not between", value1, value2, "rentStartlat");
            return (Criteria) this;
        }

        public Criteria andRentStartlngIsNull() {
            addCriterion("rent_startlng is null");
            return (Criteria) this;
        }

        public Criteria andRentStartlngIsNotNull() {
            addCriterion("rent_startlng is not null");
            return (Criteria) this;
        }

        public Criteria andRentStartlngEqualTo(Double value) {
            addCriterion("rent_startlng =", value, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngNotEqualTo(Double value) {
            addCriterion("rent_startlng <>", value, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngGreaterThan(Double value) {
            addCriterion("rent_startlng >", value, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngGreaterThanOrEqualTo(Double value) {
            addCriterion("rent_startlng >=", value, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngLessThan(Double value) {
            addCriterion("rent_startlng <", value, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngLessThanOrEqualTo(Double value) {
            addCriterion("rent_startlng <=", value, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngIn(List<Double> values) {
            addCriterion("rent_startlng in", values, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngNotIn(List<Double> values) {
            addCriterion("rent_startlng not in", values, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngBetween(Double value1, Double value2) {
            addCriterion("rent_startlng between", value1, value2, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentStartlngNotBetween(Double value1, Double value2) {
            addCriterion("rent_startlng not between", value1, value2, "rentStartlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlatIsNull() {
            addCriterion("rent_endlat is null");
            return (Criteria) this;
        }

        public Criteria andRentEndlatIsNotNull() {
            addCriterion("rent_endlat is not null");
            return (Criteria) this;
        }

        public Criteria andRentEndlatEqualTo(Double value) {
            addCriterion("rent_endlat =", value, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatNotEqualTo(Double value) {
            addCriterion("rent_endlat <>", value, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatGreaterThan(Double value) {
            addCriterion("rent_endlat >", value, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatGreaterThanOrEqualTo(Double value) {
            addCriterion("rent_endlat >=", value, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatLessThan(Double value) {
            addCriterion("rent_endlat <", value, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatLessThanOrEqualTo(Double value) {
            addCriterion("rent_endlat <=", value, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatIn(List<Double> values) {
            addCriterion("rent_endlat in", values, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatNotIn(List<Double> values) {
            addCriterion("rent_endlat not in", values, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatBetween(Double value1, Double value2) {
            addCriterion("rent_endlat between", value1, value2, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlatNotBetween(Double value1, Double value2) {
            addCriterion("rent_endlat not between", value1, value2, "rentEndlat");
            return (Criteria) this;
        }

        public Criteria andRentEndlngIsNull() {
            addCriterion("rent_endlng is null");
            return (Criteria) this;
        }

        public Criteria andRentEndlngIsNotNull() {
            addCriterion("rent_endlng is not null");
            return (Criteria) this;
        }

        public Criteria andRentEndlngEqualTo(Double value) {
            addCriterion("rent_endlng =", value, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngNotEqualTo(Double value) {
            addCriterion("rent_endlng <>", value, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngGreaterThan(Double value) {
            addCriterion("rent_endlng >", value, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngGreaterThanOrEqualTo(Double value) {
            addCriterion("rent_endlng >=", value, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngLessThan(Double value) {
            addCriterion("rent_endlng <", value, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngLessThanOrEqualTo(Double value) {
            addCriterion("rent_endlng <=", value, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngIn(List<Double> values) {
            addCriterion("rent_endlng in", values, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngNotIn(List<Double> values) {
            addCriterion("rent_endlng not in", values, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngBetween(Double value1, Double value2) {
            addCriterion("rent_endlng between", value1, value2, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentEndlngNotBetween(Double value1, Double value2) {
            addCriterion("rent_endlng not between", value1, value2, "rentEndlng");
            return (Criteria) this;
        }

        public Criteria andRentPriceIsNull() {
            addCriterion("rent_price is null");
            return (Criteria) this;
        }

        public Criteria andRentPriceIsNotNull() {
            addCriterion("rent_price is not null");
            return (Criteria) this;
        }

        public Criteria andRentPriceEqualTo(BigDecimal value) {
            addCriterion("rent_price =", value, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceNotEqualTo(BigDecimal value) {
            addCriterion("rent_price <>", value, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceGreaterThan(BigDecimal value) {
            addCriterion("rent_price >", value, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rent_price >=", value, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceLessThan(BigDecimal value) {
            addCriterion("rent_price <", value, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rent_price <=", value, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceIn(List<BigDecimal> values) {
            addCriterion("rent_price in", values, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceNotIn(List<BigDecimal> values) {
            addCriterion("rent_price not in", values, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rent_price between", value1, value2, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rent_price not between", value1, value2, "rentPrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceIsNull() {
            addCriterion("rent_insurance_price is null");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceIsNotNull() {
            addCriterion("rent_insurance_price is not null");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceEqualTo(BigDecimal value) {
            addCriterion("rent_insurance_price =", value, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceNotEqualTo(BigDecimal value) {
            addCriterion("rent_insurance_price <>", value, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceGreaterThan(BigDecimal value) {
            addCriterion("rent_insurance_price >", value, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rent_insurance_price >=", value, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceLessThan(BigDecimal value) {
            addCriterion("rent_insurance_price <", value, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rent_insurance_price <=", value, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceIn(List<BigDecimal> values) {
            addCriterion("rent_insurance_price in", values, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceNotIn(List<BigDecimal> values) {
            addCriterion("rent_insurance_price not in", values, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rent_insurance_price between", value1, value2, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentInsurancePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rent_insurance_price not between", value1, value2, "rentInsurancePrice");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeIsNull() {
            addCriterion("rent_pay_time is null");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeIsNotNull() {
            addCriterion("rent_pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeEqualTo(Date value) {
            addCriterion("rent_pay_time =", value, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeNotEqualTo(Date value) {
            addCriterion("rent_pay_time <>", value, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeGreaterThan(Date value) {
            addCriterion("rent_pay_time >", value, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rent_pay_time >=", value, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeLessThan(Date value) {
            addCriterion("rent_pay_time <", value, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("rent_pay_time <=", value, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeIn(List<Date> values) {
            addCriterion("rent_pay_time in", values, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeNotIn(List<Date> values) {
            addCriterion("rent_pay_time not in", values, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeBetween(Date value1, Date value2) {
            addCriterion("rent_pay_time between", value1, value2, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("rent_pay_time not between", value1, value2, "rentPayTime");
            return (Criteria) this;
        }

        public Criteria andRentStateIsNull() {
            addCriterion("rent_state is null");
            return (Criteria) this;
        }

        public Criteria andRentStateIsNotNull() {
            addCriterion("rent_state is not null");
            return (Criteria) this;
        }

        public Criteria andRentStateEqualTo(Integer value) {
            addCriterion("rent_state =", value, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateNotEqualTo(Integer value) {
            addCriterion("rent_state <>", value, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateGreaterThan(Integer value) {
            addCriterion("rent_state >", value, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_state >=", value, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateLessThan(Integer value) {
            addCriterion("rent_state <", value, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateLessThanOrEqualTo(Integer value) {
            addCriterion("rent_state <=", value, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateIn(List<Integer> values) {
            addCriterion("rent_state in", values, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateNotIn(List<Integer> values) {
            addCriterion("rent_state not in", values, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateBetween(Integer value1, Integer value2) {
            addCriterion("rent_state between", value1, value2, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentStateNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_state not between", value1, value2, "rentState");
            return (Criteria) this;
        }

        public Criteria andRentDistanceIsNull() {
            addCriterion("rent_distance is null");
            return (Criteria) this;
        }

        public Criteria andRentDistanceIsNotNull() {
            addCriterion("rent_distance is not null");
            return (Criteria) this;
        }

        public Criteria andRentDistanceEqualTo(Integer value) {
            addCriterion("rent_distance =", value, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceNotEqualTo(Integer value) {
            addCriterion("rent_distance <>", value, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceGreaterThan(Integer value) {
            addCriterion("rent_distance >", value, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_distance >=", value, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceLessThan(Integer value) {
            addCriterion("rent_distance <", value, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceLessThanOrEqualTo(Integer value) {
            addCriterion("rent_distance <=", value, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceIn(List<Integer> values) {
            addCriterion("rent_distance in", values, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceNotIn(List<Integer> values) {
            addCriterion("rent_distance not in", values, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceBetween(Integer value1, Integer value2) {
            addCriterion("rent_distance between", value1, value2, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentDistanceNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_distance not between", value1, value2, "rentDistance");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeIsNull() {
            addCriterion("rent_pay_type is null");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeIsNotNull() {
            addCriterion("rent_pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeEqualTo(Integer value) {
            addCriterion("rent_pay_type =", value, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeNotEqualTo(Integer value) {
            addCriterion("rent_pay_type <>", value, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeGreaterThan(Integer value) {
            addCriterion("rent_pay_type >", value, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_pay_type >=", value, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeLessThan(Integer value) {
            addCriterion("rent_pay_type <", value, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("rent_pay_type <=", value, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeIn(List<Integer> values) {
            addCriterion("rent_pay_type in", values, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeNotIn(List<Integer> values) {
            addCriterion("rent_pay_type not in", values, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("rent_pay_type between", value1, value2, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_pay_type not between", value1, value2, "rentPayType");
            return (Criteria) this;
        }

        public Criteria andRentIsdelIsNull() {
            addCriterion("rent_isdel is null");
            return (Criteria) this;
        }

        public Criteria andRentIsdelIsNotNull() {
            addCriterion("rent_isdel is not null");
            return (Criteria) this;
        }

        public Criteria andRentIsdelEqualTo(Integer value) {
            addCriterion("rent_isdel =", value, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelNotEqualTo(Integer value) {
            addCriterion("rent_isdel <>", value, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelGreaterThan(Integer value) {
            addCriterion("rent_isdel >", value, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_isdel >=", value, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelLessThan(Integer value) {
            addCriterion("rent_isdel <", value, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelLessThanOrEqualTo(Integer value) {
            addCriterion("rent_isdel <=", value, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelIn(List<Integer> values) {
            addCriterion("rent_isdel in", values, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelNotIn(List<Integer> values) {
            addCriterion("rent_isdel not in", values, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelBetween(Integer value1, Integer value2) {
            addCriterion("rent_isdel between", value1, value2, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentIsdelNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_isdel not between", value1, value2, "rentIsdel");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdIsNull() {
            addCriterion("rent_start_block_id is null");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdIsNotNull() {
            addCriterion("rent_start_block_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdEqualTo(Long value) {
            addCriterion("rent_start_block_id =", value, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdNotEqualTo(Long value) {
            addCriterion("rent_start_block_id <>", value, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdGreaterThan(Long value) {
            addCriterion("rent_start_block_id >", value, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_start_block_id >=", value, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdLessThan(Long value) {
            addCriterion("rent_start_block_id <", value, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_start_block_id <=", value, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdIn(List<Long> values) {
            addCriterion("rent_start_block_id in", values, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdNotIn(List<Long> values) {
            addCriterion("rent_start_block_id not in", values, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdBetween(Long value1, Long value2) {
            addCriterion("rent_start_block_id between", value1, value2, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_start_block_id not between", value1, value2, "rentStartBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdIsNull() {
            addCriterion("rent_end_block_id is null");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdIsNotNull() {
            addCriterion("rent_end_block_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdEqualTo(Long value) {
            addCriterion("rent_end_block_id =", value, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdNotEqualTo(Long value) {
            addCriterion("rent_end_block_id <>", value, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdGreaterThan(Long value) {
            addCriterion("rent_end_block_id >", value, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_end_block_id >=", value, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdLessThan(Long value) {
            addCriterion("rent_end_block_id <", value, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_end_block_id <=", value, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdIn(List<Long> values) {
            addCriterion("rent_end_block_id in", values, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdNotIn(List<Long> values) {
            addCriterion("rent_end_block_id not in", values, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdBetween(Long value1, Long value2) {
            addCriterion("rent_end_block_id between", value1, value2, "rentEndBlockId");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_end_block_id not between", value1, value2, "rentEndBlockId");
            return (Criteria) this;
        }
        public Criteria andRentIsvillagerIsNull() {
            addCriterion("rent_isvillager is null");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerIsNotNull() {
            addCriterion("rent_isvillager is not null");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerEqualTo(Integer value) {
            addCriterion("rent_isvillager =", value, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerNotEqualTo(Integer value) {
            addCriterion("rent_isvillager <>", value, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerGreaterThan(Integer value) {
            addCriterion("rent_isvillager >", value, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_isvillager >=", value, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerLessThan(Integer value) {
            addCriterion("rent_isvillager <", value, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerLessThanOrEqualTo(Integer value) {
            addCriterion("rent_isvillager <=", value, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerIn(List<Integer> values) {
            addCriterion("rent_isvillager in", values, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerNotIn(List<Integer> values) {
            addCriterion("rent_isvillager not in", values, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerBetween(Integer value1, Integer value2) {
            addCriterion("rent_isvillager between", value1, value2, "rentIsvillager");
            return (Criteria) this;
        }

        public Criteria andRentIsvillagerNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_isvillager not between", value1, value2, "rentIsvillager");
            return (Criteria) this;
        }
        
        public Criteria andRentTemporarylocktimeIsNull() {
            addCriterion("rent_temporarylocktime is null");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeIsNotNull() {
            addCriterion("rent_temporarylocktime is not null");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeEqualTo(Date value) {
            addCriterion("rent_temporarylocktime =", value, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeNotEqualTo(Date value) {
            addCriterion("rent_temporarylocktime <>", value, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeGreaterThan(Date value) {
            addCriterion("rent_temporarylocktime >", value, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rent_temporarylocktime >=", value, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeLessThan(Date value) {
            addCriterion("rent_temporarylocktime <", value, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeLessThanOrEqualTo(Date value) {
            addCriterion("rent_temporarylocktime <=", value, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeIn(List<Date> values) {
            addCriterion("rent_temporarylocktime in", values, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeNotIn(List<Date> values) {
            addCriterion("rent_temporarylocktime not in", values, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeBetween(Date value1, Date value2) {
            addCriterion("rent_temporarylocktime between", value1, value2, "rentTemporarylocktime");
            return (Criteria) this;
        }

        public Criteria andRentTemporarylocktimeNotBetween(Date value1, Date value2) {
            addCriterion("rent_temporarylocktime not between", value1, value2, "rentTemporarylocktime");
            return (Criteria) this;
        }
        
        public Criteria andRentInvoiceIdIsNull() {
            addCriterion("rent_invoice_id is null");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdIsNotNull() {
            addCriterion("rent_invoice_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdEqualTo(Long value) {
            addCriterion("rent_invoice_id =", value, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdNotEqualTo(Long value) {
            addCriterion("rent_invoice_id <>", value, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdGreaterThan(Long value) {
            addCriterion("rent_invoice_id >", value, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_invoice_id >=", value, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdLessThan(Long value) {
            addCriterion("rent_invoice_id <", value, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_invoice_id <=", value, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdIn(List<Long> values) {
            addCriterion("rent_invoice_id in", values, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdNotIn(List<Long> values) {
            addCriterion("rent_invoice_id not in", values, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdBetween(Long value1, Long value2) {
            addCriterion("rent_invoice_id between", value1, value2, "rentInvoiceId");
            return (Criteria) this;
        }

        public Criteria andRentInvoiceIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_invoice_id not between", value1, value2, "rentInvoiceId");
            return (Criteria) this;
        }
        
        public Criteria andRentLongtimeIsNull() {
            addCriterion("rent_longtime is null");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeIsNotNull() {
            addCriterion("rent_longtime is not null");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeEqualTo(Integer value) {
            addCriterion("rent_longtime =", value, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeNotEqualTo(Integer value) {
            addCriterion("rent_longtime <>", value, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeGreaterThan(Integer value) {
            addCriterion("rent_longtime >", value, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_longtime >=", value, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeLessThan(Integer value) {
            addCriterion("rent_longtime <", value, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeLessThanOrEqualTo(Integer value) {
            addCriterion("rent_longtime <=", value, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeIn(List<Integer> values) {
            addCriterion("rent_longtime in", values, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeNotIn(List<Integer> values) {
            addCriterion("rent_longtime not in", values, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeBetween(Integer value1, Integer value2) {
            addCriterion("rent_longtime between", value1, value2, "rentLongtime");
            return (Criteria) this;
        }

        public Criteria andRentLongtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_longtime not between", value1, value2, "rentLongtime");
            return (Criteria) this;
        }
        
        public Criteria andRentStartFixedIdIsNull() {
            addCriterion("rent_start_fixed_id is null");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdIsNotNull() {
            addCriterion("rent_start_fixed_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdEqualTo(Long value) {
            addCriterion("rent_start_fixed_id =", value, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdNotEqualTo(Long value) {
            addCriterion("rent_start_fixed_id <>", value, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdGreaterThan(Long value) {
            addCriterion("rent_start_fixed_id >", value, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_start_fixed_id >=", value, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdLessThan(Long value) {
            addCriterion("rent_start_fixed_id <", value, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_start_fixed_id <=", value, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdIn(List<Long> values) {
            addCriterion("rent_start_fixed_id in", values, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdNotIn(List<Long> values) {
            addCriterion("rent_start_fixed_id not in", values, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdBetween(Long value1, Long value2) {
            addCriterion("rent_start_fixed_id between", value1, value2, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentStartFixedIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_start_fixed_id not between", value1, value2, "rentStartFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdIsNull() {
            addCriterion("rent_end_fixed_id is null");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdIsNotNull() {
            addCriterion("rent_end_fixed_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdEqualTo(Long value) {
            addCriterion("rent_end_fixed_id =", value, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdNotEqualTo(Long value) {
            addCriterion("rent_end_fixed_id <>", value, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdGreaterThan(Long value) {
            addCriterion("rent_end_fixed_id >", value, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_end_fixed_id >=", value, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdLessThan(Long value) {
            addCriterion("rent_end_fixed_id <", value, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_end_fixed_id <=", value, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdIn(List<Long> values) {
            addCriterion("rent_end_fixed_id in", values, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdNotIn(List<Long> values) {
            addCriterion("rent_end_fixed_id not in", values, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdBetween(Long value1, Long value2) {
            addCriterion("rent_end_fixed_id between", value1, value2, "rentEndFixedId");
            return (Criteria) this;
        }

        public Criteria andRentEndFixedIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_end_fixed_id not between", value1, value2, "rentEndFixedId");
            return (Criteria) this;
        }
        
        public Criteria andRentBikeChannelIdIsNull() {
            addCriterion("rent_bike_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdIsNotNull() {
            addCriterion("rent_bike_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdEqualTo(Long value) {
            addCriterion("rent_bike_channel_id =", value, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdNotEqualTo(Long value) {
            addCriterion("rent_bike_channel_id <>", value, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdGreaterThan(Long value) {
            addCriterion("rent_bike_channel_id >", value, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_bike_channel_id >=", value, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdLessThan(Long value) {
            addCriterion("rent_bike_channel_id <", value, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_bike_channel_id <=", value, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdIn(List<Long> values) {
            addCriterion("rent_bike_channel_id in", values, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdNotIn(List<Long> values) {
            addCriterion("rent_bike_channel_id not in", values, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdBetween(Long value1, Long value2) {
            addCriterion("rent_bike_channel_id between", value1, value2, "rentBikeChannelId");
            return (Criteria) this;
        }

        public Criteria andRentBikeChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_bike_channel_id not between", value1, value2, "rentBikeChannelId");
            return (Criteria) this;
        }
        
        public Criteria andRentStartBlockIsNull() {
            addCriterion("rent_start_block is null");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIsNotNull() {
            addCriterion("rent_start_block is not null");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockEqualTo(String value) {
            addCriterion("rent_start_block =", value, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockNotEqualTo(String value) {
            addCriterion("rent_start_block <>", value, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockGreaterThan(String value) {
            addCriterion("rent_start_block >", value, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockGreaterThanOrEqualTo(String value) {
            addCriterion("rent_start_block >=", value, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockLessThan(String value) {
            addCriterion("rent_start_block <", value, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockLessThanOrEqualTo(String value) {
            addCriterion("rent_start_block <=", value, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockLike(String value) {
            addCriterion("rent_start_block like", value, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockNotLike(String value) {
            addCriterion("rent_start_block not like", value, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockIn(List<String> values) {
            addCriterion("rent_start_block in", values, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockNotIn(List<String> values) {
            addCriterion("rent_start_block not in", values, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockBetween(String value1, String value2) {
            addCriterion("rent_start_block between", value1, value2, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentStartBlockNotBetween(String value1, String value2) {
            addCriterion("rent_start_block not between", value1, value2, "rentStartBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIsNull() {
            addCriterion("rent_end_block is null");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIsNotNull() {
            addCriterion("rent_end_block is not null");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockEqualTo(String value) {
            addCriterion("rent_end_block =", value, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockNotEqualTo(String value) {
            addCriterion("rent_end_block <>", value, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockGreaterThan(String value) {
            addCriterion("rent_end_block >", value, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockGreaterThanOrEqualTo(String value) {
            addCriterion("rent_end_block >=", value, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockLessThan(String value) {
            addCriterion("rent_end_block <", value, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockLessThanOrEqualTo(String value) {
            addCriterion("rent_end_block <=", value, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockLike(String value) {
            addCriterion("rent_end_block like", value, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockNotLike(String value) {
            addCriterion("rent_end_block not like", value, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockIn(List<String> values) {
            addCriterion("rent_end_block in", values, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockNotIn(List<String> values) {
            addCriterion("rent_end_block not in", values, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockBetween(String value1, String value2) {
            addCriterion("rent_end_block between", value1, value2, "rentEndBlock");
            return (Criteria) this;
        }

        public Criteria andRentEndBlockNotBetween(String value1, String value2) {
            addCriterion("rent_end_block not between", value1, value2, "rentEndBlock");
            return (Criteria) this;
        }
        
        public Criteria andRentCouponMoneyIsNull() {
            addCriterion("rent_coupon_money is null");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyIsNotNull() {
            addCriterion("rent_coupon_money is not null");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyEqualTo(BigDecimal value) {
            addCriterion("rent_coupon_money =", value, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyNotEqualTo(BigDecimal value) {
            addCriterion("rent_coupon_money <>", value, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyGreaterThan(BigDecimal value) {
            addCriterion("rent_coupon_money >", value, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rent_coupon_money >=", value, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyLessThan(BigDecimal value) {
            addCriterion("rent_coupon_money <", value, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rent_coupon_money <=", value, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyIn(List<BigDecimal> values) {
            addCriterion("rent_coupon_money in", values, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyNotIn(List<BigDecimal> values) {
            addCriterion("rent_coupon_money not in", values, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rent_coupon_money between", value1, value2, "rentCouponMoney");
            return (Criteria) this;
        }

        public Criteria andRentCouponMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rent_coupon_money not between", value1, value2, "rentCouponMoney");
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