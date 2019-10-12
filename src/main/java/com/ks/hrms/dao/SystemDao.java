package com.ks.hrms.dao;

import com.ks.hrms.core.component.FormField;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SystemDao extends BaseDao {

    static final String TABLE_NAME = "SYSTEM_INFO";
    static final String LOAD_SYS_INFO = "SELECT COMPANYNAME,VER,ABOUT FROM "+TABLE_NAME;
    static final String SAVE_SYS_INFO = "UPDATE "+TABLE_NAME+" SET COMPANYNAME = ?,VER = ?,ABOUT = ? WHERE ID = ?";

    public SystemInfomation getSysInfo(){
        final SystemInfomation info = new SystemInfomation();
        this.getTemplate().query(LOAD_SYS_INFO,resultSet -> {
                 info.setCompanyName(resultSet.getString(1));
                 info.setVer(resultSet.getString(2));
                 info.setAbout(resultSet.getString(3));
        });
        return info;
    }

    public void update(SystemInfomation sysInfo){
        this.getTemplate().update(SAVE_SYS_INFO,sysInfo.getCompanyName(),sysInfo.getVer(),sysInfo.getAbout(),"SYSTEM");
    }

    public ArrayList<FormField.FormFieldAttribute> getItemList(String query){
        final ArrayList<FormField.FormFieldAttribute> result = new ArrayList<>();
        this.getTemplate().query(query,resultSet -> {
            result.add(new FormField.FormFieldAttribute(resultSet.getString(1),resultSet.getString(2)));
        });

        return result;
    }

    public static class SystemInfomation {

        private String companyName;
        private String ver;
        private String about;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }
    }
}
