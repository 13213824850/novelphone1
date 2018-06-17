package com.novel.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NovelClockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NovelClockExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andChapterurlIsNull() {
            addCriterion("chapterurl is null");
            return (Criteria) this;
        }

        public Criteria andChapterurlIsNotNull() {
            addCriterion("chapterurl is not null");
            return (Criteria) this;
        }

        public Criteria andChapterurlEqualTo(String value) {
            addCriterion("chapterurl =", value, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlNotEqualTo(String value) {
            addCriterion("chapterurl <>", value, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlGreaterThan(String value) {
            addCriterion("chapterurl >", value, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlGreaterThanOrEqualTo(String value) {
            addCriterion("chapterurl >=", value, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlLessThan(String value) {
            addCriterion("chapterurl <", value, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlLessThanOrEqualTo(String value) {
            addCriterion("chapterurl <=", value, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlLike(String value) {
            addCriterion("chapterurl like", value, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlNotLike(String value) {
            addCriterion("chapterurl not like", value, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlIn(List<String> values) {
            addCriterion("chapterurl in", values, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlNotIn(List<String> values) {
            addCriterion("chapterurl not in", values, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlBetween(String value1, String value2) {
            addCriterion("chapterurl between", value1, value2, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andChapterurlNotBetween(String value1, String value2) {
            addCriterion("chapterurl not between", value1, value2, "chapterurl");
            return (Criteria) this;
        }

        public Criteria andClockstateIsNull() {
            addCriterion("clockstate is null");
            return (Criteria) this;
        }

        public Criteria andClockstateIsNotNull() {
            addCriterion("clockstate is not null");
            return (Criteria) this;
        }

        public Criteria andClockstateEqualTo(Integer value) {
            addCriterion("clockstate =", value, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateNotEqualTo(Integer value) {
            addCriterion("clockstate <>", value, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateGreaterThan(Integer value) {
            addCriterion("clockstate >", value, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("clockstate >=", value, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateLessThan(Integer value) {
            addCriterion("clockstate <", value, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateLessThanOrEqualTo(Integer value) {
            addCriterion("clockstate <=", value, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateIn(List<Integer> values) {
            addCriterion("clockstate in", values, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateNotIn(List<Integer> values) {
            addCriterion("clockstate not in", values, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateBetween(Integer value1, Integer value2) {
            addCriterion("clockstate between", value1, value2, "clockstate");
            return (Criteria) this;
        }

        public Criteria andClockstateNotBetween(Integer value1, Integer value2) {
            addCriterion("clockstate not between", value1, value2, "clockstate");
            return (Criteria) this;
        }

        public Criteria andTitleurlIsNull() {
            addCriterion("titleurl is null");
            return (Criteria) this;
        }

        public Criteria andTitleurlIsNotNull() {
            addCriterion("titleurl is not null");
            return (Criteria) this;
        }

        public Criteria andTitleurlEqualTo(String value) {
            addCriterion("titleurl =", value, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlNotEqualTo(String value) {
            addCriterion("titleurl <>", value, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlGreaterThan(String value) {
            addCriterion("titleurl >", value, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlGreaterThanOrEqualTo(String value) {
            addCriterion("titleurl >=", value, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlLessThan(String value) {
            addCriterion("titleurl <", value, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlLessThanOrEqualTo(String value) {
            addCriterion("titleurl <=", value, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlLike(String value) {
            addCriterion("titleurl like", value, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlNotLike(String value) {
            addCriterion("titleurl not like", value, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlIn(List<String> values) {
            addCriterion("titleurl in", values, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlNotIn(List<String> values) {
            addCriterion("titleurl not in", values, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlBetween(String value1, String value2) {
            addCriterion("titleurl between", value1, value2, "titleurl");
            return (Criteria) this;
        }

        public Criteria andTitleurlNotBetween(String value1, String value2) {
            addCriterion("titleurl not between", value1, value2, "titleurl");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
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