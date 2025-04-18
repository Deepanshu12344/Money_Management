package com.demo.savings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
    Savings findByUserId(Long userId);
}
