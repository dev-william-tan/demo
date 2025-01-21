package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testConnection() throws Exception {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();

        // Execute a simple query to verify the connection
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertThat(result).isEqualTo(1);
    }

    @Test 
    public void testUsersTableExists() throws Exception {
        // Check if the users table exists
        Integer tableCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'USERS' AND table_schema = 'PUBLIC'", Integer.class);
        assertThat(tableCount).isEqualTo(1);
    }

    @Test
    public void testUsersTableData() throws Exception {
        // Check the data in the users table
        String dataQuery = "SELECT COUNT(*) FROM users WHERE name = ? AND email = ?";
        Integer johnDoeCount = jdbcTemplate.queryForObject(dataQuery, Integer.class, "John Doe", "john.doe@example.com");
        Integer janeDoeCount = jdbcTemplate.queryForObject(dataQuery, Integer.class, "Jane Doe", "jane.doe@example.com");

        System.out.println("John Doe count: " + johnDoeCount);
        System.out.println("Jane Doe count: " + janeDoeCount);

        assertThat(johnDoeCount).isNotNull();
        assertThat(johnDoeCount).isEqualTo(1);

        assertThat(janeDoeCount).isNotNull();
        assertThat(janeDoeCount).isEqualTo(1);
    }

}