package com.thumbtack.migration_table.repository;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Repository
public class MigrationRunner {

    @Autowired
    private Environment env;

    private static final String SQL_FILE = ".sql";
    private static final String DESCRIPTION = "__Description-";
    private static final String VERSION_NAME = "/V1.";
    private static final String MIGRATION_DIR = new File("").getAbsolutePath() + "/";
    private static final String DATA_FORMAT = "yyyy-MM-dd-HH-mm-ss";
    private static final String RESOURCE_MIGRATION_PATH = "src/main/resources/db/migration";
    private static final String TARGET_MIGRATION_PATH = "target/classes/db/migration";

    private String datasourceUrl() {
        return env.getProperty("spring.datasource.url");
    }

    private String username() {
        return env.getProperty("spring.datasource.username");
    }

    private String password() {
        return env.getProperty("spring.datasource.password");
    }

    private void runMigration() {
        Flyway flyway = Flyway.configure().dataSource(datasourceUrl(), username(), password()).load();
        flyway.migrate();
    }

    public void createAndRunMigration(String command) {
        createMigrationFile(command);
        runMigration();
    }

    private static void createMigrationFile(String command) {
        String format = new SimpleDateFormat(DATA_FORMAT).format(new Date());
        int version = countMigrationVersion();
        File resourceFolder = new File(MIGRATION_DIR + RESOURCE_MIGRATION_PATH + VERSION_NAME +
                version + DESCRIPTION + format + SQL_FILE);
        File targetFolder = new File(MIGRATION_DIR + TARGET_MIGRATION_PATH + VERSION_NAME +
                version + DESCRIPTION + format + SQL_FILE);
        try {
            Files.write(resourceFolder.toPath(), command.getBytes());
            Files.write(targetFolder.toPath(), command.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countMigrationVersion() {
        return Objects.requireNonNull(new File(MIGRATION_DIR + RESOURCE_MIGRATION_PATH).list()).length + 1;
    }
}
