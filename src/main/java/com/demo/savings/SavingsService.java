package com.demo.savings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;

    public Savings setGoal(Savings savings) {
        return savingsRepository.save(savings);
    }

    public Savings updateSavings(Long userId, double amountToAdd) {
        Savings savings = savingsRepository.findByUserId(userId);
        if (savings != null) {
            savings.setAmountSaved(savings.getAmountSaved() + amountToAdd);
            return savingsRepository.save(savings);
        }
        return null;  // Could throw exception if needed
    }

    public Savings getSavingsByUser(Long userId) {
        return savingsRepository.findByUserId(userId);
    }
}
