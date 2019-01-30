package com.thumbtack.migration_table.service;

import com.thumbtack.migration_table.model.MigrationTable;
import com.thumbtack.migration_table.repository.MigrationRepository;
import com.thumbtack.migration_table.repository.TableRepository;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository repository;
    @Autowired
    private MigrationRepository migrationRepository;


    @Override
    public MigrationTable getTableByName(String name) {
        if (repository.existsById(name))
            return repository.findById(name).get();
        else return null;
    }

    @Override
    public boolean existField(String tableName, String fieldName) {
        return repository.existsById(tableName) ;
    }

    @Override
    public void deleteTable(String name) {
        if (repository.existsById(name))
            migrationRepository.deleteById(name);
    }

    @Override
    public void deleteField(String tableName, String fieldName) {
        if (existField(tableName, fieldName)) {
//            migrationRepository.deleteField(tableName, fieldName);
        }
    }

    @Override
    public void addField(String tableName, String fieldName) {
        if (existField(tableName, fieldName)) {
//            migrationRepository.addField(tableName, fieldName);
        }
    }

    @Override
    public boolean existTable(String name) {
        return repository.existsById(name);
    }

    @Override
    public MigrationTable newTable(MigrationTable table) {
        if (!existTable(table.getMigrationName()))
            return migrationRepository.save(table);
        return null;
    }

    @Override
    public ArrayList tableListAll() {
        return Lists.newArrayList(repository.findAll());
    }

}
