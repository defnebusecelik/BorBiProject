public class AboutFlight {

        private int flightNumber;
        private String company;
        private String airline;
        private String weekdays;
        private String source;
        private String destination;

        public int getFlightNumber() {
            return flightNumber;
        }

        public void setFlightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getAirline() {
            return airline;
         }

        public void setAirline(String airline) {
            this.airline = airline;
        }

         public String getWeekdays() {
            return weekdays;
        }

        public void setWeekdays(String weekdays) {
            this.weekdays = weekdays;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }


        @Override
        public String toString() {
            return "Flight: \nFlight Number=\t" + flightNumber + "\nCompany=\t" + company + "\nAirline=\t"+ airline + "\nDay=\t" + weekdays + "\nSource=\t" + source + "\nDestination=\t" + destination;
        }
    }
