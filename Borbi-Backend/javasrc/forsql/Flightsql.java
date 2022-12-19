package javasrc.forsql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javasrc.model.AboutCompany;
import javasrc.model.Airline;
import javasrc.model.AboutFlight;
import javasrc.model.Flight;

public class FlightSql {

        private final String jdbcURL = "jdbc:mysql://localhost:";
        private final String jdbcUsername = "";
        private final String jdbcPassword = "";

        private static final String FLIGHT_INSERT ="INSERT INTO flight (sourceId, destinationId, flightDate, flightHour, flightTime, companyId, planeId, ticketPayment) VALUES (?,?,?,?,?,?,?,?);";
        private static final String COMPANY_SELECT_ALL = "select * from company;";
        private static final String AIRLINE_SELECT_ALL = "select * airline;";
        private static final String PLANE_SELECT_ALL = "select * from plane;";
        private static final String UPDATEFLIGHT_SELECT_ALL="select flightId, s.airlineName as sourceCity, p.airlineName as destinationCity, flightDate, flightHour, flightTime, company.companyName, plane.planeId , ticketPayment from flight\n" +
                "INNER JOIN  plane ON (plane.planeId = flight.planeId)\n" +
                "INNER JOIN  company ON (company.companyId = flight.companyId)\n" +
                "INNER JOIN  airline s ON (s.airlineId = flight.sourceId)\n" +
                "INNER JOIN  airline p ON (p.airlineId = flight.destinationId)\n" +
                "WHERE flightDate >= ? ;";

        private static final String PASTFLIGHT_SELECT_ALL="select flightId, s.airlineName as sourceCity, p.airlineName as destinationCity, flightDate, flightHour, flightTime, company.companyName, plane.planeId, ticketPayment from flight\n" +
                "INNER JOIN  plane ON (plane.planeId = flight.planeId)\n" +
                "INNER JOIN  company ON (company.companyId = flight.companyId)\n" +
                "INNER JOIN  airline s ON (s.airlineId = flight.sourceId)\n" +
                "INNER JOIN  airline p ON (p.airlineId = flight.destinationId)\n" +
                "WHERE flightDate < ? ;";

        private static final String FLIGHT_SELECT_ID = "SELECT * FROM flight  where flightId=?;";
        private static final String FLIGHT_DELETE = "delete from flight where flightId = ?;";
        private static final String FLIGHT_UPDATE = "update flight set sourceCity = ?, destinationCity=?, flightDate=?, flightHour=?, flightTime=?, companyName=?, planeId=?, ticketPayment=? where flightId = ?;";
        private static final String FLIGHT_KONTROL = "select * from flight as u \n" +
                "join plane as k on k.planeId=u.planeId\n" +
                "where u.planeId=? and u.flightDate=? and ((u.flightHour BETWEEN ? AND ?) or (ADDTIME(u.flightHour, u.flightTime) BETWEEN ? AND ?));";


