package config4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import telegrambot.TelegramBotApplication;
import telegrambot.model.Card;
import telegrambot.repository.CardRepository;

import java.math.BigDecimal;

@Testcontainers
@SpringBootTest(classes = TelegramBotApplication.class)
@ContextConfiguration
public class RpoTest4 {

    @Autowired
    private CardRepository cardRepository;

    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:14.3-alpine")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Test
    public void should_be_able_to_get_senior_consultant_by_technology() {
        //arrange
        Card savedCard2 = cardRepository.save(new Card(1L, "name", BigDecimal.ONE));

        //act
        var r = cardRepository.getByName("name").orElse(new Card());

        //assert
        Assertions.assertThat(r.getName()).isEqualTo("name");
    }
}
