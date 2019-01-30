package com.thumbtack.migration_table.controller;

import com.thumbtack.migration_table.model.MigrationTable;
import com.thumbtack.migration_table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;

@Controller
public class TableController {

    @Autowired
    private TableService service;

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public String saveTable() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        service.newTable(new MigrationTable(String.valueOf(timestamp.getTime()), null));
        return null;
    }
}
