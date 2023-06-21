package com.ucm.myproject.config;

import com.ucm.myproject.config.repository.impl.BudgetDetailsDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(Main.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        try{
            jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS USER_BUDGET");
            jdbcTemplate.execute("USE USER_BUDGET");

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS BudgetDetails (" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT," +
                    "UserName VARCHAR(255) NOT NULL," +
                    "Age INT NOT NULL," +
                    "AnnualSalary INT NOT NULL" +
                    ")");
        }catch (Exception e){
            logger.info("Error occurred while creating database. Create Datable and required table manually");
            System.exit(0);
        }
       logger.info("USER_BUDGET Database and BudgetDetails table created successfully");
    }
}
