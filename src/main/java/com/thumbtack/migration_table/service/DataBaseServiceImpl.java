package com.thumbtack.migration_table.service;

import com.thumbtack.migration_table.model.MigrationTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;


@Service
public class DataBaseServiceImpl extends JdbcDaoSupport implements DataBaseService {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public boolean existsById(String name) {
        String sql = "SELECT count(*) FROM information_schema.tables WHERE table_name = 's" + name + "';";
        int i = getJdbcTemplate().queryForObject(sql, Integer.class);
        return i != 0;
    }

    @Override
    public MigrationTable findById(String name) {
        String sql = "select column_name from information_schema.columns where table_name = 's" + name + "';";
        return getJdbcTemplate().query(sql, rs -> {
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            Map map = new HashMap<String, String>();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rs.getString(i), String.class.getName());
                }
            }
            return new MigrationTable(name, map);
        });
    }
}

