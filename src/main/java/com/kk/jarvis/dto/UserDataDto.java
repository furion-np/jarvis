package com.kk.jarvis.dto;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: kkedari
 * Date: 5/13/14
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDataDto {

    private String name;

    private int userId;

    private String value;

    private String category;

    private String subCategory;

    private Date addTime;

    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String toString() {
        return "UserData:{" +
                "userId:" + userId+
                ",name:" +name+ ",value:" +  value + ",category:"+ category+
                ",subcategory:" + subCategory + ",unit:" + unit + ",addtime:" + addTime + "}";
    }

}
