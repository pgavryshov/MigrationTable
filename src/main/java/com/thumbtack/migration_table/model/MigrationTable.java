package com.thumbtack.migration_table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class MigrationTable {
    @Getter
    @Setter
    @Id
    private String migrationName;

    @Getter
    @Setter
    private Map<String, String> fields;
}
