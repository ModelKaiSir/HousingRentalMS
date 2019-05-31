package com.ks.hrms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 所有Dao类的基类
 * @author QiuKai
 */
@Repository
public class BaseDao {

    AutoGenerateDocNo generateNo = null;

    @Autowired
    protected JdbcTemplate template;

    public BaseDao() {

    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
        generateNo = new GenerateDocNo(template);
    }


}
