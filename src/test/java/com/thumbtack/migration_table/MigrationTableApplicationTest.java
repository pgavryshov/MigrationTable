package com.thumbtack.migration_table;

import com.thumbtack.migration_table.service.DataBaseService;
import com.thumbtack.migration_table.service.TableService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MigrationTableApplicationTest {

    @Autowired
    TableService service;

    @Autowired
    DataBaseService dataBaseService;

    @Before
    public void dropDB(){
//        dataBaseService.getAllTables();
    }

    @Test
    public void testNewTable() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        service.newTable(String.valueOf(timestamp.getTime()));
        Assert.assertTrue(service.existTable(String.valueOf(timestamp.getTime())));
    }

    @Test
    public void testAddColumn() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        service.newTable(String.valueOf(timestamp.getTime()));
        service.addField(String.valueOf(timestamp.getTime()), "new_column");
        Assert.assertTrue(service.existField(String.valueOf(timestamp.getTime()), "new_column"));
    }

    @Test
    public void testDeleteField() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        service.newTable(String.valueOf(timestamp.getTime()));
        service.addField(String.valueOf(timestamp.getTime()), "new_column");
        service.deleteField(String.valueOf(timestamp.getTime()), "new_column");
        Assert.assertFalse(service.existField(String.valueOf(timestamp.getTime()), "new_column"));
    }

    @Test
    public void testDeleteTable() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        service.newTable(String.valueOf(timestamp.getTime()));
        service.deleteTable(String.valueOf(timestamp.getTime()));
        Assert.assertFalse(service.existTable(String.valueOf(timestamp.getTime())));
    }
}
