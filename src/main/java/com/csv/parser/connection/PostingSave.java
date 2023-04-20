package com.csv.parser.connection;

import com.csv.parser.readers.ReadPostings;
import com.csv.parser.entity.Login;
import com.csv.parser.entity.Posting;
import com.csv.parser.readers.ReadLogins;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class PostingSave {
    private static final String URL = "jdbc:mysql://localhost:3306/a1_db";
    private static final String USER = "bestuser";
    private static final String PASSWORD = "bestuser";

    public static void main(String[] args) {
        ReadPostings readPostings = new ReadPostings();
        List<Posting> list = readPostings.getCompletePostingList();
        fillingPostingsFieldAuthorized();
        saveLoginsToDB(list);
    }

    public static void fillingPostingsFieldAuthorized() {

        List<Login> logins = new ReadLogins().getCompleteLoginList();
        List<Posting> postings = new ReadPostings().getCompletePostingList();

        for (Posting posting : postings) {
            Login login = logins.stream().filter(f -> f.isActive() &&
                    f.getAppAccountName().equals(posting.getUserName())).findAny().orElse(null);
            posting.setAuthorized(!Objects.isNull(login));
        }
    }

    public static void saveLoginsToDB(List<Posting> postings) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO posting (mat_doc, item, doc_date, pstng_date, material_description, quantity, bUn, amount_lc, crcy, username, is_authorized) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            for (Posting posting : postings) {
                preparedStatement.setLong(1, posting.getMatDoc());
                preparedStatement.setString(2, posting.getItem());
                preparedStatement.setDate(3, Date.valueOf(posting.getDocDate()));
                preparedStatement.setDate(4, Date.valueOf(posting.getPstngDate()));
                preparedStatement.setString(5, posting.getMaterialDescription());
                preparedStatement.setString(6, posting.getQuantity());
                preparedStatement.setString(7, posting.getbUn());
                preparedStatement.setString(8, posting.getAmountLC());
                preparedStatement.setString(9, posting.getCrcy());
                preparedStatement.setString(10, posting.getUserName());
                preparedStatement.setBoolean(11, posting.isAuthorized());
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
