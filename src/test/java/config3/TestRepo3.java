//package config3;
//
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.context.TestPropertySource;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import telegrambot.TelegramBotApplication;
//import telegrambot.model.Card;
//import telegrambot.repository.CardRepository;
//
//import javax.sql.DataSource;
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.NONE,
//        classes = {CardRepository.class, TelegramBotApplication.class})
////@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Testcontainers
//public class TestRepo3 {
//    @Container
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine")
//            .withDatabaseName("test")
//            .withUsername("sa")
//            .withPassword("sa");
//
//    @DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgres::getJdbcUrl);
//        registry.add("spring.datasource.username", postgres::getUsername);
//        registry.add("spring.datasource.password", postgres::getPassword);
//    }
//
//    @Autowired
//    private CardRepository repository;
//
//    @BeforeEach
//    void setUp() {
//        repository.deleteAll();
//        repository.save(new Card(3L, "a3", BigDecimal.valueOf(333)));
//        repository.save(new Card(4L, "a4", BigDecimal.valueOf(444)));
//        repository.save(new Card(5L, "a5", BigDecimal.valueOf(555)));
//    }
//
//    @Test
//    void test3() {
//        assertThat(repository.getByName("a3").orElse(new Card()))
//                .isEqualTo(new Card(3L, "a3", BigDecimal.valueOf(333)));
//    }
//}
