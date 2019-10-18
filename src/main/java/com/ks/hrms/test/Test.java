package com.ks.hrms.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Test {

    private SimpleStringProperty userName = new SimpleStringProperty();
    private SimpleStringProperty sex = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleStringProperty userGroup = new SimpleStringProperty();

    public Test(SimpleStringProperty userName, SimpleStringProperty sex, SimpleStringProperty phone, SimpleStringProperty userGroup) {
        this.userName = userName;
        this.sex = sex;
        this.phone = phone;
        this.userGroup = userGroup;
    }

    public Test(String userName, String sex, String phone, String userGroup) {
        this.userName.set(userName);
        this.sex.set(sex);
        this.phone.set(phone);
        this.userGroup.set(userGroup);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getUserGroup() {
        return userGroup.get();
    }

    public SimpleStringProperty userGroupProperty() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup.set(userGroup);
    }
}
