package com.thumbtack.migration_table.repository;

import com.thumbtack.migration_table.model.MigrationTable;
import org.flywaydb.core.Flyway;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Repository
public class MigrationRepository {

    public void deleteById(String tableName) {

    }

    public MigrationTable save(MigrationTable table) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String command = "create table s" + table.getMigrationName() + "(id VARCHAR (25) NOT NULL PRIMARY KEY); ";
        createMigrationFile(command);
        executeMigration();
        return table;
    }

    public void deleteField(String tableName, String fieldName) {

    }

    public void addField(String tableName, String fieldName) {

    }

    private void createMigrationFile(String sqlCommand) {
        try {
            String projectFolder = this.getClass().getResource("/db/migration")
                    .getFile().split("target")[0];

            String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            int version = countMigrations(projectFolder) + 1;
            File testResultsFolder = new File(projectFolder + "src/main/resources/db/migration/V" +
                    version + format + ".sql");
            FileUtils.createNewFile(testResultsFolder);
            Files.write(testResultsFolder.toPath(), sqlCommand.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeMigration() {

        Flyway flyway = Flyway.configure().locations("classpath:/db/migration")
                .dataSource("jdbc:postgresql://localhost:5432/migrations", "pgavryshov", "antispam1.").load();
        flyway.migrate();
    }

    private int countMigrations(String projectFolder) {
        return Objects.requireNonNull(new File(projectFolder +
                "src/main/resources/db/migration/").list()).length;
    }
}
