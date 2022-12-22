package javasrc.forsql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javasrc.model.Reservation;


public class ReservationSql {
        private final String jdbcURL = "jdbc:mysql://localhost:";
        private final String jdbcUsername = "";
        private final String jdbcPassword = "";

        private static final String ONEWAY_FLIGHT_SELECT1="select distinct flightId,(aboutFlight.seatNumber-(SELECT COUNT(flightId) FROM reservation WHERE flightId=flight.flightId )) as caseSeat ,a.airlineCityName as sourceAirlineName, b.airlineCityName as destinationAirlineName," +
                " s.airlineName as sourceAirlineName,s.airlineId as sourceCityId, p.airlineName as destinationCityName, p.airlineId as destinationCityId, flightDate, flightHour, flightTime, company.companyName ,company.companyId , aboutFlight.planeId, ticketPayment from flight JOIN airline JOIN airlineCityName\n" +
                "INNER JOIN  aboutFlight ON (aboutFlight.planeId = flight.planeId)\n" +
                "INNER JOIN  company ON (company.companyId = flight.companyId)\n" +
                "INNER JOIN  airline s ON (s.airlineId = reservation.sourceAirlineId)\n" +
                "INNER JOIN  airline p ON (p.airlineId = reservation.destinationAirlineId)\n" +
                "INNER JOIN  city a ON (a.airlineCityId = s.airlineId)\n" +
                "INNER JOIN  city b ON (b.airlineCityId = p.airlineId)\n" +
                "WHERE s.airlineId = ? AND p.airlineId =? AND flightDate=? AND (aboutFlight.seat-(SELECT COUNT(flightId) FROM reservation WHERE flightId=flight.flightId )) >= ?;";

