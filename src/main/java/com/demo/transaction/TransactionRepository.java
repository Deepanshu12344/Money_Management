package com.demo.transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByUserIdAndCategory(Long userId, String category);
    List<Transaction> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
