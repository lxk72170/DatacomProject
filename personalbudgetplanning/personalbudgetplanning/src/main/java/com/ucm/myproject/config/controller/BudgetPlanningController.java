package com.ucm.myproject.config.controller;

import com.ucm.myproject.config.domain.ExpenditureDetails;
import com.ucm.myproject.config.domain.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ucm.myproject.config.service.impl.BudgetPlanningServiceImpl;
import com.ucm.myproject.config.service.impl.ValidationServiceImpl;

@RestController
@RequestMapping("/budget")
public class BudgetPlanningController {

    public static Logger logger = LoggerFactory.getLogger(BudgetPlanningController.class);

    @Autowired
    private ValidationServiceImpl validationService;

    @Autowired
    private BudgetPlanningServiceImpl budgetPlanningService;

    @PostMapping("/planning")
    public ResponseEntity<String> budgetPlanning(UserDetails details) {
        boolean isValidationSuccess = validationService.validateUserDetails(details);
        if(isValidationSuccess){
            ExpenditureDetails planningDetails = budgetPlanningService.planUserBudget(details);
            return new ResponseEntity<>("Rent="+planningDetails.getRent() +
                    " Investment="+planningDetails.getInvestment() +
                    " Emi="+planningDetails.getEmi() +
                    " Savings="+planningDetails.getSavings() +
                    " Spending="+planningDetails.getMiscellaneous() +
                    " Food="+planningDetails.getFood() +
                    " DailySpending="+planningDetails.getDailyExpenditure(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User details incomplete. Cannot plan budget", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/calculateBudget")
    public ModelAndView loadReservationForm(Model model) {
        ModelAndView modelAndView = new ModelAndView("BudgetPlanning.html");
        return modelAndView;
    }
}
