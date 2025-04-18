package com.demo.transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public Transaction add(@RequestBody Transaction transaction) {
        return service.add(transaction);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @GetMapping("/user/{userId}/category")
    public List<Transaction> getByCategory(@PathVariable Long userId,
                                           @RequestParam String category) {
        return service.getByCategory(userId, category);
    }

    @GetMapping("/user/{userId}/range")
    public List<Transaction> getByDateRange(@PathVariable Long userId,
                                            @RequestParam String start,
                                            @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return service.getByDateRange(userId, startDate, endDate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
