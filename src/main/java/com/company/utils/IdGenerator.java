package com.company.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdGenerator {

    public static Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getLong(1);
        } else {
            throw new SQLException("Creation failed, no id obtained.");
        }
    }
}
