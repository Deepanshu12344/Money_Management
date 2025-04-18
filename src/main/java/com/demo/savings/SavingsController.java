package com.demo.savings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/savings")
public class SavingsController {

    @Autowired
    private SavingsService savingsService;

    @PostMapping
    public Savings setGoal(@RequestBody Savings savings) {
        return savingsService.setGoal(savings);
    }

    @PutMapping("/user/{userId}/add")
    public Savings updateSavings(@PathVariable Long userId, @RequestParam double amountToAdd) {
        return savingsService.updateSavings(userId, amountToAdd);
    }

    @GetMapping("/user/{userId}")
    public Savings getSavings(@PathVariable Long userId) {
        return savingsService.getSavingsByUser(userId);
    }
}