        public FlightSql() {
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


        public boolean cleanFlight(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(FLIGHT_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
        }

        public Flight chooseFlight(int id) {
            Flight flight = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(FLIGHT_SELECT_ID);) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int sourceId = result.getInt("sourceId");
                    int destinationId = result.getInt("destinationId");
                    String flightDate = result.getString("flightDate");
                    String flightHour = result.getString("flightHour");
                    String flightTime = result.getString("flightTime");
                    int companyId = result.getInt("companyId");
                    String planeId = result.getString("planeId");
                    Double ticketPayment = result.getDouble("ticketPayment");
                    flight = new Flight(id,sourceId,destinationId,flightDate,flightHour,flightTime,companyId,planeId,ticketPayment);
                }
             } catch (SQLException e) {
                printSQLException(e);
            }
            return flight;
        }

        public boolean updateFlight(Flight flight) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(FLIGHT_UPDATE);) {
                statement.setInt(1, flight.getStartClockId());
                statement.setInt(2, flight.getEndClockId());
                statement.setString(3, flight.getFlightDate());
                statement.setString(4, flight.getFlightHour());
                statement.setString(5, flight.getFlightTime());
                statement.setString(6, flight.getCompanyName());
                statement.setString(7, flight.getPlaneId());
                statement.setDouble(8, flight.getTicketPayment());
                statement.setInt(9, flight.getFlightId());
                updatingRow = statement.executeUpdate() > 0;
            }
            return updatingRow;
        }


        public boolean ControlFlight(Flight flight)throws SQLException {
            String flightHour = flight.getFlightHour();
            flightHour = flightHour.substring(0, 5);
            String flightTime = flight.getFlightTime();
            String[] ARRAYflightTime = flightTime.split(":");
            String hourtime = ARRAYflightTime[0];
            String minutetime = ARRAYflightTime[1];
            String[] ARRAYflightHour = flightHour.split(":");
            String hour = ARRAYflightHour[0];
            String minute = ARRAYflightHour[1];
            int hournumber = (Integer.parseInt(hour) + Integer.parseInt(hour)) % 24;
            int minutenumber = (Integer.parseInt(minute) + Integer.parseInt(minute)) % 60;
            String Sminute;
            if (minutenumber < 10) {
                Sminute = "0" + String.valueOf(minutenumber);
            } else {
                Sminute = String.valueOf(minutenumber);
            }
            String Shour;
            if (hournumber < 10) {
                Shour = "0" + String.valueOf(hournumber);
            } else {
                Shour = String.valueOf(hournumber);
            }
            String destinationHour = Shour + ":" + Sminute;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(FLIGHT_KONTROL);) {
                preparedStatement.setString(1, flight.getPlaneId());
                preparedStatement.setString(2, flight.getFlightDate());
                preparedStatement.setString(3, flight.getFlightHour());
                preparedStatement.setString(4, flightTime);
                preparedStatement.setString(5, destinationHour);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    return false;
                }

            } catch (SQLException e) {
                printSQLException(e);
            }

            return true;
        }

        public void newFlight(Flight flight) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(FLIGHT_INSERT)) {
                preparedStatement.setInt(1, flight.getStartClockId());
                preparedStatement.setInt(2, flight.getEndClockId());
                preparedStatement.setString(3, flight.getFlightDate());
                preparedStatement.setString(4, flight.getFlightHour());
                preparedStatement.setString(5, flight.getFlightTime());
                preparedStatement.setInt(6, flight.getCompanyId());
                preparedStatement.setString(7, flight.getPlaneId());
                preparedStatement.setDouble(8, flight.getTicketPayment());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public List<Flight> upFlightList() {
            List<Flight> flights = new ArrayList<> ();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String str = now.format(formatter);

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATEFLIGHT_SELECT_ALL);) {
                preparedStatement.setString(1, str);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int flightId = result.getInt("flightId");
                    String startClockId = result.getString("startClockId");
                    String endClockId = result.getString("endClockId");
                    String flightDate = result.getString("flightDate");
                    String flightHour = result.getString("flightHour");
                    String flightTime = result.getString("flightTime");
                    String companyName = result.getString("companyName");
                    String planeId = result.getString("planeId");
                    Double ticketPayment = result.getDouble("ticketPayment");
                    flights.add(new Flight(flightId, flightDate, flightHour, flightTime, ticketPayment, companyName, planeId, startClockId, endClockId));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return flights;
        }

        public List<Flight> pastFlightsList() {
            List<Flight> pastFlights = new ArrayList<> ();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String str = now.format(formatter);

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(PASTFLIGHT_SELECT_ALL);) {
                preparedStatement.setString(1, str);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int flightId = result.getInt("flightId");
                    String sourceCity = result.getString("sourceCity");
                    String destinationCity = result.getString("destinationCity");
                    String flightDate = result.getString("flightDate");
                    String flightHour = result.getString("flightHour");
                    flightHour=flightHour.substring(0, 5);
                    String flightTime = result.getString("flightTime");
                    String companyName = result.getString("companyName");
                    String planeId = result.getString("planeId");
                    Double ticketPayment = result.getDouble("ticketPayment");
                    pastFlights.add(new Flight(flightId, flightDate, flightHour, flightTime, ticketPayment, companyName ,planeId, sourceCity,destinationCity));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return pastFlights;
        }

        public List<Flight> plane() {
            List<Flight> plane = new ArrayList<> ();
            try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(PLANE_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String planeId = result.getString("planeId");
                    plane.add(new Flight(planeId));
                }
            } catch (SQLException e) {
            printSQLException(e);
             }
            return plane;
        }


    public List<AboutCompany> company() {
            List<AboutCompany> company= new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(COMPANY_SELECT_ALL);) {
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

        public List<Airline> airlineList() {
            List<Airline> airlineList= new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(AIRLINE_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String airlineId = result.getString("airlineId");
                    String airlineName = result.getString("airlineName");
                    airlineList.add(new Airline(airlineId, airlineName));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return airlineList;
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