        private static final String ONEWAY_FLIGHT_SELECT2="select distinct flightId,(aboutFlight.seat-(SELECT COUNT(flightId) FROM reservation WHERE flightId=flight.flightId )) as caseSeat, a.airlineCityName as sourceAirlineName , b.airlineCityName as destinationAirlineName ," +
                "s.airlineName as sourceAirlineName,s.airlineId as sourceCityId, p.airlineName as destinationCityName, p.airlineId as destinationCityId, flightDate, flightHour, flightTime, company.companyName, company.companyId , aboutFlight.planeId, ticketPayment from flight JOIN airline JOIN airlineCityName\n" +
                "INNER JOIN  aboutFlight ON (aboutFlight.planeId = flight.planeId)\n" +
                "INNER JOIN  company ON (company.companyId = flight.flightId)\n" +
                "INNER JOIN  airline s ON (s.airlineId = reservation.sourceAirlineId)\n" +
                "INNER JOIN  airline p ON (p.airlineId = reservation.destinationAirlineId)\n" +
                "INNER JOIN  city a ON (a.airlineCityId = s.airlineId)\n" +
                "INNER JOIN  city b ON (b.airlineCityId = p.airlineId)\n" +
                "WHERE s.airlineId = ? AND p.airlineId =? AND flightDate=? AND flightHour > ? AND (aboutFlight.seat-(SELECT COUNT(flightId) FROM reservation WHERE flightId=flight.flightId )) >= ?;";
        private static final String RESERVATION_SELECT_COUNT="SELECT COUNT(*) as result FROM reservation WHERE reservationDate BETWEEN ? AND ?;";
        private static final String FLIGHT_SELECT_COUNT="SELECT count(*) as result FROM flight WHERE flightDate >= ? ;";
        private static final String RESERVATION_DELETE = "delete from reservation where reservationId = ?;";
        private static final String RESERVATION_SELECT_PNRNO="SELECT * FROM reservation where passengerNameRecord=? and customerLastName=?;";
        private static final String RESERVATION_SELECT_FLIGHT_ID="select DISTINCT k.planeId, u.flightHour, u.flightDate, u.flightHour, customerName, customerLastName, customerMail, verisonNumber, a.airlineCityName AS sourceAirlineName, " +
                "s.airlineName as sourceAirlineName, s.airlineId as sourceCityId, b.airlineCityName as sourceAirlineName, p.airlineName as destinationAirlineName, p.airlineId as destinationCityId, f.companyName, f.companyId from reservation JOIN airline JOIN airlineCityName JOIN flight JOIN company JOIN aboutFlight\n" +
                "INNER JOIN  flight u ON (reservation.flightId = flight.flightId)\n" +
                "INNER JOIN  company f ON (f.companyId = u.companyId)\n" +
                "INNER JOIN  aboutFlight k ON (k.planeId = u.planeId)\n" +
                "INNER JOIN  airline s ON (u.sourceAirlineId = s.airlineId)\n" +
                "INNER JOIN airline p ON (u.destinationAirlineId = p.airlineId)\n" +
                "INNER JOIN  city a ON (s.airlineId = a.airlineCityId )\n" +
                "INNER JOIN  city b ON (p.airlineId = b.airlineCityId)\n" +
                "WHERE u.flightId=? and reservation.reservationId=?;";
        private static final String SELECT_FLIGHT_INFO = "select distinct flightId,(aboutFlight.seat-(SELECT COUNT(flightId) FROM reservation WHERE flightId=flight.flightId )) as caseSeat, a.airlineCityName as sourceAirlineName, b.airlineCityName as destinationAirlineName ," +
                "s.airlineName as sourceAirlineName,s.airlineId as sourceCityId, p.airlineName as destinationAirlineName, p.airlineId as destinationAirlineId, flightDate, flightHour, flightTime, cmpany.companyName, company.companyId , aboutFlight.planeId, aboutFlight.seat, ticketPayment from flight JOIN airline JOIN airlineCity\n" +
                "INNER JOIN  aboutFlight ON (aboutFlight.planeId = flight.planeId)\n" +
                "INNER JOIN  company ON (company.companyId = flight.companyId)\n" +
                "INNER JOIN  airline s ON (s.airlineId = reservation.sourceAirlineId)\n" +
                "INNER JOIN  airline p ON (p.airlineId = flight.destinationAirlineId)\n" +
                "INNER JOIN  city a ON (a.airlineCityId = s.airlineId)\n" +
                "INNER JOIN  city b ON (b.airlineCityId = p.airlineId)\n" +
                "WHERE flightId=?;";
        private static final String SEAT_INFO_SELECT="SELECT seatNumber FROM reservation \n" +
                "WHERE flightId=?\n" +
                "ORDER BY seatNumber ASC;";
        private static final String SEAT_FULL_SELECT="SELECT COUNT(seatNumber) as fullSeat FROM reservation \n" +
                "WHERE flightId=?;";
        private static final String RESERVATION_INSERT ="INSERT INTO reservation (flightId, customerId, passengerNameRecord, customerName, customerLastName, customerMail, customerPhone,versionNumber, flightDate, ticketPayment, seatNumber) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        private static final String SEAT_NO_SELECT="SELECT * FROM reservation WHERE flightId=? and seatNumber=?;";
        private static final String RESERVATION_PROCESS_SELECT="SELECT r.reservationId, r.reservationDate, r.passengerNameRecord, r.customerName, r.customerLastName, r.customerMail, r.customerPhone,r.versionNumber, r.reservationDate, r.ticketPayment, r.seatNumber, u.flightDate, " +
                "u.flightHour, u.flightTime, k.airlineName as sourceCityName, k.airlineId as sourceAirlineId, v.airlineName as destinationCityName, v.airlineId as destinationAirlineId, s1.airlineCityName as sourceCityName, s2.airlineCityName as destinationCityName, f.companyName,p.planeId from reservation AS r\n" +
                "JOIN flight AS u ON u.flightId = r.flightId\n" +
                "JOIN airline AS k ON k.airlineId=u.sourceAirlineId \n" +
                "JOIN airline AS v ON v.airlineId=u.destinationAirlineId\n" +
                "JOIN city AS s1 ON s1.airlineCityId=k.sourceAirlineId\n" +
                "JOIN city AS s2 ON s2.airlineCityId=v.destinationAirlineId\n" +
                "JOIN company AS f ON f.companyId=u.companyId\n" +
                "JOIN flight AS p ON p.planeId=u.planeId\n" +
                "WHERE r.customerId=?\n"+
                "ORDER BY r.reservationDate DESC;";
        private static final String CANCEL_1="update reservation r\n" +
                "join flight u on r.flightId = u.flightId\n" +
                "WHERE (r.customerId=? and u.flightDate > ?) OR (u.flightDate = ? and u.flightTime > ?);";
        private static final String CANCEL_0="update reservation r\n" +
                "join flight u on r.flightId = u.flightId\n" +
                "WHERE (r.customerId=? and u.flightDate < ?) OR (u.flightDate = ? and u.flightHour < ?);";
        private static final String RESERVATION_UPDATE = "update reservation set customerName = ?, customerLastName=?,reservationDate=?, customerMail=?, customerPhone=? where reservationId= ?;";

