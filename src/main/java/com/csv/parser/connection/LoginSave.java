package com.csv.parser.connection;

import com.csv.parser.entity.Login;
import com.csv.parser.readers.ReadLogins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LoginSave {
    private static final String URL = "jdbc:mysql://localhost:3306/a1_db";
    private static final String USER = "bestuser";
    private static final String PASSWORD = "bestuser";

    public static void main(String[] args) {
        ReadLogins readLogins = new ReadLogins();
        List<Login> list = readLogins.getCompleteLoginList();
        saveLoginsToDatabase(list);
    }

    public static void saveLoginsToDatabase(List<Login> logins) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO login (application, app_account_name, is_active, job_title, department) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            for (Login login : logins) {
                preparedStatement.setString(1, login.getApplication());
                preparedStatement.setString(2, login.getAppAccountName());
                preparedStatement.setBoolean(3, login.isActive());
                preparedStatement.setString(4, login.getJobTitle());
                preparedStatement.setString(5, login.getDepartment());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
