package pharmamanagement.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pharmamanagement.model.med;

public class MedDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/pharma?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "vishal@1";

	private static final String INSERT_MEDS_SQL = "INSERT INTO meds" + "  (name, company) VALUES " + " (?, ?);";

	private static final String SELECT_MEDS_BY_ID = "select id,name,company from meds where id =?";
	private static final String SELECT_ALL_MEDS = "select * from meds";
	private static final String DELETE_MEDS_SQL = "delete from meds where id = ?;";
	private static final String UPDATE_MEDS_SQL = "update meds set name = ?,company= ? where id = ?;";

	public MedDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertMed(med med) throws SQLException {
		System.out.println(INSERT_MEDS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEDS_SQL)) {
			preparedStatement.setString(1, med.getName());
			preparedStatement.setString(2, med.getCompany());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public med selectMed(int id) {
		med med = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String company = rs.getString("company");

				med = new med(id, name, company);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return med;
	}

	public List <med> selectAllMeds() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<med> meds = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String company = rs.getString("company");

				meds.add(new med(id, name, company));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return meds;
	}

	public boolean deleteMed(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MEDS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateMed(med med) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_MEDS_SQL);) {
			statement.setString(1, med.getName());
			statement.setString(2, med.getCompany());
			statement.setInt(3, med.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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