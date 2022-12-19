package javasrc.forsql;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javasrc.model.AboutUser;

public class AboutUserSql {

        private final String jdbcURL = "jdbc:mysql://localhost:";
        private final String jdbcUsername = "";
        private final String jdbcPassword = "";


        private static final String USER_INSERT = "INSERT INTO aboutUser" +
                "  (userName, userLastName, userMail, userPassword) VALUES " +
                " (?, ?, ?, ?);";
        private static final String USER_SELECT_ID = "select * from aboutUser where userId=?;";
        private static final String USER_DELETE = "delete from aboutUser where userId = ?;";
        private static final String USER_SELECT_EMAIL = "select * from aboutUser where userMail = ?;";
        private static final String USER_SELECT_ALL = "select * from aboutUser;";
        private static final String USER_SELECT_EMAIL_PASSWORD = "select * from aboutUser where userMail = ? and userPassword = ?;";
        private static final String USER_INSERT_ADMIN ="INSERT INTO aboutUser (userName, userLastName, userMail, userPassword) VALUES (?,?,?,?);";
        private static final String USER_UPDATE = "update aboutUser set userName = ?, userLastName = ?, userMail = ?, userPassword = ? where userId = ?;";
        private static final String PROFIL_UPDATE = "update aboutUser set userName= ?, userLastName = ?, userMail = ? where userId= ?;";
        private static final String ADMIN_SELECT_EMAIL_PASSWORD = "select * from aboutUser where userMail = ? and userPassword = ?;";
        private static final String PASSWORD_CONTROL_SELECT = "select * from aboutUser where userId=? and userPassword=?;";
        private static final String PASSWORD_UPDATE = "update aboutUser set userPassword = ? where userId = ?;";
        private static final String ACCOUNT_DELETE = "delete from aboutUser where userId = ?;";


        public AboutUserSql() {

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

        public List<AboutUser> userList() {
            List<AboutUser> users = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int userId = result.getInt("userId");
                    String userName= result.getString("userName");
                    String userLastName = result.getString("userLastName");
                    String userMail = result.getString("userMail");
                    users.add(new AboutUser(userId, userName, userLastName, userMail));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return users;
        }

        public boolean controlPassword(int id, String userPassword) {

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(PASSWORD_CONTROL_SELECT);) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, userPassword);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    return true;
                }

            } catch (SQLException e) {
                printSQLException(e);
            }

            return false;
        }

        public boolean updatePassword(AboutUser user) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(PASSWORD_UPDATE);) {
                statement.setString(1, user.getUserPassword());
                statement.setInt(2, user.getUserId());
                updatingRow = statement.executeUpdate() > 0;
             }
            return updatingRow;
        }

        public boolean deleteUser(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(USER_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
        }

        public boolean deleteUserAccount(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(ACCOUNT_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
        }

        public AboutUser chooseUser(int id) {
            AboutUser user = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_ID);) {
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String userName = result.getString("userName");
                    String userLastName = result.getString("userLastName");
                    String userMail = result.getString("userMail");
                    String userPassword = result.getString("userPassword");
                    user = new AboutUser(id, userName, userLastName , userMail, userPassword);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return user;
        }

        public AboutUser takePassword(String userMail) {
            AboutUser user = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_EMAIL);) {
                preparedStatement.setString(1, userMail);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    String userPassword = result.getString("userPassword");
                    user = new AboutUser(userMail, userPassword);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return user;
        }

        public boolean updateAdmin(AboutUser user) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(USER_UPDATE);) {
                statement.setString(1, user.getUserFirstName());
                statement.setString(2, user.getUserLastName());
                statement.setString(3, user.getUserMail());
                statement.setString(4, user.getUserPassword());
                statement.setInt(5, user.getUserId());
                updatingRow = statement.executeUpdate() > 0;
            }
            return updatingRow;
        }

        public boolean updateProfile(AboutUser user) throws SQLException {
            boolean updatingRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(PROFIL_UPDATE);) {
                statement.setString(1, user.getUserFirstName());
                statement.setString(2, user.getUserLastName());
                statement.setString(3, user.getUserMail());
                statement.setInt(4, user.getUserId());
                updatingRow = statement.executeUpdate() > 0;
            }
            return updatingRow;
        }

        public void beUser(AboutUser user) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(USER_INSERT)) {
                preparedStatement.setString(1, user.getUserFirstName());
                preparedStatement.setString(2, user.getUserLastName());
                preparedStatement.setString(3, user.getUserMail());
                preparedStatement.setString(4, user.getUserPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public void addAdmin(AboutUser user) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(USER_INSERT_ADMIN)) {
                preparedStatement.setString(1, user.getUserFirstName());
                preparedStatement.setString(2, user.getUserLastName());
                preparedStatement.setString(3, user.getUserMail());
                preparedStatement.setString(4, user.getUserPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public boolean controlUser(String userMail) {

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_EMAIL);) {
                preparedStatement.setString(1, userMail);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    return false;
                }

            } catch (SQLException e) {
                printSQLException(e);
            }
            return true;
        }

        public boolean controlUserLogin(String userMail, String userPassword) {

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_EMAIL_PASSWORD);) {
                preparedStatement.setString(1, userMail);
                preparedStatement.setString(2, userPassword);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    return true;
                }

            } catch (SQLException e) {
                printSQLException(e);
            }

            return false;
        }

        public boolean controlAdminLogin(String adminMail, String adminPassword) {

            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_SELECT_EMAIL_PASSWORD);) {
                preparedStatement.setString(1, adminMail);
                preparedStatement.setString(2, adminPassword);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    return true;
                }

            } catch (SQLException e) {
                printSQLException(e);
            }

            return false;
        }

        public AboutUser userLogin(String userMail, String userPassword) {
            AboutUser user = null;
            try (Connection connection = getConnection();

                 PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_EMAIL_PASSWORD);) {
                preparedStatement.setString(1, userMail);
                preparedStatement.setString(2, userPassword);
                ResultSet result = preparedStatement.executeQuery();

                while (result.next()) {
                    int userId = result.getInt("userId");
                    String userName= result.getString("userName");
                    String userLastName = result.getString("userLastName");
                    user = new AboutUser(userId, userName, userLastName, userMail);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return user;
        }

        public AboutUser adminLogin(String adminMail, String adminPassword) {
            AboutUser user = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_SELECT_EMAIL_PASSWORD);) {
                preparedStatement.setString(1, adminMail);
                preparedStatement.setString(2, adminPassword);
                ResultSet result = preparedStatement.executeQuery();

                while (result.next()) {
                    int userId = result.getInt("userId");
                    String userName = result.getString("userName");
                    String userLastName = result.getString("userLastName");
                    user = new AboutUser(userId, userName, userLastName, adminMail);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return user;
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
