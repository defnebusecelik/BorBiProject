public class AboutCustomer {
        private int customerId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phoneNumber;


        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
        
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return "Customer:\n"+"Customer Id=\t" + customerId + "\nName=\t" + firstName + "\nLast Name=\t" + lastName + "\nE-mail=\t" + email + "\nPassword=\t" + password + "\nPhone Number=\t" + phoneNumber;
        }
    }