        private static final String RESERVATION_SEARCH="SELECT r.reservationId, r.reservationDate, r.passengerNameRecord, r.customerName, r.customerLastName, r.customerMail, r.customerPhone, r.versionNumber, r.ticketPayment, r.seatNumber, u.flightDate, u.flightHour, u.flightTime," +
                " k.airlineName as sourceCityName, k.airlineId as sourceAirlineId, v.airlineName as destinationCityName, v.airlineId as destinationCityId, s1.airlineCityName as sourceCityName, s2.airlineCityName as destinationCityName, f.companyName, p.planeId from reservation AS r\n" +
                "JOIN flight AS u ON u.flightId = r.flightId\n" +
                "JOIN airline AS k ON k.airlineId=u.sourceAirlineId\n" +
                "JOIN airline AS v ON v.airlineId=u.destinationAirlineId\n" +
                "JOIN city AS s1 ON s1.airlineCityId=k.sourceAirlineId\n" +
                "JOIN city AS s2 ON s2.airlineCityId=v.destinationAirlineId\n" +
                "JOIN company AS f ON f.companyId=u.companyId\n" +
                "JOIN aboutFlight AS p ON p.planeId=u.planeId\n" +
                "ORDER BY r.reservationDate DESC;";


        public ReservationSql() {
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

        public boolean cancelReservation1(int id) throws SQLException {
            boolean updatingRow;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String date = now.format(formatter);
            String hour = now.format(timeformatter);

            String[] ARRAYhour = hour.split(":");
            String hournumber = ARRAYhour[0];
            String minutenumber = ARRAYhour[1];
            int hh = Integer.parseInt(hournumber);
            int mm = Integer.parseInt(minutenumber);
            String Sminute;
            if (mm < 10) {
                Sminute = "0" + String.valueOf(mm);
            } else {
                Sminute = String.valueOf(mm);
            }
            String Shour;
            if (hh < 10) {
                Shour = "0" + String.valueOf(hh + 2);
            } else {
                Shour = String.valueOf(hh + 2);
            }
            String flightHour = Shour + ":" + Sminute;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(CANCEL_1);) {
                statement.setInt(1, id);
                statement.setString(2, date);
                statement.setString(3, hour);
                statement.setString(4, flightHour);
                updatingRow = statement.executeUpdate() > 0;
            }
            return updatingRow;
        }

