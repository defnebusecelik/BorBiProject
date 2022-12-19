package javasrc.forsql;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javasrc.model.City;


public class CitySql {
        private final String jdbcURL = "jdbc:mysql://localhost:";
        private final String jdbcUsername = "t";
        private final String jdbcPassword = "";

        private static final String CITY_SELECT_ID = "select * from city where airlineCityId=?;";
        private static final String CITY_SELECT_ALL = "select * from city;";
        private static final String CITY_INSERT = "INSERT INTO city (airlineCityName) VALUES " +
                " (?);";
        private static final String CITY_DELETE = "delete from city where airlineCityId = ?;";
        private static final String CITY_UPDATE = "update city set airlineCityName= ? where airlineCityId = ?;";


        public CitySql() {
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


        public List<City> cityList() {
            List<City> cities = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(CITY_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int airlineCityId = result.getInt("airlineCityId");
                    String airlineCityName= result.getString("airlineCityName");
                    cities.add(new City(airlineCityId, airlineCityName));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return cities;
        }

        public void addCity(City city) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(CITY_INSERT)) {
                    preparedStatement.setString(1, city.getAirlineCityName());
                    preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public boolean updateCity(City city) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(CITY_UPDATE);) {
                statement.setString(1, city.getAirlineCityName());
                statement.setInt(2, city.getAirlineCityId());
                updatingRow = statement.executeUpdate() > 0;
        }
        return updatingRow;
    }

        public boolean cleanCity(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(CITY_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
        }

        public City chooseCity(int id) {
            City city = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(CITY_SELECT_ID);) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String airlineCityName = result.getString("airlineCityName");
                    city = new City(id, airlineCityName);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return city;
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
