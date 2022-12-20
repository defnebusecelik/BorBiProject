package javasrc.forsql;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javasrc.model.AboutFlight;
import javasrc.model.AboutCompany;

public class AboutFlightSql {

    private final String jdbcURL = "jdbc:mysql://localhost:";
    private final String jdbcUsername = "";
    private final String jdbcPassword = "";

    private static final String ABOUTFLIGHT_SELECT_ALL = "SELECT flightId, airlineName, seat, company.companyName FROM aboutFlight INNER JOIN company ON aboutFlight.companyId=company.companyId;";
    private static final String COMPANY_SELECT_ALL = "select * from company;";
    private static final String ABOUTFLIGHT_INSERT = "INSERT INTO aboutFlight (companyName, seat, companyId) VALUES (?,?,?);";
    private static final String ABOUTFLIGHT_DELETE = "delete from aboutFlight where flightId = ?;";
    private static final String ABOUTFLIGHT_UPDATE = "update aboutFlight set companyName= ?, seat=?, companyId=? where flightId = ?;";
    private static final String ABOUTFLIGHT_SELECT_ID = "SELECT * FROM aboutFlight where flightId=?;";


    public AboutFlightSql() {
    }

    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<AboutFlight> flightList() {
        List<AboutFlight> planes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ABOUTFLIGHT_SELECT_ALL);) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int flightId = result.getInt("flightId");
                String companyName = result.getString("companyName");
                int seat = result.getInt("seat");
                int companyId = result.getInt("companyId");
                planes.add(new AboutFlight(flightId, companyName, seat, companyId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return planes;
    }

    public void addPlane(AboutFlight plane) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ABOUTFLIGHT_INSERT)) {
            preparedStatement.setString(1, plane.getCompanyName());
            preparedStatement.setInt(2, plane.getSeat());
            preparedStatement.setInt(3, plane.getCompanyId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean deletePlane(int id) throws SQLException {
        boolean cleaningRow;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(ABOUTFLIGHT_DELETE);) {
            statement.setInt(1, id);
            cleaningRow = statement.executeUpdate() > 0;
        }
        return cleaningRow;
    }

    public List<AboutCompany> company() {
        List<AboutCompany> company = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ABOUTFLIGHT_SELECT_ALL);) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int companyId = result.getInt("companyId");
                String companyName = result.getString("companyName");
                company.add(new AboutCompany(companyId, companyName));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return company;
    }

    public boolean updatePlane(AboutFlight plane) throws SQLException {
        boolean updatingRow;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(ABOUTFLIGHT_INSERT);) {
            statement.setString(1, plane.getCompanyName());
            statement.setInt(2, plane.getSeat());
            statement.setInt(3, plane.getCompanyId());
            statement.setInt(4, plane.getFlightId());
            updatingRow = statement.executeUpdate() > 0;
        }
        return updatingRow;
    }

    public AboutFlight choosePlane(int id) {
        AboutFlight plane = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ABOUTFLIGHT_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String companyName = result.getString("companyName");
                int seat = result.getInt("seat");
                int companyId = result.getInt("companyId");
                plane = new AboutFlight(id, companyName, seat, companyId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return plane;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable te = ex.getCause();
                while (te != null) {
                    System.out.println("Cause: " + te);
                    te = te.getCause();
                }
            }
        }
    }
}
