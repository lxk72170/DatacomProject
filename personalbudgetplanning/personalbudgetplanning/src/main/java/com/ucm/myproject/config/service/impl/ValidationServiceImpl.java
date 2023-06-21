package com.ucm.myproject.config.service.impl;

import com.ucm.myproject.config.domain.UserDetails;
import com.ucm.myproject.config.service.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    public static Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Override
    public boolean validateUserDetails(UserDetails details) {
        if(details != null){

            if(validateUserName(details.getUserName()) &&
                    validateUserName(details.getUserName()) &&
                    validateUserAge(details.getAge()) &&
                    validateUserAnnualSalary(details.getAnnualSalary())){
               logger.info("User details are available for budget planning. Proceeding to plan budget.");
               return true;
            }
            return false;
        }

        return false;
    }

    private boolean validateUserAnnualSalary(long annualSalary) {
        if(annualSalary > 0 && annualSalary <= Long.MAX_VALUE){
            return true;
        }
        return false;
    }

    private boolean validateUserAge(int age) {
        if(age > 0 && age < 130){
            return true;
        }
        return false;
    }

    private boolean validateUserName(String name) {
        if(StringUtils.isNotEmpty(name)){
                return true;
        }
        return false;
    }
}
