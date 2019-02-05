package com.thumbtack.migration_table.service;

import com.thumbtack.migration_table.model.MigrationTable;

public interface DataBaseService {

    boolean existsById(String name);

    MigrationTable findById(String name);

}
