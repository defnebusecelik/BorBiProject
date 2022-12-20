package javasrc.forsql;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javasrc.model.Message;

public class MessageSql {

        private final String jdbcURL = "jdbc:mysql://localhost:";
        private final String jdbcUsername = "";
        private final String jdbcPassword = "";

        private static final String MESSAGE_SELECT_ALL = "select * from message;";
        private static final String MESSAGE_DELETE = "delete from message where messageId = ?;";
        private static final String MESSAGE_INSERT = "INSERT INTO message  (messageName, messageMail, messageAbout, messageContent) VALUES " +
                " (?,?,?,?);";


        public MessageSql() {
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

        public void addMessage(Message message) throws SQLException {
            try (
                    Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(MESSAGE_INSERT)) {
                preparedStatement.setString(1, message.getMessageName());
                preparedStatement.setString(2, message.getMessageMail());
                preparedStatement.setString(3, message.getMessageAbout());
                preparedStatement.setString(4, message.getMessageContent());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }

        public List<Message> messageList() {
            List<Message> messages = new ArrayList<> ();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(MESSAGE_SELECT_ALL);) {
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int messageId = result.getInt("messageId");
                    String messageName = result.getString("messageName");
                    String messageMail = result.getString("messageMail");
                    String messageAbout = result.getString("messageAbout");
                    String messageContent = result.getString("messageContent");
                    String messageDate = result.getString("messageDate");
                    messages.add(new Message(messageId,messageName,messageMail,messageAbout,messageContent,messageDate));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return messages;
        }

        public boolean deleteMessage(int id) throws SQLException {
            boolean cleaningRow;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(MESSAGE_DELETE);) {
                statement.setInt(1, id);
                cleaningRow = statement.executeUpdate() > 0;
            }
            return cleaningRow;
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
