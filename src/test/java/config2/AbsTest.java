//package config2;
//
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.utility.DockerImageName;
//import telegrambot.TelegramBotApplication;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(
//        classes = TelegramBotApplication.class,
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        properties = {"spring.datasource.url=jdbc:postgresql://localhost:5432/test"})
//@ActiveProfiles("test")
//public class AbsTest {
//    static GenericContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.3-alpine"));
//
//    @DynamicPropertySource
//    static void postgresProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.username", ()->"test");
//        registry.add("spring.datasource.url", ()->"jdbc:postgresql://localhost:5432/test");
//        registry.add("spring.datasource.password", ()->"test");
//        postgres.start();
//    }
//}
