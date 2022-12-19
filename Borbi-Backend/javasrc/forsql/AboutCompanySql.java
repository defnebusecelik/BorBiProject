package javasrc.forsql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javasrc.model.AboutCompany;

public class AboutCompanySql {

        private final String jdbcURL = "jdbc:mysql://localhost:";
        private final String jdbcUsername = "";
        private final String jdbcPassword = "";

        private static final String FIRMA_INSERT = "INSERT INTO firma (firma_ad, firma_logo) VALUES (?, ?);";
        private static final String FIRMA_SELECT_ALL = "select * from firma;";
        private static final String FIRMA_DELETE = "delete from firma where firma_id = ?;";
        private static final String FIRMA_SELECT_ID = "select * from firma where firma_id=?;";
        private static final String FIRMA_UPDATE = "update firma set firma_ad = ?, firma_logo=? where firma_id = ?;";


        public AboutCompanySql() {
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

        public List<AboutCompany> companyList() {
            List<AboutCompany> companies = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();

                while (result.next()) {
                    int companyId = result.getInt("companyId");
                    String companyName = result.getString("companyName");
                    companies.add(new AboutCompany(companyId, companyName));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return companies;
        }

        public void addCompany(AboutCompany company) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_INSERT)) {
                preparedStatement.setString(1, company.getCompanyName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public boolean deleteCompany(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(FIRMA_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
        }

        public AboutCompany chooseCompany(int id) {
            AboutCompany company = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_SELECT_ID);) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String companyName = result.getString("companyName");
                    company = new AboutCompany(id, companyName);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return company;
        }

        public boolean updateCompany(AboutCompany company) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(FIRMA_UPDATE);) {
                statement.setString(1, company.getCompanyName());
                statement.setInt(2, company.getCompanyId());
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
