package com.demo.budget;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(Long userId);
    Budget findByUserIdAndCategory(Long userId, String category);
}
