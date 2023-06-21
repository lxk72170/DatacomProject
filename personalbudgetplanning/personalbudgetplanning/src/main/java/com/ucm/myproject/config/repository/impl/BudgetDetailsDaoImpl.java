package com.ucm.myproject.config.repository.impl;

import com.ucm.myproject.config.domain.BudgetDetails;
import com.ucm.myproject.config.domain.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ucm.myproject.config.repository.BudgetDetailsDao;

@Repository
public class BudgetDetailsDaoImpl implements BudgetDetailsDao {

    public static Logger logger = LoggerFactory.getLogger(BudgetDetailsDaoImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int saveBudgetDetails(BudgetDetails details) {
        try{
            String query = "INSERT INTO BudgetDetails(UserName, Age, AnnualSalary) VALUES (?, ?, ?)";
            return jdbcTemplate.update(query, details.getName(), details.getAge(), details.getSalary());
        }catch (Exception e){
            logger.error("Error occurred while saving user details into database.");
            return 1;
        }
    }
}
