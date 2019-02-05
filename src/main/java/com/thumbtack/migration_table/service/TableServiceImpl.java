package com.thumbtack.migration_table.service;

import com.thumbtack.migration_table.model.MigrationTable;
import com.thumbtack.migration_table.repository.MigrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private MigrationRepository migrationRepository;

    @Autowired
    private DataBaseService dataBaseService;

    @Override
    public MigrationTable getTableByName(String name) {
        if (dataBaseService.existsById(name))
            return dataBaseService.findById(name);
        else return null;
    }

    @Override
    public boolean existField(String tableName, String fieldName) {
        return dataBaseService.findById(tableName).getFields().containsKey(fieldName);
    }

    @Override
    public void deleteTable(String name) {
        if (existTable(name))
            migrationRepository.deleteById(name);
    }

    @Override
    public void deleteField(String tableName, String fieldName) {
        if (existField(tableName, fieldName)) {
            migrationRepository.deleteField(tableName, fieldName);
        }
    }

    @Override
    public void addField(String tableName, String fieldName) {
        if (!existField(tableName, fieldName)) {
            migrationRepository.addField(tableName, fieldName);
        }
    }

    @Override
    public boolean existTable(String name) {
        return dataBaseService.existsById(name);
    }

    @Override
    public MigrationTable newTable(String tableName) {
        if (!existTable(tableName))
            migrationRepository.createNewTable(tableName);
        return null;
    }
}
