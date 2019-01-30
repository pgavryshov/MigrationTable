package com.thumbtack.migration_table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class MigrationTable {
    @Getter
    @Setter
    @Id
    private String migrationName;

    @Getter
    @Setter
    private Map<String, String> fields;
}
