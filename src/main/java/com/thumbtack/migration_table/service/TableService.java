package com.thumbtack.migration_table.service;

import com.thumbtack.migration_table.model.MigrationTable;

public interface TableService {

    MigrationTable getTableByName(String name);

    void deleteTable(String name);

    boolean existTable(String name);

    MigrationTable newTable(String tableName);

    boolean existField(String tableName, String fieldName);

    void addField(String tableName, String fieldName);

    void deleteField(String tableName, String fieldName);
}
