package com.demo.transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public Transaction add(Transaction transaction) {
        return repository.save(transaction);
    }

    public List<Transaction> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<Transaction> getByCategory(Long userId, String category) {
        return repository.findByUserIdAndCategory(userId, category);
    }

    public List<Transaction> getByDateRange(Long userId, LocalDate start, LocalDate end) {
        return repository.findByUserIdAndDateBetween(userId, start, end);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