        public boolean cancelReservation2(int id) throws SQLException {
            boolean updatingRow;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String date = now.format(formatter);
            String hour = now.format(timeformatter);

            String[] ARRAYhour = hour.split(":");
            String hournumber = ARRAYhour[0];
            String minutenumber = ARRAYhour[1];
            int hh = Integer.parseInt(hournumber);
            int mm = Integer.parseInt(minutenumber);
            String Sminute;
            if (mm < 10) {
                Sminute = "0" + String.valueOf(mm);
            } else {
                Sminute = String.valueOf(mm);
            }
            String Shour;
            if (hh < 10) {
                Shour = "0" + String.valueOf(hh + 2);
            } else {
                Shour = String.valueOf(hh + 2);
            }
            String flightHour = Shour + ":" + Sminute;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(CANCEL_0);) {
                statement.setInt(1, id);
                statement.setString(2, date);
                statement.setString(3, hour);
                statement.setString(4, flightHour);
                updatingRow = statement.executeUpdate() > 0;
            }
            return updatingRow;
        }

        public boolean controlSeat(int id, String seatNumber) {

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SEAT_NO_SELECT);) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, seatNumber);
                ResultSet result = preparedStatement.executeQuery();

                if (result.next()) {
                    return true;
                }

            } catch (SQLException e) {
                printSQLException(e);
            }

            return false;
        }

        public void addReservation(Reservation reservation) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(RESERVATION_INSERT)) {
                preparedStatement.setInt(1, reservation.getFlightId());
                preparedStatement.setInt(2, reservation.getCustomerId());
                preparedStatement.setString(3, reservation.getPassengerNameRecord());
                preparedStatement.setString(4, reservation.getCustomerName());
                preparedStatement.setString(5, reservation.getCustomerLastName());
                preparedStatement.setString(6, reservation.getCustomerMail());
                preparedStatement.setString(7, reservation.getCustomerPhone());
                preparedStatement.setInt(9, reservation.getVersionNumber());
                preparedStatement.setString(10, reservation.getFlightDate());
                preparedStatement.setDouble(11, reservation.getTicketPayment());
                preparedStatement.setString(12, reservation.getSeatNumber());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public List<Reservation> reservationProcess(int id) {
            List<Reservation> reservations = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(RESERVATION_PROCESS_SELECT);) {
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    String passengerNameRecord = result.getString("passengerNameRecord");
                    String customerName = result.getString("customerName");
                    String customerLastName = result.getString("customerLastName");
                    String customerMail = result.getString("customerMail");
                    String customerPhone = result.getString("customerPhone");
                    int versionNumber = result.getInt("versionNumber");
                    String reservationDate = result.getString("reservationDate");
                    Double ticketPayment=result.getDouble("ticketPayment");
                    String seatNumber = result.getString("seatNumber");
                    String flightHour=result.getString("flightHour");
                    flightHour=flightHour.substring(0, 5);
                    String flightDate=result.getString("flightDate");
                    String flightTime=result.getString("flightTime");
                    String[] ARRAYflightTime = flightTime.split(":");
                    String hournumber = ARRAYflightTime[0];
                    String minutenumber = ARRAYflightTime[1];
                    String[] ARRAYflightHour = flightHour.split(":");
                    String h = ARRAYflightHour[0];
                    String d = ARRAYflightHour[1];
                    int hour=(Integer.parseInt(h)+Integer.parseInt(hournumber))%24 ;
                    int minute=(Integer.parseInt(d)+Integer.parseInt(minutenumber))%60 ;
                    String Sminute;
                    if(minute < 10){
                        Sminute="0"+String.valueOf(minutenumber);
                    }else{
                        Sminute=String.valueOf(minutenumber);
                    }
                    String Shour;
                    if(hour < 10){
                        Shour="0"+String.valueOf(hournumber);
                    }else{
                        Shour=String.valueOf(hournumber);
                    }
                    String flightEndHour = Shour+":"+Sminute;
                    int reservationId = result.getInt("reservationId");
                    String sourceCityId=result.getString("sourceCityId");
                    String sourceCity=result.getString("sourceCity");
                    String sourceAirlineId=result.getString("sourceAirlineId");
                    String destinationCityId=result.getString("destinationCityId");
                    String destinationCity=result.getString("destinationCity");
                    String destinationAirlineId=result.getString("destinationAirlineId");
                    String companyName=result.getString("companyName");
                    String planeId=result.getString("planeId");
                    reservations.add(new Reservation(reservationId,reservationDate, customerName, customerLastName, customerMail, customerPhone, versionNumber, seatNumber, flightDate, sourceCityId, sourceCity, sourceAirlineId, destinationCityId, destinationCity, destinationAirlineId, flightHour, flightTime, companyName, hournumber, minutenumber, flightEndHour, planeId, flightDate, ticketPayment));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return reservations;
        }

        public boolean updateReservation(Reservation reservation) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(RESERVATION_UPDATE);) {
                preparedStatement.setString(1, reservation.getCustomerName());
                preparedStatement.setString(2, reservation.getCustomerLastName());
                preparedStatement.setString(4, reservation.getReservationDate());
                preparedStatement.setString(5, reservation.getCustomerMail());
                preparedStatement.setString(6, reservation.getCustomerPhone());
                preparedStatement.setInt(7, reservation.getReservationId());
                updatingRow = preparedStatement.executeUpdate() > 0;
            }
            return updatingRow;
        }

        public List<Reservation> oneWayReservation(Reservation reservation) {
            List<Reservation> reservations = new ArrayList<>();
            DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String sss = now.format(timeformatter);

            String[] ARRAYhour = sss.split(":");
            String hournumber = ARRAYhour[0];
            String minutenumber= ARRAYhour[1];
            int hh = Integer.parseInt(hournumber);
            int mm = Integer.parseInt(minutenumber);
            String Ssminute;
            if (mm < 10) {
                Ssminute = "0" + String.valueOf(mm);
            } else {
                Ssminute = String.valueOf(mm);
            }
            String Sshour;
            if (hh < 10) {
                Sshour = "0" + String.valueOf(hh + 1);
            } else {
                Sshour = String.valueOf(hh + 1);
            }
            String u_hour = Sshour + ":" + Ssminute;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.now();
            String date1 = date.format(formatter);
            if (equals(reservation.getFlightDate(), date1)) {
                try (Connection connection = getConnection();
                     PreparedStatement statement = connection.prepareStatement(ONEWAY_FLIGHT_SELECT1);) {
                    statement.setInt(1, reservation.getSourceAirlineId());
                    statement.setInt(2, reservation.getDestinationAirlineId());
                    statement.setString(3, reservation.getFlightDate());
                    statement.setInt(4, (reservation.getChildNumber() + reservation.getAdultNumber()));
                    ResultSet result = statement.executeQuery();

                    while (result.next()) {
                        int flightId = result.getInt("flightId");
                        String sourceCityId = result.getString("sourceCityId");
                        String sourceCity = result.getString("sourceCity");
                        String sourceAirlineId = result.getString("sourceAirlineId");
                        String destinationCityId = result.getString("destinationCityId");
                        String destinationCity = result.getString("destinationCity");
                        String destinationAirlineId = result.getString("destinationAirlineId");
                        String flightHour = result.getString("flightHour");
                        flightHour = flightHour.substring(0, 5);
                        String flightDate = result.getString("flightDate");
                        String flightTime = result.getString("flightTime");

                        String[] ARRAYflightTime = flightTime.split(":");
                        String hourNumber = ARRAYflightTime[0];
                        String minuteNumber = ARRAYflightTime[1];
                        String[] ARRAYflightHour = flightHour.split(":");
                        String h = ARRAYflightHour[0];
                        String m = ARRAYflightHour[1];
                        int hour = (Integer.parseInt(h) + Integer.parseInt(hourNumber)) % 24;
                        int minute = (Integer.parseInt(m) + Integer.parseInt(minuteNumber)) % 60;
                        String Sminute;
                        if (minute < 10) {
                            Sminute = "0" + String.valueOf(minute);
                        } else {
                            Sminute = String.valueOf(minute);
                        }
                        String Shour;
                        if (hour < 10) {
                            Shour = "0" + String.valueOf(hour);
                        } else {
                            Shour = String.valueOf(hour);
                        }
                        String flightEndHour = Shour + ":" + Sminute;
                        String companyName = result.getString("companyName");
                        String companyId = result.getString("companyId");
                        Double ticketPayment = result.getDouble("ticketPayment");
                        reservations.add(new Reservation(flightDate, flightId, sourceCityId, sourceCity, sourceAirlineId, destinationCityId, destinationCity, destinationAirlineId, flightHour, flightTime,
                                companyName, companyId, ticketPayment, hournumber, minuteNumber, flightEndHour));
                    }
                } catch (SQLException e) {
                    printSQLException(e);
                }
                return reservations;
            } else {
                try (Connection connection = getConnection();
                     PreparedStatement statement = connection.prepareStatement(ONEWAY_FLIGHT_SELECT2);) {
                    statement.setInt(1, reservation.getSourceAirlineId());
                    statement.setInt(2, reservation.getDestinationAirlineId());
                    statement.setString(3, reservation.getFlightDate());
                    statement.setString(4, u_hour);
                    statement.setInt(5, (reservation.getChildNumber() + reservation.getAdultNumber()));
                    ResultSet result = statement.executeQuery();

                    while (result.next()) {
                        int flightId = result.getInt("flightId");
                        String sourceCityId = result.getString("sourceCityId");
                        String sourceCity = result.getString("sourceCity");
                        String sourceAirlineId = result.getString("sourceAirlineId");
                        String destinationCityId = result.getString("destinationCityId");
                        String destinationCity = result.getString("destinationCity");
                        String destinationAirlineId = result.getString("destinationAirlineId");
                        String flightHour = result.getString("flightHour");
                        flightHour = flightHour.substring(0, 5);
                        String flightDate = result.getString("flightDate");
                        String flightTime = result.getString("flightTime");

                        String[] ARRAYflightTime = flightTime.split(":");
                        String hourrnumber = ARRAYflightTime[0];
                        String minuteenumber = ARRAYflightTime[1];
                        String[] ARRAYflightHour = flightHour.split(":");
                        String h = ARRAYflightHour[0];
                        String m = ARRAYflightHour[1];
                        int hour = (Integer.parseInt(h) + Integer.parseInt(hourrnumber)) % 24;
                        int minute = (Integer.parseInt(m) + Integer.parseInt(minuteenumber)) % 60;
                        String Sminute;
                        if (minute < 10) {
                            Sminute = "0" + String.valueOf(minute);
                        } else {
                            Sminute = String.valueOf(minute);
                        }
                        String Shour;
                        if (hour < 10) {
                            Shour = "0" + String.valueOf(hour);
                        } else {
                            Shour = String.valueOf(hour);
                        }
                        String flightEndHour = Shour + ":" + Sminute;
                        String companyName = result.getString("companyName");
                        String companyId = result.getString("companyId");
                        Double ticketPayment = result.getDouble("ticketPayment");
                        reservations.add(new Reservation(flightDate, flightId, sourceCityId, sourceCity, sourceAirlineId, destinationCityId, destinationCity, destinationAirlineId,
                                flightHour, flightTime, companyName, companyId, ticketPayment, hourrnumber, minuteeNumber, flightEndHour));
                    }
                } catch (SQLException e) {
                    printSQLException(e);
                }
                return reservations;
            }
        }

        public Reservation infoFlight(int id) {
            Reservation reservation =null;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_FLIGHT_INFO);) {
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    int flightId = result.getInt("flightId");
                    String sourceCityId=result.getString("sourceCityId");
                    String sourceCity=result.getString("sourceCity");
                    String sourceAirlineId=result.getString("sourceAirlineId");
                    String destinationCityId=result.getString("destinationCityId");
                    String destinationCity=result.getString("destinationCity");
                    String destinationAirlineId=result.getString("destinationAirlineId");
                    String flightHour=result.getString("flightHour");
                    flightHour=flightHour.substring(0, 5);
                    String flightDate=result.getString("flightDate");
                    String flightTime=result.getString("flightTime");

                    String[] ARRAYflightTime = flightTime.split(":");
                    String hournumber = ARRAYflightTime[0];
                    String minuteNumber = ARRAYflightTime[1];
                    String[] ARRAYflightHour = flightHour.split(":");
                    String h = ARRAYflightHour[0];
                    String m = ARRAYflightHour[1];
                    int hour=(Integer.parseInt(h)+Integer.parseInt(hournumber))%24 ;
                    int minute=(Integer.parseInt(m)+Integer.parseInt(minuteNumber))%60 ;
                    String Sminute;
                    if(minute < 10){
                        Sminute="0"+String.valueOf(minute);
                    }else{
                        Sminute=String.valueOf(minute);
                    }
                    String Shour;
                    if(hour < 10){
                        Shour="0"+String.valueOf(hour);
                    }else{
                        Shour=String.valueOf(hour);
                    }
                    String flightEndHour = Shour+":"+Sminute;
                    String companyName=result.getString("companyName");
                    String companyId=result.getString("companyId");
                    Double ticketPayment=result.getDouble("ticketPaymetn");
                    String planeId=result.getString("planeId");
                    int seatNumber = result.getInt("seatNumber");
                    reservation = new Reservation(flightDate, flightId, sourceCityId, sourceCity,sourceAirlineId,destinationCityId,destinationCity,
                            destinationAirlineId, flightHour, flightTime, companyName, companyId, ticketPayment, hournumber, minuteNumber, flightEndHour,planeId,seatNumber);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return reservation;
        }

        public Reservation chooseReservation(String passengerNameRecord, String customerLastName) {
            Reservation reservation = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(RESERVATION_SELECT_PNRNO);) {
                preparedStatement.setString(1, passengerNameRecord);
                preparedStatement.setString(2, customerLastName);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int reservationId = result.getInt("reservationId");
                    String reservationDate = result.getString("reservationDate");
                    int flightId = result.getInt("flightId");
                    int customerId = result.getInt("customerId");
                    String customerName = result.getString("customerName");
                    String customerMail = result.getString("customerMail");
                    String customerPhone = result.getString("customerPhone");
                    int versionNumber = result.getInt("versionNumber");
                    String seatNumber = result.getString("seatNumber");
                    reservation = new Reservation(reservationId, reservationDate,passengerNameRecord, customerName, customerLastName,
                            customerMail,customerPhone,versionNumber,seatNumber,customerId,flightId );
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return reservation;
        }

        public Reservation informationReservation(int flightId, int reservationId) {
            Reservation reservation = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(RESERVATION_SELECT_FLIGHT_ID);) {
                preparedStatement.setInt(1, flightId);
                preparedStatement.setInt(2, reservationId);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String flightHour = result.getString("flightHour");
                    flightHour=flightHour.substring(0, 5);
                    String flightTime = result.getString("flightTime");
                    String[] ARRAYflightTime = flightTime.split(":");
                    String hournumber = ARRAYflightTime[0];
                    String minutenumber = ARRAYflightTime[1];
                    String flightDate = result.getString("flightDate");
                    String planeId = result.getString("planeId");
                    String companyName = result.getString("companyName");
                    String sourceCityId = result.getString("sourceCityId");
                    String sourceCity = result.getString("sourceCity");
                    String sourceAirlineId = result.getString("sourceAirlineId");
                    String destinationCityId = result.getString("destinationCity");
                    String destinationAirlineId = result.getString("destinationAirlineId");
                    String destinationCity = result.getString("destinationCity");

                    reservation = new Reservation(flightDate, sourceCityId, sourceCity, sourceAirlineId, destinationCityId,
                            destinationCity, destinationAirlineId, flightHour, companyName, hournumber, minutenumber,planeId);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return reservation;
        }

        public boolean cancelReservation(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(RESERVATION_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
        }

        public List<Reservation> reservationNumber() {
            List<Reservation> reservation = new ArrayList<> ();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate months = LocalDate.now().minusMonths(1);
            String date1 = months.format(formatter);
            LocalDateTime now = LocalDateTime.now().plusDays(1);
            String date2 = now.format(formatter);
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(RESERVATION_SELECT_COUNT);) {
                statement.setString(1, date1);
                statement.setString(2, date2);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int total = result.getInt("total");
                    reservation.add(new Reservation(total));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return reservation;
        }

        public List<Reservation> flightNumber() {
            List<Reservation> reservations = new ArrayList<> ();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String date1 = now.format(formatter);
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(FLIGHT_SELECT_COUNT);) {
                statement.setString(1, date1);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int total = result.getInt("total");
                    reservations.add(new Reservation(total));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return reservations;
        }

        public List<Reservation> listReservation() {
            List<Reservation> reservations = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(RESERVATION_SEARCH);) {
                ResultSet result = preparedStatement.executeQuery();

                while (result.next()) {
                    String passengerNameRecord = result.getString("passengerNameRecord");
                    String customerName = result.getString("customerName");
                    String customerLastName = result.getString("customerLastName");
                    String customerMail = result.getString("customerMail");
                    String customerPhone = result.getString("customerPhone");
                    int versionNumber = result.getInt("versionNumber");
                    String reservationDate = result.getString("reservationDate");
                    Double ticketPayment=result.getDouble("ticketPayment");
                    String seatNumber = result.getString("seatNumber");
                    String flightHour=result.getString("flightHour");
                    flightHour=flightHour.substring(0, 5);
                    String flightDate=result.getString("flightDate");
                    String flightTime=result.getString("flightTime");
                    String[] ARRAYflightTime = flightTime.split(":");
                    String hournumber = ARRAYflightTime[0];
                    String minutenumber = ARRAYflightTime[1];
                    String[] ARRAYflightHour = flightHour.split(":");
                    String h = ARRAYflightHour[0];
                    String m = ARRAYflightHour[1];
                    int hour=(Integer.parseInt(h)+Integer.parseInt(hournumber))%24 ;
                    int minute=(Integer.parseInt(m)+Integer.parseInt(minutenumber))%60 ;
                    String Sminute;
                    if(minute < 10){
                        Sminute="0"+String.valueOf(minute);
                    }else{
                        Sminute=String.valueOf(minute);
                    }
                    String Shour;
                    if(hour < 10){
                        Shour="0"+String.valueOf(hour);
                    }else{
                        Shour=String.valueOf(hour);
                    }
                    String flightEndHour = Shour+":"+Sminute;
                    int reservationId = result.getInt("reservationId");
                    String sourceCityId=result.getString("sourceCityId");
                    String sourceCity=result.getString("sourceCity");
                    String sourceAirlineId=result.getString("sourceAirlineId");
                    String destinationCityId=result.getString("destinationCityId");
                    String destinationCity=result.getString("destinationCity");
                    String destinationAirlineId=result.getString("destinationAirlineId");
                    String companyName=result.getString("companyName");
                    String companyId=result.getString("companyId");
                    String planeId=result.getString("planeId");
                    String reservationDate=result.getString("reservationDate");
                    reservations.add(new Reservation(reservationId, reservationDate, passengerNameRecord, customerName, customerLastName,customerMail, customerPhone, versionNumber,seatNumber,
                            flightDate, sourceCityId, sourceCity, sourceAirlineId, destinationCityId, destinationCity, destinationAirlineId, flightHour, flightTime, companyName, companyId,
                            flightEndHour,planeId, ticketPayment));
                }
            } catch (SQLException e) {
                printSQLException(e);
            } return reservations;
        }

        public List<Reservation> informationSeat(int id) {
            List<Reservation> reservations = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SEAT_INFO_SELECT);) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int seatNumber = result.getInt("seatNumber");
                    reservations.add(new Reservation(seatNumber));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return reservations;
        }

        public Reservation fullSeats(int id) {
            Reservation reservations = new Reservation();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SEAT_FULL_SELECT);) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int fullSeat = result.getInt("fullSeat");
                    reservations = new Reservation(fullSeat);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return reservations;
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
