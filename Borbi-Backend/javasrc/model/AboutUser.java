package javasrc.model;
public class AboutUser {
        protected int userId;
        protected String userFirstName;
        protected String userLastName;
        protected String userMail;
        protected String userPassword;

        public AboutUser() {
        }

        public AboutUser(int userId, String userPassword) {
            this.userId = userId;
            this.userPassword = userPassword;
        }

        public AboutUser(String userMail, String userPassword) {
            this.userMail= userMail;
            this.userPassword = userPassword;
        }

        public AboutUser(int userId, String userFirstName, String userLastName, String userMail, String userPassword) {
            this.userId = userId;
            this.userFirstName = userFirstName;
            this.userLastName = userLastName;
            this.userMail = userMail;
            this.userPassword = userPassword;
        }

        public AboutUser(String userFirstName, String userLastName, String userMail) {
            this.userFirstName= userFirstName;
            this.userLastName = userLastName;
            this.userMail = userMail;
        }

        public AboutUser(String userFirstName, String userLastName, String userMail, String userPassword) {
            this.userFirstName = userFirstName;
            this.userLastName = userLastName;
            this.userMail = userMail;
            this.userPassword = userPassword;
        }

        public AboutUser(int userId, String userFirstName, String userLastName, String userMail) {
            this.userId = userId;
            this.userFirstName = userFirstName;
            this.userLastName = userLastName;
            this.userMail = userMail;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserFirstName() {
            return userFirstName;
        }

        public void setUserFirstName(String userFirstName) {
            this.userFirstName= userFirstName;
        }

        public String getUserLastName() {
            return userLastName;
        }

        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        public String getUserMail() {
            return userMail;
        }

        public void setUserMail(String userMail) {
            this.userMail = userMail;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

    }
