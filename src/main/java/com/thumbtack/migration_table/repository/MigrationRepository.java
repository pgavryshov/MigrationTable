package com.thumbtack.migration_table.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MigrationRepository {

    @Autowired
    MigrationRunner runner;

    public void deleteById(String tableName) {
        String command = "DROP TABLE IF EXISTS s" + tableName + ";";
        runner.createAndRunMigration(command);
    }

    public void createNewTable(String tableName) {
        String command = "CREATE TABLE s" + tableName + "(id VARCHAR (25) NOT NULL PRIMARY KEY); ";
        runner.createAndRunMigration(command);
    }

    public void deleteField(String tableName, String fieldName) {
        String command = "ALTER TABLE s" + tableName + " DROP COLUMN " + fieldName + ";";
        runner.createAndRunMigration(command);
    }

    public void addField(String tableName, String fieldName) {
        String command = "ALTER TABLE s" + tableName + " ADD COLUMN " + fieldName + " VARCHAR (25) NOT NULL;";
        runner.createAndRunMigration(command);
    }
}
