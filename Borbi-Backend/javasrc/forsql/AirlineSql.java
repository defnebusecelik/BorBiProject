package javasrc.forsql;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javasrc.model.Airline;
import javasrc.model.Country;
import javasrc.model.City;

public class AirlineSql {

        private final String jdbcURL = "jdbc:mysql://localhost:";
        private final String jdbcUsername = "";
        private final String jdbcPassword = "";


        private static final String AIRLINE_SELECT_ALL = "SELECT airlineId, airlineName, country.airlineCountryId, country.countryAirlineName, city.airlineCityId, city.airlineCityName  FROM airline INNER JOIN country ON airline.airlineCountryId= country.airlineCountryName INNER JOIN city ON airline.airlineCityId= city.airlineCityId;";
        private static final String AIRLINE_INSERT ="INSERT INTO airline (airlineName, airlineId, airlineCityId, airlineCountryId) VALUES (?,?,?,?);";
        private static final String AIRLINE_CITY_SELECT_ALL ="select * from city;";
        private static final String AIRLINE_COUNTRY_SELECT_ALL ="select * from country;";
        private static final String AIRLINE_SELECT_ID = "SELECT * FROM airline  where airlineId=?;";
        private static final String AIRLINE_DELETE = "delete from airline where airlineId = ?;";
        private static final String AIRLINE_UPDATE = "update airline set airlineName = ?, airlineCountryName=?, airlineCityName=? where airlineId = ?;";


        public AirlineSql() {
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

        public List<Airline> airlineList() {
            List<Airline> airline = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(AIRLINE_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();

                while (result.next()) {
                    int airlineCityId = result.getInt("airlineCityId");
                    String airlineCityName = result.getString("airlineCityName");
                    int airlineCountryId = result.getInt("airlineCountryId");
                    String airlineCountryName = result.getString("airlineCountryName");
                    String airlineName = result.getString("airlineName");
                    String airlineId = result.getString("airlineId");
                    airline.add(new Airline(airlineId, airlineCountryId, airlineCityId, airlineName, airlineCountryName,airlineCityName));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return airline;
        }

        public List<Country> airlineCountry() {
            List<Country> airlineCountry = new ArrayList<> ();
            try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(AIRLINE_COUNTRY_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int airlineCountryId = result.getInt("airlineCountryId");
                    String airlineCountryName = result.getString("airlineCountryName");
                    airlineCountry.add(new Country(airlineCountryId, airlineCountryName));
                 }
             } catch (SQLException e) {
                printSQLException(e);
            }
            return airlineCountry;
         }
    
        public List<City> airlineCity() {
            List<City> airlineCity = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(AIRLINE_CITY_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int airlineCityId = result.getInt("airlineCityId");
                    String airlineCityName = result.getString("airlineCityName");
                    airlineCity.add(new City(airlineCityId, airlineCityName));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return airlineCity;
        }

        public void addAirline(Airline airline) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(AIRLINE_INSERT)) {
                preparedStatement.setString(1, airline.getAirlineName());
                preparedStatement.setString(2, airline.getAirlineId());
                preparedStatement.setInt(3, airline.getAirlineCountryId());
                preparedStatement.setInt(4, airline.getAirlineCityId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public boolean deleteAirline(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(AIRLINE_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
        }

        public Airline chooseAirline(int id) {
            Airline airline = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(AIRLINE_SELECT_ID);) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String airlineName = result.getString("airlineName");
                    String airlineId = result.getString("airlineId");
                    int airlineCountryId = result.getInt("airlineCountryId");
                    int airlineCityId = result.getInt("airlineCityId");

                    airline = new Airline(id, airlineCountryId, airlineCityId,airlineName, airlineId);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return airline;
        }

        public boolean updateAirline(Airline airline) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(AIRLINE_UPDATE);) {
                statement.setString(1, airline.getAirlineName());
                statement.setString(2, airline.getAirlineId());
                statement.setInt(3, airline.getAirlineCountryId());
                statement.setInt(4, airline.getAirlineCityId());
                updatingRow = statement.executeUpdate() > 0;
            }
            return updatingRow;
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
