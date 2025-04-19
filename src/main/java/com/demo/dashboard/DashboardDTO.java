package com.demo.dashboard;

public class DashboardDTO {
    private Long userId;
    private String name;
    private String email;
    private Double totalIncome;
    private Double totalExpense;
    private Double budgetLimit;
    private Double totalSavings;

    // Constructor
    public DashboardDTO(Long userId, String name, String email, Double totalIncome,
                        Double totalExpense, Double budgetLimit, Double totalSavings) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.budgetLimit = budgetLimit;
        this.totalSavings = totalSavings;
    }

    // Getters and Setters
    // ... (omitted for brevity)
}
