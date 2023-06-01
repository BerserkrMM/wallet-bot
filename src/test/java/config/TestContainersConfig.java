package config;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class TestContainersConfig {
    public static class CustomPostgreSQLContainer extends PostgreSQLContainer<CustomPostgreSQLContainer> {

        private static final String IMAGE_VERSION = "postgres";

        private static CustomPostgreSQLContainer container;

        public CustomPostgreSQLContainer() {
            super(IMAGE_VERSION);
        }

        public static CustomPostgreSQLContainer getInstance() {
            if (container == null) {
                container = new CustomPostgreSQLContainer()
                        .withInitScript("script/init_db.sql")
                        .withPassword("test")
                        .waitingFor(Wait.forListeningPort());
            }
            return container;
        }

        @Override
        public void start() {
            super.start();
            System.setProperty("spring.datasource.driver-class-name", container.getDriverClassName());
            System.setProperty("spring.datasource.url", container.getJdbcUrl());
            System.setProperty("spring.datasource.username", container.getUsername());
            System.setProperty("spring.datasource.password", container.getPassword());
        }

        @Override
        public void stop() {
            //JVM handles shut down
        }

    }
}