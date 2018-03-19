package org.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UserExample() {
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
        
        //qudao
        public Criteria andChannelIdEqualTo(Long value) {
            addCriterion("models_channel_id =", value, "modelsChannelId");
            return (Criteria) this;
        }
        
        public Criteria andChannelIdIn(List<Long> values) {
            addCriterion("models_channel_id in", values, "modelsChannelId");
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

        public Criteria andUserAccountIdIsNull() {
            addCriterion("user_account_id is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdIsNotNull() {
            addCriterion("user_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdEqualTo(Long value) {
            addCriterion("user_account_id =", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdNotEqualTo(Long value) {
            addCriterion("user_account_id <>", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdGreaterThan(Long value) {
            addCriterion("user_account_id >", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_account_id >=", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdLessThan(Long value) {
            addCriterion("user_account_id <", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("user_account_id <=", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdIn(List<Long> values) {
            addCriterion("user_account_id in", values, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdNotIn(List<Long> values) {
            addCriterion("user_account_id not in", values, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdBetween(Long value1, Long value2) {
            addCriterion("user_account_id between", value1, value2, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("user_account_id not between", value1, value2, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIsNull() {
            addCriterion("user_nickname is null");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIsNotNull() {
            addCriterion("user_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andUserNicknameEqualTo(String value) {
            addCriterion("user_nickname =", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotEqualTo(String value) {
            addCriterion("user_nickname <>", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameGreaterThan(String value) {
            addCriterion("user_nickname >", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("user_nickname >=", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLessThan(String value) {
            addCriterion("user_nickname <", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLessThanOrEqualTo(String value) {
            addCriterion("user_nickname <=", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLike(String value) {
            addCriterion("user_nickname like", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotLike(String value) {
            addCriterion("user_nickname not like", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIn(List<String> values) {
            addCriterion("user_nickname in", values, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotIn(List<String> values) {
            addCriterion("user_nickname not in", values, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameBetween(String value1, String value2) {
            addCriterion("user_nickname between", value1, value2, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotBetween(String value1, String value2) {
            addCriterion("user_nickname not between", value1, value2, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserTelIsNull() {
            addCriterion("user_tel is null");
            return (Criteria) this;
        }

        public Criteria andUserTelIsNotNull() {
            addCriterion("user_tel is not null");
            return (Criteria) this;
        }

        public Criteria andUserTelEqualTo(String value) {
            addCriterion("user_tel =", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotEqualTo(String value) {
            addCriterion("user_tel <>", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelGreaterThan(String value) {
            addCriterion("user_tel >", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelGreaterThanOrEqualTo(String value) {
            addCriterion("user_tel >=", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLessThan(String value) {
            addCriterion("user_tel <", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLessThanOrEqualTo(String value) {
            addCriterion("user_tel <=", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLike(String value) {
            addCriterion("user_tel like", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotLike(String value) {
            addCriterion("user_tel not like", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelIn(List<String> values) {
            addCriterion("user_tel in", values, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotIn(List<String> values) {
            addCriterion("user_tel not in", values, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelBetween(String value1, String value2) {
            addCriterion("user_tel between", value1, value2, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotBetween(String value1, String value2) {
            addCriterion("user_tel not between", value1, value2, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserIdcardIsNull() {
            addCriterion("user_idcard is null");
            return (Criteria) this;
        }

        public Criteria andUserIdcardIsNotNull() {
            addCriterion("user_idcard is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdcardEqualTo(String value) {
            addCriterion("user_idcard =", value, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardNotEqualTo(String value) {
            addCriterion("user_idcard <>", value, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardGreaterThan(String value) {
            addCriterion("user_idcard >", value, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("user_idcard >=", value, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardLessThan(String value) {
            addCriterion("user_idcard <", value, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardLessThanOrEqualTo(String value) {
            addCriterion("user_idcard <=", value, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardLike(String value) {
            addCriterion("user_idcard like", value, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardNotLike(String value) {
            addCriterion("user_idcard not like", value, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardIn(List<String> values) {
            addCriterion("user_idcard in", values, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardNotIn(List<String> values) {
            addCriterion("user_idcard not in", values, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardBetween(String value1, String value2) {
            addCriterion("user_idcard between", value1, value2, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserIdcardNotBetween(String value1, String value2) {
            addCriterion("user_idcard not between", value1, value2, "userIdcard");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIsNull() {
            addCriterion("user_realname is null");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIsNotNull() {
            addCriterion("user_realname is not null");
            return (Criteria) this;
        }

        public Criteria andUserRealnameEqualTo(String value) {
            addCriterion("user_realname =", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotEqualTo(String value) {
            addCriterion("user_realname <>", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameGreaterThan(String value) {
            addCriterion("user_realname >", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("user_realname >=", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLessThan(String value) {
            addCriterion("user_realname <", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLessThanOrEqualTo(String value) {
            addCriterion("user_realname <=", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLike(String value) {
            addCriterion("user_realname like", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotLike(String value) {
            addCriterion("user_realname not like", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIn(List<String> values) {
            addCriterion("user_realname in", values, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotIn(List<String> values) {
            addCriterion("user_realname not in", values, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameBetween(String value1, String value2) {
            addCriterion("user_realname between", value1, value2, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotBetween(String value1, String value2) {
            addCriterion("user_realname not between", value1, value2, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageIsNull() {
            addCriterion("user_profile_image is null");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageIsNotNull() {
            addCriterion("user_profile_image is not null");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageEqualTo(String value) {
            addCriterion("user_profile_image =", value, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageNotEqualTo(String value) {
            addCriterion("user_profile_image <>", value, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageGreaterThan(String value) {
            addCriterion("user_profile_image >", value, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageGreaterThanOrEqualTo(String value) {
            addCriterion("user_profile_image >=", value, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageLessThan(String value) {
            addCriterion("user_profile_image <", value, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageLessThanOrEqualTo(String value) {
            addCriterion("user_profile_image <=", value, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageLike(String value) {
            addCriterion("user_profile_image like", value, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageNotLike(String value) {
            addCriterion("user_profile_image not like", value, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageIn(List<String> values) {
            addCriterion("user_profile_image in", values, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageNotIn(List<String> values) {
            addCriterion("user_profile_image not in", values, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageBetween(String value1, String value2) {
            addCriterion("user_profile_image between", value1, value2, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserProfileImageNotBetween(String value1, String value2) {
            addCriterion("user_profile_image not between", value1, value2, "userProfileImage");
            return (Criteria) this;
        }

        public Criteria andUserGenderIsNull() {
            addCriterion("user_gender is null");
            return (Criteria) this;
        }

        public Criteria andUserGenderIsNotNull() {
            addCriterion("user_gender is not null");
            return (Criteria) this;
        }

        public Criteria andUserGenderEqualTo(Integer value) {
            addCriterion("user_gender =", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotEqualTo(Integer value) {
            addCriterion("user_gender <>", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderGreaterThan(Integer value) {
            addCriterion("user_gender >", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_gender >=", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLessThan(Integer value) {
            addCriterion("user_gender <", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLessThanOrEqualTo(Integer value) {
            addCriterion("user_gender <=", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderIn(List<Integer> values) {
            addCriterion("user_gender in", values, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotIn(List<Integer> values) {
            addCriterion("user_gender not in", values, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderBetween(Integer value1, Integer value2) {
            addCriterion("user_gender between", value1, value2, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("user_gender not between", value1, value2, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserOpenidIsNull() {
            addCriterion("user_openId is null");
            return (Criteria) this;
        }

        public Criteria andUserOpenidIsNotNull() {
            addCriterion("user_openId is not null");
            return (Criteria) this;
        }

        public Criteria andUserOpenidEqualTo(String value) {
            addCriterion("user_openId =", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidNotEqualTo(String value) {
            addCriterion("user_openId <>", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidGreaterThan(String value) {
            addCriterion("user_openId >", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("user_openId >=", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidLessThan(String value) {
            addCriterion("user_openId <", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidLessThanOrEqualTo(String value) {
            addCriterion("user_openId <=", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidLike(String value) {
            addCriterion("user_openId like", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidNotLike(String value) {
            addCriterion("user_openId not like", value, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidIn(List<String> values) {
            addCriterion("user_openId in", values, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidNotIn(List<String> values) {
            addCriterion("user_openId not in", values, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidBetween(String value1, String value2) {
            addCriterion("user_openId between", value1, value2, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserOpenidNotBetween(String value1, String value2) {
            addCriterion("user_openId not between", value1, value2, "userOpenid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidIsNull() {
            addCriterion("user_unionId is null");
            return (Criteria) this;
        }

        public Criteria andUserUnionidIsNotNull() {
            addCriterion("user_unionId is not null");
            return (Criteria) this;
        }

        public Criteria andUserUnionidEqualTo(String value) {
            addCriterion("user_unionId =", value, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidNotEqualTo(String value) {
            addCriterion("user_unionId <>", value, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidGreaterThan(String value) {
            addCriterion("user_unionId >", value, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidGreaterThanOrEqualTo(String value) {
            addCriterion("user_unionId >=", value, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidLessThan(String value) {
            addCriterion("user_unionId <", value, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidLessThanOrEqualTo(String value) {
            addCriterion("user_unionId <=", value, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidLike(String value) {
            addCriterion("user_unionId like", value, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidNotLike(String value) {
            addCriterion("user_unionId not like", value, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidIn(List<String> values) {
            addCriterion("user_unionId in", values, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidNotIn(List<String> values) {
            addCriterion("user_unionId not in", values, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidBetween(String value1, String value2) {
            addCriterion("user_unionId between", value1, value2, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserUnionidNotBetween(String value1, String value2) {
            addCriterion("user_unionId not between", value1, value2, "userUnionid");
            return (Criteria) this;
        }

        public Criteria andUserStateIsNull() {
            addCriterion("user_state is null");
            return (Criteria) this;
        }

        public Criteria andUserStateIsNotNull() {
            addCriterion("user_state is not null");
            return (Criteria) this;
        }

        public Criteria andUserStateEqualTo(Integer value) {
            addCriterion("user_state =", value, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateNotEqualTo(Integer value) {
            addCriterion("user_state <>", value, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateGreaterThan(Integer value) {
            addCriterion("user_state >", value, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_state >=", value, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateLessThan(Integer value) {
            addCriterion("user_state <", value, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateLessThanOrEqualTo(Integer value) {
            addCriterion("user_state <=", value, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateIn(List<Integer> values) {
            addCriterion("user_state in", values, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateNotIn(List<Integer> values) {
            addCriterion("user_state not in", values, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateBetween(Integer value1, Integer value2) {
            addCriterion("user_state between", value1, value2, "userState");
            return (Criteria) this;
        }

        public Criteria andUserStateNotBetween(Integer value1, Integer value2) {
            addCriterion("user_state not between", value1, value2, "userState");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeIsNull() {
            addCriterion("user_createtime is null");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeIsNotNull() {
            addCriterion("user_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeEqualTo(Date value) {
            addCriterion("user_createtime =", value, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeNotEqualTo(Date value) {
            addCriterion("user_createtime <>", value, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeGreaterThan(Date value) {
            addCriterion("user_createtime >", value, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_createtime >=", value, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeLessThan(Date value) {
            addCriterion("user_createtime <", value, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("user_createtime <=", value, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeIn(List<Date> values) {
            addCriterion("user_createtime in", values, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeNotIn(List<Date> values) {
            addCriterion("user_createtime not in", values, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeBetween(Date value1, Date value2) {
            addCriterion("user_createtime between", value1, value2, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("user_createtime not between", value1, value2, "userCreatetime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeIsNull() {
            addCriterion("user_logintime is null");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeIsNotNull() {
            addCriterion("user_logintime is not null");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeEqualTo(Date value) {
            addCriterion("user_logintime =", value, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeNotEqualTo(Date value) {
            addCriterion("user_logintime <>", value, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeGreaterThan(Date value) {
            addCriterion("user_logintime >", value, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_logintime >=", value, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeLessThan(Date value) {
            addCriterion("user_logintime <", value, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeLessThanOrEqualTo(Date value) {
            addCriterion("user_logintime <=", value, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeIn(List<Date> values) {
            addCriterion("user_logintime in", values, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeNotIn(List<Date> values) {
            addCriterion("user_logintime not in", values, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeBetween(Date value1, Date value2) {
            addCriterion("user_logintime between", value1, value2, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserLogintimeNotBetween(Date value1, Date value2) {
            addCriterion("user_logintime not between", value1, value2, "userLogintime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeIsNull() {
            addCriterion("user_usetime is null");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeIsNotNull() {
            addCriterion("user_usetime is not null");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeEqualTo(Integer value) {
            addCriterion("user_usetime =", value, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeNotEqualTo(Integer value) {
            addCriterion("user_usetime <>", value, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeGreaterThan(Integer value) {
            addCriterion("user_usetime >", value, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_usetime >=", value, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeLessThan(Integer value) {
            addCriterion("user_usetime <", value, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeLessThanOrEqualTo(Integer value) {
            addCriterion("user_usetime <=", value, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeIn(List<Integer> values) {
            addCriterion("user_usetime in", values, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeNotIn(List<Integer> values) {
            addCriterion("user_usetime not in", values, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeBetween(Integer value1, Integer value2) {
            addCriterion("user_usetime between", value1, value2, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUsetimeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_usetime not between", value1, value2, "userUsetime");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceIsNull() {
            addCriterion("user_use_distance is null");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceIsNotNull() {
            addCriterion("user_use_distance is not null");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceEqualTo(Integer value) {
            addCriterion("user_use_distance =", value, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceNotEqualTo(Integer value) {
            addCriterion("user_use_distance <>", value, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceGreaterThan(Integer value) {
            addCriterion("user_use_distance >", value, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_use_distance >=", value, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceLessThan(Integer value) {
            addCriterion("user_use_distance <", value, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceLessThanOrEqualTo(Integer value) {
            addCriterion("user_use_distance <=", value, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceIn(List<Integer> values) {
            addCriterion("user_use_distance in", values, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceNotIn(List<Integer> values) {
            addCriterion("user_use_distance not in", values, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceBetween(Integer value1, Integer value2) {
            addCriterion("user_use_distance between", value1, value2, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserUseDistanceNotBetween(Integer value1, Integer value2) {
            addCriterion("user_use_distance not between", value1, value2, "userUseDistance");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdIsNull() {
            addCriterion("user_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdIsNotNull() {
            addCriterion("user_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdEqualTo(Long value) {
            addCriterion("user_channel_id =", value, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdNotEqualTo(Long value) {
            addCriterion("user_channel_id <>", value, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdGreaterThan(Long value) {
            addCriterion("user_channel_id >", value, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_channel_id >=", value, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdLessThan(Long value) {
            addCriterion("user_channel_id <", value, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("user_channel_id <=", value, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdIn(List<Long> values) {
            addCriterion("user_channel_id in", values, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdNotIn(List<Long> values) {
            addCriterion("user_channel_id not in", values, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdBetween(Long value1, Long value2) {
            addCriterion("user_channel_id between", value1, value2, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("user_channel_id not between", value1, value2, "userChannelId");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistIsNull() {
            addCriterion("user_isblacklist is null");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistIsNotNull() {
            addCriterion("user_isblacklist is not null");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistEqualTo(Short value) {
            addCriterion("user_isblacklist =", value, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistNotEqualTo(Short value) {
            addCriterion("user_isblacklist <>", value, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistGreaterThan(Short value) {
            addCriterion("user_isblacklist >", value, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistGreaterThanOrEqualTo(Short value) {
            addCriterion("user_isblacklist >=", value, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistLessThan(Short value) {
            addCriterion("user_isblacklist <", value, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistLessThanOrEqualTo(Short value) {
            addCriterion("user_isblacklist <=", value, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistIn(List<Short> values) {
            addCriterion("user_isblacklist in", values, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistNotIn(List<Short> values) {
            addCriterion("user_isblacklist not in", values, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistBetween(Short value1, Short value2) {
            addCriterion("user_isblacklist between", value1, value2, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserIsblacklistNotBetween(Short value1, Short value2) {
            addCriterion("user_isblacklist not between", value1, value2, "userIsblacklist");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeIsNull() {
            addCriterion("user_defriend_time is null");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeIsNotNull() {
            addCriterion("user_defriend_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeEqualTo(Date value) {
            addCriterion("user_defriend_time =", value, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeNotEqualTo(Date value) {
            addCriterion("user_defriend_time <>", value, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeGreaterThan(Date value) {
            addCriterion("user_defriend_time >", value, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_defriend_time >=", value, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeLessThan(Date value) {
            addCriterion("user_defriend_time <", value, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeLessThanOrEqualTo(Date value) {
            addCriterion("user_defriend_time <=", value, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeIn(List<Date> values) {
            addCriterion("user_defriend_time in", values, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeNotIn(List<Date> values) {
            addCriterion("user_defriend_time not in", values, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeBetween(Date value1, Date value2) {
            addCriterion("user_defriend_time between", value1, value2, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserDefriendTimeNotBetween(Date value1, Date value2) {
            addCriterion("user_defriend_time not between", value1, value2, "userDefriendTime");
            return (Criteria) this;
        }

        public Criteria andUserTokenIsNull() {
            addCriterion("user_token is null");
            return (Criteria) this;
        }

        public Criteria andUserTokenIsNotNull() {
            addCriterion("user_token is not null");
            return (Criteria) this;
        }

        public Criteria andUserTokenEqualTo(String value) {
            addCriterion("user_token =", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenNotEqualTo(String value) {
            addCriterion("user_token <>", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenGreaterThan(String value) {
            addCriterion("user_token >", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenGreaterThanOrEqualTo(String value) {
            addCriterion("user_token >=", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenLessThan(String value) {
            addCriterion("user_token <", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenLessThanOrEqualTo(String value) {
            addCriterion("user_token <=", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenLike(String value) {
            addCriterion("user_token like", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenNotLike(String value) {
            addCriterion("user_token not like", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenIn(List<String> values) {
            addCriterion("user_token in", values, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenNotIn(List<String> values) {
            addCriterion("user_token not in", values, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenBetween(String value1, String value2) {
            addCriterion("user_token between", value1, value2, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenNotBetween(String value1, String value2) {
            addCriterion("user_token not between", value1, value2, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidIsNull() {
            addCriterion("user_small_openId is null");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidIsNotNull() {
            addCriterion("user_small_openId is not null");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidEqualTo(String value) {
            addCriterion("user_small_openId =", value, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidNotEqualTo(String value) {
            addCriterion("user_small_openId <>", value, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidGreaterThan(String value) {
            addCriterion("user_small_openId >", value, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("user_small_openId >=", value, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidLessThan(String value) {
            addCriterion("user_small_openId <", value, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidLessThanOrEqualTo(String value) {
            addCriterion("user_small_openId <=", value, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidLike(String value) {
            addCriterion("user_small_openId like", value, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidNotLike(String value) {
            addCriterion("user_small_openId not like", value, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidIn(List<String> values) {
            addCriterion("user_small_openId in", values, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidNotIn(List<String> values) {
            addCriterion("user_small_openId not in", values, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidBetween(String value1, String value2) {
            addCriterion("user_small_openId between", value1, value2, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSmallOpenidNotBetween(String value1, String value2) {
            addCriterion("user_small_openId not between", value1, value2, "userSmallOpenid");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyIsNull() {
            addCriterion("user_session_key is null");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyIsNotNull() {
            addCriterion("user_session_key is not null");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyEqualTo(String value) {
            addCriterion("user_session_key =", value, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyNotEqualTo(String value) {
            addCriterion("user_session_key <>", value, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyGreaterThan(String value) {
            addCriterion("user_session_key >", value, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyGreaterThanOrEqualTo(String value) {
            addCriterion("user_session_key >=", value, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyLessThan(String value) {
            addCriterion("user_session_key <", value, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyLessThanOrEqualTo(String value) {
            addCriterion("user_session_key <=", value, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyLike(String value) {
            addCriterion("user_session_key like", value, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyNotLike(String value) {
            addCriterion("user_session_key not like", value, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyIn(List<String> values) {
            addCriterion("user_session_key in", values, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyNotIn(List<String> values) {
            addCriterion("user_session_key not in", values, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyBetween(String value1, String value2) {
            addCriterion("user_session_key between", value1, value2, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserSessionKeyNotBetween(String value1, String value2) {
            addCriterion("user_session_key not between", value1, value2, "userSessionKey");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateIsNull() {
            addCriterion("user_vipexpirationdate is null");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateIsNotNull() {
            addCriterion("user_vipexpirationdate is not null");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateEqualTo(Date value) {
            addCriterion("user_vipexpirationdate =", value, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateNotEqualTo(Date value) {
            addCriterion("user_vipexpirationdate <>", value, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateGreaterThan(Date value) {
            addCriterion("user_vipexpirationdate >", value, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateGreaterThanOrEqualTo(Date value) {
            addCriterion("user_vipexpirationdate >=", value, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateLessThan(Date value) {
            addCriterion("user_vipexpirationdate <", value, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateLessThanOrEqualTo(Date value) {
            addCriterion("user_vipexpirationdate <=", value, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateIn(List<Date> values) {
            addCriterion("user_vipexpirationdate in", values, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateNotIn(List<Date> values) {
            addCriterion("user_vipexpirationdate not in", values, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateBetween(Date value1, Date value2) {
            addCriterion("user_vipexpirationdate between", value1, value2, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserVipexpirationdateNotBetween(Date value1, Date value2) {
            addCriterion("user_vipexpirationdate not between", value1, value2, "userVipexpirationdate");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeIsNull() {
            addCriterion("user_lastusebiketime is null");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeIsNotNull() {
            addCriterion("user_lastusebiketime is not null");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeEqualTo(Date value) {
            addCriterion("user_lastusebiketime =", value, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeNotEqualTo(Date value) {
            addCriterion("user_lastusebiketime <>", value, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeGreaterThan(Date value) {
            addCriterion("user_lastusebiketime >", value, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_lastusebiketime >=", value, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeLessThan(Date value) {
            addCriterion("user_lastusebiketime <", value, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeLessThanOrEqualTo(Date value) {
            addCriterion("user_lastusebiketime <=", value, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeIn(List<Date> values) {
            addCriterion("user_lastusebiketime in", values, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeNotIn(List<Date> values) {
            addCriterion("user_lastusebiketime not in", values, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeBetween(Date value1, Date value2) {
            addCriterion("user_lastusebiketime between", value1, value2, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserLastusebiketimeNotBetween(Date value1, Date value2) {
            addCriterion("user_lastusebiketime not between", value1, value2, "userLastusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeIsNull() {
            addCriterion("user_fistusebiketime is null");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeIsNotNull() {
            addCriterion("user_fistusebiketime is not null");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeEqualTo(Date value) {
            addCriterion("user_fistusebiketime =", value, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeNotEqualTo(Date value) {
            addCriterion("user_fistusebiketime <>", value, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeGreaterThan(Date value) {
            addCriterion("user_fistusebiketime >", value, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_fistusebiketime >=", value, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeLessThan(Date value) {
            addCriterion("user_fistusebiketime <", value, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeLessThanOrEqualTo(Date value) {
            addCriterion("user_fistusebiketime <=", value, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeIn(List<Date> values) {
            addCriterion("user_fistusebiketime in", values, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeNotIn(List<Date> values) {
            addCriterion("user_fistusebiketime not in", values, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeBetween(Date value1, Date value2) {
            addCriterion("user_fistusebiketime between", value1, value2, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebiketimeNotBetween(Date value1, Date value2) {
            addCriterion("user_fistusebiketime not between", value1, value2, "userFistusebiketime");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidIsNull() {
            addCriterion("user_fistusebikeid is null");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidIsNotNull() {
            addCriterion("user_fistusebikeid is not null");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidEqualTo(Long value) {
            addCriterion("user_fistusebikeid =", value, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidNotEqualTo(Long value) {
            addCriterion("user_fistusebikeid <>", value, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidGreaterThan(Long value) {
            addCriterion("user_fistusebikeid >", value, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidGreaterThanOrEqualTo(Long value) {
            addCriterion("user_fistusebikeid >=", value, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidLessThan(Long value) {
            addCriterion("user_fistusebikeid <", value, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidLessThanOrEqualTo(Long value) {
            addCriterion("user_fistusebikeid <=", value, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidIn(List<Long> values) {
            addCriterion("user_fistusebikeid in", values, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidNotIn(List<Long> values) {
            addCriterion("user_fistusebikeid not in", values, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidBetween(Long value1, Long value2) {
            addCriterion("user_fistusebikeid between", value1, value2, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserFistusebikeidNotBetween(Long value1, Long value2) {
            addCriterion("user_fistusebikeid not between", value1, value2, "userFistusebikeid");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNull() {
            addCriterion("user_birthday is null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNotNull() {
            addCriterion("user_birthday is not null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayEqualTo(Date value) {
            addCriterion("user_birthday =", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotEqualTo(Date value) {
            addCriterion("user_birthday <>", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThan(Date value) {
            addCriterion("user_birthday >", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("user_birthday >=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThan(Date value) {
            addCriterion("user_birthday <", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("user_birthday <=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIn(List<Date> values) {
            addCriterion("user_birthday in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotIn(List<Date> values) {
            addCriterion("user_birthday not in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayBetween(Date value1, Date value2) {
            addCriterion("user_birthday between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("user_birthday not between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieIsNull() {
            addCriterion("user_total_calorie is null");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieIsNotNull() {
            addCriterion("user_total_calorie is not null");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieEqualTo(Double value) {
            addCriterion("user_total_calorie =", value, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieNotEqualTo(Double value) {
            addCriterion("user_total_calorie <>", value, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieGreaterThan(Double value) {
            addCriterion("user_total_calorie >", value, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieGreaterThanOrEqualTo(Double value) {
            addCriterion("user_total_calorie >=", value, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieLessThan(Double value) {
            addCriterion("user_total_calorie <", value, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieLessThanOrEqualTo(Double value) {
            addCriterion("user_total_calorie <=", value, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieIn(List<Double> values) {
            addCriterion("user_total_calorie in", values, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieNotIn(List<Double> values) {
            addCriterion("user_total_calorie not in", values, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieBetween(Double value1, Double value2) {
            addCriterion("user_total_calorie between", value1, value2, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalCalorieNotBetween(Double value1, Double value2) {
            addCriterion("user_total_calorie not between", value1, value2, "userTotalCalorie");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceIsNull() {
            addCriterion("user_total_distance is null");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceIsNotNull() {
            addCriterion("user_total_distance is not null");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceEqualTo(Double value) {
            addCriterion("user_total_distance =", value, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceNotEqualTo(Double value) {
            addCriterion("user_total_distance <>", value, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceGreaterThan(Double value) {
            addCriterion("user_total_distance >", value, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceGreaterThanOrEqualTo(Double value) {
            addCriterion("user_total_distance >=", value, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceLessThan(Double value) {
            addCriterion("user_total_distance <", value, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceLessThanOrEqualTo(Double value) {
            addCriterion("user_total_distance <=", value, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceIn(List<Double> values) {
            addCriterion("user_total_distance in", values, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceNotIn(List<Double> values) {
            addCriterion("user_total_distance not in", values, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceBetween(Double value1, Double value2) {
            addCriterion("user_total_distance between", value1, value2, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalDistanceNotBetween(Double value1, Double value2) {
            addCriterion("user_total_distance not between", value1, value2, "userTotalDistance");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeIsNull() {
            addCriterion("user_total_ride_time is null");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeIsNotNull() {
            addCriterion("user_total_ride_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeEqualTo(Double value) {
            addCriterion("user_total_ride_time =", value, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeNotEqualTo(Double value) {
            addCriterion("user_total_ride_time <>", value, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeGreaterThan(Double value) {
            addCriterion("user_total_ride_time >", value, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeGreaterThanOrEqualTo(Double value) {
            addCriterion("user_total_ride_time >=", value, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeLessThan(Double value) {
            addCriterion("user_total_ride_time <", value, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeLessThanOrEqualTo(Double value) {
            addCriterion("user_total_ride_time <=", value, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeIn(List<Double> values) {
            addCriterion("user_total_ride_time in", values, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeNotIn(List<Double> values) {
            addCriterion("user_total_ride_time not in", values, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeBetween(Double value1, Double value2) {
            addCriterion("user_total_ride_time between", value1, value2, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserTotalRideTimeNotBetween(Double value1, Double value2) {
            addCriterion("user_total_ride_time not between", value1, value2, "userTotalRideTime");
            return (Criteria) this;
        }

        public Criteria andUserLevelIsNull() {
            addCriterion("user_level is null");
            return (Criteria) this;
        }

        public Criteria andUserLevelIsNotNull() {
            addCriterion("user_level is not null");
            return (Criteria) this;
        }

        public Criteria andUserLevelEqualTo(Integer value) {
            addCriterion("user_level =", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotEqualTo(Integer value) {
            addCriterion("user_level <>", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelGreaterThan(Integer value) {
            addCriterion("user_level >", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_level >=", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelLessThan(Integer value) {
            addCriterion("user_level <", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelLessThanOrEqualTo(Integer value) {
            addCriterion("user_level <=", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelIn(List<Integer> values) {
            addCriterion("user_level in", values, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotIn(List<Integer> values) {
            addCriterion("user_level not in", values, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelBetween(Integer value1, Integer value2) {
            addCriterion("user_level between", value1, value2, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("user_level not between", value1, value2, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserGradeIsNull() {
            addCriterion("user_grade is null");
            return (Criteria) this;
        }

        public Criteria andUserGradeIsNotNull() {
            addCriterion("user_grade is not null");
            return (Criteria) this;
        }

        public Criteria andUserGradeEqualTo(Integer value) {
            addCriterion("user_grade =", value, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeNotEqualTo(Integer value) {
            addCriterion("user_grade <>", value, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeGreaterThan(Integer value) {
            addCriterion("user_grade >", value, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_grade >=", value, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeLessThan(Integer value) {
            addCriterion("user_grade <", value, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeLessThanOrEqualTo(Integer value) {
            addCriterion("user_grade <=", value, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeIn(List<Integer> values) {
            addCriterion("user_grade in", values, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeNotIn(List<Integer> values) {
            addCriterion("user_grade not in", values, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeBetween(Integer value1, Integer value2) {
            addCriterion("user_grade between", value1, value2, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_grade not between", value1, value2, "userGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeIsNull() {
            addCriterion("user_total_grade is null");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeIsNotNull() {
            addCriterion("user_total_grade is not null");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeEqualTo(Integer value) {
            addCriterion("user_total_grade =", value, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeNotEqualTo(Integer value) {
            addCriterion("user_total_grade <>", value, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeGreaterThan(Integer value) {
            addCriterion("user_total_grade >", value, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_total_grade >=", value, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeLessThan(Integer value) {
            addCriterion("user_total_grade <", value, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeLessThanOrEqualTo(Integer value) {
            addCriterion("user_total_grade <=", value, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeIn(List<Integer> values) {
            addCriterion("user_total_grade in", values, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeNotIn(List<Integer> values) {
            addCriterion("user_total_grade not in", values, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeBetween(Integer value1, Integer value2) {
            addCriterion("user_total_grade between", value1, value2, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserTotalGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_total_grade not between", value1, value2, "userTotalGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeIsNull() {
            addCriterion("user_use_grade is null");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeIsNotNull() {
            addCriterion("user_use_grade is not null");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeEqualTo(Integer value) {
            addCriterion("user_use_grade =", value, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeNotEqualTo(Integer value) {
            addCriterion("user_use_grade <>", value, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeGreaterThan(Integer value) {
            addCriterion("user_use_grade >", value, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_use_grade >=", value, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeLessThan(Integer value) {
            addCriterion("user_use_grade <", value, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeLessThanOrEqualTo(Integer value) {
            addCriterion("user_use_grade <=", value, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeIn(List<Integer> values) {
            addCriterion("user_use_grade in", values, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeNotIn(List<Integer> values) {
            addCriterion("user_use_grade not in", values, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeBetween(Integer value1, Integer value2) {
            addCriterion("user_use_grade between", value1, value2, "userUseGrade");
            return (Criteria) this;
        }

        public Criteria andUserUseGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_use_grade not between", value1, value2, "userUseGrade");
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