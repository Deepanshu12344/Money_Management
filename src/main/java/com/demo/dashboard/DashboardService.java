package com.demo.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DashboardDTO getDashboardData(Long userId) {
        String sql = "SELECT u.id AS user_id, u.name, u.email, " +
                     "COALESCE(SUM(CASE WHEN t.amount > 0 THEN t.amount ELSE 0 END), 0) AS total_income, " +
                     "COALESCE(SUM(CASE WHEN t.amount < 0 THEN t.amount ELSE 0 END), 0) AS total_expense, " +
                     "COALESCE(b.limit_amount, 0) AS budget_limit, " +
                     "COALESCE(s.amount_saved, 0) AS total_savings " +
                     "FROM users u " +
                     "LEFT JOIN transactions t ON t.user_id = u.id " +
                     "LEFT JOIN budget b ON b.user_id = u.id " +
                     "LEFT JOIN savings s ON s.user_id = u.id " +
                     "WHERE u.id = ? " +
                     "GROUP BY u.id, u.name, u.email, b.limit_amount, s.amount_saved";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) ->
                new DashboardDTO(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDouble("total_income"),
                        rs.getDouble("total_expense"),
                        rs.getDouble("budget_limit"),
                        rs.getDouble("total_savings")
                ));
    }
}
