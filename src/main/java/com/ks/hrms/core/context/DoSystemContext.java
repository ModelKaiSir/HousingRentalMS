package com.ks.hrms.core.context;

import com.ks.hrms.dao.SystemDao;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DoSystemContext implements AppContext{

    static final String DEF_VER = "1.0.1";

    protected SimpleStringProperty ver = new SimpleStringProperty(DEF_VER);
    protected SimpleStringProperty appName = new SimpleStringProperty();
    protected SimpleStringProperty about = new SimpleStringProperty();

    protected SystemDao dao;
    protected SystemDao.SystemInfomation infomation;

    public DoSystemContext() {

    }

    @Override
    public void init() {
        infomation = dao.getSysInfo();
        appName.set(infomation.getCompanyName());
        ver.set(infomation.getVer());
        about.set(infomation.getAbout());
    }

    @Autowired
    public void setDao(SystemDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean checkVer(String old) {
        return old.compareTo(ver.get()) > 0;
    }

    @Override
    public void updateSystemInfomation(SystemDao.SystemInfomation infomation) {
        dao.update(infomation);
    }

    @Override
    public SimpleStringProperty getVer() {
        return ver;
    }

    @Override
    public SimpleStringProperty about() {
        return about;
    }

    @Override
    public SimpleStringProperty getAppName() {
        return appName;
    }
}
