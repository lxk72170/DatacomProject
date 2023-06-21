package com.ucm.myproject.config.repository;

import com.ucm.myproject.config.domain.BudgetDetails;
import com.ucm.myproject.config.domain.UserDetails;

public interface BudgetDetailsDao {

    int saveBudgetDetails(BudgetDetails details);
}
