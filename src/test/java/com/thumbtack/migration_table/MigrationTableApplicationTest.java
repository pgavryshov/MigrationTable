package com.thumbtack.migration_table;

import com.thumbtack.migration_table.model.MigrationTable;
import com.thumbtack.migration_table.service.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MigrationTableApplicationTest {

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    TableService service;


    @Test
    public void testSaveCity() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        service.newTable(new MigrationTable(String.valueOf(timestamp.getTime()), null));
    }
}
