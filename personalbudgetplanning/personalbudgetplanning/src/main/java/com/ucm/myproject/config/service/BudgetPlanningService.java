package com.ucm.myproject.config.service;

import com.ucm.myproject.config.domain.ExpenditureDetails;
import com.ucm.myproject.config.domain.UserDetails;

public interface BudgetPlanningService {

    ExpenditureDetails planUserBudget(UserDetails details);
}
