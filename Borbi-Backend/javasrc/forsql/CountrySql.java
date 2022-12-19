package javasrc.forsql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javasrc.model.Country;

public class CountrySql {

        private final String jdbcURL = "jdbc:mysql://localhost:";
        private final String jdbcUsername = "";
        private final String jdbcPassword = "";

        private static final String COUNTRY_SELECT_ID = "select * from Country where airlineCountryName=?;";
        private static final String COUNTRY_SELECT_ALL = "select * from Country;";
        private static final String COUNTRY_INSERT = "INSERT INTO Country" + "  (airlineCountryName) VALUES " +
                " (?);";
        private static final String COUNTRY_UPDATE = "update Country set airlineCountryName = ? where airlineCountryId = ?;";

        private static final String COUNTRY_DELETE = "delete from Country where airlineCountryId = ?;";

        public CountrySql() {
        }

        protected Connection getConnection() {
            Connection connection = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
        }


        public List<Country> countryList() {
            List<Country> countries = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(COUNTRY_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int airlineCountryId = result.getInt("airlineCountryId");
                    String airlineCountryName = result.getString("airlineCityId");
                    countries.add(new Country(airlineCountryId, airlineCountryName));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return countries;
        }

        public void addCountry(Country country) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(COUNTRY_INSERT)) {
                preparedStatement.setString(1, country.getAirlineCountryName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public boolean deleteCountry(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(COUNTRY_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
        }

        public boolean updateCountry(Country country) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(COUNTRY_UPDATE);) {
                statement.setString(1, country.getAirlineCountryName());
                statement.setInt(2, country.getAirlineCountryId());
                updatingRow = statement.executeUpdate() > 0;
            }
            return updatingRow;
        }

        public Country chooseCountry(int id) {
            Country country = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(COUNTRY_SELECT_ID);) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String airlineCountryName = result.getString("airlineCountryName");
                    country = new Country(id, airlineCountryName);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return country;
        }

        private void printSQLException(SQLException ex) {
            for (Throwable e: ex) {
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
