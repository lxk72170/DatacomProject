package com.ucm.myproject.config.service.impl;

import com.ucm.myproject.config.domain.BudgetDetails;
import com.ucm.myproject.config.domain.ExpenditureDetails;
import com.ucm.myproject.config.domain.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ucm.myproject.config.repository.impl.BudgetDetailsDaoImpl;
import com.ucm.myproject.config.service.BudgetPlanningService;

import java.text.DecimalFormat;
import java.util.Locale;

@Service
public class BudgetPlanningServiceImpl implements BudgetPlanningService {

    public static Logger logger = LoggerFactory.getLogger(BudgetPlanningServiceImpl.class);

    @Autowired
    private BudgetDetailsDaoImpl budgetDetailsDao;

    @Override
    public ExpenditureDetails planUserBudget(UserDetails details) {
        ExpenditureDetails expenditure = budgetPlanning(details);
        saveUserDetails(details);
        return expenditure;
    }

    private void saveUserDetails(UserDetails details) {
        BudgetDetails detailsToSave = new BudgetDetails();
        detailsToSave.setName(details.getUserName().toUpperCase(Locale.ROOT));
        detailsToSave.setAge(details.getAge());
        detailsToSave.setSalary(details.getAnnualSalary());
        int isDetailsSaved = budgetDetailsDao.saveBudgetDetails(detailsToSave);
        if(isDetailsSaved == 1){
            logger.info("Budget details saved successfully.");
        }else{
            logger.info("Budget details not saved successfully.");

        }
    }

    //(30% rent, 20% Investment, 10% EMI, 10%Savings, 10% spendings,20%food)
    //Daily spendings= (food+Spendings)/30 //
    private ExpenditureDetails budgetPlanning(UserDetails details) {
        int monthlySalary = (int) (details.getAnnualSalary()/12);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double rent = monthlySalary * 0.3;
        double investment = monthlySalary * 0.2;
        double emi = monthlySalary * 0.1;
        double savings = monthlySalary * 0.1;
        double miscellaneous = monthlySalary * 0.1;
        double food = monthlySalary * 0.2;
        double dailyExpenditure = (food + miscellaneous)/30;

        ExpenditureDetails expenditureDetails = new ExpenditureDetails();
        expenditureDetails.setRent(Math.round(rent * 100.0) / 100.0);
        expenditureDetails.setInvestment(Math.round(investment * 100.0) / 100.0);
        expenditureDetails.setEmi(Math.round(emi * 100.0) / 100.0);
        expenditureDetails.setSavings(Math.round(savings * 100.0) / 100.0);
        expenditureDetails.setMiscellaneous(Math.round(miscellaneous * 100.0) / 100.0);
        expenditureDetails.setFood(Math.round(food * 100.0) / 100.0);
        expenditureDetails.setDailyExpenditure(Math.round(dailyExpenditure * 100.0) / 100.0);

        return expenditureDetails;
    }
}
