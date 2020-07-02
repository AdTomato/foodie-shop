package com.imooc.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemsExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNull() {
            addCriterion("cat_id is null");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNotNull() {
            addCriterion("cat_id is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdEqualTo(Integer value) {
            addCriterion("cat_id =", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotEqualTo(Integer value) {
            addCriterion("cat_id <>", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThan(Integer value) {
            addCriterion("cat_id >", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cat_id >=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThan(Integer value) {
            addCriterion("cat_id <", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("cat_id <=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdIn(List<Integer> values) {
            addCriterion("cat_id in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotIn(List<Integer> values) {
            addCriterion("cat_id not in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdBetween(Integer value1, Integer value2) {
            addCriterion("cat_id between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cat_id not between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdIsNull() {
            addCriterion("root_cat_id is null");
            return (Criteria) this;
        }

        public Criteria andRootCatIdIsNotNull() {
            addCriterion("root_cat_id is not null");
            return (Criteria) this;
        }

        public Criteria andRootCatIdEqualTo(Integer value) {
            addCriterion("root_cat_id =", value, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdNotEqualTo(Integer value) {
            addCriterion("root_cat_id <>", value, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdGreaterThan(Integer value) {
            addCriterion("root_cat_id >", value, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("root_cat_id >=", value, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdLessThan(Integer value) {
            addCriterion("root_cat_id <", value, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("root_cat_id <=", value, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdIn(List<Integer> values) {
            addCriterion("root_cat_id in", values, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdNotIn(List<Integer> values) {
            addCriterion("root_cat_id not in", values, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdBetween(Integer value1, Integer value2) {
            addCriterion("root_cat_id between", value1, value2, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andRootCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("root_cat_id not between", value1, value2, "rootCatId");
            return (Criteria) this;
        }

        public Criteria andSellCountsIsNull() {
            addCriterion("sell_counts is null");
            return (Criteria) this;
        }

        public Criteria andSellCountsIsNotNull() {
            addCriterion("sell_counts is not null");
            return (Criteria) this;
        }

        public Criteria andSellCountsEqualTo(Integer value) {
            addCriterion("sell_counts =", value, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsNotEqualTo(Integer value) {
            addCriterion("sell_counts <>", value, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsGreaterThan(Integer value) {
            addCriterion("sell_counts >", value, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sell_counts >=", value, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsLessThan(Integer value) {
            addCriterion("sell_counts <", value, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsLessThanOrEqualTo(Integer value) {
            addCriterion("sell_counts <=", value, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsIn(List<Integer> values) {
            addCriterion("sell_counts in", values, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsNotIn(List<Integer> values) {
            addCriterion("sell_counts not in", values, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsBetween(Integer value1, Integer value2) {
            addCriterion("sell_counts between", value1, value2, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andSellCountsNotBetween(Integer value1, Integer value2) {
            addCriterion("sell_counts not between", value1, value2, "sellCounts");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusIsNull() {
            addCriterion("on_off_status is null");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusIsNotNull() {
            addCriterion("on_off_status is not null");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusEqualTo(Integer value) {
            addCriterion("on_off_status =", value, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusNotEqualTo(Integer value) {
            addCriterion("on_off_status <>", value, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusGreaterThan(Integer value) {
            addCriterion("on_off_status >", value, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("on_off_status >=", value, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusLessThan(Integer value) {
            addCriterion("on_off_status <", value, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusLessThanOrEqualTo(Integer value) {
            addCriterion("on_off_status <=", value, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusIn(List<Integer> values) {
            addCriterion("on_off_status in", values, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusNotIn(List<Integer> values) {
            addCriterion("on_off_status not in", values, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusBetween(Integer value1, Integer value2) {
            addCriterion("on_off_status between", value1, value2, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andOnOffStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("on_off_status not between", value1, value2, "onOffStatus");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
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