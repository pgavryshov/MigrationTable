package com.thumbtack.migration_table.repository;

import com.thumbtack.migration_table.model.MigrationTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends CrudRepository<MigrationTable, String> {



}
