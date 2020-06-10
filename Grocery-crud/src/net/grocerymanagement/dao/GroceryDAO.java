package net.grocerymanagement.dao;

import java.sql.Connection;




import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.grocerymanagement.model.Grocery;


public class GroceryDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3307/test?useSSL=false";
    private String jdbcGroceryname = "root";
    private String jdbcPassword = "";

    private static final String INSERT_GROCERYS_SQL = "INSERT INTO grocerys" + "  (name, type, company) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_GROCERY_BY_ID = "select id,name,type,company from grocerys where id =?";
    private static final String SELECT_ALL_GROCERYS = "select * from grocerys";
    private static final String DELETE_GROCERYS_SQL = "delete from grocerys where id = ?;";
    private static final String UPDATE_GROCERYS_SQL = "update grocerys set name = ?,type= ?, company =? where id = ?;";

    public GroceryDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcGroceryname, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertGrocery(Grocery grocery) throws SQLException {
        System.out.println(INSERT_GROCERYS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROCERYS_SQL)) {
            preparedStatement.setString(1, grocery.getName());
            preparedStatement.setString(2, grocery.getType());
            preparedStatement.setString(3, grocery.getCompany());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Grocery selectGrocery(int id) {
        Grocery grocery = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROCERY_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String company = rs.getString("company");
                grocery = new Grocery(id, name, type, company);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return grocery;
    }

    public List < Grocery > selectAllGrocerys() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Grocery > grocerys = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GROCERYS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String company = rs.getString("company");
                grocerys.add(new Grocery(id, name, type, company));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return grocerys;
    }

    public boolean deleteGrocery(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_GROCERYS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateGrocery(Grocery grocery) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_GROCERYS_SQL);) {
            statement.setString(1, grocery.getName());
            statement.setString(2, grocery.getType());
            statement.setString(3, grocery.getCompany());
            statement.setInt(4, grocery.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
