package com.demo.budget;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget addOrUpdateBudget(Budget budget) {
        Budget existingBudget = budgetRepository.findByUserIdAndCategory(budget.getUserId(), budget.getCategory());
        if (existingBudget != null) {
            existingBudget.setLimitAmount(budget.getLimitAmount());
            return budgetRepository.save(existingBudget);
        } else {
            return budgetRepository.save(budget);
        }
    }

    public List<Budget> getBudgetsByUserId(Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
