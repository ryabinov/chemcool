package com.chemcool.school.registration.app;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Сначала запустите ConfigService(обязательно), остальное можно не запускать.
 * Запустите программу Docker. При первом запуске теста будет скачивать образы, если их не скачивали ранее.
 * Просто наследуйтесь от этого класса в ваших классах с тестами.
 *
 * Используется образ Docker - postgres:13, на основе которого в докере запускается одноразовый контейнер.
 * Используется FlyWay, для применения миграций базы данных(таблиц) в тестовый контейнер POSTGRE_SQL_CONTAINER.
 * Используется DockerComposeContainer, который запускает одноразовые контейнеры zookeeper & Kafka,
 * используя kafka-compose.yml
 *
 * Если кафка уже была запущена вами ранее вручную, закомментируйте KAFKA_CONTAINER и runKafka(), либо используй @Ignore
 **/

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(initializers = {ContainerConfigurationTest.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContainerConfigurationTest {


    private static final String DOCKER_IMAGE_POSTGRES = "postgres:13";
    private static final String DB_NAME = "registration-users-test";
    private static final String USERNAME = "postgresTest";
    private static final String PASSWORD = "postgres";

    @Container
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>(DOCKER_IMAGE_POSTGRES)
            .withDatabaseName(DB_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD);

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    "spring.datasource.username=" + POSTGRE_SQL_CONTAINER.getUsername(),
                    "spring.datasource.password=" + POSTGRE_SQL_CONTAINER.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    @Order(1)
    @DisplayName("Проверка запуска контейнера Postgres")
    void isRunningPostgreSQLContainer() {
        assertTrue(POSTGRE_SQL_CONTAINER.isRunning());
    }

    @Test
    @Order(2)
    @DisplayName("Миграция FlyWay таблиц в тестовый контейнер Postgres в docker")
    public void runMigrations() {
        POSTGRE_SQL_CONTAINER.withDatabaseName(DB_NAME);
        Flyway flyway = Flyway.configure()
                .locations("db/migration")
                .schemas("public")
                .dataSource(POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                        POSTGRE_SQL_CONTAINER.getUsername(),
                        POSTGRE_SQL_CONTAINER.getPassword())
                .load();
        flyway.info();
        flyway.migrate();
    }

    @Container
    public static DockerComposeContainer<?> KAFKA_CONTAINER = new DockerComposeContainer<>(
            new File("src/test/resources/kafka-compose.yml"));

    @Test
    @Order(3)
    @DisplayName("Запуск тестовых контейнеров kafka/zookeeper в docker")
    void runKafka() {
        KAFKA_CONTAINER.start();
    }

}