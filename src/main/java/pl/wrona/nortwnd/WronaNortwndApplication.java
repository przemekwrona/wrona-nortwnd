package pl.wrona.nortwnd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class WronaNortwndApplication {

    public static void main(String[] args) {
        SpringApplication.run(WronaNortwndApplication.class, args);
    }

    @Bean
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:sqlserver://127.0.0.1:1433;database=NORTHWND");
        config.setUsername("pwrona");
        config.setPassword("welcome1");
        config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return new HikariDataSource(config);
    }
}
