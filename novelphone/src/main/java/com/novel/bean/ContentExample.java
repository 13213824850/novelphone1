package com.novel.bean;

import java.util.ArrayList;
import java.util.List;

public class ContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ContentExample() {
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

        public Criteria andNovelidIsNull() {
            addCriterion("novelid is null");
            return (Criteria) this;
        }

        public Criteria andNovelidIsNotNull() {
            addCriterion("novelid is not null");
            return (Criteria) this;
        }

        public Criteria andNovelidEqualTo(Integer value) {
            addCriterion("novelid =", value, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidNotEqualTo(Integer value) {
            addCriterion("novelid <>", value, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidGreaterThan(Integer value) {
            addCriterion("novelid >", value, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidGreaterThanOrEqualTo(Integer value) {
            addCriterion("novelid >=", value, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidLessThan(Integer value) {
            addCriterion("novelid <", value, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidLessThanOrEqualTo(Integer value) {
            addCriterion("novelid <=", value, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidIn(List<Integer> values) {
            addCriterion("novelid in", values, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidNotIn(List<Integer> values) {
            addCriterion("novelid not in", values, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidBetween(Integer value1, Integer value2) {
            addCriterion("novelid between", value1, value2, "novelid");
            return (Criteria) this;
        }

        public Criteria andNovelidNotBetween(Integer value1, Integer value2) {
            addCriterion("novelid not between", value1, value2, "novelid");
            return (Criteria) this;
        }

        public Criteria andChaptertextIsNull() {
            addCriterion("chaptertext is null");
            return (Criteria) this;
        }

        public Criteria andChaptertextIsNotNull() {
            addCriterion("chaptertext is not null");
            return (Criteria) this;
        }

        public Criteria andChaptertextEqualTo(String value) {
            addCriterion("chaptertext =", value, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextNotEqualTo(String value) {
            addCriterion("chaptertext <>", value, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextGreaterThan(String value) {
            addCriterion("chaptertext >", value, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextGreaterThanOrEqualTo(String value) {
            addCriterion("chaptertext >=", value, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextLessThan(String value) {
            addCriterion("chaptertext <", value, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextLessThanOrEqualTo(String value) {
            addCriterion("chaptertext <=", value, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextLike(String value) {
            addCriterion("chaptertext like", value, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextNotLike(String value) {
            addCriterion("chaptertext not like", value, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextIn(List<String> values) {
            addCriterion("chaptertext in", values, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextNotIn(List<String> values) {
            addCriterion("chaptertext not in", values, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextBetween(String value1, String value2) {
            addCriterion("chaptertext between", value1, value2, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andChaptertextNotBetween(String value1, String value2) {
            addCriterion("chaptertext not between", value1, value2, "chaptertext");
            return (Criteria) this;
        }

        public Criteria andPreidIsNull() {
            addCriterion("preid is null");
            return (Criteria) this;
        }

        public Criteria andPreidIsNotNull() {
            addCriterion("preid is not null");
            return (Criteria) this;
        }

        public Criteria andPreidEqualTo(Integer value) {
            addCriterion("preid =", value, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidNotEqualTo(Integer value) {
            addCriterion("preid <>", value, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidGreaterThan(Integer value) {
            addCriterion("preid >", value, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidGreaterThanOrEqualTo(Integer value) {
            addCriterion("preid >=", value, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidLessThan(Integer value) {
            addCriterion("preid <", value, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidLessThanOrEqualTo(Integer value) {
            addCriterion("preid <=", value, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidIn(List<Integer> values) {
            addCriterion("preid in", values, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidNotIn(List<Integer> values) {
            addCriterion("preid not in", values, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidBetween(Integer value1, Integer value2) {
            addCriterion("preid between", value1, value2, "preid");
            return (Criteria) this;
        }

        public Criteria andPreidNotBetween(Integer value1, Integer value2) {
            addCriterion("preid not between", value1, value2, "preid");
            return (Criteria) this;
        }

        public Criteria andNextidIsNull() {
            addCriterion("nextid is null");
            return (Criteria) this;
        }

        public Criteria andNextidIsNotNull() {
            addCriterion("nextid is not null");
            return (Criteria) this;
        }

        public Criteria andNextidEqualTo(Integer value) {
            addCriterion("nextid =", value, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidNotEqualTo(Integer value) {
            addCriterion("nextid <>", value, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidGreaterThan(Integer value) {
            addCriterion("nextid >", value, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidGreaterThanOrEqualTo(Integer value) {
            addCriterion("nextid >=", value, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidLessThan(Integer value) {
            addCriterion("nextid <", value, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidLessThanOrEqualTo(Integer value) {
            addCriterion("nextid <=", value, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidIn(List<Integer> values) {
            addCriterion("nextid in", values, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidNotIn(List<Integer> values) {
            addCriterion("nextid not in", values, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidBetween(Integer value1, Integer value2) {
            addCriterion("nextid between", value1, value2, "nextid");
            return (Criteria) this;
        }

        public Criteria andNextidNotBetween(Integer value1, Integer value2) {
            addCriterion("nextid not between", value1, value2, "nextid");
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